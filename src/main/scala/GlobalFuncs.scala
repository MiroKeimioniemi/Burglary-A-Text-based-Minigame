import scala.collection.mutable.Map

/** Converts relative directions (forward, right, back, left) to absolute directions
  * (north, east, south, west) based on the orientation of the player 
  * @param relativeDirection the direction relative to the player's current facing (forward, right, back, left)
  * @param facing the absolute direction the player is currently facing (north, east, south, west) */
def toAbsoluteDirection(relativeDirection: String, facing: String): String =
    def orientation: Map[String, String] = facing match
      case "north" => Map(Forward -> "north", Right -> "east", Back -> "south", Left -> "west")
      case "east"  => Map(Forward -> "east", Right -> "south", Back -> "west", Left -> "north")
      case "south" => Map(Forward -> "south", Right -> "west", Back -> "north", Left -> "east")
      case "west"  => Map(Forward -> "west", Right -> "north", Back -> "east", Left -> "south")
      case other   => Map(Forward -> "north", Right -> "east", Back -> "south", Left -> "west")
    if Vector(Forward, Left, Back, Right).contains(relativeDirection) then
      orientation(relativeDirection)
    else
      relativeDirection // if relative directions are not used, passes whatever was used for appropriate messages

/** Converts absolute directions (north, east, south, west) to relative directions
  * (north, east, south, west) based on the orientation of the player 
  * @param absoluteDirection the absolute direction (north, east, south, west) to be converted based on the player's orientation
  * @param facing the absolute direction the player is currently facing (north, east, south, west) */
def toRelativeDirection(absoluteDirection: String, facing: String): String =
    def orientation: Map[String, String] = facing match
      case "north" => Map("north" -> Forward, "east" -> Right, "south" -> Back, "west" -> Left)
      case "east"  => Map("east" -> Forward, "south" -> Right, "west" -> Back, "north" -> Left)
      case "south" => Map("south" -> Forward, "west" -> Right, "north" -> Back, "east" -> Left)
      case "west"  => Map("west" -> Forward, "north" -> Right, "east" -> Back, "south" -> Left)
      case other   => Map("north" -> Forward, "east" -> Right, "south" -> Back, "west" -> Left)
    orientation(absoluteDirection)
