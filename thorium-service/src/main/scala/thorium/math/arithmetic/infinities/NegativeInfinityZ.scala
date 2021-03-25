package thorium.math.arithmetic.infinities

import thorium.math.arithmetic.numbers.Complex

object NegativeInfinityZ extends DirectedInfinity(Complex(0, -1)) {

  override def toString: String = "NegativeInfinityZ[]"

  override def unicode: String = "-i∙∞"

  override def latex: String = "-i\\infty"

}
