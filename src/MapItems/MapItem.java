/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapItems;

/**
 *
 * @author Compaq
 */
public class MapItem {

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    int xCoordinate;
    int yCoordinate;

    public MapItem(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
}
