# Burglary - A Text-based Minigame

## Description

You are a desperate burglar buried in debt. Your goal is to steal a minimum of 200 000 € worth of items from a mansion during night-time without getting caught by the residents in order to pay off the debt. The more you score on top, the better. You can only carry so much at once, but anything you manage to deposit on the street before escaping to your hideout will be counted towards your score. Lifting heavy items may produce noise, waking up the residents or alerting them depending on how close you are. Use what you have and what you find. Don't get caught. Good luck!

## Game Design

The game world consists of a mansion with a front door, a porch and a side door, emergency ladders, a hallway with a kitchen, a dining hall, a living room, a bathroom and an office. There is also an upper level with bedrooms and another bathroom. The layout and two different example playthroughs are provided in the ["Map diagrams and example walkthroughs" pdf file](https://github.com/MiroKeimioniemi/Burglary-A-Text-based-Minigame/blob/master/Map%20diagram%20and%20example%20walkthroughs.pdf).

The longer the player stays in the mansion, the higher the risk for a resident to go to the bathroom or to the kitchen for a nightly snack and therefore the higher the chance of getting caught.

The heavier the items the player attempts to carry, the higher the risk of making a noise, which can awaken someone to come check the source of the noise.

Available room information:

- General location and short label about what lies in each direction in the format:

    - Forward: hallway
    - Right: wall with painting
    - Behind: front door
    - Left: kitchen

- Detailed information about each direction:
  E.g. You see a dark, wood paneled wall with an ever-so-slightly tilted painting depicting a man digging up a treasure chest. It seems like authentic work, but it 
  has a bit of wear and tear in the bottom right corner.

Events section provides detailed visual descriptions, auditory descriptions and feedback to actions.

Available player actions:

- help - get a list of all available commands.
- look DIRECTION - gives the description of the area (and potential people) neighboring the player's current location in the given direction.
- get ITEM - attempt to add the item into your inventory from the area you are currently standing in or facing. For items with names containing multiple words, using only one is enough for the command.
- sound - make noise.
- inspect AREA - reveals all items and their descriptions that are located in the specified location given that it is either the current location or one of its neighbors.
- drop ITEM - drop an item from your inventory. For items with names containing multiple words, using only one is enough for the command. Type "drop all" or "drop everything" to empty your inventory on to the area you are standing on.
- rest - do nothing for one turn.
- go DIRECTION - attempt to move to a neighboring area to the direction specified.
- examine ITEM - gives the description of an item in your inventory. For items with names containing multiple words, using only one is enough for the command
- quit - quit the game.
- inventory - shows the items and you are currently holding along with their values and weights.
- use ITEM - execute the predetermined function of an item on the area in front. For items with names containing multiple words, using only one is enough for the command.
ITEMs have to be discerned based on room descriptions or discovered by inspecting an AREA, which takes a label (e.g. hallway) listed next to the available DIRECTIONs as an argument.

Replaying requires restarting the game by closing the window and relaunching the entire application.

Run the AdventureGUI class to play the game.
