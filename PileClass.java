import java.awt.*;
import java.util.*;

public class PileClass extends ShapeClass
{
    Vector pile = new Vector ();
    String size;

    public void setSize (String newSize)
    {
	size = newSize;
	if (size.equals ("sm"))
	{
	    setHeight (60);
	}
	else if (size.equals ("md"))
	{
	    setHeight (80);
	}
	else if (size.equals ("lg"))
	{
	    setHeight (100);
	}
	else if (size.equals ("xl"))
	{
	    setHeight (120);
	}
	setWidth ((int) (getHeight () * 0.7));
    }


    public void addCard (CardClass newCard)
    {
	pile.addElement (newCard);
    }


    public boolean removeTopCard ()
    {
	if (pile.size () > 0)
	{
	    pile.removeElement (pile.lastElement ());
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public boolean removeBottomCard ()
    {
	if (pile.size () > 0)
	{
	    pile.removeElement (pile.firstElement ());
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public boolean removeCard (CardClass rmCard)
    {
	if (pile.size () > 0)
	{
	    pile.removeElement (rmCard);
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public Object getTopCard ()
    {
	return pile.lastElement ();
    }


    public int getDeckLength ()
    {
	return pile.size ();
    }


    public void draw (Graphics g)
    {
	for (int i = 0 ; i < pile.size () ; i++)
	{
	    CardClass c1 = (CardClass) pile.get (i);
	    c1.setSize (size);
	    c1.setCentre (iCentreX, iCentreY + i * 15);
	    if (i < pile.size () - 1)
	    {
		c1.setSide (0);
	    }
	    c1.draw (g);
	}
    }


    public void erase (Graphics g)
    {
	Color cOldColor = iColour;
	g.setColor (Color.white);
	g.fillRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth + 1, iHeight + 1);
	setColor (cOldColor);
    }
}
