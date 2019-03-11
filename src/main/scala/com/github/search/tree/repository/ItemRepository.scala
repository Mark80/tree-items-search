package com.github.search.tree.repository

import com.github.search.tree.model.Item
import com.github.search.tree.service.DataRepository

class ItemRepository(override val items: List[Item]) extends DataRepository[Item] {

  def nameCategoryPair(item: Item): (String, Item) =
    (item.label, item)

  def getChildren(t: Item): Option[List[Item]] = t.values
}
