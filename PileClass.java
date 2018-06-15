import java.awt.*;
import java.util.*;

public class PileClass extends DeckClass {
	// Get amount of face up cards in the pile
	public int numberOfFaceUpCards() {
		for (int i = 0; i < deck.size(); i++) {
			if (((CardClass) deck.get(i)).getSide() == 1) {
				return getDeckLength() - i;
			}
		}
		return 0;
	}

	// Checks if a point is inside the face up cards
	public boolean isPointInsideFaceUpCards(int x, int y) {
		for (int i = 0; i < deck.size(); i++) {
			if (((CardClass) (deck.get(i))).isPointInside(x, y) && ((CardClass) (deck.get(i))).getSide() == 1) {
				return true;
			}
		}
		return false;
	}

	// Check which card tab was clicked
	public int cardTabClicked(int x, int y) {
		// check which card the point is in
		for (int i = 0; i < deck.size(); i++) {
			if (((CardClass) (deck.get(i))).isPointInsideTab(x, y)
					|| ((i == deck.size() - 1) && ((CardClass) (deck.get(i))).isPointInside(x, y))) {
				return i;
			}
		}
		return -1;
	}

	public void draw(Graphics g) {
		if (deck.size() > 0) {
			for (int i = 0; i < deck.size(); i++) {
				CardClass c1 = (CardClass) deck.get(i);
				c1.setSize(getDeckSize());
				c1.setCentre(iCentreX, iCentreY + i * 25);
				c1.draw(g);
			}
		} else {
			g.setColor(new Color(42, 147, 72));
			g.fillRoundRect(iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight, 7, 7);
			g.setColor(Color.black);
			g.drawRoundRect(iCentreX - iWidth / 2 - 1, iCentreY - iHeight / 2 - 1, iWidth + 1, iHeight + 1, 7, 7);
		}
	}
}
