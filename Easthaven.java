// The "Easthaven" class.
import java.applet.*;
import java.awt.*;

public class Easthaven extends Applet
{
    Graphics g;
    DeckClass tempOriginDeck = new DeckClass ();
    DeckClass dealDeck = new DeckClass ();
    PileClass[] tableau = new PileClass [7];
    FoundationClass[] foundations = new FoundationClass [4];
    int tableauCards = 21;

    public void init ()
    {
	// Initialization of applet
	g = getGraphics ();
	setSize (800, 550);
	setBackground (new Color (38, 166, 91));

	dealDeck.standardDeck ();
	dealDeck.shuffle ();

	// Initialization of foundations
	for (int i = 0 ; i < foundations.length ; i++)
	{
	    foundations [i] = new FoundationClass ();
	    foundations [i].setSuit (i);
	    foundations [i].setSize ("lg");
	    foundations [i].setCentre (400 + 100 * i, 100);
	}

	// Initialization of tableau
	for (int i = 0 ; i < tableau.length ; i++)
	{
	    tableau [i] = new PileClass ();
	    tableau [i].setSize ("lg");
	    tableau [i].setCentre (100 + 100 * i, 235);
	}

	for (int i = 0 ; i < tableauCards ; i++)
	{
	    tableau [i % tableau.length].addCard ((CardClass) dealDeck.getTopCard ());
	    dealDeck.removeTopCard ();
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
	for (int i = 0 ; i < foundations.length ; i++)
	{
	    foundations [i].draw (g);
	}

    }
} // Easthaven class
