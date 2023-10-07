class Resident(startingArea: Area, description: String) extends Person(description):
  private var currentLocation = startingArea
  private var facing = "north"

  def location = this.currentLocation

  def fieldOfView = this.currentLocation.neighbor(facing).getOrElse(currentLocation)

  def goToKitchen(turnCounter: Int) =
    val step = turnCounter % 60
    val previousLocation = this.currentLocation
    val routeToKitchen: LazyList[Area] = LazyList[Area](
      this.currentLocation,                                                 // bedroom1
      this.currentLocation.neighbor("east").getOrElse(currentLocation),     // hallway6
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway5
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // stairs
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway4
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway3
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway2
      this.currentLocation.neighbor("west").getOrElse(currentLocation),     // kitchen
      this.currentLocation,                                                 // kitchen
      this.currentLocation,                                                 // kitchen
      this.currentLocation,                                                 // kitchen
      this.currentLocation,                                                 // kitchen
      this.currentLocation.neighbor("east").getOrElse(currentLocation),     // hallway2
      this.currentLocation.neighbor("east").getOrElse(currentLocation),     // bathroom
      this.currentLocation,                                                 // bathroom
      this.currentLocation,                                                 // bathroom
      this.currentLocation.neighbor("west").getOrElse(currentLocation),     // hallway2
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // hallway3
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // hallway4
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // stairs
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // hallway5
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // hallway6
      this.currentLocation.neighbor("west").getOrElse(currentLocation))     // bedroom1

    if (step >= DaughterToKitchenTiming && (step - DaughterToKitchenTiming < routeToKitchen.length)) then
      previousLocation.removePerson(this)
      this.currentLocation = routeToKitchen(step - DaughterToKitchenTiming)
      this.currentLocation.addPerson(this)
      if previousLocation != this.currentLocation then
        this.facing = previousLocation.accessibleNeighbors.map(_.swap)(this.currentLocation)
      s"\nYou heard a noise from the ${this.currentLocation.name}."
    else
      ""
  end goToKitchen

  def inspectHouse(step: Int) =
    val previousLocation = this.currentLocation
    val action: LazyList[Area] = LazyList[Area](
      this.currentLocation,                                                 // masterBedroom
      this.currentLocation.neighbor("west").getOrElse(currentLocation),     // hallway7
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway6
      this.currentLocation.neighbor("west").getOrElse(currentLocation),     // bedroom1
      this.currentLocation.neighbor("east").getOrElse(currentLocation),     // hallway6
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway5
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // stairs
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway4
      this.currentLocation.neighbor("east").getOrElse(currentLocation),     // office
      this.currentLocation,                                                 // office
      this.currentLocation,                                                 // office
      this.currentLocation.neighbor("west").getOrElse(currentLocation),     // hallway4
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway3
      this.currentLocation.neighbor("south").getOrElse(currentLocation),    // hallway2
      this.currentLocation.neighbor("west").getOrElse(currentLocation),     // kitchen
      this.currentLocation,                                                 // kitchen
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // dining hall
      this.currentLocation,                                                 // dining hall
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // living room
      this.currentLocation,                                                 // living room
      this.currentLocation.neighbor("east").getOrElse(currentLocation),     // hallway4
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // stairs
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // hallway5
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // hallway6
      this.currentLocation.neighbor("north").getOrElse(currentLocation),    // hallway7
      this.currentLocation.neighbor("east").getOrElse(currentLocation))     // master bedroom

      if step < action.length then
        previousLocation.removePerson(this)
        this.currentLocation = action(step)
        this.currentLocation.addPerson(this)
        if previousLocation != this.currentLocation then
          this.facing = previousLocation.accessibleNeighbors.map(_.swap)(this.currentLocation)
        if step == 0 then
          s"\nYou heard $ReactionToSound from the ${this.currentLocation.name}."
        else if this.currentLocation.name == "bedroom" then
          s"\nYou heard $YouOK from the ${this.currentLocation.name}."

        else
          s"\nYou heard a noise from the ${this.currentLocation.name}."
      else
        ""
  end inspectHouse

  def reactToItem =
    if this.currentLocation.name != "porch" && ( this.currentLocation.contains("angels statues") || fieldOfView.contains("angel statues")) then
      s"\n${'"'}$ReactionToAngels${'"'}"
    else if this.currentLocation.name != "office" && this.currentLocation.contains("stuffed grizzly bear") then
      s"\n${'"'}$ReactionToBear${'"'}"
    else if this.currentLocation.name != "living room" && this.currentLocation.contains("grand piano") then
      s"\n${'"'}${ReactionToPiano.replace("#", this.currentLocation.name)}${'"'}"
    else if (this.currentLocation.name == "chandelierHallway" && !this.currentLocation.contains("chandelier")) || (this.currentLocation.name == "living room" && !this.currentLocation.contains("grand piano")) || (this.currentLocation.name == "office" && !this.currentLocation.contains("stuffed grizzly bear")) then
      s"\n${'"'}$ReactionToMissingItems${'"'}"
    else ""