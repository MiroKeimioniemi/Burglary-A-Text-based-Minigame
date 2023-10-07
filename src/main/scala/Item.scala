/** The class `Item` represents items in a text undercover game. Each item has a name
  * and a longer description. (In later versions of the undercover game, items may
  * have other features as well.)
  *
  * N.B. It is assumed, but not enforced by this class, that items have unique names.
  * That is, no two items in a game world have the same name.
  *
  * @param name         the item’s name
  * @param description  the item’s description */
class Item(val name: String, val description: String, val value: Int, val weight: Int):

  def executeFunction(target: Area) =
    this.name match
      case "lockpick" =>
        if target.description.contains("locked door") then
          target.unlock()
          "You picked the lock."
        else
          "You didn't do anyhting. Use lockpick on a locked door to unlock it."
      case "keys" =>
        if !target.open then
          target.unlock()
          target.openContainer()
          s"You opened the ${target.name}."
        else
          "You didn't do anything. Use keys on locked containers."
      case "stone" =>
        if target.description.contains("window") || target.description.contains("glass") then
          target.unlock()
          target.openContainer()
          "You smashed the glass."
        else
          "You didn't do anything. Use stone for smashing glass."
      case "rock" =>
        if target.description.contains("window") || target.description.contains("glass") then
          target.unlock()
          target.openContainer()
          "You smashed the glass."
        else
          "You didn't do anything. Use stone for smashing glass."
      case "pebble" =>
        if target.description.contains("window") || target.description.contains("glass") then
          target.unlock()
          target.openContainer()
          "You smashed the glass."
        else
          "You didn't do anything. Use stone for smashing glass."
      case "trench coats" =>
        "You put on four trench coats.\nAgility: -4"
      case "grand piano" =>
        "You play Yiruma's River Flows in You because what else would you ever play?"
      case "stuffed grizzly bear" =>
        "Taking out all the stuffing produced quite the mess but now you look like a native american chieftain."
      case "silverware" =>
        "Stab!"
      case "persian rug 1" =>
        "Unfortunately this carpet doesn't fly"
      case "persian rug 2" =>
        "Wrapped like a burrito!"
      case "persian rug 3" =>
        "Unfortunately this carpet doesn't fly"
      case "donald duck magazines" =>
        "These are my favorite stories!"
      case "toys" =>
        "Boom! Kah! Pew Pew! Kachow!"
      case "toiletries" =>
        "Looking pretty!"
      case "jewelry" =>
        "Looking shiny!"
      case "golden antique clock" =>
        "Better hurry! Time's running out."
      case "miniature model pirate ship" =>
        "I am the captain now!"
      case "wine" =>
        "Live like it's your last day, eh?"
      case "english literature classics" =>
        "To be or not to be? What, You Egg? [He Stabs Him]"
      case "accounting documents" =>
        "I thought I had already gotten rid of IEM! Guess not..."
      case other =>
        "This item cannot be used for anything."

  /** Returns a short textual representation of the item (its name, that is). */
  override def toString = this.name

end Item

