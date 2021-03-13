package thorium.math.arithmetic.numbers

import org.apache.logging.log4j.LogManager
import thorium.math.Entity
import thorium.math.general.exceptions.{IndeterminateException, InfiniteValueException}

object Complex {

  def zero: Complex = Complex(0, 0)

  def apply(re: Double, im: Double): Complex = new Complex(re, im)

}

//noinspection DuplicatedCode
class Complex(private val _re: Double, private val _im: Double) extends Entity {

  // ----- VALIDATION -----

  if(_re.isNaN || _im.isNaN) {
    LogManager.getLogger(classOf[Complex].getName).fatal("This number seems to be approaching infinity in an unknown way")
    throw new InfiniteValueException("Value of Re or Im is NaN")
  }

  // ----- COMPONENTS -----

  def Re: Double = _re
  def Im: Double = _im

  // ----- ADDITION -----

  def +(z: Complex): Complex = Complex(_re + z.Re, _im + z.Im)

  def +(n: Int): Complex = Complex(_re + n, _im)

  def +(l: Long): Complex = Complex(_re + l, _im)

  def +(f: Float): Complex = Complex(_re + f, _im)

  def +(r: Double): Complex = Complex(_re + r, _im)

  // ----- SUBTRACTION -----

  def -(z: Complex): Complex = Complex(_re - z.Re, _im - z.Im)

  def -(n: Int): Complex = Complex(_re - n, _im)

  def -(l: Long): Complex = Complex(_re - l, _im)

  def -(f: Float): Complex = Complex(_re - f, _im)

  def -(r: Double): Complex = Complex(_re - r, _im)

  // ----- MULTIPLICATION -----

  def *(z: Complex): Complex = Complex(_re*z.Re - _im*z.Im, _im*z.Re + _re*z.Im)

  def *(n: Int): Complex = Complex(_re*n, _im*n)

  def *(l: Long): Complex = Complex(_re * l, _im)

  def *(f: Float): Complex = Complex(_re * f, _im)

  def *(r: Double): Complex = Complex(_re*r, _im*r)

  // ----- DIVISION -----

  def /(z: Complex): Complex = {
    if(z == Complex.zero) {
      if(this == Complex.zero) {
        LogManager.getLogger(classOf[Complex].getName).fatal("Value of 0/0 is indeterminate")
        throw new IndeterminateException("Value of 0/0 is indeterminate")
      }
      LogManager.getLogger(classOf[Complex].getName).fatal("Complex number divided by zero is infinite")
      throw new InfiniteValueException("Complex number divided by zero")
    }
    Complex(
      (_re*z.Re + _im*z.Im)/(z.Re*z.Re + z.Im*z.Im),
      (_im*z.Re - _re*z.Im)/(z.Re*z.Re + z.Im*z.Im)
    )
  }

  def /(n: Int): Complex = {
    if(n == 0) {
      if(this == Complex.zero) {
        LogManager.getLogger(classOf[Complex].getName).fatal("Value of 0/0 is indeterminate")
        throw new IndeterminateException("Value of 0/0 is indeterminate")
      }
      LogManager.getLogger(classOf[Complex].getName).fatal("Complex number divided by zero is infinite")
      throw new InfiniteValueException("Complex number divided by zero")
    }
    Complex(_re/n.toDouble, _im/n.toDouble)
  }

  def /(l: Long): Complex = {
    if(l == 0) {
      if(this == Complex.zero) {
        LogManager.getLogger(classOf[Complex].getName).fatal("Value of 0/0 is indeterminate")
        throw new IndeterminateException("Value of 0/0 is indeterminate")
      }
      LogManager.getLogger(classOf[Complex].getName).fatal("Complex number divided by zero is infinite")
      throw new InfiniteValueException("Complex number divided by zero")
    }
    Complex(_re/l.toDouble, _im/l.toDouble)
  }

  def /(f: Float): Complex = {
    if(f == 0) {
      if(this == Complex.zero) {
        LogManager.getLogger(classOf[Complex].getName).fatal("Value of 0/0 is indeterminate")
        throw new IndeterminateException("Value of 0/0 is indeterminate")
      }
      LogManager.getLogger(classOf[Complex].getName).fatal("Complex number divided by zero is infinite")
      throw new InfiniteValueException("Complex number divided by zero")
    }
    Complex(_re/f.toDouble, _im/f.toDouble)
  }

  def /(r: Double): Complex = {
    if(r == 0) {
      if(this == Complex.zero) {
        LogManager.getLogger(classOf[Complex].getName).fatal("Value of 0/0 is indeterminate")
        throw new IndeterminateException("Value of 0/0 is indeterminate")
      }
      LogManager.getLogger(classOf[Complex].getName).fatal("Complex number divided by zero is infinite")
      throw new InfiniteValueException("Complex number divided by zero")
    }
    Complex(_re/r, _im/r)
  }

