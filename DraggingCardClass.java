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


    public void draw (Graphics g)
    {
	if (getDeckLength () > 0)
	{
	    CardClass c1 = (CardClass) getTopCard ();
	    c1.setCentre (iCentreX, iCentreY);
	    c1.draw (g);
	}
    }
}
