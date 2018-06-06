import hsa.Console;
import java.awt.*;

public abstract class ShapeClass {
	protected Color iColour;
	protected int iWidth, iHeight, iCentreX, iCentreY;

	public abstract void draw(Graphics g);

	public void setWidth(int iNewWidth) {
		iWidth = iNewWidth;
	}

	public void setHeight(int iNewHeight) {
		iHeight = iNewHeight;
	}

	public void setCentre(int iNewCentreX, int iNewCentreY) {
		iCentreX = iNewCentreX;
		iCentreY = iNewCentreY;
	}

	public void setColor(Color iNewColor) {
		iColour = iNewColor;
	}

	public int getWidth() {
		return iWidth;
	}

	public int getHeight() {
		return iHeight;
	}

	public int getCentreX() {
		return iCentreX;
	}

	public int getCentreY() {
		return iCentreY;
	}

	public Color getColor() {
		return iColour;
	}

	public boolean isPointInside (int x, int y) {
		if (x > iCentreX - iWidth/2 && x < iCentreX + iWidth/2 && y > iCentreY - iHeight/2 && y < iCentreY + iHeight/2) {
			return true;
		}
		return false;
	}
	
	public void erase(Graphics g) {
		Color cOldColor = iColour;
		setColor(Color.white);
		draw(g);
		setColor(cOldColor);
	}

	public void delay(int iDelayTime) {
		long lFinalTime = System.currentTimeMillis() + iDelayTime;
		do {
		} while (lFinalTime >= System.currentTimeMillis());
	}
}
