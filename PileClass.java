import java.awt.*;
import java.util.*;

public class PileClass extends DeckClass
{
    public booleanIsPointInside (int x, int y) {
	// check if point is inside face up cards
    }

    public void draw (Graphics g)
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    CardClass c1 = (CardClass) deck.get (i);
	    c1.setSize (size);
	    c1.setCentre (iCentreX, iCentreY + i * 15);
	    if (i < deck.size () - 1)
	    {
		c1.setSide (0);
	    }
	    c1.draw (g);
	}
    }


    public void erase (Graphics g)
    {
       // TODOs
    }
}
