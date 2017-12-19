# Question Generator REST API
This project is a simple proof-of-concept for a REST API written in Scala using the Play framework. The API contains a single  ```/question```, which serves a JSON response such as the following:


## Distractor Selection
The project uses a custom-designed algorithm for generating the incorrect. This algorithm is contained in the [Question Generator](app/services/QuestionGenerator.scala) class

## Usage
This project can be executed using [sbt](http://www.scala-sbt.org/):
```
sbt run
```
This will initialise a development server at http://localhost:9000 hosting the application. Further instructions are contained on the homepage.