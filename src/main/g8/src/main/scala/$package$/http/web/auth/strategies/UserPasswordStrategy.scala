package $package$.http.web.auth.strategies

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

import $package$.data.models.AdminAccount
import org.scalatra.ScalatraBase
import org.scalatra.auth.ScentryStrategy
import org.slf4j.LoggerFactory

class UserPasswordStrategy(protected val app: ScalatraBase)
                          (implicit request: HttpServletRequest, response: HttpServletResponse)
  extends ScentryStrategy[AdminAccount] {

  override def name: String = "UserPassword"

  val logger = LoggerFactory.getLogger(getClass)

  private def username = app.params.getOrElse("username", "")
  private def password = app.params.getOrElse("password", "")

  /***
    * Determine whether the strategy should be run for the current request.
    */
  override def isValid(implicit request: HttpServletRequest) = {
    username != "" && password != ""
  }


  /**
    *  In real life, this is where we'd consult our data store, asking it whether the user credentials matched
    *  any existing user. Here, we'll just check for a known username/password combination and return a user if
    *  it's found.
    */
  def authenticate()(implicit request: HttpServletRequest, response: HttpServletResponse): Option[AdminAccount] = {
    if(username == "admin" && password == "password") {
      Some(AdminAccount("admin"))
    } else {
      None
    }
  }

  /**
    * What should happen if the user is currently not authenticated?
    */
  override def unauthenticated()(implicit request: HttpServletRequest, response: HttpServletResponse) {
    app.redirect("/admin/sessions/new")
  }
}