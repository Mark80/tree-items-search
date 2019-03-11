package com.github.search.tree.integration

import com.github.search.tree.JsonDataParser
import com.github.search.tree.model.Category
import com.github.search.tree.repository.CategoryRepository
import com.github.search.tree.service.CategoryService
import org.scalatest.{Matchers, OptionValues, WordSpec}

class IntegrationTest extends WordSpec with Matchers with OptionValues {

  val categories = JsonDataParser.getData[Category]("categories.json")
  val repository = new CategoryRepository(categories)
  val categoryService = new CategoryService(repository)

  "As client" should {

    "i'm be able to get category by name" in {

      val expectedResult = Category(430, Some(-3), Some(332), Some(333), "Winter Sports", 4, None)

      val categoryFound = categoryService.getByName("Winter Sports").value

      categoryFound shouldBe expectedResult

    }

  }

}
