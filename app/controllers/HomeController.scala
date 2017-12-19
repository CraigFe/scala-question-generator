package controllers

import javax.inject._
import play.api.mvc._

/**
 * This controller serves the homepage.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Question Generation API"))
  }

}