  // ----- EXPONENTIATION -----

  def ^(n: Int): Complex = {
    if(n == 0) {
      if(_re == 0 && _im == 0) {
        LogManager.getLogger(classOf[Complex].getName).fatal("The value of expression (0+0i)^0 is indeterminate")
        throw new IndeterminateException("Value of 0^0 is indeterminate")
      }
      return Complex(1, 0)
    }
    var it = n - 1
    var result = this
    if(n > 0) {
      while(it > 0) {
        result = result * this
        it -= 1
      }
      result
    } else {
      if(this == Complex.zero) {
        LogManager.getLogger(classOf[Complex].getName).fatal("Value of expression 1/0 is infinite")
        throw new InfiniteValueException("Number divided by zero")
      }
      while(it > 0) {
        result = result / this
        it -= 1
      }
      result
    }
  }

  // ----- MISC -----

  def conjugate: Complex = Complex(_re, 0 - _im)

  def mod: Complex = {
    if(_im == 0) {
      return Complex(Math.abs(_re), 0)
    } else if(_re == 0) {
      return Complex(Math.abs(_im), 0)
    }
    Complex(Math.sqrt(_re*_re + _im*_im), 0)
  }

  def arg: Complex = Complex(Math.atan2(_im, _re), 0)

  def ln: Complex = {
    if(this == Complex.zero) {
      LogManager.getLogger(classOf[Complex].getName).fatal("Value of ln(0) is infinite (expression approaches negative infinity)")
      throw new InfiniteValueException("Logarithm of 0")
    }
    if(_im == 0) {
      return Complex(Math.log(_re), 0)
    }
    Complex(Math.log(this.mod.toDouble), this.arg.toDouble)
  }

  // ----- COMPARISON -----

  def isNatural: Boolean = _im == 0 && _re >= 0 && _re.isWhole()

  def isInteger: Boolean = _im == 0 && Math.abs(_re).isWhole()

  def isReal: Boolean = _im == 0

  def isImaginary: Boolean = _re == 0

  def isPureImaginary: Boolean = _re == 0 && _im != 0

  def ==(z: Complex): Boolean = z._re == _re && z._im == _im

  def !=(z: Complex): Boolean = z._re != _re || z._im != _im

  // ----- AUX -----

  def toInt: Int = {
    if(_im == 0) {
      return _re.toInt
    } else if(_re == 0) {
      return _im.toInt
    }
    this.mod.toInt
  }

  def toDouble: Double = {
    if(_im == 0) {
      return _re.toDouble
    } else if(_re == 0) {
      return _im.toDouble
    }
    this.mod.toDouble
  }

  // ----- INTERNAL -----

  override def toString: String = "Complex[" + _re + ", " + _im + "]"

  override def equals(obj: Any): Boolean = {
    if (obj == this) {
      return true
    }
    if (!obj.isInstanceOf[Complex]) {
      return false
    }
    val z: Complex = obj.asInstanceOf[Complex]
    _re == z._re && _im == z._im
  }

  override def unicode: String = _re + " + i∙" + _im

  override def latex: String = formatLatex()

  private def formatLatex(): String = {
    if (this == Complex.zero) return "0"
    if(_im == 0) {
      if(_re < 0) {
        if(_re.isWhole()) {
          "-" + math.abs(_re).toInt
        } else {
          "-" + math.abs(_re)
        }
      } else {
        if(_re.isWhole()) {
          "" +_re.toInt
        } else {
          "" + _re
        }
      }
    } else if(_re == 0) {
      if(_im < 0) {
        if(_im == -1.0) {
          return "-i"
        }
        if(_im.isWhole()) {
          "-" + math.abs(_im).toInt + "i"
        } else {
          "-i\\cdot " + _im
        }
      } else {
        if(_im == 1.0) {
          return "i"
        }
        if(_im.isWhole()) {
          _im.toInt + "i"
        } else {
          "i\\cdot " + _im
        }
      }
    } else {
      var output = ""
      if(_re < 0) {
        if(_re.isWhole()) {
          output = output + "-" + math.abs(_re).toInt
        } else {
          output = output + "-" + math.abs(_re)
        }
      } else {
        if(_re.isWhole()) {
          output = output + _re.toInt
        } else {
          output = output + _re
        }
      }
      if(_im < 0) {
        if(_im == -1.0) {
          output = output + " -i"
        }  else if(_im.isWhole()) {
          output = output + " - " + math.abs(_im).toInt + "i"
        } else {
          output = output + " - i\\cdot " + math.abs(_im)
        }
      } else {
        if(_im == 1.0) {
          output = output + " +i"
        } else if(_im.isWhole()) {
          output = output + " + " + _im.toInt + "i"
        } else {
          output = output + " + i\\cdot " + _im
        }
      }
      output
    }
  }

}

