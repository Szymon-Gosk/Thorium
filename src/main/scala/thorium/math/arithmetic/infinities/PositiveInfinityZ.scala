package thorium.math.arithmetic.infinities

import thorium.math.arithmetic.numbers.Complex

object PositiveInfinityZ extends DirectedInfinity(Complex(0, 1)) {

  override def toString: String = "PositiveInfinityZ[]"

  override def unicode: String = "i∙∞"

  override def latex: String = "i\\infty"

}
