import javax.swing.UIDefaults.LazyValue
import scala.collection.mutable.Map

/** The class `Area` represents locations in a text game world. A game world
  * consists of areas. In general, an “area” can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name             the name of the area
  * @param description      a basic description of the area (typically not including information about items)
  * @param passable         determines whether the player can pass into the area
  * @param stayableLocation determines whether the player can stay or only travel through the area  */
class Area(var name: String, var description: String, var passable: Boolean = true, var stayableLocation: Boolean = true, val unlockable: Boolean = false, var open: Boolean = true):

  private val neighbors = Map[String, Area]()
  private var items: Map[String, Item] = Map[String, Item]()
  private lazy val firstItem = if items.nonEmpty then Some(items.toVector(0)._2) else None
  var people = Vector[Person]()

  /** Adds a person to the `Area` object
    * @param person the person to be added */
  def addPerson(person: Person): Unit =
    if open then
      this.people = this.people :+ person

  /** Returns true if the `Area` object contains a specific person; returns false otherwise
    * @param person the name of the person of interest */
  def containsPerson(person: Person): Boolean =
    this.people.contains(person)

  /** Removes a specific person from the `Area` object
    * @param person the name of the person to be removed */
  def removePerson(person: Person): Option[Person] =
    if this.people.contains(person) then
      val droppedPerson = this.people.filter(_ == person)(0)
      this.people = this.people.filterNot(_ == droppedPerson)
      Some(droppedPerson)
    else
      None

  /** Adds an item to the `Area` object
    * @param item the item to be added */
  def addItem(item: Item): Unit =
    this.items = this.items += item.name -> item

  /** Returns true if the `Area` object contains a specific item; returns false otherwise
    * @param itemName the name of the item of interest */
  def contains(itemName: String): Boolean =
    this.items.contains(itemName)

  /** Removes a specific item from the `Area` object
    * @param itemName the name of the item to be removed */
  def removeItem(itemName: String): Option[Item] =
    if this.contains(itemName) && this.open then
      val droppedItem = this.items.get(itemName)
      this.items -= itemName
      droppedItem
    else
      None

  /** Unlocks a locked area so that it becomes passable */
  def unlock() =
    if unlockable then
      this.passable = true
    else
      false

  /** Locks an area so that it becomes unpassable  */
  def lock() =
    this.passable = false

  /** makes non-stayble areas' items available for taking */
  def openContainer() =
    this.open = true

  /** makes non-stayable areas' items unavailable for taking */
  def closeContainer() =
    this.open = false

  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String) = this.neighbors.get(direction)

  /** Returns a map of all neighbors accessible from the area */
  def accessibleNeighbors = this.neighbors

  /** Returns a map of all items contained by the area */
  def containedItems = this.items

  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction–area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits

  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. If there are no
    * items present, the return value has the form "DESCRIPTION\n\nExits available:
    * DIRECTIONS SEPARATED BY SPACES". If there are one or more items present, the return
    * value has the form "DESCRIPTION\nYou see here: ITEMS SEPARATED BY SPACES\n\nExits available:
    * DIRECTIONS SEPARATED BY SPACES". The items and directions are listed in an arbitrary order. */
  def fullDescription(playerFacing: String) =
    val neighborList = this.neighbors.values.map(_.name)
    val directionsList = this.neighbors.keys.map( x => toRelativeDirection(x, playerFacing))
    val neighborLocations = directionsList.zip(neighborList).toVector.sortBy(-_._1.length).mkString("\n").replace("(", "").replace(")", "").replace(",", ": ")
    if firstItem.isDefined && !this.items.contains(firstItem.get.name) then
      this.description.replace('+', ' ').slice(0, description.indexOf('~')) + this.description.slice(description.lastIndexOf('~') + 1, description.length).replace('+', ' ') + "\n\nSurroundings:\n" + neighborLocations
    else
      this.description.replace('+', ' ').replaceAll("~", "") + "\n\nSurroundings:\n" + neighborLocations


  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)

end Area

