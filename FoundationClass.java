import java.awt.*;
import java.util.*;

public class FoundationClass extends DeckClass
{
    int suit;

    public void setSuit (int newSuit)
    {
	suit = newSuit;
    }


    public void draw (Graphics g)
    {
	if (deck.size () > 0)
	{
	    CardClass c1 = (CardClass) deck.lastElement ();
	    c1.setSize (size);
	    c1.setCentre (iCentreX, iCentreY);
	    c1.draw (g);
	}
	else
	{
	    g.setColor (new Color (42, 147, 72));
	    g.fillRoundRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight, 7, 7);
	    g.setColor (Color.black);
	    g.drawRoundRect (iCentreX - iWidth / 2 - 1, iCentreY - iHeight / 2 - 1, iWidth + 1, iHeight + 1, 7, 7);

	    switch (suit)
	    {
		case 0:
		    SpadeClass s1 = new SpadeClass ();
		    s1.setCenter (iCentreX, iCentreY);
		    s1.setHeight ((int) Math.round (iHeight * 0.25));
		    s1.setWidth ((int) (s1.getHeight () * 0.8));
		    s1.setColor (Color.black);
		    s1.draw (g);
		    break;
		case 1:
		    DiamondClass d1 = new DiamondClass ();
		    d1.setCenter (iCentreX, iCentreY);
		    d1.setHeight ((int) (iHeight * 0.25));
		    d1.setWidth ((int) (d1.getHeight () * 0.8));
		    d1.setColor (Color.red);
		    d1.draw (g);
		    break;
		case 2:
		    ClubClass c1 = new ClubClass ();
		    c1.setCenter (iCentreX, iCentreY);
		    c1.setHeight ((int) (iHeight * 0.25));
		    c1.setWidth ((int) (c1.getHeight () * 0.8));
		    c1.setColor (Color.black);
		    c1.draw (g);
		    break;
		case 3:
		    HeartClass h1 = new HeartClass ();
		    h1.setCenter (iCentreX, iCentreY);
		    h1.setHeight ((int) (iHeight * 0.25));
		    h1.setWidth ((int) (h1.getHeight () * 0.8));
		    h1.setColor (Color.red);
		    h1.draw (g);
		    break;
	    }
	}
    }


    public void erase (Graphics g)
    {
	// TODOs
    }
}
