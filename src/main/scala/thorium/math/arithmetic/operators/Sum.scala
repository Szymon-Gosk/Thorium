package thorium.math.arithmetic.operators

import thorium.math.Entity
import thorium.math.arithmetic.numbers.Complex

object Sum extends Entity {

  def apply(i: Int, n: Int, a: Int => Complex): Complex = {
    var output = Complex.zero
    for(x <- i to n) {
      output = output + a(x)
    }
    output
  }

  override def unicode: String = "Σ"

  def latex: String = "\\sum"

  def latex(arg: String, i: Int, n: Int): String = {
    "\\sum\\limits_{" + arg + " = " + i + "}^{" + n + "}"
  }

}
