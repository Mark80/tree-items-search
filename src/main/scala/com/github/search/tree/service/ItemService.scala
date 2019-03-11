package com.github.search.tree.service

import com.github.search.tree.model.Item

class ItemService(dataRepository: DataRepository[Item]) {

  def getByName(name: String): Option[Item] =
    dataRepository.getLeafBy(name)

  def getFromParentByName(parent: String, name: String): Option[Item] =
    dataRepository.getLeafFrom(parent, name)

}
