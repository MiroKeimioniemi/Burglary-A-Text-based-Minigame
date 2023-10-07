/** The class `Adventure` represents text games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of “hard-coded” information that pertains to a very
  * specific game that involves robbing a mansion. All newly created instances of class
  * `Adventure` are identical to each other. */
class Adventure:

  /** The name of the game */
  val title = "Undercover"

  /** Game areas */
  private val street                 = Area("street",                   StreetDescription)
  private val frontDoor              = Area("front door",               FrontDoorDescription)
  private val pathToPorch            = Area("path to porch",            PathToPorchDescription)
  private val hideout                = Area("hideout",                  HideoutDescription)
  private val porch                  = Area("porch",                    PorchDescription)
  private val pathToEmergencyLadder1 = Area("path to emergency ladder", PathToEmergencyLadderDescription)
  private val pathToEmergencyLadder2 = Area("path to emergency ladder", PathToEmergencyLadderDescription)
  private val emergencyLadder        = Area("emergency ladders",        EmergencyLadderDescription)
  private val hallway1               = Area("hallway",                  DownstairsHallwayDescription)
  private val chandelierHallway      = Area("hallway",                  ChandelierHallwayDescription)
  private val hallway3               = Area("hallway",                  DownstairsHallwayDescription)
  private val hallway4               = Area("hallway",                  DownstairsHallwayDescription)
  private val kitchen                = Area("kitchen",                  KitchenDescription)
  private val downstairsBathroom     = Area("bathroom",                 DownstairsBathroomDescription)
  private val diningHall             = Area("dining hall",              DiningHallDescription)
  private val livingRoom             = Area("living room",              LivingRoomDescription)
  private val office                 = Area("office",                   OfficeDescription)
  private val stairs                 = Area("stairs",                   StairsDescription)
  private val hallway5               = Area("hallway",                  UpstairsHallwayDescription)
  private val hallway6               = Area("hallway",                  UpstairsHallwayDescription)
  private val hallway7               = Area("hallway",                  UpstairsHallwayDescription)
  private val upstairsBathroom       = Area("bathroom",                 UpstairsBathroomDescription)
  private val bedroom1               = Area("bedroom",                  Bedroom1Description)
  private val bedroom2               = Area("bedroom",                  Bedroom2Description)
  private val masterBedroom          = Area("master bedroom",           MasterBedroomDescription)
  private val walkinCloset           = Area("walk-in closet",           WalkinClosetDescription)
  private val safe                   = Area("safe",                     SafeDescription)

  /** Game obstacles */
  private val frontDoorObstacle = Area("front door",                    FrontDoorObstacleDescription, false, false, true)
  private val backDoorObstacle  = Area("back door",                     BackDoorObstacleDescription,  false, false, true)
  private val windowObstacle    = Area("window",                        WindowObstacleDescription,    false, false, true)
  private val coatrackWall      = Area("wall with coatrack",            CoatrackWallDescription,      false, false, false)
  private val paintingWall      = Area("wall with painting",            PaintingWallDescription,      false, false, false)
  private val bathroomMirror    = Area("mirror and sink",               MirrorAndSinkDescription,     false, false, false)
  private val masterNightstand  = Area("nightstands",                   NightstandDescription,        false, false, false)
  private val cabinets          = Area("glass cabinets",                CabinetsDescription,          false, false, false, false)
  private val glassCupboard     = Area("glass cupboard",                GlassCupboardDescription,     false, false, false, false)
  private val fridge            = Area("fridge",                        FridgeDescription,            false, false, false)
  private val fireplace         = Area("fireplace",                     FireplaceDescription,         false, false, false)
  private val muralWall         = Area("mural wall",                    MuralWallDescription,         false, false, false)
  private val safeDoor          = Area("safe door",                     SafeDoorDescription,          false, false, true, false)
  private val bookcase          = Area("bookcase",                      BookcaseDescription,          false, false, false)
  private val officeDesk        = Area("desk",                          DeskDescription,              false, false, false)

  /** Game area relationships */
  street                .setNeighbors(Vector("north" -> frontDoor,              "east" -> pathToEmergencyLadder1, "south" -> hideout,                "west" -> pathToPorch))
  frontDoor             .setNeighbors(Vector("north" -> frontDoorObstacle,      "east" -> street,                 "south" -> street,                 "west" -> street))
  pathToPorch           .setNeighbors(Vector("north" -> porch,                  "east" -> street,                 "south" -> street,                 "west" -> street))
  porch                 .setNeighbors(Vector("north" -> street,                 "east" -> backDoorObstacle,       "south" -> pathToPorch,            "west" -> street))
  emergencyLadder       .setNeighbors(Vector("north" -> street,                 "east" -> pathToEmergencyLadder2, "south" -> windowObstacle,         "west" -> street))
  pathToEmergencyLadder1.setNeighbors(Vector("north" -> pathToEmergencyLadder2, "east" -> street,                 "south" -> street,                 "west" -> street))
  pathToEmergencyLadder2.setNeighbors(Vector("north" -> street,                 "east" -> street,                 "south" -> pathToEmergencyLadder1, "west" -> emergencyLadder))
  hallway1              .setNeighbors(Vector("north" -> chandelierHallway,      "east" -> paintingWall,           "south" -> frontDoor,              "west" -> coatrackWall))
  chandelierHallway     .setNeighbors(Vector("north" -> hallway3,               "east" -> downstairsBathroom,     "south" -> hallway1,               "west" -> kitchen))
  hallway3              .setNeighbors(Vector("north" -> hallway4,               "east" -> glassCupboard,          "south" -> chandelierHallway,      "west" -> diningHall))
  hallway4              .setNeighbors(Vector("north" -> stairs,                 "east" -> office,                 "south" -> hallway3,               "west" -> livingRoom))
  kitchen               .setNeighbors(Vector("north" -> diningHall,             "east" -> chandelierHallway,      "south" -> fridge))
  downstairsBathroom    .setNeighbors(Vector(                                   "east" -> bathroomMirror,                                            "west" -> chandelierHallway))
  diningHall            .setNeighbors(Vector("north" -> livingRoom,             "east" -> hallway3,               "south" -> kitchen))
  livingRoom            .setNeighbors(Vector("north" -> fireplace,              "east" -> hallway4,               "south" -> diningHall,             "west" -> backDoorObstacle))
  office                .setNeighbors(Vector("north" -> safeDoor,               "east" -> bookcase,               "south" -> officeDesk,             "west" -> hallway4))
  stairs                .setNeighbors(Vector("north" -> hallway5,                                                 "south" -> hallway4,               "west" -> muralWall))
  hallway5              .setNeighbors(Vector("north" -> hallway6,               "east" -> upstairsBathroom,       "south" -> stairs))
  hallway6              .setNeighbors(Vector("north" -> hallway7,                                                 "south" -> hallway5,               "west" -> bedroom1))
  hallway7              .setNeighbors(Vector("north" -> windowObstacle,         "east" -> masterBedroom,          "south" -> hallway6,               "west" -> bedroom2))
  upstairsBathroom      .setNeighbors(Vector(                                   "east" -> bathroomMirror,                                            "west" -> hallway5))
  bedroom1              .setNeighbors(Vector(                                   "east" -> hallway6))
  bedroom2              .setNeighbors(Vector(                                   "east" -> hallway7))
  masterBedroom         .setNeighbors(Vector(                                   "east" -> masterNightstand,       "south" -> walkinCloset,           "west" -> hallway7))
  walkinCloset          .setNeighbors(Vector("north" -> masterBedroom,                                            "south" -> cabinets))
  safe                  .setNeighbors(Vector(                                                                     "south" -> safeDoor))

  frontDoorObstacle.setNeighbors(Vector("north" -> hallway1,        "south" -> frontDoor))
  backDoorObstacle .setNeighbors(Vector("east" -> livingRoom,       "west" -> porch))
  windowObstacle   .setNeighbors(Vector("north" -> emergencyLadder, "south" -> hallway7))
  safeDoor         .setNeighbors(Vector("north" -> safe,            "south" -> office))

  // items
  porch                 .addItem(Item("angel statues",                AngelStatuesDescription,      2000,    10))
  hallway1              .addItem(Item("persian rug 1",                PersianRugDescription,        1000,    8))
  chandelierHallway     .addItem(Item("chandelier",                   HallwayChandelierDescription, 36000,   90))
  hallway3              .addItem(Item("persian rug 2",                PersianRugDescription,        2000,    8))
  hallway4              .addItem(Item("persian rug 3",                PersianRugDescription,        4000,    8))
  downstairsBathroom    .addItem(Item("emerald tiles",                EmeraldTilesDescription,      40000,   80))
  diningHall            .addItem(Item("silverware",                   SilverwareDescription,        9800,    10))
  livingRoom            .addItem(Item("grand piano",                  GrandPianoDescription,        50000,   100))
  office                .addItem(Item("stuffed grizzly bear",         BearDescription,              26000,   80))
  upstairsBathroom      .addItem(Item("donald duck magazines",        DonaldDuckDescription,        12,      1))
  bedroom1              .addItem(Item("toys",                         ToysDescription,              180,     5))
  bedroom2              .addItem(Item("backpack full of electronics", BackpackDescription,          5500,    7))
  walkinCloset          .addItem(Item("clothing rack",                ClothingRackDescription,      6000,    50))
  coatrackWall          .addItem(Item("trench coats",                 CoatsDescription,             1400,    7))
  paintingWall          .addItem(Item("treasure painting",            TreasurePaintingDescription,  8000,    16))
  bathroomMirror        .addItem(Item("toiletries",                   ToiletriesDescription,        400,     2))
  masterNightstand      .addItem(Item("keys",                         KeysDescription,              0,       1))
  pathToPorch           .addItem(Item("pebble",                       StoneDescription,             0,       3))
  pathToEmergencyLadder1.addItem(Item("stone",                        StoneDescription,             0,       3))
  pathToEmergencyLadder2.addItem(Item("rock",                         StoneDescription,             0,       3))
  cabinets              .addItem(Item("jewelry",                      JewelryDescription,           160000,  6))
  glassCupboard         .addItem(Item("miniature model pirate ship",  MiniatureShipDescription,     17000,   40))
  fridge                .addItem(Item("wine",                         WineDescription,              3400,    11))
  fireplace             .addItem(Item("golden antique clock",         AntiqueClockDescription,      8500,    14))
  safe                  .addItem(Item("gold bars",                    GoldBarsDescription,          2400000, 43))
  bookcase              .addItem(Item("english literature classics",  ClassicsDescription,          1000,    35))
  officeDesk            .addItem(Item("accounting documents",         DocumentsDescription,         0,       1))


  /** The character that the player controls in the game. */
  val player = Player(street)

  /** Other game characters */
  val dad             = Resident(masterBedroom, DadDescription)
  val mom             = Resident(masterBedroom, MomDescription)
  val olderDaughter   = Resident(bedroom2,      OlderDaughterDescription)
  val youngerDaughter = Resident(bedroom1,      YoungerDaughterDescription)

  /** Initial resident locations */
  masterBedroom.addPerson(mom)
  masterBedroom.addPerson(dad)
  bedroom2.addPerson(olderDaughter)
  bedroom1.addPerson(youngerDaughter)

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this undercover game allows before time runs out. */
  val timeLimit = TimeLimit
  /** Tracks the inspection step of the residents' dad's inspection round (Starts high so that it does not do anything initially) */
  var inspectionStep = 100
  /** Minimum score required to win */
  val minScore = ValueThresholdToWin
  /** Will result in a loss if true */
  private var tooMuchNoiseTooClose = false

  /** Player score */
  def playerScore = this.player.score.takeWhile(_ != ' ').toInt

  /** The value of the items placed on the street */
  def valueOnStreet =
    var totalValue = 0
    street.containedItems.values.map(_.value).foreach(x => totalValue = totalValue + x)
    totalValue

  def totalValueOfStolenItems =
    playerScore + valueOnStreet

  /** Determines whether the player has been caught or no */
  def caught = this.player.location == this.youngerDaughter.location || this.player.location == this.youngerDaughter.fieldOfView || this.player.location == this.dad.location || this.player.location == this.dad.fieldOfView

  /** Determines if the player has won. */
  def isComplete = totalValueOfStolenItems >= minScore && player.location == hideout // this.player.location == this.destination && this.player.has("remote") && this.player.has("battery")

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || caught || tooMuchNoiseTooClose || this.turnCount == this.timeLimit || player.location == hideout

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = WelcomeText

  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage =
    if this.isComplete then
      CompletedSuccessfully.replace("#", totalValueOfStolenItems.toString).replace("*", (totalValueOfStolenItems - minScore).toString + " €")
    else if playerScore < minScore && player.location == hideout then
      CompletedUnsuccessfully.replace("#", totalValueOfStolenItems.toString).replace("*", (minScore - totalValueOfStolenItems).toString + " €")
    else if this.turnCount == this.timeLimit then
      TimeIsUp
    else if caught || tooMuchNoiseTooClose then
      GotCaught
    else  // game over due to player quitting
      "Quitter!"


  /** Plays a turn by executing the given in-game command, such as “go west”. Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String): String =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined && !(command.contains("look") || command.contains("inventory") || command.contains("quit") || command.contains("help") || command.contains("command"))  then
      this.turnCount += 1
    inspectionStep += 1
    if outcomeReport.getOrElse(" ").contains(player.sound().replace(".", "")) && player.accessibleAreas.exists(_.people.exists(_.description != player.description)) then // gets the player caught if they make noise next to a resident
      tooMuchNoiseTooClose = true
    if outcomeReport.getOrElse(" ").contains(player.sound().replace(".", "")) && inspectionStep > InspectionLength then // triggers the dad's inspection round upon sound
      inspectionStep = 0
    outcomeReport.getOrElse(s"Unknown command: \"$command\".") + "\n" + youngerDaughter.goToKitchen(turnCount) + youngerDaughter.reactToItem + dad.inspectHouse(inspectionStep) + dad.reactToItem

end Adventure

