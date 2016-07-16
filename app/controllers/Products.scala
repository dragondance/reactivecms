package  controllers

import play.api.mvc.{Action, Controller}
import models.Product

object Products extends Controller{
  def list = Action { implicit request =>
    val products = Product.findAll

    ok(views.html.products.list(products))
  }
}