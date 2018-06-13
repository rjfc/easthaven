import hsa.Console;
import java.awt.*;

public class CardClass extends ShapeClass
{
    int suit, value;
    boolean isFaceUp = true;

    static Font f1 = new Font ("SanSerif", Font.BOLD, 14);
    static Font f2 = new Font ("SanSerif", Font.BOLD, 18);
    static Font f3 = new Font ("SanSerif", Font.BOLD, 22);
    static Font f4 = new Font ("SanSerif", Font.BOLD, 24);

    public void setSuit (int newSuit)
    {
	suit = newSuit;
    }


    public void setValue (int newValue)
    {
	value = newValue;
    }


    public void setSize (String size)
    {
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


    public void setSide (int newSide)
    {
	isFaceUp = (newSide == 1) ? true:
	false;
    }


    public int getSuit ()
    {
	return suit;
    }


    public int getValue ()
    {
	return value;
    }


    public int getSide ()
    {
	return (isFaceUp) ? 1:
	0;
    }


    public boolean isPointInsideTab (int x, int y)
    {
	if (x >= iCentreX - iWidth / 2 && x <= iCentreX + iWidth / 2 && y >= iCentreY - iHeight / 2 && y <= iCentreY - iHeight/2 + 25)
	{
	    return true;
	}
	return false;
    }


    public void draw (Graphics g)
    {
	if (isFaceUp)
	{
	    g.setColor (Color.white);
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
	    switch (iHeight)
	    {
		case 60:
		    g.setFont (f1);
		    break;
		case 80:
		    g.setFont (f2);
		    break;
		case 100:
		    g.setFont (f3);
		    break;
		case 120:
		    g.setFont (f4);
		    break;
	    }
	    if (value > 1 && value < 11)
	    {
		if (value == 10)
		{
		    g.drawString (Integer.toString (value), iCentreX + (int) (iWidth * 0.1), iCentreY + (int) (iHeight * 0.45));
		    g.drawString (Integer.toString (value), iCentreX - (int) (iWidth * 0.45), iCentreY - (int) (iHeight * 0.275));
		}
		else
		{
		    g.drawString (Integer.toString (value), iCentreX + (int) (iWidth * 0.245), iCentreY + (int) (iHeight * 0.45));
		    g.drawString (Integer.toString (value), iCentreX - (int) (iWidth * 0.45), iCentreY - (int) (iHeight * 0.275));
		}
	    }
	    else
	    {
		String displayValue = null;
		switch (value)
		{
		    case 1:
			displayValue = "A";
			break;
		    case 11:
			displayValue = "J";
			break;
		    case 12:
			displayValue = "Q";
			break;
		    case 13:
			displayValue = "K";
			break;
		}
		g.drawString (displayValue, iCentreX - (int) (iWidth * 0.45), iCentreY - (int) (iHeight * 0.275));
		g.drawString (displayValue, iCentreX + (int) (iWidth * 0.21), iCentreY + (int) (iHeight * 0.45));
	    }
	}
	else
	{
	    switch (suit)
	    {
		case 0:
		    g.setColor (Color.black);
		    break;
		case 1:
		    g.setColor (Color.red);
		    break;
		case 2:
		    g.setColor (Color.black);
		    break;
		case 3:
		    g.setColor (Color.red);
		    break;
	    }

	    g.setColor (new Color (249, 191, 59));
	    g.fillRoundRect (iCentreX - iWidth / 2, iCentreY - iHeight / 2, iWidth, iHeight, 7, 7);
	    g.setColor (Color.black);
	    g.drawRoundRect (iCentreX - iWidth / 2 - 1, iCentreY - iHeight / 2 - 1, iWidth + 1, iHeight + 1, 7, 7);
	}
    }
}
