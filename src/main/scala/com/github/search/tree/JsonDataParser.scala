package com.github.search.tree

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, JsonFormat, JsonParser}

import scala.io.Source

object JsonDataParser extends SprayJsonSupport with DefaultJsonProtocol {

  def getData[T](locationData: String)(implicit format: JsonFormat[T]): List[T] = {
    val json: String = Source.fromResource(locationData).getLines().mkString
    JsonParser(json).convertTo[List[T]]
  }

}
