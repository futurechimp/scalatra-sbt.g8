package $package$.api

import com.escalatesoft.subcut.inject.BindingModule
import org.scalatra.swagger.Swagger
import $package$.api.$model;format="Camel"$Service


class $model;format="Camel"$Controller(implicit val bindingModule: BindingModule, implicit val swagger: Swagger)
  extends ApiStack with $model;format="Camel"ControllerDocs {
  get("/") {
    "foo"
  }
}