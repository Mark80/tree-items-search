package com.github.search.tree.repository

import com.github.search.tree.model.Category
import org.scalatest.{Matchers, OptionValues, WordSpec}

class CategoryRepositorySpec extends WordSpec with Matchers with OptionValues {

  val category4 = Category(17, Some(-3), Some(13), None, "Category 4", 3, None)
  val category2 = Category(18, Some(-3), Some(13), None, "Category 2", 2, None)
  val category3 = Category(19, Some(-3), Some(13), None, "Category 3", 2, Some(List(category4)))
  val category1A = Category(27, Some(-1), Some(13), None, "Category 1", 1, Some(List(category3, category2)))
  val category1B = Category(57, Some(-1), Some(13), None, "Category 1B", 1, None)

  private val categories: List[Category] = List(category1A, category1B)
  val dataRepository = new CategoryRepository(categories)

  "Parse json" should {

    "get leaf by name" in {

      dataRepository.getLeafBy("Category 3").value shouldBe category3

      dataRepository.getLeafBy("Category 4").value shouldBe category4
    }

    "get root category" in {

      dataRepository.getLeafBy("Category 1B").value shouldBe category1B

    }

    "return None if name not exist" in {

      dataRepository.getLeafBy("Not exist") shouldBe empty

    }

    "get leaf by name from other leaf" in {

      dataRepository.getLeafFrom("Category 1B", "Category 3") shouldBe empty

      dataRepository.getLeafFrom("Category 1", "Category 3").value shouldBe category3

    }

  }

}
