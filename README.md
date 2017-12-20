# Question Generator REST API
This project is a simple REST API written in Scala using the [Play framework](https://playframework.com/). 
The API provides a single URI (```/question```), which generates a simple addition question and returns a JSON response such as the following:
```
{
  "question" : "94 + 980?",
  "answers" : [ 1044, 1074, 1071, 1054 ]
}
```
The URI accepts two optional query parameters, ``lower`` and ``upper``, which specify the lower and upper bounds of the answer 
(inclusive). The lower and upper bounds default to the minimum and maximum values: [0, 1,000,000]. Invalid query 
parameters will result in an Error 400 response.

The operation of the API is simple: The ``/question`` URI is managed by the [QuestionController](app/controllers/QuestionController.scala) 
class, which invokes a singleton instance of the [QuestionGenerator](app/services/QuestionGenerator.scala) class in 
order to generate questions. These two files have been commented excessively in order to explain the design goals of the code as well as the functionality.

## Usage
This project can be executed using [sbt](http://www.scala-sbt.org/) as follows:
```
sbt run
```
This will initialise a development server at http://localhost:9000 to host the application. Further information is contained on the homepage.