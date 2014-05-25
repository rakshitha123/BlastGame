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
public class Coins extends MapItem implements Runnable {
    
    public boolean isGrabbed;
    public Menu menu;

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int lifeTime;
    int value;
    public Coins(int x, int y,int lifeTime,int value,Menu menu) {
        super(x, y);
        this.lifeTime=lifeTime;
        this.value=value;
        this.isGrabbed=false;
        this.menu=menu;
    }

    @Override
    public void run() {
        
       exist(); 
    }
    
    public void exist(){
    menu.canvas[super.xCoordinate][super.yCoordinate]=40;
        try {
            Thread.sleep(lifeTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Coins.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   menu.canvas[super.xCoordinate][super.yCoordinate]=0;
    }
    
    
}
