package com.github.search.tree.repository

import com.github.search.tree.model.Category
import com.github.search.tree.service.DataRepository

class CategoryRepository(override val items: List[Category]) extends DataRepository[Category] {

  def nameCategoryPair(item: Category): (String, Category) =
    (item.name, item)

  def getChildren(t: Category): Option[List[Category]] = t.children
}
