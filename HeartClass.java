// The DiamondClass Class
// Second in a series of demonstration programs for introducing Java

import hsa.Console;
import java.awt.*;

class HeartClass extends SuitClass
{
    // global variables for this class
    // encapsulated data
    private int iWidth = 80;
    private int iHeight = 100;
    private int iCentreX = 100;
    private int iCentreY = 100;
    private Color cColor = Color.red;
    private boolean filled = true;

    // communicator methods
    public void setWidth (int iNewWidth)
    {
	iWidth = iNewWidth;
    }


    public void setHeight (int iNewHeight)
    {
	iHeight = iNewHeight;
    }


    public void setCenter (int iNewCentreX, int iNewCentreY)
    {
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
    }


    public void setColor (Color cNewColor)
    {

	cColor = cNewColor;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getCenterX ()
    {
	return iCentreX;
    }


    public int getCenterY ()
    {
	return iCentreY;
    }


    public Color getColor ()
    {
	return cColor;
    }


    public void setIsFilled (boolean newfilled)
    {
	filled = newfilled;
    }


    public boolean getIsFilled ()
    {
	return filled;
    }


    public void draw (Console c)
    {
	int iPointsX[] = new int [5];
	int iPointsY[] = new int [5];

	iPointsX [0] = iCentreX - iWidth / 2;
	iPointsY [0] = iCentreY;
	iPointsX [1] = iCentreX + iWidth / 2;
	iPointsY [1] = iCentreY;
	iPointsX [2] = iCentreX;
	iPointsY [2] = iCentreY + iHeight / 2;
	iPointsX [3] = iCentreX - iWidth / 2;
	iPointsY [3] = iCentreY - iHeight / 4;
	iPointsX [4] = iCentreX;
	iPointsY [4] = iCentreY - iHeight / 4;

	c.setColor (cColor);

	c.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 0, 180);
	c.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 0, 180);
	c.fillPolygon (iPointsX, iPointsY, 3);

    }


    public void draw (Graphics g)
    {
	int iPointsX[] = new int [5];
	int iPointsY[] = new int [5];

	iPointsX [0] = iCentreX - iWidth / 2;
	iPointsY [0] = iCentreY;
	iPointsX [1] = iCentreX + iWidth / 2;
	iPointsY [1] = iCentreY;
	iPointsX [2] = iCentreX;
	iPointsY [2] = iCentreY + iHeight / 2;
	iPointsX [3] = iCentreX - iWidth / 2;
	iPointsY [3] = iCentreY - iHeight / 4;
	iPointsX [4] = iCentreX;
	iPointsY [4] = iCentreY - iHeight / 4;

	g.setColor (cColor);

	g.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 0, 180);
	g.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 0, 180);
	g.fillPolygon (iPointsX, iPointsY, 3);

    }


    public void erase (Console c)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (c);
	setColor (cOldColor);
    }


    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }
}
