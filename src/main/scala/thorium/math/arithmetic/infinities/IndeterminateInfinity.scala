package thorium.math.arithmetic.infinities

object IndeterminateInfinity extends Infinity {

  override def toString: String = "IndeterminateInfinity[]"

  override def unicode: String = "?∞"

  override def latex: String = "\\overset{?}{\\infty}"

}
