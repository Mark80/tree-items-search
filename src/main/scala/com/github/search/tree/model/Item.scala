package com.github.search.tree.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class Item(id: Int, value: String, label: String, pic: Option[String], values: Option[List[Item]])

object Item extends SprayJsonSupport with DefaultJsonProtocol {

  implicit lazy val itemFormat: RootJsonFormat[Item] = rootFormat(lazyFormat(jsonFormat5(Item.apply)))

}
