package com.github.search.tree.repository

import com.github.search.tree.model.{Category, Psychographics}
import org.scalatest.{Matchers, OptionValues, WordSpec}

class PsychographicsRepositorySpec extends WordSpec with Matchers with OptionValues {

  val psychographic4 = Psychographics(17, "value4", "label 4", None, None)
  val psychographic2 = Psychographics(27, "value2", "label 2", None, Some(List(psychographic4)))
  val psychographic3 = Psychographics(37, "value3", "label 3", None, None)
  val psychographic1A = Psychographics(47, "value1A", "label 1A", None, Some(List(psychographic2, psychographic3)))
  val psychographic1B = Psychographics(57, "value1B", "label 1B", None, None)

  private val items: List[Psychographics] = List(psychographic1A, psychographic1B)
  val dataRepository = new ItemRepository(items)

  "Parse json" should {

    "get leaf by name" in {

      dataRepository.getLeafBy("label 3").value shouldBe psychographic3

      dataRepository.getLeafBy("label 4").value shouldBe psychographic4
    }

    "get root items" in {

      dataRepository.getLeafBy("label 1B").value shouldBe psychographic1B

    }

    "return None if label not exist" in {

      dataRepository.getLeafBy("Not exist") shouldBe empty

    }

  }

}
