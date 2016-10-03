package $package$.api

import com.escalatesoft.subcut.inject.Injectable
import $package$.api.StringObjectIdSerializer
import org.json4s.ext.JodaTimeSerializers
import org.json4s.{DefaultFormats, Formats, _}
import org.scalatra._
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.swagger.SwaggerSupport

trait ApiStack extends ScalatraServlet with Injectable with SwaggerSupport with JacksonJsonSupport {

    implicit val jsonFormats: Formats = DefaultFormats + new StringObjectIdSerializer ++ JodaTimeSerializers.all

    before() {
        contentType = formats("json")
    }

    def error(handler: Any): Unit = ???

}
