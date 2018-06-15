import java.awt.*;

public class ButtonClass extends ShapeClass {

	// Private variables
	private Font iFont;
	private String iText;
	private Color iTextColour, iButtonColour;
	private int textCentreX, textCentreY;

	public void setFont(Font newFont) {
		iFont = newFont;
	}

	public void setText(String newText) {
		iText = newText;
	}

	public void setTextColour(Color newColour) {
		iTextColour = newColour;
	}

	public void setButtonColour(Color newColour) {
		iButtonColour = newColour;
	}

	public void setTextCentre(int x, int y) {
		textCentreX = x;
		textCentreY = y;
	}

	// Draw button
	public void draw(Graphics g) {
		g.setColor(iButtonColour);
		g.setFont(iFont);
		g.fillRoundRect(iCentreX - iWidth/2, iCentreY - iHeight/2, iWidth, iHeight, 10, 10);
		g.setColor(Color.black);
		g.drawRoundRect(iCentreX - iWidth/2 - 1, iCentreY- iHeight/2 - 1, iWidth + 1, iHeight + 1, 10, 10);
		g.setColor(iTextColour);
		g.drawString(iText, textCentreX, textCentreY);
	}
}
