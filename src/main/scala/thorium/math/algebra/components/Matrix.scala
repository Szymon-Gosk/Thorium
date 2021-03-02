package thorium.math.algebra.components

import thorium.math.Entity

/*

[ a ]   }
[ b ]     } m [i]
[ c ]   }

  _
  n [j]

  c = a[3, 1]

  A (m x n) [i, j]

 */

/*
    Consecutive Rows from the topmost to the bottommost
 */
class Matrix(private val _entries: Vector[Row]) extends Entity {

  private val _m = _entries.size
  private val _n = if(_m == 0) 0 else _entries(0).width

  // ----- COMPONENTS -----

  def height: Double = _m
  def width: Double = _n

  override def toString: String = {
    ""
  }

  override def equals(obj: Any): Boolean = {
    if (obj == this) {
      return true
    }
    if (!obj.isInstanceOf[Matrix]) {
      return false
    }
    val m: Matrix = obj.asInstanceOf[Matrix]
    if(height != m.height || width != m.width) return false
    true
  }

  override def unicode: String = ""

  override def latex: String = ""

}
