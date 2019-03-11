package com.github.search.tree

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.github.search.tree.routes.{CategoryRoutes, ItemRoutes}
import akka.http.scaladsl.server.Directives._

abstract class Routes extends SprayJsonSupport with CategoryRoutes with ItemRoutes {

  val routes = getCategory ~ getItem ~ getCategoryFromParent ~ getItemFromParent

}
