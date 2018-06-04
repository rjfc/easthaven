// The "Easthaven" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Easthaven extends Applet implements MouseMotionListener
{
    Graphics bufferGraphics;
    Image offscreen;
    Dimension dim;
    int curX, curY;

    DeckClass tempOriginDeck = new DeckClass ();
    DeckClass dealDeck = new DeckClass ();
    PileClass[] tableau = new PileClass [7];
    FoundationClass[] foundations = new FoundationClass [4];
    int tableauCards = 21;

    public void init ()
    {
	// Initialization of applet
	setSize (800, 550);
	setBackground (new Color (38, 166, 91));

	// bufferGraphics variables
	dim = getSize ();
	addMouseMotionListener (this);
	offscreen = createImage (dim.width, dim.height);
	bufferGraphics = offscreen.getGraphics ();

	// Standardize and shuffle deck
	dealDeck.standardDeck ();
	dealDeck.shuffle ();

	// Initialization of foundations
	for (int i = 0 ; i < foundations.length ; i++)
	{
	    foundations [i] = new FoundationClass ();
	    foundations [i].setSuit (i);
	    foundations [i].setSize ("lg");
	    foundations [i].setCentre (400 + 100 * i, 100);
	}

	// Initialization of tableau
	for (int i = 0 ; i < tableau.length ; i++)
	{
	    tableau [i] = new PileClass ();
	    tableau [i].setSize ("lg");
	    tableau [i].setCentre (100 + 100 * i, 235);
	}

	for (int i = 0 ; i < tableauCards ; i++)
	{
	    tableau [i % tableau.length].addCard ((CardClass) dealDeck.getTopCard ());
	    dealDeck.removeTopCard ();
	}

	dealDeck.setSize ("lg");
	dealDeck.setCentre (100, 100);
    }


    public void paint (Graphics g)
    {
	bufferGraphics.clearRect (0, 0, dim.width, dim.width);
	dealDeck.draw (bufferGraphics);
	for (int i = 0 ; i < tableau.length ; i++)
	{
	    tableau [i].draw (bufferGraphics);
	}
	for (int i = 0 ; i < foundations.length ; i++)
	{
	    foundations [i].draw (bufferGraphics);
	}

	// CHANGE THIS TO DRAW WHATEVER NEEDS TO MOVE bufferGraphics.fillRect (curX, curY, 20, 20);
	bufferGraphics.fillRect (curX, curY, 20, 20);
	g.drawImage (offscreen, 0, 0, this);
    }


    public void update (Graphics g)
    {
	paint (g);
    }


    public void mousePressed (MouseEvent e)
    {
	if (d1.isPointInside (e.getX (), e.getY ()))
	{
	    textFieldAction.setText ("Pressed");
	    OKtoMove = true;
	    d1.setCentre (e.getX (), e.getY ());
	    d1.draw (g);
	    repaint ();
	}
    }


    // Save the current mouse position to paint a rectangle there.
    // and request a repaint()
    public void mouseMoved (MouseEvent evt)
    {
	curX = evt.getX ();
	curY = evt.getY ();
	repaint ();
    }


    // The necessary methods.
    public void mouseDragged (MouseEvent evt)
    {
    }
} // Easthaven class
