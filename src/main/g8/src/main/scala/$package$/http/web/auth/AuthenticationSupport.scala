package $package$.http.web.auth

import $package$.data.models.AdminAccount
import $package$.http.web.auth.strategies.{RememberMeStrategy, UserPasswordStrategy}
import org.scalatra.ScalatraBase
import org.scalatra.auth.{ScentryConfig, ScentrySupport}
import org.slf4j.LoggerFactory

trait AuthenticationSupport extends ScalatraBase with ScentrySupport[AdminAccount] {
  self: ScalatraBase =>

  val logger = LoggerFactory.getLogger(getClass)


  protected val scentryConfig = (new ScentryConfig {}).asInstanceOf[ScentryConfiguration]

  protected def fromSession = {
    case id: String => AdminAccount(id)
  }

  protected def toSession = {
    case usr: AdminAccount => usr.id
  }

  protected def requireLogin() = {
    if (!isAuthenticated) {
      redirect("/admin/sessions/new")
    }
  }


  override protected def configureScentry = {
    scentry.unauthenticated {
      scentry.strategies("UserPassword").unauthenticated()
    }
  }

  override protected def registerAuthStrategies = {
    scentry.register("UserPassword", app => new UserPasswordStrategy(app))
    scentry.register("RememberMe", app => new RememberMeStrategy(app))
  }

}