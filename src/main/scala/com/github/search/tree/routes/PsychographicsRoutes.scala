package com.github.search.tree.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.github.search.tree.routes.json.JsonSupport
import com.github.search.tree.service.PsychographicsService

import scala.concurrent.ExecutionContext

trait PsychographicsRoutes extends JsonSupport {

  implicit val executionContext: ExecutionContext

  def psychographicsService: PsychographicsService

  val getPsychographic: Route =
    path("psychographic" / Segment) { label =>
      psychographicsService.getByName(label) match {
        case Some(item) => complete(item)
        case None       => complete(StatusCodes.NotFound)
      }
    }

  val getPsychographicFromParent: Route =
    path("psychographic" / "parent" / Segment / "name" / Segment) { (parent, name) =>
      psychographicsService.getFromParentByName(parent, name) match {
        case Some(category) => complete(category)
        case None           => complete(StatusCodes.NotFound)
      }
    }

}
