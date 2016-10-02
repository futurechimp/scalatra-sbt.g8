import $package$._
import org.scalatra._
import javax.servlet.ServletContext
import $package$.bindings.AppBindings
import $package$.http.api.{ApiDocsController, $name;format="Camel"$ApiDocs}

class ScalatraBootstrap extends LifeCycle {
  implicit val swagger = new $name;format="Camel"$ApiDocs
  implicit val bindingModule = AppBindings

  override def init(context: ServletContext) {
    // Docs
    context.mount(new ApiDocsController, "/api-docs")

  }
}
