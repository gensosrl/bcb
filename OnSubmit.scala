package code
package snippet
​
import net.liftweb._
import http._
import util.Helpers._
import scala.xml.NodeSeq
​
object OnSubmit {
  def render = {
    // define some variables to put our values into
    var name = ""
    var age = 0
​
    // process the form
    def process() {
      // if the age is < 13, display an error
      if (age < 13) S.error("Too young!")
      else {
        // otherwise give the user feedback and
        // redirect to the home page
        S.notice("Name: "+name)
        S.notice("Age: "+age)
        S.redirectTo("/")
      }
    }
​
    "name=name" #> SHtml.onSubmit(name = _) & 
    "name=age" #> SHtml.onSubmit(s => asInt(s).foreach(age = _)) &
    "type=submit" #> SHtml.onSubmitUnit(process)
  }
}
