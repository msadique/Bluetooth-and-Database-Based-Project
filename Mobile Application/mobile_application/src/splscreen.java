import java.io.IOException;
import javax.microedition.lcdui.game.*;
import javax.microedition.lcdui.*;
import java.util.*;
public class splscreen extends GameCanvas 
{
    //Main main;
    Image img;
    Sprite s1;
    Graphics g;
       
    public splscreen() 
    {
        super(false);
        setFullScreenMode(true);
        
        g= getGraphics();
        try
        {
           img = Image.createImage("/back1.png");
            
        }
        
        catch(IOException e)
        {
            System.err.println("Error loading images" + e.getMessage());
        }
    }
    
    public void paint()
    {
            g.setColor(3);
            g.fillRect(0,0,getWidth(),getHeight());
		
            g.drawImage(img,(getWidth()/2)-(img.getWidth()/2),(getHeight()/2)-(img.getHeight()/2), Graphics.TOP|Graphics.LEFT);
		//g.drawImage(img,(getwidth()/2)-(img.getWidth()/2),0, Graphics.HCENTER|Graphics.VCENTER);
           
    }
    
        public void run()
        {
          //  paint();
        }
    
}
