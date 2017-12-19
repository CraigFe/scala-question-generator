# Question Generator REST API
This project is a simple proof-of-concept for a REST API written in Scala using the [Play framework](https://playframework.com/). 
The API contains a single URI (```/question```), which generates a simple addition question and returns a JSON response such as the following:
```
{
  "question" : "94 + 980?",
  "answers" : [ 1044, 1074, 1071, 1054 ]
}
```
The URI accepts two optional parameters, ``lower`` and ``upper``, which specify the lower and upper bounds of the answer 
(inclusive). The lower and upper bounds default to the minimum and maximum values: [0, 1,000,000]. Invalid query 
parameters will result in an Error 400 (bad request) being thrown.

The operation of the API is simple: The ``/question`` URI is managed by the [QuestionController](app/controllers/QuestionController.scala) class, which invokes a singleton instance of [QuestionGenerator](app/services/QuestionGenerator.scala) in order to generate a question in response to the GET request.

## Usage
This project can be executed using [sbt](http://www.scala-sbt.org/):
```
sbt run
```
This will initialise a development server at http://localhost:9000 to host the application. Further information is contained on the homepage.