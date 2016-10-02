package $package$.utils.logging

import org.slf4j.LoggerFactory

/**
  * Allows us to have a logger available when needed.
  */
trait Logger {
  val logger = LoggerFactory.getLogger(getClass)
}
