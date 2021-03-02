package thorium.math.arithmetic.infinities

import thorium.math.arithmetic.numbers.Complex

object NegativeInfinity extends DirectedInfinity(Complex(-1, 0)) {

  override def toString: String = "NegativeInfinity[]"

  override def unicode: String = "-∞"

  override def latex: String = "-\\infty"

}
