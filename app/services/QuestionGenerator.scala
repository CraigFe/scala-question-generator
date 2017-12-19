package services

import models.Question
import javax.inject._

trait QuestionGenerator {
  def generateQuestion(lower: Int, upper: Int): Question
}

@Singleton
class AdditionQuestion extends QuestionGenerator {

  /**
    * Generates a
    * @param lower
    * @param upper
    * @return
    */
  override def generateQuestion(lower: Int, upper: Int): Question = {
    val random = scala.util.Random;

    // First generate the correct answer
    val ans = random.nextInt(upper - lower + 1) + lower;

    val addend = random.nextInt(ans - 1) + 1; // addend in range [1, ans)
    val augend = ans - addend;

    return new Question(addend + " + " + augend + "?", (ans, 0, 0, 0))
  }

}