package com.github.search.tree.model

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class Psychographics(id: Int, value: String, label: String, pic: Option[String], values: Option[List[Psychographics]])

object Psychographics extends SprayJsonSupport with DefaultJsonProtocol {

  implicit lazy val itemFormat: RootJsonFormat[Psychographics] = rootFormat(lazyFormat(jsonFormat5(Psychographics.apply)))

}
