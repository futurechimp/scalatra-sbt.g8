trait ApiStack extends ScalatraServlet with ScalateSupport {

  implicit val jsonFormats: Formats = DefaultFormats + new StringObjectIdSerializer ++ JodaTimeSerializers.all

  before() {
    contentType = formats("json")
  }


  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

}
