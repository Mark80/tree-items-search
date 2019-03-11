package com.github.search.tree.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.github.search.tree.routes.json.JsonSupport
import com.github.search.tree.service.ItemService

import scala.concurrent.ExecutionContext

trait ItemRoutes extends JsonSupport {

  implicit val executionContext: ExecutionContext

  def itemService: ItemService

  val getItem: Route =
    path("item" / Segment) { label =>
      itemService.getByName(label) match {
        case Some(item) => complete(item)
        case None       => complete(StatusCodes.NotFound)
      }
    }

  val getItemFromParent: Route =
    path("item" / "parent" / Segment / "name" / Segment) { (parent, name) =>
      itemService.getFromParentByName(parent, name) match {
        case Some(category) => complete(category)
        case None           => complete(StatusCodes.NotFound)
      }
    }

}
