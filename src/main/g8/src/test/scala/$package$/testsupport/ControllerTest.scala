package $package$.testsupport

import org.json4s.JsonAST.JValue
import org.json4s.ext.JodaTimeSerializers
import org.json4s.jackson.JsonMethods._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.test.{EmbeddedJettyContainer, HttpComponentsClient}

/**
  * From many years of habit, I routinely write BDD-style tests in ScalaTest as I would in Ruby:
  *
  * ```
  * describe("foo") {
  * val bar = doSomeSetup()
  * it("should baz") {
  * // assertions here
  * }
  * }
  * ```
  * This fucks up in ScalaTest because under normal conditions in a ScalaTest FunSpec, you need to do the `val bar` line
  * in a `before` block. But there can be only one `before` block in a single spec.
  *
  * The solution, according to Bill Venners (who should know), is to use path.FunSpec instead. This provides exactly the
  * same kind of thing guarantees about scoping that MiniTest does in Ruby: each nested `describe` block generates a new,
  * ordered scope.
  *
  * Problem solved? Unfortunately, not quite. `ScalatraTest`, defined in Scalatra itself, uses `beforeAll` and `afterAll`
  * to do some setup, i.e. firing up an embedded Jetty so we can make fake requests to our application.
  *
  * In `path.FunSpec` these are defined as `final`. Trying to extend them counts as an `override`, and the whole thing
  * won't compile.
  *
  * So, in order to get the Scalatra goodness (e.g. `addServlet`, testing on HTTP verbs) I've had to pull
  * `EmbeddedJettyContainer` and `HttpComponentsClient` into another trait that doesn't mix in `beforeAll` and `afterAll`.
  *
  * This means that it's now necessary to call `start()` and `stop()` manually in controller tests, so that the embedded
  * servlet container used in controller tests fires up.
  *
  * Time will tell if this will prove more of a gotcha than the alternative, which was to write a test, have
  * side-effects get mixed up, and then realise that it wasn't application code but my tests causing the problem.
  */
trait ControllerTest extends EmbeddedJettyContainer with HttpComponentsClient {

  /**
    * REMINDER:
    * *
    * addServlet(new FooController, "/*")
    * start() // start the embedded Jetty servlet
    * *
    * describe("The FooController") {
    * describe("The X") {
    *// tests go here
    * }
    * *
    * }
    * *
    * stop() // stop the embedded Jetty servlet
    *
    */


//  protected implicit lazy val jsonFormats: Formats = DefaultFormats + new StringObjectIdSerializer ++ JodaTimeSerializers.all
//
//  def postJson[A](uri: String, body: JValue)(f: => A): A = {
//    post(uri, compact(render(body)).getBytes("utf-8"), Map("Content-Type" -> "application/json"))(f)
//  }

}

