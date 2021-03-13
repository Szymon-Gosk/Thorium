package thorium.latex

object Latex {

  // ----- STYLE -----

  def mathrm(latex: String): String = "\\mathrm{" + latex + "}"
  def mathbb(latex: String): String = "\\mathbb{" + latex + "}"

  def parentheses(latex: String): String = "\\left( " + latex + " \\right)"
  def brackets(latex: String): String = "\\left[ " + latex + " \\right]"
  def braces(latex: String): String = "\\left{ " + latex + " \\right}"

  // ----- OPERATIONS -----

  def times: String = "\\cdot "

  // ----- LOGIC -----

  def If: String = "\\Rightarrow "
  def Iff: String = "\\Leftrightarrow"
  def Or: String = "\\vee "
  def And: String = "\\wedge "
  def Forall(latex: String): String = "\\displaystyle\\mathop{\\forall}_{" + latex + "}"
  def Exists(latex: String): String = "\\displaystyle\\mathop{\\exists}_{" + latex + "}"
  def In: String = "\\in "

  def >=(): String = "\\geq"
  def <=(): String = "\\leq"
  def !=(): String = "\\neq"

}
