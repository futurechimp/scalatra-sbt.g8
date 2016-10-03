package $package$.api

import $package$.testsupport.{ControllerTest, TestStack}

class ApiDocsControllerSpec extends ControllerTest with TestStack {

  implicit val swagger = new $name;format="Camel"$ApiDocs

  addServlet(new ApiDocsController, "/*")

  describe("The ApiDocsController") {
    start()

    describe("requesting the api-docs") {
      it("should work") {
        get("/") {
          status shouldEqual 200
        }
      }
    }

    stop()
  }
}