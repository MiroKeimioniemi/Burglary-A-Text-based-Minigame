// Constants
val DaughterToKitchenTiming = 10
val InspectionLength        = 26 // = length of inspect house action (hard coded because otherwise weird trouble arises and I am already beyond the original deadline)
val TimeLimit               = 400
val ValueThresholdToWin     = 180000
val PlayerCarryingCapacity  = 100

// Area descriptions
// '+' differentiates between descriptions for 'go' and 'look' commands
// '~' text removable when said item is removed
val HideoutDescription               = "You are standing in+an undisclosed location, far away from the mansion."
val StreetDescription                = "You are standing on+a street opposite to a grand, highly decorated mansion."
val FrontDoorDescription             = "You are standing in front of+the dark wooden front door of a grand, highly decorated mansion."
val PathToPorchDescription           = "You are standing on+a cross tiled path~, laid with pebbles,~ surrounded with roses on the side of the mansion that connects the porch and the front door."
val PorchDescription                 = "You are standing on+a sizeable porch enclosed from the garden by an intricately carved wooden fence~ that has small angel statues standing on top of it~."
val PathToEmergencyLadderDescription = "You are standing on+a well-maintained gravel path around the house,~ laid with stones,~ connecting the front door and the emergency ladder to a second floor window."
val EmergencyLadderDescription       = "You are standing under+the emergency ladders that climb up to a second floor window."
val DownstairsHallwayDescription     = "You are standing in+a tall and wide, marbled hallway~ covered with a long, light brown persian rug~."
val ChandelierHallwayDescription     = "You are standing in+a tall and wide, marbled hallway~ under a tall, ornamented waterfall chandelier made of thousands of individually hanging crystals~."
val KitchenDescription               = "You are standing in+a bright modern kitchen with marble tabletops and dark wood accented cabinetry."
val DownstairsBathroomDescription    = "You are standing in+a spacious bathroom with a black and white tiled floor and~ emerald green walls~."
val DiningHallDescription            = "You are standing in+a large but cozy dining hall with a long table set for 16 people~ with gilded silverware~."
val LivingRoomDescription            = "You are standing in+a warmly toned, wood-paneled living room blending together old and modern architecture~ with a grand piano in the corner~."
val OfficeDescription                = "You are standing in+a dark, modern office with glinting, black surfaces~ overshadowed by a standing stuffed grizzly bear~."
val StairsDescription                = "You are standing in+a modern spiral staircase with marble steps and a black steel rail."
val UpstairsHallwayDescription       = "You are standing in+a softly lit, wood paneled hallway with a skylight running across its entire length."
val UpstairsBathroomDescription      = "You are standing in+a large white bathroom with~ a few Donald Duck magazines laying on~ a fluffy carpet leading from the door to the sink."
val Bedroom1Description              = "You are standing in+a small, colorfully painted bedroom~ littered with toys~."
val Bedroom2Description              = "You are standing in+a comfortably sized bedroom with muted grey walls, except for a deep blue accent wall perpendicular to the bed, from where light breathing can be heard.~ In the corner, there is a black backpack full of electronics.~"
val MasterBedroomDescription         = "You are standing in+a large, warmly lit master bedroom with a large arched window where you can hear light snoring coming from the bed."
val WalkinClosetDescription          = "You are standing in+a sizeable walk-in closet with brown cabinetry on all four walls~ and a clothing rack in the middle filled with business casual clothes~."
val SafeDescription                  = "You are standing in+a small, grey, steel-reinforced room~ containing 43 gold bars~."

