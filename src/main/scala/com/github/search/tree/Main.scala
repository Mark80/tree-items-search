package com.github.search.tree

import akka.actor.{ActorSystem, Terminated}
import akka.event.LoggingAdapter
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.stream.ActorMaterializer
import com.github.search.tree.model.{Category, Psychographics}
import com.github.search.tree.repository.{CategoryRepository, ItemRepository}
import com.github.search.tree.service.{CategoryService, PsychographicsService}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Main extends Routes {

  protected val AkkaHttpGracefulShutdownTimeout: FiniteDuration = 5 seconds

  implicit val system: ActorSystem = ActorSystem("system")

  val logger: LoggingAdapter = system.log

  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val categoryRepository =
    new CategoryRepository(JsonDataParser.getData[Category]("categories.json"))
  val psychographicsRepository =
    new ItemRepository(JsonDataParser.getData[Psychographics]("psychographics.json"))

  val categoryService = new CategoryService(categoryRepository)
  val psychographicsService = new PsychographicsService(psychographicsRepository)

  def main(args: Array[String]): Unit = {

    val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandle(getCategory, Config.Host, Config.Port)

    serverBinding.onComplete {
      case Success(bound) =>
        logger.info(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
      case Failure(e) =>
        logger.error(s"Server could not start!")
        e.printStackTrace()
        system.terminate()
    }

    logger.info(s"Starting service with the following configuration:")
    addServerShutdownHook(serverBinding)
  }

  private def addServerShutdownHook(serverBinding: Future[ServerBinding]): Unit = {
    sys.addShutdownHook {
      logger.info(s"Request to stop application received, stopping Akka Http server and actor system...")
      val shutdownServerRequest = serverBinding.flatMap(stopServerAndActorSystem)
      shutdownServerRequest.onComplete {
        case Failure(error) =>
          logger.error(s"Application: stopped with an error!", error)
        case Success(_) =>
          logger.info(s"Application: stopped successfully.")
      }
      Await.ready(shutdownServerRequest, AkkaHttpGracefulShutdownTimeout)
      ()
    }
    ()
  }

  private def stopServerAndActorSystem(serverBinding: ServerBinding): Future[Terminated] =
    serverBinding.unbind().flatMap { _ =>
      system.terminate()
    }

}
