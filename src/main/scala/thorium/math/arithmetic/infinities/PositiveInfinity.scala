package thorium.math.arithmetic.infinities

import thorium.math.arithmetic.numbers.Complex

object PositiveInfinity extends DirectedInfinity(Complex(1, 0)) {

  override def toString: String = "PositiveInfinity[]"

  override def unicode: String = "∞"

  override def latex: String = "\\infty"

}
