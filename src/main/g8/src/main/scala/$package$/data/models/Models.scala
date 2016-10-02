package $package$.data.models

case class AdminAccount(id: String) {
  def forgetMe() = {
    println("Destroying token in datastore")
  }
}