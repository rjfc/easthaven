import java.awt.*;
import java.util.*;

public class PileClass extends DeckClass
{
    boolean firstGeneration = true;

    public int numberOfFaceUpCards ()
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    if (((CardClass) deck.get (i)).getSide () == 1)
	    {
		return getDeckLength () - i;
	    }
	}
	return 0;
    }


    public boolean isPointInsideFaceUpCards (int x, int y)
    {
	// check if point is inside face up cards

	for (int i = 0 ; i < deck.size () ; i++)
	{
	    if (((CardClass)(deck.get (i))).isPointInside (x, y))
	    {
		return true;
	    }
	}
	return false;
    }


    public void draw (Graphics g)
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    CardClass c1 = (CardClass) deck.get (i);
	    c1.setSize (size);
	    c1.setCentre (iCentreX, iCentreY + i * 25);
	    if (i < deck.size () - 1 && firstGeneration)
	    {
		c1.setSide (0);
	    }
	    c1.draw (g);
	}
	firstGeneration = false;
    }


    public void erase (Graphics g)
    {
	// TODOs
    }
}
