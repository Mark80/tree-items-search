package com.github.search.tree.service

import com.github.search.tree.model.Category

class CategoryService(dataRepository: DataRepository[Category]) {

  def getByName(name: String): Option[Category] =
    dataRepository.getLeafBy(name)

  def getFromParentByName(parent: String, name: String): Option[Category] =
    dataRepository.getLeafFrom(parent, name)

}
