package com.github.search.tree.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class Category(id: Int, l1: Option[Int], l2: Option[Int], l3: Option[Int], name: String, level: Int, children: Option[List[Category]])

object Category extends SprayJsonSupport with DefaultJsonProtocol {

  implicit lazy val categoryFormat: RootJsonFormat[Category] = rootFormat(lazyFormat(jsonFormat7(Category.apply)))

}
