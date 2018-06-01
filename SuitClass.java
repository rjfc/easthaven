import hsa.Console;
import java.awt.*;

public abstract class SuitClass extends ShapeClass
{
    public void setWidth (int iNewWidth)
    {
	super.setHeight ((int) (iNewWidth / 0.8));
	super.setWidth (iNewWidth);
    }


    public void setHeight (int iNewHeight)
    {
	super.setHeight (iNewHeight);
	super.setWidth ((int) (iNewHeight * 0.8));
    }
}


