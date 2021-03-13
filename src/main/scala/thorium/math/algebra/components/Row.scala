package thorium.math.algebra.components

import org.apache.logging.log4j.LogManager
import thorium.math.Entity
import thorium.math.arithmetic.numbers.Complex

//private[components]
//noinspection DuplicatedCode
class Row(_entries: Vector[Complex]) extends Entity {

  private val _width = _entries.size

  // ----- COMPONENTS -----

  def width: Int = _width

  // ----- OPERATIONS -----

  def +(r: Row): Row = {
    var out: Vector[Complex] = Vector()
    for(i <- 0 until _width) {
      out = out :+ (r(i) + this(i))
    }
    new Row(out)
  }

  def -(r: Row): Row = {
    var out: Vector[Complex] = Vector()
    for(i <- 0 until _width) {
      out = out :+ (r(i) - this(i))
    }
    new Row(out)
  }

  def *(z: Complex): Row = {
    var out: Vector[Complex] = Vector()
    for(i <- 0 until _width) {
      out = out :+ (this(i) * z)
    }
    new Row(out)
  }

  def *(n: Int): Row = {
    var out: Vector[Complex] = Vector()
    for(i <- 0 until _width) {
      out = out :+ (this(i) * n)
    }
    new Row(out)
  }

  def *(l: Long): Row = {
    var out: Vector[Complex] = Vector()
    for(i <- 0 until _width) {
      out = out :+ (this(i) * l)
    }
    new Row(out)
  }

  def *(f: Float): Row = {
    var out: Vector[Complex] = Vector()
    for(i <- 0 until _width) {
      out = out :+ (this(i) * f)
    }
    new Row(out)
  }

  def *(r: Double): Row = {
    var out: Vector[Complex] = Vector()
    for(i <- 0 until _width) {
      out = out :+ (this(i) * r)
    }
    new Row(out)
  }

  def *(row: Row): Complex = {
    if(_width != row.width) {
      LogManager.getLogger(classOf[Row].getName).fatal("Multiplied rows must have the same width")
      throw new IllegalArgumentException("Multiplied rows must have the same width")
    }
    var out: Complex = Complex.zero
    for(i <- 0 until _width) {
      out = out + (this(i) * row(i))
    }
    out
  }

  // ----- MISC -----

  def sum(): Complex = _entries.foldLeft(Complex.zero)((x, y) => x + y)

  def product(): Complex = _entries.foldLeft(Complex.zero)((x, y) => x * y)

  def foldLeft[T](t: T)(f: (T, Complex) => T): T = _entries.foldLeft(t)((t, z) => f(t, z))

  def forall(p: Complex => Boolean): Boolean = _entries.forall(p)

  def foreach[U](f: Complex => U): Unit = _entries.foreach(f)

  // ----- COMPARISON -----

  def ==(r: Row): Boolean = {
    for(i <- 0 until _width) {
      if(r(i) != this(i)) return false
    }
    true
  }

  def !=(r: Row): Boolean = {
    for(i <- 0 until _width) {
      if(r(i) == this(i)) return false
    }
    true
  }

  def isEmpty: Boolean = _entries.isEmpty

  // ----- AUX -----

  def toSeq: Seq[Complex] = _entries

  def toList: List[Complex] = _entries.toList

  def toArray: Array[Complex] = _entries.toArray

  def toVector: Vector[Complex] = _entries

  // ----- ACCESS -----

  def apply(i: Int): Complex = {
    if(i < 0) {
      LogManager.getLogger(classOf[Row].getName).fatal("Element of Row cannot have negative ID")
      throw new IndexOutOfBoundsException("The element with ID value of " + i + " does not exist")
    } else if(i >= _entries.size) {
      LogManager.getLogger(classOf[Row].getName).fatal("The element with ID value of " + i + " does not exist")
      throw new IndexOutOfBoundsException("The element with ID value of " + i + " does not exist")
    }
    _entries(i)
  }

  // ----- INTERNAL -----

  override def equals(obj: Any): Boolean = {
    if (obj == this) {
      return true
    }
    if (!obj.isInstanceOf[Row]) {
      return false
    }
    val r: Row = obj.asInstanceOf[Row]
    if(width != r.width) return false
    for(i <- 0 until _width) {
      if(r(i) != this(i)) return false
    }
    true
  }

  override def toString: String = {
    val e = _entries.foldLeft("")((s, z) => s + z.toString + ", ")
    if(e.length == 0) return "Row[]"
    "Row[" + e.substring(0, e.length - 2) + "]"
  }

  def valuesToString: String = {
    val e = _entries.foldLeft("")((s, z) => s + z.toString + ", ")
    e.substring(0, e.length - 2)
  }

  override def unicode: String = {
    val e = _entries.foldLeft("")((s, z) => s + z.unicode + ", ")
    "[" + e.substring(0, e.length - 2) + "]"
  }

  override def latex: String = {
    val e = _entries.foldLeft("")((s, z) => s + z.latex + ", \\ ")
    "\\left[" + e.substring(0, e.length - 4) + "\\right]"
  }

  def latexMatrixRow: String = {
    val e = _entries.foldLeft("")((s, z) => s + z.latex + " & ")
    e.substring(0, e.length - 3) + " \\\\ \n"
  }

}