// Obstacle descriptions
// '+' differentiates between descriptions for 'go' and 'look' commands
// '/' differentiates between locked (left) and unlocked (right) descriptions
val FrontDoorObstacleDescription = "+a locked door./ an unlocked door."
val BackDoorObstacleDescription  = "+a locked door./ an unlocked door."
val WindowObstacleDescription    = "+a locked window. / an unlocked window."
val CoatrackWallDescription      = "+a white wall with a coatrack~, on which hangs a few trench coats~."
val PaintingWallDescription      = "+a dark, wood paneled wall~ with an ever-so-slightly tilted painting depicting a man digging up a treasure chest. It seems like authentic work, but it has a bit of wear and tear in the bottom right corner~."
val MirrorAndSinkDescription     = "+a large, clean mirror under which is a modern sink with an incomprehensible user interface, under which is a cabinet~ that contains toiletries~."
val NightstandDescription        = "+a minimalistic kingsized bed with floating nightstands on which there lies keys."
val CabinetsDescription          = "+dark, well organized, locked cabinets with glass doors~, beyond which exquisite collections of jewelry are laid out on display~."
var GlassCupboardDescription     = "+an antique glass cupboard~ that holds a detailed, historical miniatue model pirate ship within~."
var FridgeDescription            = "+a fridge with children's doodles hanging from its door~, holding a small wine collection within~."
val FireplaceDescription         = "+a modern fireplace with brick elements~ with a golden antique clock on its sheld~."
val MuralWallDescription         = "+a wall with a vibrant mural artwork blending impressionism, photorealism and abstract art in one seamless transition."
val SafeDoorDescription          = "+an intimidating looking, thick steel door to a walk-in safe with a keyhole."
val BookcaseDescription          = "+a dark wood bookcase spanning the entire length of the office~, full of Englis literature classics, ranging from Shakespeare to Blake and Orwell to Tolkien~."
val DeskDescription              = "+a large, dark mahogany desk against a lumionous window~ with tidy piles of documents in its corner~."

// Resident descriptions
val DadDescription = "a tall man with an intimidating build in shorts and a sleeveless shirt"
val MomDescription = "a tall, blond woman in a beige nightgown"
val OlderDaughterDescription = "a brown-haired girl in shorts and a top"
val YoungerDaughterDescription = "a short, messy-haired girl with a wrinkly, white nightgown"

// Item descriptions
val LockpickDescription = "Can be used to open locked doors"

val AngelStatuesDescription      = "are +made of stone and appear to be covering their eyes."
val PersianRugDescription        = "is +made of silky smooth wool of varying colors woven into intricate tribal patterns."
val HallwayChandelierDescription = "is +undoubtedly very expensive, potentially brittle and very noisy to move."
val EmeraldTilesDescription      = "are +cut with precision into smooth, shiny rectangles."
val SilverwareDescription        = "is +plain but shiny and most likely very expensive."
val GrandPianoDescription        = "is +a magnificent, unique piece of perfect craftwork with ivory and ebony keys."
val BearDescription              = "is +huge and angry-looking with exposed teeth but soft and furry fur."
val DonaldDuckDescription        = "happen to be +Don Rosa special editions featuring The Lost Charts of Columbus story."
val ToysDescription              = "consist of +various dolls, doll clothes, miniature furniture and toy cars, all in pristine condition."
val BackpackDescription          = "is +a black backpack with a laptop, tablet, phone and wallet inside."
val ClothingRackDescription      = "is +full of designer clothes, half of them carefully hanged on it and the other half just tossed on top."
val CoatsDescription             = "are +made of premium wool and custom fitted for each resident."
val TreasurePaintingDescription  = "is +an authentic seeming work depicting a man digging up a treasure chest that has a bit of wear and tear in the bottom right corner."
val ToiletriesDescription        = "consist of +soaps, shampoos, toothpastes and Icelandic skincare products."
val KeysDescription              = "are +sturdy and shiny with a keychain made of bear leather."
val StoneDescription             = "is +grey and hard. Certainly capable of breking glass."
val JewelryDescription           = "consists of +various delicate gold and silver necklaces, rings, bracelets and earrings embedded with diamonds of varying sizes and colors."
val MiniatureShipDescription     = "is +a detailed, historical miniature moder ship of exceptional craftsmanship with a raised pirate flag."
val WineDescription              = "is +vintage wine directly from Italy."
val AntiqueClockDescription      = "is +a golden antique clock carved into the shape of a bear."
val GoldBarsDescription          = "are +43 shiny 1 kg pure 24 karat gold bars."
val ClassicsDescription          = "are +full of Englis literature classics, ranging from Shakespeare to Blake and Orwell to Tolkien."
val DocumentsDescription         = "reveal +the family's most valuable items: \n2400000€ in gold in the safe vault\n160000€ in jewelry in the walk-in closet of the master bedroom\n50000€ grand piano in the living room\n36000€ chandelier in the downstairs hallway\n26000€ stuffed grizzly bear in the office\n17000€ miniature model pirate ship in the hallway"

