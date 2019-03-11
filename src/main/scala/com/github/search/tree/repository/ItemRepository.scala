package com.github.search.tree.repository

import com.github.search.tree.model.Psychographics
import com.github.search.tree.service.DataRepository

class ItemRepository(override val items: List[Psychographics]) extends DataRepository[Psychographics] {

  def nameCategoryPair(item: Psychographics): (String, Psychographics) =
    (item.label, item)

  def getChildren(t: Psychographics): Option[List[Psychographics]] = t.values
}
