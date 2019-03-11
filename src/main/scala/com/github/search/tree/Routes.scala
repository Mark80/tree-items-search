package com.github.search.tree

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.github.search.tree.routes.{CategoryRoutes, PsychographicsRoutes}
import akka.http.scaladsl.server.Directives._

abstract class Routes extends SprayJsonSupport with CategoryRoutes with PsychographicsRoutes {

  val routes = getCategory ~ getPsychographic ~ getCategoryFromParent ~ getPsychographicFromParent

}
