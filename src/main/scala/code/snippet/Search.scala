package code.snippet

import net.liftweb.json._
  import Extraction._
  import JsonDSL._
import net.liftweb.http.SHtml._
import net.liftweb.http.js._
  import JE._
import net.liftweb.util.Helpers._

case class Company(id: Long, name: String)

class Search {
  implicit val formats = DefaultFormats

  val duck = Company(1, "Duck, Inc")
  val cat = Company(2, "Cat, Inc")
  val dog = Company(3, "Dog, Inc")
  val cow = Company(4, "Cow, Inc")
  val sheep = Company(5, "Sheep, Inc")
  val horse = Company(6, "Horse, Inc")
  val chicken = Company(7, "Chicken, Inc")

  val data: List[JValue] =
    (duck :: cat :: dog :: cow :: sheep :: horse :: chicken :: Nil).map(decompose(_))

  def runIt = {
    var length = 1
    def showResults = {
      val result = Call("kmo.showResults", data.take(length):JValue).cmd
      length += 1

      result
    }

    "button [onclick]" #> ajaxInvoke(showResults _)
  }
}
