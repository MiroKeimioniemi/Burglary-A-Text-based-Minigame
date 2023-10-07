import scala.collection.mutable.Map

/** A `Player` object represents a player character controlled by the real-life user
  * of the program.
  *
  * A player object’s state is mutable: the player’s location and possessions can change,
  * for instance.
  *
  * @param startingArea  the player’s initial location */
class Player(startingArea: Area) extends Person():

  // private variables tracking the state of the player
  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var inventoryContents: Map[String, Item] = Map[String, Item]("lockpick" -> Item("lockpick", LockpickDescription, 0, 0))
  private var facing = "north"
  private var inventoryValue = 0
  private var inventoryWeight = 0
  private var carryingCapacity = PlayerCarryingCapacity

  def accessibleAreas      = Vector(currentLocation,
                                      currentLocation.neighbor("north").getOrElse(currentLocation),
                                      currentLocation.neighbor("east").getOrElse(currentLocation),
                                      currentLocation.neighbor("south").getOrElse(currentLocation),
                                      currentLocation.neighbor("west").getOrElse(currentLocation))
  def accessibleAreasNames = Vector(currentLocation.name,
                                      currentLocation.neighbor("north").getOrElse(currentLocation).name,
                                      currentLocation.neighbor("east").getOrElse(currentLocation).name,
                                      currentLocation.neighbor("south").getOrElse(currentLocation).name,
                                      currentLocation.neighbor("west").getOrElse(currentLocation).name)

  /** Enables interacting with items via a single correctly written word by returning the full name
    * of an item whose name contains one or more of the given word(s) at the given location
    * @param itemName full, partial or misspelled name of the item
    * @param inventory inventory against which itemName is compared */
  private def matchItem(itemName: String, inventory: Map[String, Item]): String =
    val keywords = itemName.split(' ')
    val keywordMatch =
      for keyword <- keywords yield
        inventory.keys.toVector.map(_.contains(keyword))
    val indexOfMatch = keywordMatch.toVector.filter(_.contains(true)).flatten.indexOf(true)
    if indexOfMatch >= 0 then
      inventory.toVector(indexOfMatch)._1
    else
      itemName
  end matchItem


  /** Returns the full description of the area in front of the player */
  def surroundingsDescription = currentLocation.fullDescription(this.facing)

  /** Returns the value of the player's inventory */
  def score = s"${this.inventoryValue} €"

  /** Returns the fraction of carrying capacity currently in use */
  def carrying = s"${this.inventoryWeight}/${carryingCapacity} kg"


  /** Removes an item from the location the player is currently standing on or looking at if it fits in
    * the player's remaining carrying capacity. Also updates the score and weight carried.
    * @param itemName name of the item to be picked up */
  def get(itemName: String): String =
    val areaInFront = this.location.neighbor(this.facing).getOrElse(this.currentLocation)

      if this.currentLocation.containedItems.contains(matchItem(itemName, location.containedItems)) && this.currentLocation.open then // get item from current location

        this.currentLocation.removeItem(matchItem(itemName, location.containedItems)) match
          case Some(item) =>
            if inventoryWeight + item.weight <= carryingCapacity then
              inventoryContents += item.name -> item
              this.inventoryWeight += item.weight
              this.inventoryValue += item.value
              if item.weight >= 80 then
                s"${this.sound().replace(".", "")} while picking up the ${item.name}."
              else
                s"You pick up the ${item.name}."
            else
              this.currentLocation.addItem(item)
              s"You don't have the strength to pick up ${item.name} with everything you're curently carrying."
          case None =>
            s"There is no ${matchItem(itemName, location.containedItems)} here to pick up"

      else                                                                                               // get item from the location player is facing

        areaInFront.removeItem(matchItem(itemName, areaInFront.containedItems)) match
          case Some(item) =>
            if inventoryWeight + item.weight <= carryingCapacity then
              inventoryContents += item.name -> item
              this.inventoryWeight += item.weight
              this.inventoryValue += item.value
              if item.weight >= 80 then
                s"${this.sound().replace(".", "")} while picking up the ${item.name}"
              else
                s"You pick up the ${item.name}."
            else
              this.currentLocation.addItem(item)
              s"You don't have the strength to pick up ${item.name} with everything you're currently carrying."
          case None =>
            s"There is no ${matchItem(itemName, areaInFront.containedItems)} here to pick up"

  end get

  /** Removes the item indicated from the player's inventory and places it in the
    * current location while updating the player's state correspondingly.
    * @param itemName name of the item to be dropped */
  def drop(itemName: String): String =
    def dropAction(nameOfItem: String) =
      inventoryContents.get(matchItem(nameOfItem, this.inventoryContents)) match
        case Some(item) =>
          inventoryContents -= item.name
          currentLocation.addItem(item)
          this.inventoryValue -= item.value
          this.inventoryWeight -= item.weight
          s"You drop the ${item.name}."
        case None =>
          "You don't have that!"

    if itemName == "everything" || itemName == "all" then
      inventoryContents.foreach(x => dropAction(x._1))
      "You emptied your inventory."
    else
      dropAction(itemName)
  end drop

  /** Returns the description of a specified item in the player's inventory.
    * @param itemName name of the item to be examined */
  def examine(itemName: String): String =
    inventoryContents.get(matchItem(itemName, this.inventoryContents)) match
      case Some(item) =>
        s"The ${matchItem(itemName, this.inventoryContents)} ${item.description.replace("+", "")}"
      case None =>
        "Either pick up the item you want to examine or try inspecting the room or your surroundings instead."

  /** Returns all the items' descriptions that are located in the specified
    * area given it is either the current location or one of its neighbors.
    * @param areaName name of the area to be inspected */
  def inspect(areaName: String): String =

    if currentLocation.name == areaName && currentLocation.containedItems.nonEmpty then
      "Items available: \n" + currentLocation.containedItems.map( (name, item) => s"$name: ${item.description.dropWhile(_ != '+').tail}" ).mkString("\n").replace("(", "").replace(")", "")
    else if currentLocation.name == areaName then
      "There are no items available here."
    else if accessibleAreasNames.contains(areaName) && accessibleAreas(accessibleAreasNames.indexOf(areaName)).containedItems.nonEmpty then
      val areaInspected = accessibleAreas(accessibleAreasNames.indexOf(areaName))
      this.facing = currentLocation.accessibleNeighbors.map(_.swap)(areaInspected)
      "Items available: \n" + areaInspected.containedItems.map( (name, item) => s"$name: ${item.description.dropWhile(_ != '+').tail}" ).mkString("\n").replace("(", "").replace(")", "")
    else if !accessibleAreasNames.contains(areaName) then
      "You can't inspect this here."
    else
      val areaInspected = accessibleAreas(accessibleAreasNames.indexOf(areaName))
      this.facing = currentLocation.accessibleNeighbors.map(_.swap)(areaInspected)
      "There are no items available here."
  end inspect


  /** Use an item; executes the item's predetermined function on the area
    * in front of the player given that the player holds said item.
    * @param itemName the item to be used */
  def use(itemName: String) =
    inventoryContents.get(matchItem(itemName, inventoryContents)) match
      case Some(item) =>
        item.executeFunction(currentLocation.neighbor(this.facing).getOrElse(currentLocation))
      case None =>
        "You don't have such an item."

  /** Displays the contents of the player's inventory */
  def inventory: String =
    if inventoryContents.nonEmpty then
      s"You are carrying:\n" + inventoryContents.map( x => (x._2.name, " [value: " + x._2.value, " | weight: " + x._2.weight + "]") ).mkString("\n").replace("(", "").replace(")", "").replace(",", "") + "\n\n" + score + ", " + carrying
    else
      "You are empty-handed."

  /** Returns 'true' if the player holds a given item; false if not
    * @param itemName name of the item of interest */
  def has(itemName: String): Boolean =
    inventoryContents.contains(itemName)

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the player’s current location. */
  def location = this.currentLocation

  /** Looks in the given direction; returns the description of the area
    * neighboring the player's current location in the given direction
    * @param direction the direction looked at */
  def look(direction: String): String =
    val absoluteDirection = toAbsoluteDirection(direction, this.facing)
    val areaLookedAt = this.location.neighbor(absoluteDirection).getOrElse(this.currentLocation)
    val areaDescription = areaLookedAt.fullDescription(absoluteDirection).takeWhile(_ != '\n').replace(areaLookedAt.description.takeWhile(_ != '+'), "")

    this.facing = absoluteDirection

    if areaLookedAt.unlockable && !areaLookedAt.passable then
      "You see" + areaDescription.takeWhile(_ != '/') // locked obstacle area description
    else if areaLookedAt.unlockable && areaLookedAt.passable then
      "You see" + areaDescription.dropWhile(_ != '/').tail // unlocked obstacle area description
    else if areaLookedAt.people.nonEmpty && areaLookedAt.name.contains("bedroom") then
      "You see " + areaLookedAt.people.map(_.description).mkString(" and ") + " sleeping in" + areaDescription
    else if areaLookedAt.people.nonEmpty then
      "You see " + areaLookedAt.people.map(_.description).mkString(" and ") + " in" + areaDescription
    else
      "You see" + areaDescription // stayable area description
  end look

  /** Attempts to move the player in the given direction. This is successful if there is a passable
    * and stayable location from the player’s current location towards the given direction.
    * Returns a description of the result: "You go DIRECTION." or "You can't go DIRECTION because REASON."
    * @param direction the direction towards which the player attempts to move */
  def go(direction: String): String =

    val absoluteDirection = toAbsoluteDirection(direction, this.facing)
    val destination = this.location.neighbor(absoluteDirection)

    this.currentLocation.removePerson(this)

    if destination.isDefined && destination.forall(_.passable) then

      if destination.forall(_.stayableLocation) then                        // go to destination
        this.currentLocation = destination.getOrElse(this.currentLocation)
        this.facing = absoluteDirection
        this.currentLocation.addPerson(this)
        "You go " + direction + "."
      else                                                                  // go through the obstacle to the destination beyond it
        this.currentLocation = destination match
          case Some(loc) => loc.neighbor(absoluteDirection).getOrElse(this.currentLocation)
          case None => this.currentLocation
        this.facing = absoluteDirection
        "You go " + direction + "."

    else if destination.isDefined && destination.forall(_.unlockable) && destination.forall(!_.passable) then

      this.facing = absoluteDirection
      "You can't go " + direction + " because there is " + destination.getOrElse(this.currentLocation).description.dropWhile(_ != '+').tail.takeWhile(_ != '/').toLowerCase

    else if destination.isDefined && destination.forall(!_.unlockable) && destination.forall(!_.passable) then

      this.facing = absoluteDirection
      "You can't go " + direction + " because there is" + destination.getOrElse(this.currentLocation).fullDescription(this.facing).takeWhile(_ != '\n')

    else
      this.facing = absoluteDirection
      "You can't go " + direction + "."

  end go

  def sound() =
    "You made noise."

  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest(): String =
    "You pause, hearing your own heart beat in the silence."


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit(): String =
    this.quitCommandGiven = true
    ""

  def help: String =
    Command + "\n\n" + Look.takeWhile(_ != '-').dropRight(0) + "  |  " + Inspect.takeWhile(_ != '-').dropRight(0) + "  |  " + Go.takeWhile(_ != '-').dropRight(0) + "  |  " + Inventory.takeWhile(_ != '-').dropRight(0) + "\n" + Get.takeWhile(_ != '-').dropRight(0) + "        |  " + Drop.takeWhile(_ != '-').dropRight(0) + "     |  " + Examine.takeWhile(_ != '-').dropRight(0) + "  |  " + Use.takeWhile(_ != '-').dropRight(0) + "\n" + Sound.takeWhile(_ != '-').dropRight(0) + "           |  " + Rest.takeWhile(_ != '-').dropRight(0) + "          |  " + Quit.takeWhile(_ != '-').dropRight(0) + "          |  " + Help.takeWhile(_ != '-').dropRight(0)

  def command(command: String) =
    command match
      case "command"   => CommandInCommand
      case "look"      => Look
      case "inspect"   => Inspect
      case "go"        => Go
      case "inventory" => Inventory
      case "get"       => Get
      case "drop"      => Drop
      case "examine"   => Examine
      case "use"       => Use
      case "sound"     => Sound
      case "rest"      => Rest
      case "quit"      => Quit
      case "help"      => Help


  /** Returns a brief description of the player’s state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

end Player

