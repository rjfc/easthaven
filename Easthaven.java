
// The "Easthaven" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Easthaven extends Applet implements MouseListener, MouseMotionListener {
	// BufferGraphics variables
	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;

	// Deck variables and arrays
	DeckClass dealDeck; // Primary deck that deals
	DraggingCardClass draggingCard; // Where cards go when they are being dragged
	PileClass[] piles; // Piles where user can move cards around
	FoundationClass[] foundations; // Foundation where final organized cards go

	// Button variables
	ButtonClass endGameButton/* , restartGameButton */;

	// Global variables for use throughout the program
	int pilesCards; // Amount of cards to be distributed amongst the piles
	int tempDeckLength; // Temporary deck length for use during loops where the length of a deck is a
						// condition but also changes
	int points; // Amount of points the user has
	boolean gameWon, gameEnded; // If user has won or ended the game prematurely

	static Font f1, /* f2, */ f3; // Fonts for graphics

	public void init() {
		// Initialization of applet size and background colour
		setSize(800, 800);
		setBackground(new Color(38, 166, 91));

		// BufferGraphics variables
		dim = getSize();
		addMouseListener(this);
		addMouseMotionListener(this);
		offscreen = createImage(dim.width, dim.height);
		bufferGraphics = offscreen.getGraphics();

		// Initialize decks
		dealDeck = new DeckClass();
		draggingCard = new DraggingCardClass();
		piles = new PileClass[7];
		foundations = new FoundationClass[4];

		// Initialize buttons
		endGameButton = new ButtonClass();
		// restartGameButton = new ButtonClass();

		// Initialize global variables (refer to global scope for explanation)
		pilesCards = 21;
		points = 0;
		gameWon = false;
		gameEnded = false;

		f1 = new Font("SanSerif", Font.BOLD, 14);
		// f2 = new Font("SanSerif", Font.BOLD, 18);
		f3 = new Font("SanSerif", Font.BOLD, 22);

		// Standardize and shuffle dealing deck
		dealDeck.standardDeck();
		dealDeck.shuffle();
		// Set size and position of dealing deck
		dealDeck.setSize("lg");
		dealDeck.setCentre(100, 100);

		// Initialization of foundations
		for (int i = 0; i < foundations.length; i++) {
			foundations[i] = new FoundationClass();
			foundations[i].setSuit(i);
			foundations[i].setSize("lg");
			foundations[i].setCentre(400 + 100 * i, 100);
		}

		// Initialization of piles
		for (int i = 0; i < piles.length; i++) {
			piles[i] = new PileClass();
			piles[i].setSize("lg");
			piles[i].setCentre(100 + 100 * i, 235);
		}

		// Deal appropriate amount of cards from the dealing deck to the piles
		for (int i = 0; i < pilesCards; i++) {
			piles[i % piles.length].addCard((CardClass) dealDeck.getTopCard());
			((CardClass) piles[i % piles.length].getTopCard()).setSide(0);
			dealDeck.removeTopCard();
		}

		// Make the top card of the piles face up
		for (int i = 0; i < 7; i++) {
			((CardClass) piles[i].getTopCard()).setSide(1);
		}

		// Set size of dragging cards
		draggingCard.setSize("lg");

		// Calculate the initial amount of points
		calculatePoints();

		// Initialization of the end game button
		endGameButton.setCentre(107, getHeight() - 40);
		endGameButton.setWidth(100);
		endGameButton.setHeight(30);
		endGameButton.setButtonColour(Color.white);
		endGameButton.setFont(f1);
		endGameButton.setText("End game");
		endGameButton.setTextColour(Color.black);
		endGameButton.setTextCentre(71, getHeight() - 35);
		/*
		 * // Initialization of the restart game button
		 * restartGameButton.setCentre(getWidth() / 2 - 75, getHeight() / 2 - 100);
		 * restartGameButton.setWidth(150); restartGameButton.setHeight(60);
		 * restartGameButton.setButtonColour(Color.white);
		 * restartGameButton.setFont(f2); restartGameButton.setText("Restart game");
		 * restartGameButton.setTextColour(Color.black);
		 * restartGameButton.setTextCentre(getWidth() / 2 - 60, getHeight() / 2 - 63);
		 */

	}

	public void paint(Graphics g) {
		// Clear the canvas
		bufferGraphics.clearRect(0, 0, dim.width, dim.width);

		// Draw the dealing deck
		dealDeck.draw(bufferGraphics);

		// Draw the foundations
		for (int i = 0; i < foundations.length; i++) {
			foundations[i].draw(bufferGraphics);
		}

		// Draw the piles
		for (int i = 0; i < piles.length; i++) {
			piles[i].draw(bufferGraphics);
		}

		// Text the displays the points
		bufferGraphics.setFont(f1);
		bufferGraphics.setColor(Color.black);
		bufferGraphics.drawString(points + " points", 57, 25);

		// Draw the end game button
		endGameButton.draw(bufferGraphics);

		// Check if game won
		if (gameWon) {
			// Display game win window
			bufferGraphics.setFont(f3);
			bufferGraphics.setColor(new Color(255, 255, 238));
			bufferGraphics.fillRoundRect(getWidth() / 2 - 250, getHeight() / 2 - 200, 500, 200, 10, 10);
			bufferGraphics.setColor(Color.black);
			bufferGraphics.drawRoundRect(getWidth() / 2 - 251, getHeight() / 2 - 201, 501, 201, 10, 10);
			bufferGraphics.drawString("Congratulations! You have won the game!", getWidth() / 4 - 15, getHeight() / 3);
			bufferGraphics.drawString("You finished with " + points + " points.", getWidth() / 4 + 55,
					getHeight() / 2 - 80);
		}

		// Check if user ended game
		if (gameEnded) {
			// Display end game window
			bufferGraphics.setFont(f3);
			bufferGraphics.setColor(new Color(255, 255, 238));
			bufferGraphics.fillRoundRect(getWidth() / 2 - 250, getHeight() / 2 - 200, 500, 200, 10, 10);
			bufferGraphics.setColor(Color.black);
			bufferGraphics.drawRoundRect(getWidth() / 2 - 251, getHeight() / 2 - 201, 501, 201, 10, 10);
			bufferGraphics.drawString("You finished with " + points + " points.", getWidth() / 4 + 55, getHeight() / 3);
			// restartGameButton.draw(bufferGraphics);
		}

		// Draw dragging cards
		draggingCard.draw(bufferGraphics);

		// Draw offscreen image (DoubleBuffering)
		g.drawImage(offscreen, 0, 0, this);
	}

	// Prevents flickering (DoubleBuffering)
	public void update(Graphics g) {
		paint(g);
	}

	// Calculate the current amount of points given the cards currently in piles
	public void calculatePoints() {
		// Points = 100 - # of cards in the piles
		int temp = 100;
		for (int i = 0; i < piles.length; i++) {
			for (int j = 0; j < piles[i].getDeckLength(); j++) {
				temp--;
			}
		}
		points = temp;
	}

	// Checks if a move of card(s) to a pile is valid
	public boolean isPileMoveValid(MouseEvent e, int i) {
		// if pile is empty
		if (piles[i].isPointInside(e.getX(), e.getY())) {
			if (piles[i].getDeckLength() == 0) {
				// Make sure card is king
				if (((CardClass) draggingCard.getBottomCard()).getValue() == 13) {
					return true;
				}
			}
		}
		// If pile is not empty
		if (piles[i].isPointInsideFaceUpCards(e.getX(), e.getY())) {
			// Make sure that pile would still follow order
			if (((CardClass) piles[i].getTopCard()).getValue() == ((CardClass) draggingCard.getBottomCard()).getValue()
					+ 1) {
				if ((((CardClass) piles[i].getTopCard()).getSuit()
						+ ((CardClass) draggingCard.getBottomCard()).getSuit()) % 2 == 1) {
					return true;
				}
			}
		}
		return false;
	}

	// Checks if move of a card to a foundation is valid
	public boolean isFoundationMoveValid(MouseEvent e, int i) {
		// If foundation is empty
		if (foundations[i].isPointInside(e.getX(), e.getY())) {
			if (draggingCard.getDeckLength() == 1) {
				if (foundations[i].getDeckLength() == 0) {
					// Make sure card is ace
					if (((CardClass) draggingCard.getTopCard()).getValue() == 1) {
						return true;
					}
				}
			}
		}
		// If foundation is not empty
		if (foundations[i].getDeckLength() > 0) {
			// Make sure that the foundation would still follow order
			if (((CardClass) (foundations[i].getTopCard()))
					.getValue() == ((CardClass) draggingCard.getTopCard()).getValue() - 1) {
				if (foundations[i].getSuit() == ((CardClass) draggingCard.getTopCard()).getSuit()) {
					return true;
				}
			}
		}
		return false;
	}

	// Mouse move event
	public void mouseMoved(MouseEvent e) {
		// Change cursor to pointer if hovering above the end game button
		if (endGameButton.isPointInside(e.getX(),
				e.getY()) /* || restartGameButton.isPointInside(e.getX(), e.getY()) */) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		// Make sure only left mouse button is allowed and user has not ended the game
		if (e.getButton() == 1 && !gameEnded) {
			for (int i = 0; i < piles.length; i++) {
				// If user clicks on face up cards in a pile
				if (piles[i].isPointInsideFaceUpCards(e.getX(), e.getY())) {
					// Set temporary variable to length of pile that the user clicked on
					tempDeckLength = piles[i].getDeckLength();
					// Add cards to dragging card class and check if in order
					for (int j = piles[i].cardTabClicked(e.getX(), e.getY()); j < tempDeckLength; j++) {
						draggingCard.addCard((CardClass) piles[i].getCardAt(j));
					}
					if (draggingCard.isInOrder() || draggingCard.getDeckLength() == 1) {
						// If cards are in proper order for dragging or there is only 1 card being dragged, remove them from the pile
						for (int j = piles[i].cardTabClicked(e.getX(), e.getY()); j < tempDeckLength; j++) {
							piles[i].removeTopCard();
						}
						// Dragging cards follow the mouse
						draggingCard.setCentre(e.getX(), e.getY());
						// Set original pile of dragging cards in case they need to be returned
						draggingCard.setOriginalPile(i);
						draggingCard.setOriginalFoundation(-1); // So we know where the dragging cards came from
					} else {
						// If the cards are not in proper order, remove them from the dragging card
						// class
						for (int j = piles[i].cardTabClicked(e.getX(), e.getY()); j < tempDeckLength; j++) {
							draggingCard.removeTopCard();
						}
					}
					repaint();
					break;
				}
			}
			for (int i = 0; i < foundations.length; i++) {
				// If user clicks on a foundation
				if (foundations[i].isPointInside(e.getX(), e.getY())) {
					// Add top card of foundation to dragging card class
					draggingCard.addCard((CardClass) foundations[i].getTopCard());
					// Set original foundation of dragging card in case it needs to be returned
					draggingCard.setOriginalFoundation(i);
					draggingCard.setOriginalPile(-1); // So we know where the dragging card came from
					
					foundations[i].removeTopCard(); // Remove card from foundation
				}
			}
			// If user clicks on deal deck
			if (dealDeck.getDeckLength() > 0 && ((CardClass) dealDeck.getTopCard()).isPointInside(e.getX(), e.getY())) {
				if (dealDeck.getDeckLength() >= piles.length) {
					// If there are more than or equal amount of cards in the deck than there are
					// piles
					tempDeckLength = piles.length;
				} else {
					// If there are less cards in the deck than there are piles (last iteration of
					// dealing)
					tempDeckLength = dealDeck.getDeckLength();
				}
				for (int i = 0; i < tempDeckLength; i++) {
					((CardClass) dealDeck.getTopCard()).setSide(1);
					piles[i].addCard((CardClass) dealDeck.getTopCard());
					dealDeck.removeTopCard();
				}
				calculatePoints();
				repaint();
			}
			// if user clicks on end game button
			if (endGameButton.isPointInside(e.getX(), e.getY())) {
				gameEnded = true;
				repaint();
			}
			/*
			 * // If user clicks on restart game button if
			 * (restartGameButton.isPointInside(e.getX(), e.getY())) { init(); }
			 */
		}
	}

	public void mouseReleased(MouseEvent e) {
		tempDeckLength = draggingCard.getDeckLength();
		for (int i = 0; i < piles.length; i++) {
			// Check if move to pile is valid
			if (isPileMoveValid(e, i)) {
				for (int j = 0; j < tempDeckLength; j++) {
					piles[i].addCard((CardClass) draggingCard.getBottomCard());
					draggingCard.removeBottomCard();
				}
				break;
			}
		}
		// If single card is being dragged
		if (draggingCard.getDeckLength() == 1) {
			for (int i = 0; i < foundations.length; i++) {
				// Check if move to foundation is valid
				if (isFoundationMoveValid(e, i)) {
					// Move card from dragging card to foundation
					foundations[i].addCard((CardClass) draggingCard.getTopCard());
					draggingCard.removeTopCard();
					// Check if user has won the game
					gameWon = true;
					for (int j = 0; j < foundations.length; j++) {
						if (!foundations[j].isFoundationComplete()) {
							gameWon = false;
							break;
						}
					}
					break;
				}
			}
		}
		// If dragging card class is not empty after these conditions, then it was not
		// placed properly in a pile or foundation
		if (draggingCard.getDeckLength() > 0) {
			tempDeckLength = draggingCard.getDeckLength();
			if (draggingCard.getOriginalPile() != -1) {
				for (int i = 0; i < tempDeckLength; i++) {
					// Send dragging cards back to original pile
					piles[draggingCard.getOriginalPile()].addCard((CardClass) draggingCard.getBottomCard());
				}
			} else {
				foundations[draggingCard.getOriginalFoundation()].addCard((CardClass) draggingCard.getBottomCard());
			}
			draggingCard.removeBottomCard();
		}
		// Flip the top of the original pile (in case the card was properly transferred)
		if (draggingCard.getOriginalPile() > 0 && piles[draggingCard.getOriginalPile()].getDeckLength() > 0) {
			((CardClass) piles[draggingCard.getOriginalPile()].getTopCard()).setSide(1);
		}
		// Calculate points after transfer of cards
		calculatePoints();
		repaint();
	}

	// The necessary methods.
	public void mouseDragged(MouseEvent e) {
		// If dragging card class is not empty, make it follow the mouse
		if (draggingCard.getDeckLength() > 0) {
			draggingCard.setCentre(e.getX(), e.getY());
			repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	} // Easthaven class
}
