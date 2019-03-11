package com.github.search.tree.service

import com.github.search.tree.model.Category
import com.github.search.tree.repository.CategoryRepository
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

class CategoryServiceSpec extends WordSpec with Matchers with MockitoSugar {

  val repositoryMock = mock[CategoryRepository]
  val categoryService = new CategoryService(repositoryMock)

  "CategoryService" should {

    "call repository" in {

      val name = "category name"
      val expectedResult = Category(17, Some(-1), Some(13), None, "category name", 1, None, None, None)

      when(repositoryMock.getLeafBy(name)).thenReturn(Some(expectedResult))

      categoryService.getByName(name)

      verify(repositoryMock).getLeafBy(name)

    }

  }

}
