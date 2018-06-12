// The "Easthaven" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Easthaven extends Applet implements MouseListener, MouseMotionListener
{
    Graphics bufferGraphics;
    Image offscreen;
    Dimension dim;
    int curX, curY;

    DeckClass tempOriginDeck = new DeckClass ();
    DeckClass dealDeck = new DeckClass ();
    DraggingCardClass draggingCard = new DraggingCardClass ();
    PileClass[] tableau = new PileClass [7];
    FoundationClass[] foundations = new FoundationClass [4];
    int tableauCards = 21;
    int tempDeckLength;

    public void init ()
    {
	// Initialization of applet
	setSize (800, 550);
	setBackground (new Color (38, 166, 91));

	// bufferGraphics variables
	dim = getSize ();
	addMouseListener (this);
	addMouseMotionListener (this);
	offscreen = createImage (dim.width, dim.height);
	bufferGraphics = offscreen.getGraphics ();

	// Standardize and shuffle deck
	dealDeck.standardDeck ();
	dealDeck.shuffle ();

	// Set size of dragging card
	draggingCard.setSize ("lg");

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
	    ((CardClass) tableau [i % tableau.length].getTopCard ()).setSide (0);
	    dealDeck.removeTopCard ();
	}

	for (int i = 0 ; i < 7 ; i++)
	{
	    ((CardClass) tableau [i].getTopCard ()).setSide (1);
	}
	dealDeck.setSize ("lg");
	dealDeck.setCentre (100, 100);
    }


    public void paint (Graphics g)
    {
	bufferGraphics.clearRect (0, 0, dim.width, dim.width);
	dealDeck.draw (bufferGraphics);

	for (int i = 0 ; i < foundations.length ; i++)
	{
	    foundations [i].draw (bufferGraphics);
	}

	// CHANGE THIS TO DRAW WHATEVER NEEDS TO MOVE bufferGraphics.fillRect (curX,
	// curY, 20, 20);
	for (int i = 0 ; i < tableau.length ; i++)
	{
	    tableau [i].draw (bufferGraphics);
	}
	draggingCard.draw (bufferGraphics);
	g.drawImage (offscreen, 0, 0, this);
    }


    public void update (Graphics g)
    {
	paint (g);
    }


    public void mouseReleased (MouseEvent e)
    {
	// If single card is being dragged
	if (draggingCard.getDeckLength () == 1)
	{
	    for (int i = 0 ; i < tableau.length ; i++)
	    {
		// If mouse is inside the piles, a card is being dragged, the pile would be in
		// descending order, and the pile is in alternating colours
		if (tableau [i].isPointInsideFaceUpCards (e.getX (), e.getY ()) && draggingCard.getDeckLength () == 1
			&& ((CardClass) tableau [i].getTopCard ())
			.getValue () == ((CardClass) draggingCard.getTopCard ()).getValue () + 1
			&& (((CardClass) tableau [i].getTopCard ()).getSuit ()
			    + ((CardClass) draggingCard.getTopCard ()).getSuit ()) % 2 == 1)
		{
		    tableau [i].addCard ((CardClass) draggingCard.getTopCard ());
		    draggingCard.removeTopCard ();

		}
	    }
	    for (int i = 0 ; i < foundations.length ; i++)
	    {
		// If mouse is inside the foundation, a card is being dragged, the card suit fits, and it is in ascending order
		// or foundation is empty
		if (foundations [i].isPointInside (e.getX (), e.getY ()) && draggingCard.getDeckLength () == 1
			&& ((foundations [i].getDeckLength () == 0 && ((CardClass) draggingCard.getTopCard ())
			    .getValue () == 1) || foundations [i].getDeckLength () > 0 && ((CardClass) foundations [i].getTopCard ())
			    .getValue () == ((CardClass) draggingCard.getTopCard ()).getValue () - 1)
			&& foundations[i].getSuit ()
			    == ((CardClass) draggingCard.getTopCard ()).getSuit ())
		{
		    foundations [i].addCard ((CardClass) draggingCard.getTopCard ());
		    draggingCard.removeTopCard ();

		}
	    }
	    if (draggingCard.getDeckLength () == 1)
	    {
		tableau [draggingCard.getOriginalPile ()].addCard ((CardClass) draggingCard.getTopCard ());
		draggingCard.removeTopCard ();
	    }

	    ((CardClass) tableau [draggingCard.getOriginalPile ()].getTopCard ()).setSide (1);
	    repaint ();
	}
	// If multiple cards are being dragged (dealing from deck)
	tempDeckLength = draggingCard.getDeckLength ();
	if (tempDeckLength == tableau.length)
	{
	    for (int i = 0 ; i < tableau.length ; i++)
	    {
		tableau [i].addCard ((CardClass) draggingCard.getTopCard ());
		draggingCard.removeTopCard ();
	    }
	    repaint ();
	}
	else if (tempDeckLength < tableau.length)
	{
	    for (int i = 0 ; i < tempDeckLength ; i++)
	    {
		tableau [i].addCard ((CardClass) draggingCard.getTopCard ());
		draggingCard.removeTopCard ();
	    }
	    repaint ();
	}
    }


    // Save the current mouse position to paint a rectangle there.
    // and request a repaint()
    public void mouseMoved (MouseEvent e)
    {
	curX = e.getX ();
	curY = e.getY ();
	repaint ();
    }


    public void mouseEntered (MouseEvent e)
    {

    }


    public void mouseExited (MouseEvent e)
    {

    }


    public void mousePressed (MouseEvent e)
    {
	// If user clicks on piles
	for (int i = 0 ; i < tableau.length ; i++)
	{
	    if (tableau [i].isPointInsideFaceUpCards (e.getX (), e.getY ()))
	    {
		draggingCard.setCentre (e.getX (), e.getY ());
		draggingCard.addCard ((CardClass) tableau [i].getTopCard ());
		draggingCard.setOriginalPile (i);
		tableau [i].removeTopCard ();
	    }
	}
	// If user clicks on deal deck
	if (dealDeck.getDeckLength () > 0 && ((CardClass) dealDeck.getTopCard ()).isPointInside (e.getX (), e.getY ()))
	{
	    if (dealDeck.getDeckLength () >= tableau.length)
	    {
		for (int i = 0 ; i < tableau.length ; i++)
		{
		    ((CardClass) dealDeck.getTopCard ()).setSide (1);
		    draggingCard.addCard ((CardClass) dealDeck.getTopCard ());
		    dealDeck.removeTopCard ();
		}
	    }
	    else
	    {
		tempDeckLength = dealDeck.getDeckLength ();
		for (int i = 0 ; i < tempDeckLength ; i++)
		{
		    ((CardClass) dealDeck.getTopCard ()).setSide (1);
		    draggingCard.addCard ((CardClass) dealDeck.getTopCard ());
		    dealDeck.removeTopCard ();
		}
	    }
	}
    }


    // The necessary methods.
    public void mouseDragged (MouseEvent e)
    {
	if (draggingCard.getDeckLength () > 0)
	{
	    // TODO draw multiple cards
	    draggingCard.setCentre (e.getX (), e.getY ());
	    repaint ();
	}
    }


    public void mouseClicked (MouseEvent e)
    {

    } // Easthaven class
}
