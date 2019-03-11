package com.github.search.tree

import com.typesafe.config.ConfigFactory

object Config {

  private val config = ConfigFactory.load()

  lazy val Host: String = config.getString("http.host")
  lazy val Port: Int = config.getInt("http.port")

}
