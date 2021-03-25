package thorium.processing

import thorium.latex.Latex
import thorium.math.algebra.components.Matrix
import thorium.math.arithmetic.numbers.Complex

object Processor {

  // name + latex
  def run(): Map[String, String] = {
    var output: Map[String, String] = Map()

    // BUILD 1

    val A = new Matrix(Vector(
      Vector(Complex(2, 1), Complex(0, 3), Complex(4, 0), Complex(-2, 0)),
      Vector(Complex(2, 3), Complex(1, 1), Complex(-1, 0), Complex(0, -1)),
      Vector(Complex(6, -7), Complex(0, 2), Complex(0, 3), Complex(1, 3)),
      Vector(Complex(4, 4), Complex(0, 0), Complex(1, -1), Complex(4, -4))
    ))
    val B = new Matrix(Vector(
      Vector(Complex(3, 0), Complex(2, 0), Complex(-4, 0), Complex(-3, 0)),
      Vector(Complex(1, 0), Complex(1, 0), Complex(-1, 0), Complex(2, 0)),
      Vector(Complex(-2, 0), Complex(5, 0), Complex(0, 0), Complex(1, 0)),
      Vector(Complex(1, 0), Complex(-6, 0), Complex(0, 0), Complex(0, 0))
    ))
    val C = A*B

    val str1 = A.latex + " " + Latex.times + " " + B.latex + " = " + C.latex
    output = output + (("build1", str1))

    // BUILD 2

    var z = Complex(0, 0)
    val c = Complex(0.2, 0.2)

    val I = 150
    for(_ <- 0 to I) {
      z = z*z + c
    }

    val str2 = "M_{0.2 + 0.2i}(z) = z^2 + 0.2 + 0.2i, \\ \\ \\ M^{150}_{0.2 + 0.2i}(0) = " + z.latex
    output = output + (("build2", str2))

    // RETURN BUILD

    output
  }

}
