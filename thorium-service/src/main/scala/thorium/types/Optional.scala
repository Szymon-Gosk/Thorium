package thorium.types

import org.apache.logging.log4j.LogManager
import thorium.math.Entity

object Optional {

  def value[V <: Entity, A <: Any](value : V): Optional[V, Any] = new Optional[V, Any](value, null)

  def alternative[A <: Any](option : A): Optional[Entity, A] = new Optional[Entity, A](null, option)

}

class Optional[V <: Entity, A <: Any](_value: V, _option: A) {

  def containsValue(): Boolean = _value != null

  def containsOption(): Boolean = _option != null

  def value(): V = {
    if(_value == null) {
      LogManager.getLogger(classOf[Optional[V, A]].getName).fatal("Cannot reference main value - only optional value exists")
      throw new InvalidOptionalReferenceException
    }
    _value
  }

  def option(): A = {
    if(_option == null) {
      LogManager.getLogger(classOf[Optional[V, A]].getName).fatal("Cannot reference optional value - only main value exists")
      throw new InvalidOptionalReferenceException
    }
    _option
  }

  override def toString: String = {
    if (_value == null) "Optional[" + _option.toString + ": " + _option.getClass.getSimpleName + "]"
    else "Optional[" + _value.toString + ": " + _value.getClass.getSimpleName + "]"
  }

}
