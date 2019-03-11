package com.github.search.tree.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.github.search.tree.routes.json.JsonSupport
import com.github.search.tree.service.CategoryService

import scala.concurrent.ExecutionContext

trait CategoryRoutes extends JsonSupport {

  implicit val executionContext: ExecutionContext

  def categoryService: CategoryService

  val getCategory: Route =
    path("category" / Segment) { name =>
      categoryService.getByName(name) match {
        case Some(category) => complete(category)
        case None           => complete(StatusCodes.NotFound)
      }
    }

  val getCategoryFromParent: Route =
    path("category" / "parent" / Segment / "name" / Segment) { (parent, name) =>
      categoryService.getFromParentByName(parent, name) match {
        case Some(category) => complete(category)
        case None           => complete(StatusCodes.NotFound)
      }
    }

}
