package com.github.search.tree.service

import com.github.search.tree.model.Psychographics

class PsychographicsService(dataRepository: DataRepository[Psychographics]) {

  def getByName(name: String): Option[Psychographics] =
    dataRepository.getLeafBy(name)

  def getFromParentByName(parent: String, name: String): Option[Psychographics] =
    dataRepository.getLeafFrom(parent, name)

}
