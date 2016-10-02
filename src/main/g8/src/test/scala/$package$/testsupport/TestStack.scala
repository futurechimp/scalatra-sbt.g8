package $package$.testsupport

import $package$.utils.logging.Logger
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar
import org.scalatra.test.scalatest.ScalatraSpec


trait TestStack extends ScalatraSpec with Matchers with MockitoSugar with Logger