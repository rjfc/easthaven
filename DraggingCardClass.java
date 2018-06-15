import java.awt.*;
import java.util.*;

public class DraggingCardClass extends DeckClass {
	
	// Private original variables for tracking
	private int originalPile = -1;
	private int originalFoundation = -1;

	public void setOriginalPile(int pile) {
		originalPile = pile;
	}

	public void setOriginalFoundation(int foundation) {
		originalFoundation = foundation;
	}

	public int getOriginalPile() {
		return originalPile;
	}

	public int getOriginalFoundation() {
		return originalFoundation;
	}

	// Make sure cards in the class are in order
	public boolean isInOrder() {
		if (deck.size() > 0) {
			for (int i = 1; i < deck.size(); i++) {
				if (((CardClass) (deck.get(i))).getValue() != ((CardClass) (deck.get(i - 1))).getValue() - 1
						|| (((CardClass) (deck.get(i))).getSuit() + ((CardClass) (deck.get(i - 1))).getSuit())
								% 2 == 0) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public void draw(Graphics g) {
		for (int i = 0; i < deck.size(); i++) {
			CardClass c1 = (CardClass) deck.get(i);
			c1.setSize(getDeckSize ());
			c1.setCentre(iCentreX, iCentreY + i * 25);
			c1.draw(g);
		}
	}
}
