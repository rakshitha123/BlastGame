/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamePackage;

import MapItems.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import socketConnections.InputSocket;

/**
 *
 * @author Compaq
 */
public class Menu extends BasicGameState implements Observer {

    public ArrayList<Brick> bricks;
    public ArrayList<Stone> stones;
    public ArrayList<Water> water;
    public ArrayList<Coins> coins;
    public ArrayList<LifePack> lifePacks;
    //ArrayList<Player> players;
    public Player[] player;
    GameContainer gc;
    Graphics graphics;
    int i;
    public int[][] canvas;
    public String humanPlayer;
    // NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    //0 grass
    //1 fullbrick
    //2 stone
    //3 water

    //10 tankb1_n
    //11 tankb1_e
    //12 tankb1_s
    //13 tankb1_w
    //14 tankb1_n
    //15 tankb1_e
    //16 tankb1_s
    //17 tankb1_w
    //18 tankb1_n
    //19 tankb1_e
    //20 tankb1_s
    //21 tankb1_w
    //22 tankb1_n
    //23 tankb1_e
    //24 tankb1_s
    //25 tankb1_w
    public Menu(int state) {

        bricks = new ArrayList<>();
        stones = new ArrayList<>();
        water = new ArrayList<>();
        coins = new ArrayList<>();
        lifePacks = new ArrayList<>();
        //  players = new ArrayList<>();
        player = new Player[5];
        for (int j = 0; j < 5; j++) {
            player[j] = new Player(0, 0, 0, 0, 0, 0, 0, 0);
        }
        canvas = new int[20][20];
        i = 0;
    }

