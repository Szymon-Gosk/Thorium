package thorium.processing

import thorium.latex.Latex
import thorium.math.algebra.components.{Matrix, Row}
import thorium.math.arithmetic.infinities.IndeterminateInfinity
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
    val M = new Matrix(Vector(
      Vector(Complex(1, 0), Complex(2, 0), Complex(3, 0)),
      Vector(Complex(4, 0), Complex(5, 0), Complex(6, 0)),
      Vector(Complex(7, 0), Complex(8, 0), Complex(9, 0))
    ))
    Latex.mathrm("sub") + "_{" + Latex.parentheses("1, 1") + "}" + Latex.parentheses(M.latex + "^T") +
      " = " + M.transpose.submatrix(1, 1).latex
  }

  private def run2(): String = {
    val M1 = new Matrix(Vector(
      Vector(Complex(2, 1), Complex(4, 6), Complex(3, 0)),
      Vector(Complex(-1, -1), Complex(-3, 0), Complex(0, 1)),
      Vector(Complex(1, 0), Complex(0, -2), Complex(0, 2))
    ))
    val M2 = new Matrix(Vector(
      Vector(Complex(1, 3), Complex(-3, 2)),
      Vector(Complex(2, -1), Complex(2, 0)),
      Vector(Complex(-4, 0), Complex(3, 4))
    ))
    M1.latex(Matrix.PARENTHESES) + " \\cdot " + M2.latex(Matrix.PARENTHESES) + " = " + (M1 * M2).latex(Matrix.PARENTHESES)
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
