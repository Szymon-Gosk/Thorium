package thorium.math.arithmetic.infinities

import org.apache.logging.log4j.LogManager
import thorium.math.arithmetic.numbers.Complex
import thorium.math.general.exceptions.IndeterminateException

object DirectedInfinity {

  def apply(direction: Complex): DirectedInfinity = {
    if(direction == Complex(0, 0)) {
      LogManager.getLogger(classOf[DirectedInfinity].getName).fatal("Directed infinity cannot have direction of 0+0i")
      throw new IndeterminateException("Directed infinity cannot be directionless")
    }
    if(direction.isReal) {
      if(direction.Re > 0) {
        return PositiveInfinity
      }
      return NegativeInfinity
    } else if(direction.isImaginary) {
      if(direction.Im > 0) {
        return PositiveInfinityZ
      }
      return NegativeInfinityZ
    }
    new DirectedInfinity(direction)
  }

}

class DirectedInfinity(private val _direction: Complex) extends Infinity {

  def direction: Complex = _direction

  override def toString: String = "DirectedInfinity[" + _direction.Re + ", " + _direction.Im + "]"

  override def unicode: String = "(" + _direction.unicode + ")∙∞"

  override def latex: String = "\\left( " + _direction.latex + " \\right)\\infty"
}
