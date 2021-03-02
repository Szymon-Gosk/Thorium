package thorium.math

import org.apache.logging.log4j.{LogManager, Logger}
import thorium.math.algebra.components.Row
import thorium.math.arithmetic.numbers.Complex

object Test extends App {

  private val log: Logger = LogManager.getLogger(classOf[App].getName)

  val r = new Row(Vector(Complex(2, 3), Complex(4, 5), Complex.zero))

  log.info(r(2))
  log.info(r)

}
