package thorium.math.algebra.components

import org.apache.logging.log4j.LogManager
import thorium.math.Entity
import thorium.math.arithmetic.numbers.Complex

/*
[ a ]   }
[ b ]     } m [i]
[ c ]   }

  _
  n [j]
 */

/*
    Consecutive Rows from the topmost to the bottommost
 */

object Matrix {

  def apply(m: Int, n: Int, a: (Int, Int) => Complex): Matrix = {
    //TODO
    var rows: Vector[Vector[Complex]] = Vector()
    for(i <- 0 until m) {
      var row: Vector[Complex] = Vector()
      for(j <- 0 until n) {
        row = row :+ a(i, j)
      }
      rows = rows :+ new Row(row).toVector
    }
    new Matrix(rows)
  }

  def zero(n: Int): Matrix = {
    if(n < 0) {
      // TODO
    }
    if(n == 0) {
      // TODO
    }
    var rows: Vector[Vector[Complex]] = Vector()
    for(_ <- 0 until n) {
      var row: Vector[Complex] = Vector()
      for(_ <- 0 until n) {
        row = row :+ Complex(0, 0)
      }
      rows = rows :+ new Row(row).toVector
    }
    new Matrix(rows)
  }

  def identity(n: Int): Matrix = {
    if(n < 0) {
      // TODO
    }
    if(n == 0) {
      // TODO
    }
    var rows: Vector[Vector[Complex]] = Vector()
    for(i <- 0 until n) {
      var row: Vector[Complex] = Vector()
      for(j <- 0 until n) {
        row = row :+ Complex(if(i == j) 1 else 0, 0)
      }
      rows = rows :+ new Row(row).toVector
    }
    new Matrix(rows)
  }

  final val BRACKETS: Int = 0       // [ ]
  final val PARENTHESES: Int = 1    // ( )
  final val BRACES: Int = 2         // { }
  final val LINES: Int = 3          // | |
  final val DOUBLE_LINES: Int = 4   // || ||

}

class Matrix(private var _arrays: Vector[Vector[Complex]]) extends Entity {

  private val log = LogManager.getLogger(classOf[Matrix].getName)

  // ----- VALIDATION -----

  private val _entries: Vector[Row] = {
    var out = _arrays.foldLeft(Vector(): Vector[Row])((out, v) => out :+ new Row(v))
    if(out.nonEmpty) {
      val w = out(0).width
      for(i <- 1 until out.size) {
        if(out(i).width != w) {
          log.fatal("Matrix rows must have equal number of entries")
          throw new IllegalArgumentException("Matrix rows must have equal number of entries")
        }
      }
      if(w == 0) out = Vector()
      if(_arrays == null) out = Vector()
    }
    out
  }

  private val _m = _entries.size
  private val _n = if(_m == 0) 0 else _entries(0).width

  // ----- COMPONENTS -----

  def height: Double = _m
  def width: Double = _n
  def size: (Double, Double) = (_m, _n)

  // ----- ADDITION -----

