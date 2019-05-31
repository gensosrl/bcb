
class AjaxForm {
  var state = AjaxForm.state
  var city = ""
 
  private def cityChoice(state: String): Elem = {
    val cities = AjaxForm.citiesFor(state)
    val first = cities.head
    untrustedSelect(cities.map(s => (s, s)), Full(first), s => city = s, ("id" -> "city"))
  }
 
  private def replace(state: String): JsCmd = {
    val cities = AjaxForm.citiesFor(state)
    val first = cities.head
    ReplaceOptions("city", cities.map(s => (s, s)), Full(first))
  }
 
  def render = {
    "#state" #> ajaxSelect(AjaxForm.states.map(s => (s, s)), Full(state), { s =>
      state = s; After(200, replace(state)) }) &
    "#city" #> cityChoice(state) &
    "type=submit" #> submit(?("Save"), () => {
      S.notice("City: "+city+", State: "+state); redirectTo("/")})
  }
