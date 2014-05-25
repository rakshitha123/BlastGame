/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MapItems;

/**
 *
 * @author Compaq
 */
public class Player extends MapItem {

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getWeatherShot() {
        return weatherShot;
    }

    public void setWeatherShot(int weatherShot) {
        this.weatherShot = weatherShot;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int direction;
    int weatherShot;
    int health;
    int coins;
    int points;
    int id;

    public Player(int x, int y, int id, int direction, int weatherShot, int health, int coins, int points) {
        super(x, y);
        this.id = id;
        this.direction = direction;
        this.weatherShot = weatherShot;
        this.health = health;
        this.coins = coins;
        this.points = points;
    }
}
