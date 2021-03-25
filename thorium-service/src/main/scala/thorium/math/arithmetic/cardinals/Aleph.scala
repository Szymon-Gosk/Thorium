package thorium.math.arithmetic.cardinals

import thorium.math.Entity
import thorium.math.arithmetic.numbers.Complex
import thorium.types.Optional

object Aleph {

  def apply(index: Int): Aleph = new Aleph(index)

}

class Aleph(private val _index: Int) extends Entity {

  def index: Int = _index

  // ----- ADDITION ----- //

  def +(a: Aleph): Aleph = if(a.index > _index) a else this

  def +(i: Int): Aleph = this

  def +(l: Long): Aleph = this

  def +(f: Float): Aleph = this

  def +(d: Double): Aleph = this

  // ----- MULTIPLICATION ----- //

  def *(a: Aleph): Aleph = if(a.index > _index) a else this

  def *(i: Int): Optional[Entity, Any] = {
    if(i == 0) Optional.alternative(Complex(0, 0))
    else Optional.value(this)
  }

  def *(l: Long): Optional[Entity, Any] = {
    if(l == 0) Optional.alternative(Complex(0, 0))
    else Optional.value(this)
  }

  def *(f: Float): Optional[Entity, Any] = {
    if(f == 0) Optional.alternative(Complex(0, 0))
    else Optional.value(this)
  }

  def *(d: Double): Optional[Entity, Any] = {
    if(d == 0) Optional.alternative(Complex(0, 0))
    else Optional.value(this)
  }

  override def toString: String = "Aleph[" + _index + "]"

  override def unicode: String = "\u05D0" + _index

  override def latex: String = "\\aleph_{" + _index + "}"

}
