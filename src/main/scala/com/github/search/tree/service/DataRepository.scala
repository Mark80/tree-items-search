package com.github.search.tree.service

trait DataRepository[T] {

  def items: List[T]
  def nameCategoryPair(item: T): (String, T)
  def getChildren(t: T): Option[List[T]]

  def database: Map[String, T] = toMap(items)

  def getLeafBy(value: String): Option[T] = database.get(value)
  def getLeafFrom(from: String, value: String): Option[T] =
    for {
      parent <- getLeafBy(from)
      children <- getChildren(parent)
      mapping = toMap(children)
      result <- mapping.get(value)
    } yield result

  def toMap(tree: List[T]): Map[String, T] =
    tree.foldLeft(Map.empty[String, T]) { (acc, item) =>
      acc + nameCategoryPair(item) ++ {
        getChildren(item) match {
          case Some(values) =>
            toMap(values)
          case None =>
            Map.empty
        }
      }
    }

}
