package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json
import services.QuestionGenerator

@Singleton
class QuestionController @Inject() (cc: ControllerComponents,
                                    q: QuestionGenerator) extends AbstractController(cc) {

  def generate = Action { Ok(Json.prettyPrint(Json.toJson(q.generateQuestion(0, 100)))) }

}