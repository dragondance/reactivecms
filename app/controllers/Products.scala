package controllers

import javax.inject._
import play.api.mvc.{Action, Controller}
import models.Product

class Products @Inject() extends Controller{
  def list = Action { implicit request =>
    val products = Product.findAll

    Ok(views.html.products.list(products))
  }
}