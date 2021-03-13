package thorium.math.algebra.components.test

import org.apache.logging.log4j.LogManager
import thorium.math.algebra.components._

private object TestCases {

  private val log = LogManager.getLogger(classOf[TestCases].getName)

  def main(args: Array[String]): Unit = testCases()

  def testCases(): Unit = {
    var M: Matrix = null

    /**  Matrix 0x0  */
    M = new Matrix(Vector())
    log.info(M)

    /**  Matrix 3x0  */
    M = new Matrix(Vector(Vector(), Vector(), Vector()))
    log.info(M)

    /**  Null matrix  */
    M = null
    log.info(M)


  }

}

private class TestCases
