package services

import models.Question
import scala.util.Random
import javax.inject._

trait QuestionGenerator {
  def generateQuestion(lower: Int, upper: Int): Question
}

@Singleton
class AdditionQuestion extends QuestionGenerator {
  def n = 4 // The number of answers to include
  def lowerBound = 0
  def upperBound = 1000000

  override def generateQuestion(lower: Int, upper: Int): Question = {
    // First generate the correct answer
    val ans = Random.nextInt(upper - lower + 1) + lower

    val addend = Random.nextInt(ans + 1) // addend in range [0, ans]
    val augend = ans - addend;

    new Question(addend + " + " + augend + "?", generateAnswers(addend, augend))
  }

  /**
    *  In general, it is a difficult problem to invent false answers (known as 'distractors') for use in multiple
    *  choice questions. In this case, we simply mutate one of the digits in either the addend or the augend. This
    *  will not always create 'convincing' potential answers, but it is better than simply
    */
  private def generateAnswers(addend: Int, augend: Int): List[Int] = {

    Random.shuffle(
      variantQuestions(List[(Int, Int)]((addend, augend)))
        .map{case (addend, augend) => addend + augend}
    )
  }

  private def variantQuestions(list: List[(Int, Int)], toGenerate: Int = (n - 1)): List[(Int, Int)] = {
    // Bound an integer within the limits of the answer
    val bounded = (x: Int) => math.min(math.max(x, lowerBound), upperBound)

    if (toGenerate == 0)
      list

    else {

      // Generate a new answer as either the
      val (addend, augend) = list(Random.nextInt(list.length))
      val newQuestion: (Int, Int) = if (Random.nextInt(2) == 0) (mutateDigit(addend), augend) else (addend, mutateDigit(augend))

      if (list.map{case (a, b) => bounded(a + b)}.exists(x => x == bounded(newQuestion._1 + newQuestion._2)))
        variantQuestions(list, toGenerate) // Try again

      else
        variantQuestions(newQuestion :: list, toGenerate - 1) // Add answer to list and keep going
    }

  }

  // Take an integer and mutate a random digit
  def mutateDigit(x: Int): Int = {

    // Compute a random delta
    val sign = if (Random.nextInt(2) == 0) -1 else 1
    val mantissa = Random.nextInt(4) + 1 // In range [1, 5]
    val exponent = Random.nextInt(base10Length(x))

    x + (sign * mantissa) * math.pow(10, exponent).toInt
  }

  /**
    * Utility function to return the number of digits in a positive integer when represented in base 10.
    */
  private def base10Length(x: Int, i: Int = 1): Int =
    if (x < 10) i
    else base10Length(x / 10, i + 1)

}