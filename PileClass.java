import java.awt.*;
import java.util.*;

public class PileClass extends DeckClass
{

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
	    if (((CardClass) (deck.get (i))).isPointInside (x, y) && ((CardClass) (deck.get (i))).getSide() == 1)
	    {
		return true;
	    }
	}
	return false;
    }
    
    public int cardTabClicked (int x, int y) {
	// check which card the point is in
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    if (((CardClass) (deck.get (i))).isPointInsideTab (x, y) || ((i == deck.size() - 1) &&  ((CardClass) (deck.get (i))).isPointInside (x, y)))
	    {
		return i;
	    }
	}
	return -1;
    }


    public void draw (Graphics g)
    {
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    CardClass c1 = (CardClass) deck.get (i);
	    c1.setSize (size);
	    c1.setCentre (iCentreX, iCentreY + i * 25);
	    c1.draw (g);
	}
    }


    public void erase (Graphics g)
    {
	// TODOs
    }
}
