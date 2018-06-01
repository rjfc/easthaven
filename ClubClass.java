import hsa.Console;
import java.awt.*;

class ClubClass extends SuitClass
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
	iPointsY [2] = iCentreY - iHeight / 2;
	iPointsX [3] = iCentreX - iWidth / 2;
	iPointsY [3] = iCentreY - iHeight / 4;
	iPointsX [4] = iCentreX;
	iPointsY [4] = iCentreY - iHeight / 4;

	int triPointsX[] = new int [3];
	int triPointsY[] = new int [3];

	triPointsX [0] = iCentreX;
	triPointsY [0] = iCentreY - iHeight / 4;
	triPointsX [1] = iCentreX - iWidth / 8;
	triPointsY [1] = iCentreY + iHeight / 2;
	triPointsX [2] = iCentreX + iWidth / 8;
	triPointsY [2] = iCentreY + iHeight / 2;

	c.setColor (cColor);

	c.fillOval (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2);
	c.fillOval (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2);
	c.fillOval (iCentreX - iWidth / 4, iCentreY - 4 * (iHeight / 7), iWidth / 2, iHeight / 2);
	c.fillPolygon (triPointsX, triPointsY, 3);

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
	iPointsY [2] = iCentreY - iHeight / 2;
	iPointsX [3] = iCentreX - iWidth / 2;
	iPointsY [3] = iCentreY - iHeight / 4;
	iPointsX [4] = iCentreX;
	iPointsY [4] = iCentreY - iHeight / 4;

	int triPointsX[] = new int [3];
	int triPointsY[] = new int [3];

	triPointsX [0] = iCentreX;
	triPointsY [0] = iCentreY - iHeight / 4;
	triPointsX [1] = iCentreX - iWidth / 8;
	triPointsY [1] = iCentreY + iHeight / 2;
	triPointsX [2] = iCentreX + iWidth / 8;
	triPointsY [2] = iCentreY + iHeight / 2;

	g.setColor (cColor);

	g.fillOval (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2);
	g.fillOval (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2);
	g.fillOval (iCentreX - iWidth / 4, iCentreY - 4 * (iHeight / 7), iWidth / 2, iHeight / 2);
	g.fillPolygon (triPointsX, triPointsY, 3);

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
