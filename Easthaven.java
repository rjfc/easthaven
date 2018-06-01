// The "Easthaven" class.
import java.applet.*;
import java.awt.*;

public class Easthaven extends Applet
{
    Graphics g;
    DeckClass tempOriginDeck = new DeckClass ();
    DeckClass dealDeck = new DeckClass ();
    PileClass[] tableau = new PileClass [7];
    int tempDeckLength;
    int deckSize = 31;

    public void init ()
    {
	// Initialization of applet
	g = getGraphics ();
	setSize (800, 550);
	setBackground (new Color (38, 166, 91));

	tempOriginDeck.standardDeck ();
	tempOriginDeck.shuffle ();

	// Initialization of tableau
	for (int i = 0 ; i < tableau.length ; i++)
	{
	    tableau [i] = new PileClass ();
	}

	// Distribute cards randomly amongst the deck and piles
	tempDeckLength = tempOriginDeck.getDeckLength ();
	    
	for (int i = 0 ; i < tempDeckLength ; i++)
	{
	    if (i < deckSize)
	    {
		dealDeck.addCard ((CardClass) tempOriginDeck.getTopCard ());
		tempOriginDeck.removeTopCard ();
	    }
	    else
	    {
		tableau [(i - 21) % tableau.length].addCard ((CardClass) tempOriginDeck.getTopCard ());
		tempOriginDeck.removeTopCard ();
	    }
	}

	for (int i = 0 ; i < tableau.length ; i++)
	{
	    tableau [i].setSize ("lg");
	    tableau [i].setCentre (100 + 100 * i, 235);
	}

	dealDeck.setSize ("lg");
	dealDeck.setCentre (100, 100);

    }


    public void paint (Graphics g)
    {
	dealDeck.draw (g);
	for (int i = 0 ; i < tableau.length ; i++)
	{
	    tableau [i].draw (g);
	}

    }
} // Easthaven class
