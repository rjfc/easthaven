import java.awt.*;
import java.util.*;

public class DeckClass extends ShapeClass {
	
	// Private variables
	protected Vector deck = new Vector();
	private String size;
	
	// Set size of card
	public void setSize(String newSize) {
		size = newSize;
		if (size.equals("sm")) {
			setHeight(60);
		} else if (size.equals("md")) {
			setHeight(80);
		} else if (size.equals("lg")) {
			setHeight(100);
		} else if (size.equals("xl")) {
			setHeight(120);
		}
		setWidth((int) (getHeight() * 0.7));
	}

	// Add a card to end of deck
	public void addCard(CardClass newCard) {
		deck.addElement(newCard);
	}

	// Add a card in a certain position of deck
	public void addCard(CardClass newCard, int pos) {
		Object tempCard1, tempCard2;
		deck.setSize(deck.size() + 1);
		tempCard1 = deck.get(deck.size() - pos);
		deck.setElementAt(newCard, deck.size() - pos);
		for (int i = deck.size() - pos + 1; i < deck.size(); i++) {
			tempCard2 = deck.get(i);
			deck.setElementAt(tempCard1, i);
			tempCard1 = tempCard2;
		}
	}

	public boolean removeTopCard() {
		if (deck.size() > 0) {
			deck.removeElement(deck.lastElement());
			return true;
		} else {
			return false;
		}
	}

	public boolean removeBottomCard() {
		if (deck.size() > 0) {
			deck.removeElement(deck.firstElement());
			return true;
		} else {
			return false;
		}
	}

	public boolean removeCard(CardClass rmCard) {
		if (deck.size() > 0) {
			deck.removeElement(rmCard);
			return true;
		} else {
			return false;
		}
	}

	public Object getTopCard() {
		return deck.lastElement();
	}

	public Object getBottomCard() {
		return deck.firstElement();
	}

	public Object getCardAt(int index) {
		return deck.get(index);
	}

	// Amount of cards in deck
	public int getDeckLength() {
		return deck.size();
	}

	// Physical size
	public String getDeckSize () {
		return size;
	}
	
	public boolean shuffle() {
		if (deck.size() > 0) {
			Collections.shuffle(deck);
			return true;
		} else {
			return false;
		}
	}

	// Initialize the standard 52 card deck
	public void standardDeck() {
		deck.clear();
		CardClass c1;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				c1 = new CardClass();
				c1.setSuit(i);
				c1.setValue(j);
				addCard(c1);
			}
		}
	}

	public void draw (Graphics g) {
		for (int i = 0; i < deck.size(); i++) {
			CardClass c1 = (CardClass) deck.get(i);
			c1.setSize(size);
			c1.setCentre(getCentreX() - i / 4, getCentreY() - i / 4);
			c1.setSide(0);
			c1.draw(g);
		}
	}

	public void erase (Graphics g) {
		Color cOldColor = iColour;
		g.setColor(Color.white);
		g.fillRect(iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth + 1, iHeight + 1);
		setColor(cOldColor);
	}
}
