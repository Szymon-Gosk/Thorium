package thorium.processing

import thorium.math.algebra.components.Row
import thorium.math.arithmetic.cardinals.Aleph
import thorium.math.arithmetic.infinities.{DirectedInfinity, IndeterminateInfinity}
import thorium.math.arithmetic.numbers.Complex
import thorium.math.general.exceptions.InfiniteValueException

object Processor {

  private def run0(): String = {
    var z = Complex(0, 0)
    val c = Complex(3, 6)
    try {
      for(_ <- 0 to 500) {
        z = (z * z) + c
      }
      "M(z) = z^2 + " + c.latex + ", \\ \\ \\ M^{500} (z) = " + z.latex + " \\\\ "
    } catch {
      case _: InfiniteValueException =>
        "M(z) = z^2 + " + c.latex + ", \\ \\ \\ M^{500} (z) \\rightarrow " + IndeterminateInfinity.latex + " \\\\ "
    }
  }

  private def run1(): String = {
    val a1 = Aleph(1)
    val a2 = Aleph(2)
    a1.latex + " \\cdot " + a2.latex + " = " + (a1*a2).latex + " \\\\ "
  }

  private def run2(): String = {
    val inf = DirectedInfinity(Complex(-1, 1))
    inf.latex + " \\\\ "
  }

  private def run3(): String = {
    val z = Complex(2, 3)
    "\\mathrm{mod}(" + z.latex + ") = " + z.mod.latex + " \\\\ "
  }

  private def run4(): String = {
    val r1 = new Row(Vector(Complex(2, 3), Complex(4, 5), Complex.zero))
    val r2 = new Row(Vector(Complex(1, 2), Complex(-2, -7), Complex.zero))
    r1.latex + " + " + r2.latex + " = " + (r1 + r2).latex + " \\\\ "
  }

  def runAll(): Seq[String] = {
    var out: Seq[String] = Seq()
    for (i <- 0 to 4) {
      out = out :+ run(i)
    }
    out
  }

  def run(index: Int): String = index match {
    case 0 => run0()
    case 1 => run1()
    case 2 => run2()
    case 3 => run3()
    case 4 => run4()
  }

}
