import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {

    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "ProductController" should {

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/products")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Catálogo de productos")
    }

  }

  "ApplicationController" should {

    "render the index page" in {
      val nextUrl = redirectLocation(result) match {
        case Some(s: String) => s
        case _ => ""
      }
      nextUrl must contain("/products")

      val newResult = route(FakeRequest(GET, nextUrl)).get

      status(newResult) must equalTo(OK)
      contentType(newResult) must beSome.which(_ == "text/html")
      contentAsString(newResult) must contain("Catálogo de productos")
    }

  }
}
