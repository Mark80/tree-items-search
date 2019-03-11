package com.github.search.tree.repository

import com.github.search.tree.model.{Category, Item}
import org.scalatest.{Matchers, OptionValues, WordSpec}

class ItemRepositorySpec extends WordSpec with Matchers with OptionValues {

  val item4 = Item(17, "value4", "label 4", None, None)
  val item2 = Item(27, "value2", "label 2", None, Some(List(item4)))
  val item3 = Item(37, "value3", "label 3", None, None)
  val item1A = Item(47, "value1A", "label 1A", None, Some(List(item2, item3)))
  val item1B = Item(57, "value1B", "label 1B", None, None)

  private val items: List[Item] = List(item1A, item1B)
  val dataRepository = new ItemRepository(items)

  "Parse json" should {

    "get leaf by name" in {

      dataRepository.getLeafBy("label 3").value shouldBe item3

      dataRepository.getLeafBy("label 4").value shouldBe item4
    }

    "get root items" in {

      dataRepository.getLeafBy("label 1B").value shouldBe item1B

    }

    "return None if label not exist" in {

      dataRepository.getLeafBy("Not exist") shouldBe empty

    }

  }

}
