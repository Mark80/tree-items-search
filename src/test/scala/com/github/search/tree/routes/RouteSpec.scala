package com.github.search.tree.routes

import akka.http.scaladsl.model.{ContentTypes, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.search.tree.Routes
import com.github.search.tree.model.Category
import com.github.search.tree.routes.json.JsonSupport
import com.github.search.tree.service.{CategoryService, PsychographicsService}
import org.mockito.Mockito.{verify, when}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.global

class RouteSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest with MockitoSugar with BeforeAndAfterEach with JsonSupport {

  val categoryServiceMock = mock[CategoryService]

  val routes = new Routes {
    implicit val executionContext: ExecutionContext = global
    def categoryService: CategoryService = categoryServiceMock
    def psychographicsService: PsychographicsService = ???
  }

  "CategoryRoutes" should {
    "return OK" when {

      val expectedResult = Category(17, Some(-1), Some(13), None, "Category1", 1, None, None, None)
      "category was found" in {

        when(categoryServiceMock.getByName("Category1")).thenReturn(Some(expectedResult))

        Get("/category/Category1") ~> Route.seal(routes.routes) ~> check {
          status shouldBe StatusCodes.OK

          contentType shouldBe ContentTypes.`application/json`
          entityAs[Category] shouldBe expectedResult

          verify(categoryServiceMock).getByName("Category1")
        }

      }

      "category was found from parent" in {

        when(categoryServiceMock.getFromParentByName("parent", "Category1")).thenReturn(Some(expectedResult))

        Get("/category/parent/parent/name/Category1") ~> Route.seal(routes.routes) ~> check {
          status shouldBe StatusCodes.OK

          contentType shouldBe ContentTypes.`application/json`
          entityAs[Category] shouldBe expectedResult

          verify(categoryServiceMock).getByName("Category1")
        }

      }

    }

    "return ot found404 n" when {

      "category was not found" in {

        when(categoryServiceMock.getByName("Category1")).thenReturn(None)

        Get("/category/Category1") ~> Route.seal(routes.routes) ~> check {
          status shouldBe StatusCodes.NotFound

        }

      }

    }

  }
}
