import hsa.Console;
import java.awt.*;
import java.util.*;

public class UseDeckClass
{
    public static void main (String[] args)
    {
	Console c = new Console ();
	DeckClass unshuffledD = new DeckClass ();
	DeckClass shuffledD = new DeckClass ();
	DeckClass discardD = new DeckClass ();
	unshuffledD.setSize ("xl");
	unshuffledD.setCentre (100, 160);
	unshuffledD.standardDeck ();
	unshuffledD.draw (c);
	shuffledD.setSize ("xl");
	shuffledD.setCentre (100, 330);
	shuffledD.standardDeck ();
	shuffledD.shuffle ();
	shuffledD.draw (c);
	discardD.setSize ("xl");
	discardD.setCentre (300, 245);
	int oDeckLength = unshuffledD.getDeckLength ();
	for (int i = 0 ; i < oDeckLength ; i++)
	{
	    unshuffledD.delay (500);
	    discardD.addCard ((CardClass) unshuffledD.getTopCard ());
	    unshuffledD.removeTopCard ();
	    unshuffledD.erase (c);
	    discardD.erase (c);
	    unshuffledD.draw (c);
	    discardD.draw (c);
	}
	c.readChar();
	oDeckLength = shuffledD.getDeckLength ();
	for (int i = 0 ; i < oDeckLength ; i++)
	{
	    shuffledD.delay (500);
	    discardD.addCard ((CardClass) shuffledD.getTopCard ());
	    shuffledD.removeTopCard ();
	    shuffledD.erase (c);
	    discardD.erase (c);
	    shuffledD.draw (c);
	    discardD.draw (c);
	}
	
    }
}
