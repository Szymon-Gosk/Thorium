package thorium.math.arithmetic.numbers

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TestComplex extends FunSuite {

  test("Complex.+") {
    assert(Complex(1, 2) + Complex(3, 4) == Complex(4, 6))
    assert(Complex(1, 2) + Complex(-3, 4) == Complex(-2, 6))
    assert(Complex(1, 2) + Complex(3, -4) == Complex(4, -2))
    assert(Complex(1, 2) + Complex(-3, -4) == Complex(-2, -2))
    assert(Complex(1, 2) + Complex(0, 0) == Complex(0, 0))

  }

}