    @Override
    public int getID() {
        return 0;

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

//          for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                   
//                    System.out.print(canvas[i][j]+" ");
//                }
//                System.out.println();
//            }

        Image grass = new Image("res/floor.png");
        Image brick = new Image("res/brick.png");
        Image stone = new Image("res/stone.png");
        Image water = new Image("res/water.png");
        Image tankb1_n = new Image("res/tankb1_n.png");
        Image tankb1_e = new Image("res/tankb1_e.png");
        Image tankb1_s = new Image("res/tankb1_s.png");
        Image tankb1_w = new Image("res/tankb1_w.png");
        Image tankg1_n = new Image("res/tankg1_n.png");
        Image tankg1_e = new Image("res/tankg1_e.png");
        Image tankg1_s = new Image("res/tankg1_s.png");
        Image tankg1_w = new Image("res/tankg1_w.png");
        Image tankp1_n = new Image("res/tankp1_n.png");
        Image tankp1_e = new Image("res/tankp1_e.png");
        Image tankp1_s = new Image("res/tankp1_s.png");
        Image tankp1_w = new Image("res/tankp1_w.png");
        Image tankf1_n = new Image("res/tankf1_n.png");
        Image tankf1_e = new Image("res/tankf1_e.png");
        Image tankf1_s = new Image("res/tankf1_s.png");
        Image tankf1_w = new Image("res/tankf1_w.png");
        Image tanky1_n = new Image("res/tanky1_n.png");
        Image tanky1_e = new Image("res/tanky1_e.png");
        Image tanky1_s = new Image("res/tanky1_s.png");
        Image tanky1_w = new Image("res/tanky1_w.png");

        Image coins = new Image("res/coins.png");
        Image health = new Image("res/health.png");
        for (int j = 0; j < 600; j = j + 30) {

            for (int k = 0; k < 600; k = k + 30) {
                if (canvas[j / 30][k / 30] == 0) {
                    grphcs.drawImage(grass, j, k);
                }
                if (canvas[j / 30][k / 30] == 1) {
                    grphcs.drawImage(brick, j, k);
                }
                if (canvas[j / 30][k / 30] == 2) {
                    grphcs.drawImage(stone, j, k);
                }
                if (canvas[j / 30][k / 30] == 3) {
                    grphcs.drawImage(water, j, k);
                }


                if (canvas[j / 30][k / 30] == 10) {
                    grphcs.drawImage(tankb1_n, j, k);

                }
                if (canvas[j / 30][k / 30] == 11) {
                    grphcs.drawImage(tankb1_e, j, k);

                }
                if (canvas[j / 30][k / 30] == 12) {
                    grphcs.drawImage(tankb1_s, j, k);

                }
                if (canvas[j / 30][k / 30] == 13) {
                    grphcs.drawImage(tankb1_w, j, k);

                }
                if (canvas[j / 30][k / 30] == 14) {
                    grphcs.drawImage(tankf1_n, j, k);

                }
                if (canvas[j / 30][k / 30] == 15) {
                    grphcs.drawImage(tankf1_e, j, k);

                }
                if (canvas[j / 30][k / 30] == 16) {
                    grphcs.drawImage(tankf1_s, j, k);

                }
                if (canvas[j / 30][k / 30] == 17) {
                    grphcs.drawImage(tankf1_w, j, k);

                }
                if (canvas[j / 30][k / 30] == 18) {
                    grphcs.drawImage(tankp1_n, j, k);

                }
                if (canvas[j / 30][k / 30] == 19) {
                    grphcs.drawImage(tankp1_e, j, k);

                }
                if (canvas[j / 30][k / 30] == 20) {
                    grphcs.drawImage(tankp1_s, j, k);

                }
                if (canvas[j / 30][k / 30] == 21) {
                    grphcs.drawImage(tankp1_w, j, k);

                }
                if (canvas[j / 30][k / 30] == 22) {
                    grphcs.drawImage(tankg1_n, j, k);

                }
                if (canvas[j / 30][k / 30] == 23) {
                    grphcs.drawImage(tankg1_e, j, k);

                }
                if (canvas[j / 30][k / 30] == 24) {
                    grphcs.drawImage(tankg1_s, j, k);

                }
                if (canvas[j / 30][k / 30] == 25) {
                    grphcs.drawImage(tankg1_w, j, k);

                }
                if (canvas[j / 30][k / 30] == 26) {
                    grphcs.drawImage(tanky1_n, j, k);

                }
                if (canvas[j / 30][k / 30] == 27) {
                    grphcs.drawImage(tanky1_e, j, k);
                }
                if (canvas[j / 30][k / 30] == 28) {
                    grphcs.drawImage(tanky1_s, j, k);

                }
                if (canvas[j / 30][k / 30] == 29) {
                    grphcs.drawImage(tanky1_w, j, k);

                }

                if (canvas[j / 30][k / 30] == 40) {
                    grphcs.drawImage(coins, j, k);

                }
                if (canvas[j / 30][k / 30] == 50) {
                    grphcs.drawImage(health, j, k);

                }
            }

        }


//       Image logo=new Image("res/logo.png");
//       grphcs.drawImage(logo, 20, 20);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof InputSocket) {
            String serverString = (String) arg;
            System.out.println("updated the string:" + serverString);
            analyseString(serverString);
            i++;


        }
    }

    public void analyseString(String s) {
        //I:P0:2,6;9,8;1,3;3,1;1,4;3,6;5,8:2,4;8,4;8,1;0,3;7,1;4,3;6,8;4,2:4,8;9,7;9,3;7,4;2,7;8,3;3,2;5,7;3,8;2,3
        StringTokenizer st = new StringTokenizer(s);
        String firstChar = st.nextToken(":");

        if (firstChar.equals("I")) {
            System.out.println("Initial step:" + s);
            initStaticComponents(s);

        }

        if (firstChar.equals("G")) {
            globalUpdate(s);
            System.out.println("Global update:" + s);
        }

        if (firstChar.equals("C")) {
            coinUpdates(s);
            System.out.println("Coins:" + s);

        }
        if (firstChar.equals("L")) {

            lifePackUpdates(s);


        } else {
        }
    }

    public void coinUpdates(String s) {


        StringTokenizer st = new StringTokenizer(s, ":,");
        st.nextToken();
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            System.out.println("x coordnate of coins:" + x);
            int y = Integer.parseInt(st.nextToken());
            System.out.println("y coordnate of coins:" + y);
            int lifeTime = Integer.parseInt(st.nextToken());
            System.out.println("life time of coins:" + lifeTime);
            int value = Integer.parseInt(st.nextToken());
            System.out.println("value of coins:" + value);

            canvas[x][y] = 40;
            Coins newCoin = new Coins(x, y, lifeTime, value, this);
            coins.add(newCoin);
            Thread newCoinThread = new Thread(newCoin);
            newCoinThread.start();


        }

    }

    public void globalUpdates(String s) {
    }

    public void initStaticComponents(String s) {
        //I:P0:2,6;9,8;1,3;3,1;1,4;3,6;5,8:2,4;8,4;8,1;0,3;7,1;4,3;6,8;4,2:4,8;9,7;9,3;7,4;2,7;8,3;3,2;5,7;3,8;2,3
        StringTokenizer st = new StringTokenizer(s);

        String brickCoordinates = null;
        String stoneCoordinates = null;
        String waterCoordinates = null;
        while (st.hasMoreTokens()) {
            st.nextToken(":");
            humanPlayer = st.nextToken(":").substring(1);
            brickCoordinates = st.nextToken(":");
            stoneCoordinates = st.nextToken(":");
            waterCoordinates = st.nextToken();

            System.out.println("human player:" + humanPlayer);
            System.out.println("brick coordinates:" + brickCoordinates);
            System.out.println("stone coordinates:" + stoneCoordinates);
            System.out.println("water coordinates:" + waterCoordinates);

        }

        st = new StringTokenizer(brickCoordinates + ";", ",;");
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());

            int y = Integer.parseInt(st.nextToken());
            bricks.add(new Brick(x, y));


            System.out.println("Brick in" + x + " and " + y);
            canvas[x][y] = 1;

        }

        st = new StringTokenizer(stoneCoordinates + ";", ",;");
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stones.add(new Stone(x, y));

            System.out.println("Stone in" + x + " and " + y);
            canvas[x][y] = 2;
        }

        st = new StringTokenizer(waterCoordinates + ";", ",;");
        while (st.hasMoreTokens()) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            water.add(new Water(x, y));

            System.out.println("Water in" + x + " and " + y);
            canvas[x][y] = 3;
        }



    }

    public void globalUpdate(String s) {
        ArrayList<String> playersDetails = new ArrayList<>();
        ArrayList<String> damageDetails = new ArrayList<>();
        String playerString;
        String damages;
        String s2 = s.substring(2);
        System.out.println("@@@@@@" + s2);
        StringTokenizer st = new StringTokenizer(s2, ":#");


        String s3;
        int i = 10;
        while (st.hasMoreTokens()) {
            s3 = st.nextToken();

            if (s3.startsWith("P")) {

                StringTokenizer st2 = new StringTokenizer(s3, ";,");
                Scanner sc = new Scanner(st2.nextToken().substring(1));

                int id = sc.nextInt();
                System.out.println("Player id is:" + id);
                int x = Integer.parseInt(st2.nextToken());
                System.out.println("x Coordinate:" + x);
                int y = Integer.parseInt(st2.nextToken());
                System.out.println("y Coordinate:" + y);
                int direction = Integer.parseInt(st2.nextToken());
                System.out.println("direction is:" + direction);
                int weatherShot = Integer.parseInt(st2.nextToken());
                System.out.println("weather shot:" + weatherShot);
                int health = Integer.parseInt(st2.nextToken());
                System.out.println("health:" + health);
                int coins = Integer.parseInt(st2.nextToken());
                System.out.println("coins:" + coins);
                int points = Integer.parseInt(st2.nextToken());
                System.out.println("points:" + points);


                // players.add(new Player(x, y, id, direction, weatherShot, health, coins, points));
                canvas[player[id].getxCoordinate()][player[id].getyCoordinate()] = 0;
                player[id].setxCoordinate(x);
                player[id].setyCoordinate(y);
                player[id].setId(id);
                player[id].setDirection(direction);
                player[id].setWeatherShot(weatherShot);
                player[id].setHealth(health);
                player[id].setCoins(coins);
                player[id].setPoints(points);

                if (direction == 0) {
                    canvas[x][y] = i + 0;
                }
                if (direction == 1) {
                    canvas[x][y] = i + 1;
                }
                if (direction == 2) {
                    canvas[x][y] = i + 2;
                }
                if (direction == 3) {
                    canvas[x][y] = i + 3;
                }



            } else {
                //analyse damage levels
            }

            i = i + 4;
        }
        // System.out.println("Player string is:"+playerString);
        //  System.out.println("Damages:"+damages);





    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

    private void lifePackUpdates(String s) {


        StringTokenizer st = new StringTokenizer(s, ":,");
        while (st.hasMoreTokens()) {
            st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            System.out.println("x coordnate of coins:" + x);
            int y = Integer.parseInt(st.nextToken());
            System.out.println("y coordnate of coins:" + y);
            int lifeTime = Integer.parseInt(st.nextToken());
            System.out.println("life time of coins:" + lifeTime);


            canvas[x][y] = 50;
            LifePack newLifePack = new LifePack(x, y, lifeTime, this);
            lifePacks.add(newLifePack);
            Thread newLifePackThread = new Thread(newLifePack);
            newLifePackThread.start();

        }

    }
}
