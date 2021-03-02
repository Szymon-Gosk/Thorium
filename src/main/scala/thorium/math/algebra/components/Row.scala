package thorium.math.algebra.components

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

  // ----- COMPARISON -----

  def ==(r: Row): Boolean = {
    for(i <- 0 until _width) {
      if(r(i) != this(i)) return false
    }
    true
  }

  // ----- ACCESS -----

  def apply(i: Int): Complex = {
    if(i < 0) {
      //TODO
    } else if(i >= _entries.size) {
      //TODO
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
    "Row[" + e.substring(0, e.length - 2) + "]"
  }

  override def unicode: String = {
    val e = _entries.foldLeft("")((s, z) => s + z.unicode + ", ")
    "[" + e.substring(0, e.length - 2) + "]"
  }

  override def latex: String = {
    val e = _entries.foldLeft("")((s, z) => s + z.latex + ", \\ ")
    "\\left[" + e.substring(0, e.length - 4) + "\\right]"
  }
}