  def +(m: Matrix): Matrix = {
    if(!this.isSizeOf(m)) {
      log.fatal("Added matrices must have the same size (m x n)")
      throw new IllegalArgumentException("Matrices must have the same size")
    }
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- _entries.indices) {
      arg = arg :+ (_entries(i) + m._entries(i)).toVector
    }
    new Matrix(arg)
  }

  def -(m: Matrix): Matrix = {
    if(!this.isSizeOf(m)) {
      log.fatal("Added matrices must have the same size (m x n)")
      throw new IllegalArgumentException("Matrices must have the same size")
    }
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- _entries.indices) {
      arg = arg :+ (_entries(i) - m._entries(i)).toVector
    }
    new Matrix(arg)
  }

  // ----- MULTIPLICATION -----

  def *(z: Complex): Matrix = {
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- _entries.indices) {
      arg = arg :+ (_entries(i)*z).toVector
    }
    new Matrix(arg)
  }

  def *(n: Int): Matrix = {
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- _entries.indices) {
      arg = arg :+ (_entries(i)*n).toVector
    }
    new Matrix(arg)
  }

  def *(l: Long): Matrix = {
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- _entries.indices) {
      arg = arg :+ (_entries(i)*l).toVector
    }
    new Matrix(arg)
  }

  def *(f: Float): Matrix = {
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- _entries.indices) {
      arg = arg :+ (_entries(i)*f).toVector
    }
    new Matrix(arg)
  }

  def *(r: Double): Matrix = {
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- _entries.indices) {
      arg = arg :+ (_entries(i)*r).toVector
    }
    new Matrix(arg)
  }

  def *(m: Matrix): Matrix = {
    if(_n != m._m) {
      log.fatal("Matrix [m x n] and matrix [p x q] must meet condition " +
        "that n = p for them to be multiplied")
      throw new IllegalArgumentException("Width and height of multiplied matrices must be the same")
    }
    var rows: Vector[Vector[Complex]] = Vector()
    for(i <- 0 until _m) {
      var row: Vector[Complex] = Vector()
      for(j <- 0 until m._n) {
        row = row :+ (getRow(i) * m.getColumn(j))
      }
      rows = rows :+ new Row(row).toVector
    }
    new Matrix(rows)
  }

  // ----- EXPONENTIATION -----

  def inverse(m: Matrix): Matrix = null //TODO

  def ^(n: Int): Matrix = null //TODO

  // ----- MISC -----

  def trace: Complex = Complex.zero // TODO

  def adjugate: Matrix = null // TODO

  def transpose: Matrix = {
    var arg: Vector[Vector[Complex]] = Vector()
    for(i <- 0 until _n) {
      var row: Vector[Complex] = Vector()
      for(j <- 0 until _m) {
        row = row :+ this(j, i)
      }
      arg = arg :+ row
    }
    new Matrix(arg)
  }

  def submatrix(i: Int, j: Int): Matrix = {
    if(!validate(i, j)) {
      log.fatal("Element with given coordinates extends the matrix size")
      throw new IndexOutOfBoundsException()
    }
    var arg: Vector[Vector[Complex]] = Vector()
    for(x <- 0 until _m) {
      var row: Vector[Complex] = Vector()
      for(y <- 0 until _n) {
        if(y != i) row = row :+ this(x, y)
      }
      if(x != j) arg = arg :+ row
    }
    new Matrix(arg)
  }

  def determinant: Complex = {
    if(_m != _n) {
      // TODO
    }
    if(_m == 0) return Complex(1, 0)
    if(_m == 1) return this(0, 0)
    if(_m == 2) return (this(0, 0)*this(1, 1)) - (this(0, 1)*this(1, 0))
    null // TODO
  }

  // ----- COMPARISON -----

  def isSizeOf(m: Matrix): Boolean = _m == m.height && _n == m.width

  def ==(m: Matrix): Boolean = {
    if(height != m.height || width != m.width) return false
    for(i <- 0 until _m) {
      for(j <- 0 until _n) {
        if(this(i, j) != m(i, j)) return false
      }
    }
    true
  }

  def !=(m: Matrix): Boolean = {
    if(height != m.height || width != m.width) return true
    for(i <- 0 until _m) {
      for(j <- 0 until _n) {
        if(this(i, j) != m(i, j)) return true
      }
    }
    false
  }

  def isEmpty: Boolean = _m == 0 || _n == 0

  def isSquare: Boolean = _m == _n

  def isDiagonal: Boolean = true

  def isLowerTriangular: Boolean = true

  def isUpperTriangular: Boolean = true

  def isIdentity: Boolean = true

  def isZero: Boolean = forall(z => z == Complex.zero)

  def isSymmetric: Boolean = this == this.transpose

  def isSkewSymmetric: Boolean = true

  def isInvertible: Boolean = true

  def isOrthogonal: Boolean = true

  def isPositiveDefinite: Boolean = true

  def isNegativeDefinite: Boolean = true

  // ----- ACCESS -----

  def apply(i: Int, j: Int): Complex = {
    if(!validate(i, j)) {
      log.fatal("Element with given coordinates extends the matrix size")
      throw new IndexOutOfBoundsException()
    }
    _entries(i)(j)
  }

  def getRow(i: Int): Row = {
    if(i < 0 || i >= _m) {
      log.fatal("Row with given index extends the matrix size")
      throw new IndexOutOfBoundsException()
    }
    _entries(i)
  }

  def getColumn(j: Int): Row = {
    if(j < 0 || j >= _n) {
      log.fatal("Column with given index extends the matrix size")
      throw new IndexOutOfBoundsException()
    }
    var out: Vector[Complex] = Vector()
    for(x <- 0 until _m) {
      out = out :+ this(x, j)
    }
    new Row(out)
  }

  // ----- AUX -----

  def forall(p: Complex => Boolean): Boolean = {
    for(i <- 0 until _m) {
      if(!getRow(i).forall(p)) return false
    }
    true
  }

  def foreach[U](f: Complex => U): Unit = {
    for(i <- 0 until _m) {
      getRow(i).foreach(f)
    }
  }

  def foldLeftDown[T](t: T)(f: (T, Complex) => T): T = {
    var arg: Vector[Complex] = Vector()
    for(i <- 0 until _m) {
      arg = arg ++ getRow(i).toVector
    }
    arg.foldLeft(t)(f)
  }

  // ----- INTERNAL -----

  override def equals(obj: Any): Boolean = {
    if (obj == this) {
      return true
    }
    if (!obj.isInstanceOf[Matrix]) {
      return false
    }
    val m: Matrix = obj.asInstanceOf[Matrix]
    if(height != m.height || width != m.width) return false
    for(i <- 0 until _m) {
      for(j <- 0 until _n) {
        if(this(i, j) != m(i, j)) return false
      }
    }
    true
  }

  override def toString: String = {
    val out = _entries.foldLeft("")((s, r) => s + r.toString + ", ")
    if(out.length == 0) return "Matrix[]"
    "Matrix[" + out.substring(0, out.length - 2) + "]"
  }

  override def unicode: String = "???"

  override def latex: String = {
    "\\begin{bmatrix}\n" + _entries.foldLeft("")((s, r) => s + r.latexMatrixRow) + "\\end{bmatrix}"
  }

  def latex(i: Int): String = i match {
    case Matrix.BRACKETS => latex
    case Matrix.PARENTHESES => "\\begin{pmatrix}\n" + _entries.foldLeft("")((s, r) => s + r.latexMatrixRow) + "\\end{pmatrix}"
    case Matrix.BRACES => "\\begin{Bmatrix}\n" + _entries.foldLeft("")((s, r) => s + r.latexMatrixRow) + "\\end{Bmatrix}"
    case Matrix.LINES => "\\begin{vmatrix}\n" + _entries.foldLeft("")((s, r) => s + r.latexMatrixRow) + "\\end{vmatrix}"
    case Matrix.DOUBLE_LINES => "\\begin{Vmatrix}\n" + _entries.foldLeft("")((s, r) => s + r.latexMatrixRow) + "\\end{Vmatrix}"
    case _ =>
      log.fatal("No such option for matrix latex formatting exists")
      throw new IllegalArgumentException("No such option exists")
  }

  private def validate(i: Int, j: Int): Boolean = !(i < 0 || j < 0 || i >= _m || j >= _n)

}
