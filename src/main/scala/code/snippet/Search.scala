package code.snippet

import net.liftweb.json._
  import Extraction._
  import JsonDSL._
import net.liftweb.http.SHtml._
import net.liftweb.http.js._
  import JE._
import net.liftweb.util.Helpers._

case class Action(name: String, response: String)
case class Company(id: Long, name: String, connected: Boolean, actions: List[Action])

class Search {
  implicit val formats = DefaultFormats

  val duck = Company(1, "Duck, Inc", true,
                     Action("Quack", "quack quack") :: Action("Walk", "walkity walk") :: Nil)
  val cat = Company(2, "Cat, Inc", true,
                     Action("Meow", "meowwwwwww") :: Action("Walk", "prowwwlll") :: Nil)
  val dog = Company(3, "Dog, Inc", true,
                     Action("Bark", "bark BARK") :: Action("Walk", "*lick face*") :: Nil)
  val cow = Company(4, "Cow, Inc", true,
                     Action("Moo", "mooooooooo") :: Action("Walk", "leisurely stroll") :: Nil)
  val sheep = Company(5, "Sheep, Inc", false,
                     Action("Baa", "baaa baaaaaaaa") :: Action("Walk", "Need sheepdog") :: Nil)
  val horse = Company(6, "Horse, Inc", false,
                     Action("Neigh", "neighhhhh") :: Action("Walk", "GALLOOPPP") :: Nil)
  val chicken = Company(7, "Chicken, Inc", false,
                        Action("Cluck", "po-cluck") :: Action("Walk", "Flappity flap") :: Nil)

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
