/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapItems;

import gamePackage.Menu;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Compaq
 */
public class LifePack extends MapItem implements Runnable {
public int lifeTime;
public boolean isGrabbed;
public Menu menu;
    public LifePack(int x, int y,int lifeTime,Menu menu) {
        super(x, y);
        this.lifeTime=lifeTime;
        this.menu=menu;
    }

    @Override
    public void run() {
        
       exist(); 
    }
    
    public void exist(){
    menu.canvas[super.xCoordinate][super.yCoordinate]=50;
        try {
            Thread.sleep(lifeTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Coins.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   menu.canvas[super.xCoordinate][super.yCoordinate]=0;
    }
    
}