// Dialogue
// # will be replaced by a location
val ReactionToSound        = """"What was that?""""
val YouOK                  = """"Are you okay?""""
val ReactionToAngels       = "Holy shit, I knew the angels would move! I better not blink..."
val ReactionToBear         = "AAAAAAHHHH!!! Whew, it was only the stuffed grizzly. But why is it here...?"
val ReactionToMissingItems = "Strange... I feel like something is missing..."
val ReactionToPiano        = "Am I dreaming or is the piano really in the #? I better check again in the morning to make sure..."

// Info text
// # and * will be replaced by respective numbers
val WelcomeText             = s"You are a desperate burglar buried in debt. Your goal is to steal a minimum of $ValueThresholdToWin € worth of items from a mansion during nighttime without getting caught by the residents in order to pay off the debt. The more you score on top, the better.\n\nYou can only carry so much at once, but anything you manage to deposit on the street before escaping to your hideout will be counted towards your score. Lifting heavy items may produce noise, waking up the residents or alerting them depending on how close you are. Residents can see into the room directly in front of them. Don't get caught. Good luck!"
val CompletedSuccessfully   = "You escaped to your hideout with # worth of items! After paying of the the debts, * remains. You get to continue living on comfortably!"
val CompletedUnsuccessfully = "You escaped to your hideout with # worth of items. Unfortunately, the loan sharks won't be happy with * missing. You might have been better off choosing prison."
val TimeIsUp                = "You got caught due to staying in the house until morning!"
val GotCaught               = "You got caught!"

// Commands
val Command   = s"Type ${'"'}command COMMAND${'"'} for more detailed information about a specific command:"
val Look      = "look DIRECTION - gives the description of the area (and potential people) neighboring the player's current location in the given direction."
val Inspect   = "inspect AREA - reveals all items and their descriptions that are located in the specified location given that it is either the current location or one of its neighbors."
val Go        = "go DIRECTION - attempt to move to a neighboring area to the direction specified."
val Inventory = "inventory - shows the items and you are currently holding along with their values and weights."
val Get       = "get ITEM - attempt to add the item into your inventory from the area you are currently standing in or facing. For items with names containing multiple words, using only one is enough for the command."
val Drop      = s"drop ITEM - drop an item from your inventory. For items with names containing multiple words, using only one is enough for the command. Type ${'"'}drop all${'"'} or ${'"'}drop everything${'"'} to empty your inventory on to the area you are standing on."
val Examine   = "examine ITEM - gives the description of an item in your inventory. For items with names containing multiple words, using only one is enough for the command."
val Use       = "use ITEM - execute the predetermined function of an item on the area in front. For items with names containing multiple words, using only one is enough for the command."
val Sound     = "sound - make noise."
val Rest      = "rest - do nothing for one turn."
val Quit      = "quit - quit the game."
val Help      = "help - get a list of all available commands."

val CommandInCommand = "Ha, funny.\n\nTry look, inspect, go, inventory, get, drop, examine, use, sound, rest, quit or help instead"


// Keywords
val Forward = "forward"
val Left    = "left"
val Back    = "back"
val Right   = "right"