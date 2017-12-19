package models

import play.api.libs.json.Json

case class Question(
                     question: String,
                     answers: (Int, Int, Int, Int)
                   ) { }

object Question {
  implicit val questionWrites = Json.writes[Question]
}