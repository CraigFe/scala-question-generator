package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json
import services.QuestionGenerator

/**
  * This controller provides the question generation REST API (/question), by invoking
  * an instance of the QuestionGenerator class.
  */
@Singleton
class QuestionController @Inject() (cc: ControllerComponents,
                                    q: QuestionGenerator) extends AbstractController(cc) {

  def generate(lower_bound: Int, upper_bound: Int) = Action {
    if (lower_bound < 0)
      BadRequest("ERROR: Lower bound may not be less than 0.")

    else if (upper_bound > 1000000)
      BadRequest("ERROR: Upper bound may not be greater than 1,000,000.")

    else
      Ok(Json.prettyPrint(Json.toJson(q.generateQuestion(lower_bound, upper_bound))))
  }

}