import java.awt.*;
import java.util.*;

public class DraggingCardClass extends DeckClass
{
    private int originalPile;

    public void setOriginalPile (int pile)
    {
	originalPile = pile;
    }


    public int getOriginalPile ()
    {
	return originalPile;
    }


    public boolean isInOrder ()
    {
	if (deck.size () > 0)
	{
	    for (int i = 1 ; i < deck.size () ; i++)
	    {
		if (((CardClass) (deck.get (i))).getValue () != ((CardClass) (deck.get (i - 1))).getValue () - 1)
		{
		    return false;
		}
	    }
	}
	else
	{
	    return false;
	}
	return true;
    }


    public void draw (Graphics g)
    {
	/*  if (getDeckLength () > 0)
	  {
	      CardClass c1 = (CardClass) getTopCard ();
	      c1.setCentre (iCentreX, iCentreY);
	      c1.draw (g);
	  }*/
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    CardClass c1 = (CardClass) deck.get (i);
	    c1.setSize (size);
	    c1.setCentre (iCentreX, iCentreY + i * 25);
	    c1.draw (g);
	}
    }
}


