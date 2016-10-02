package com.headlondon.virtaus.http.api.controllers

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{ApiInfo, JacksonSwaggerBase, Swagger}

class $name;format="Camel"$ApiDocs extends Swagger(Swagger.SpecVersion, "1", $name;format="Camel"$ApiInfo)

object $name;format="Camel"$ApiInfo extends ApiInfo(
  "The $name$ API",
  "Docs for the $name$ REST API",
  "http://bar.org/tos",
  "foo@bar.org",
  "Proprietary",
  "http://bar.org/api-license")

class ApiDocsController(implicit val swagger: Swagger) extends ScalatraServlet with JacksonSwaggerBase
