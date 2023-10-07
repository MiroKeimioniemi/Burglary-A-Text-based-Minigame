/** This GUI launcher class was provided to students in the CS-A1110 - Programming 1 course by
 * Juha Sorva for the text-based game final project but has been slightly modified by the author. */

import java.awt.{Dimension, Insets, Point}
import javax.swing.UIManager
import scala.language.adhocExtensions
import scala.swing.*
import scala.swing.event.*

object AdventureGUI extends SimpleSwingApplication:
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)

  def top = new MainFrame:

    // Access to the application’s internal logic:

    val game = Adventure()
    val player = game.player

    // Components:

    val locationInfo = new TextArea(7, 80):
      editable = false
      wordWrap = true
      lineWrap = true
    val turnOutput = new TextArea(7, 80):
      editable = false
      wordWrap = true
      lineWrap = true
    val input = new TextField(40):
      minimumSize = preferredSize
    this.listenTo(input.keys)
    val turnCounter = Label()
    val score = Label() // Added
    val weight = Label() // Added

    // Events:

    this.reactions += {
      case keyEvent: KeyPressed =>
        if keyEvent.source == this.input && keyEvent.key == Key.Enter && !this.game.isOver then
          val command = this.input.text.trim
          if command.nonEmpty then
            this.input.text = ""
            this.playTurn(command)
    }

    // Layout:

    this.contents = new GridBagPanel:
      import scala.swing.GridBagPanel.Anchor.*
      import scala.swing.GridBagPanel.Fill
      layout += Label("Location:") -> Constraints(0, 0, 1, 1, 0, 1, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += Label("Command:")  -> Constraints(0, 1, 1, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += Label("Events:")   -> Constraints(0, 2, 1, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += turnCounter        -> Constraints(0, 3, 2, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += score              -> Constraints(0, 4, 2, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0) // Added
      layout += weight             -> Constraints(0, 5, 2, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0) // Added
      layout += locationInfo       -> Constraints(1, 0, 1, 1, 1, 1, NorthWest.id, Fill.Both.id, Insets(5, 5, 5, 5), 0, 0)
      layout += input              -> Constraints(1, 1, 1, 1, 1, 0, NorthWest.id, Fill.None.id, Insets(5, 5, 5, 5), 0, 0)
      layout += turnOutput         -> Constraints(1, 2, 1, 1, 1, 1, SouthWest.id, Fill.Both.id, Insets(5, 5, 5, 5), 0, 0)

    // Menu:
    this.menuBar = new MenuBar:
      contents += new Menu("Program"):
        val quitAction = Action("Quit")( dispose() )
        contents += MenuItem(quitAction)

    // Set up the GUI’s initial state:
    this.title = game.title
    this.updateInfo(this.game.welcomeMessage)
    this.location = Point(50, 50)
    this.minimumSize = Dimension(200, 200)
    this.pack()
    this.input.requestFocusInWindow()


    def playTurn(command: String) =
      val turnReport = this.game.playTurn(command)
      if this.player.hasQuit then
        this.dispose()
      else
        this.updateInfo(turnReport)
        this.input.enabled = !this.game.isOver


    def updateInfo(info: String) =
      if !this.game.isOver then
        this.turnOutput.text = info
      else
        this.turnOutput.text = info + "\n\n" + this.game.goodbyeMessage
      this.locationInfo.text = this.player.surroundingsDescription
      this.turnCounter.text = "Turns played: " + this.game.turnCount
      this.score.text = "Score: " + this.game.totalValueOfStolenItems + " €"
      this.weight.text = "Weight: " + this.game.player.carrying

  end top

  // Enable this code to work even under the -language:strictEquality compiler option:
  private given CanEqual[Component, Component] = CanEqual.derived
  private given CanEqual[Key.Value, Key.Value] = CanEqual.derived

end AdventureGUI

