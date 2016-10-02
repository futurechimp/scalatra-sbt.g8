package $package$.http.web

import com.escalatesoft.subcut.inject.Injectable
import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport

trait WebStack extends ScalatraServlet with ScalateSupport with Injectable with AuthenticationSupport {

  before() {
    contentType = "text/html"
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

  def error(handler: Any): Unit = ???


}
