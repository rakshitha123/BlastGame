/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import MapItems.Coins;
import MapItems.LifePack;
import MapItems.Player;
import gamePackage.Menu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import socketConnections.InputSocket;
import socketConnections.OutputSocket;

/**
 *
 * @author Compaq
 */
public class HumanController implements Observer {

//OBSTACLE# 
//CELL_OCCUPIED#
//DEAD#
//TOO_QUICK#
//INVALID_CELL#
//GAME_HAS_FINISHED#
//GAME_NOT_STARTED_YET#
//NOT_A_VALID_CONTESTANT#
    OutputSocket outputSocket;
    String line;
    int[][] matrix;
    String lastMove;
    String path = "";
    Menu menu;
    Coins nearsetCoins;
    LifePack nearestLifePack;
    ArrayList<NodeSquare> result;
    boolean needToRecreatePath;
    int stepsPassed;
     static int a;

    public HumanController(OutputSocket outputSocket, Menu menu) {
        this.outputSocket = outputSocket;
        this.menu = menu;
        this.matrix = new int[20][20];
        this.result = new ArrayList<>();
        this.needToRecreatePath = true;
        this.path = "";


    }

    public Player ShootableFrom() {
        // int currentDirection=menu.player[Integer.parseInt(menu.humanPlayer)].getDirection();

        for (int i = 0; i < gamePackage.Game.N; i++) {
            if ((menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate() == menu.player[i].getxCoordinate() && Math.abs(menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate() - menu.player[i].getxCoordinate()) < 2) || (menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate() == menu.player[i].getxCoordinate() && Math.abs(menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate() - menu.player[i].getxCoordinate()) < 2)) {
                return menu.player[i];
            }

        }
        return null;



    }

    public void findHealthPath() {
        Iterator it = menu.lifePacks.iterator();
        LifePack l = null;
        LifePack lifePacks = null;
        double distance;
        double d;
        if (it.hasNext()) {
            lifePacks = (LifePack) it.next();
            if (!lifePacks.isGrabbed) {
                l = lifePacks;
            }
        } else {
            return;
        }
        distance = Math.pow((double) (l.getxCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate()), 2) + Math.pow((double) (l.getyCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getyCoordinate()), 2);
        lifePacks = l;
        while (it.hasNext()) {
            l = (LifePack) it.next();
            d = Math.pow((double) (l.getxCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate()), 2) + Math.pow((double) (l.getyCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getyCoordinate()), 2);
            if (d < distance) {
                lifePacks = l;
            }
        }
        System.out.println("DISTANCE OF NEAREST:" + distance);
        ArrayList<NodeSquare> queue = new ArrayList<>();
        queue.add(new NodeSquare(menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate(), menu.player[Integer.parseInt(menu.humanPlayer)].getyCoordinate(), null));

        if(distance<30&&a<1000)System.out.println(createPath(breadthFirstSearchLifePacks(queue)));
        System.out.println("Line is  " + line);


    }

    public void findPath() {
        Iterator it = menu.coins.iterator();
        Coins c = null;
        Coins coins = null;
        double distance;
        double d;
        if (it.hasNext()) {
            coins = (Coins) it.next();
            if (!coins.isGrabbed) {
                c = coins;
            }
        } else {
            return;
        }
        distance = Math.pow((double) (c.getxCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate()), 2) + Math.pow((double) (c.getyCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getyCoordinate()), 2);
        nearsetCoins = c;
        while (it.hasNext()) {
            c = (Coins) it.next();
            d = Math.pow((double) (c.getxCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate()), 2) + Math.pow((double) (c.getyCoordinate() - menu.player[Integer.parseInt(menu.humanPlayer)].getyCoordinate()), 2);
            if (d < distance) {
                nearsetCoins = c;
            }
        }
        System.out.println("DISTANCE OF NEAREST:" + distance);
        ArrayList<NodeSquare> queue = new ArrayList<>();
        queue.add(new NodeSquare(menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate(), menu.player[Integer.parseInt(menu.humanPlayer)].getyCoordinate(), null));

        if(distance<100&& a<1000)System.out.println(createPath(breadthFirstSearch(queue)));
        System.out.println("Line is  " + line);


    }

    public String createPath(ArrayList<NodeSquare> queue) {
        ArrayList<NodeSquare> result2 = new ArrayList<>();

        while (!result.isEmpty()) {
            result2.add(result.remove(result.size() - 1));

        }

        Iterator it = result2.iterator();
        int currentDirection = 0;

        NodeSquare previous = null;

        try {
            previous = (NodeSquare) it.next();
        } catch (NoSuchElementException e) {
        };

        NodeSquare current;

        while (it.hasNext()) {
            current = (NodeSquare) it.next();

            if (current.x > previous.x) {  //should be east direction=1 ?
                if (currentDirection == 3) {
                    this.path += "3";
                    line += "R";
                } else {
                    this.path += "33";
                    line += "RR";
                }
                currentDirection = 3;
            }
            if (current.x < previous.x) {      //should be west direction=3 ?
                if (currentDirection == 1) {
                    this.path += "1";
                    line += "LL";
                } else {
                    this.path += "11";
                    line += "LL";
                }
                currentDirection = 1;
            }
            if (current.y > previous.y) {
                if (currentDirection == 0) {
                    this.path += "0";
                    this.line += "D";
                } else {
                    this.path += "00";
                    this.line += "D";
                }
                currentDirection = 0;
            }
            if (current.y < previous.y) {
                if (currentDirection == 2) {
                    this.path += "2";
                    this.line += "U";
                } else {
                    this.path += "22";
                    this.line += "UU";
                }
                currentDirection = 2;
            }

            previous = current;
        }
        
        if(path.length()%4==0){
        path+="5";
        }
        return this.path;
//        this.path = "";
//        NodeSquare node = null;
//        int x = 0;
//        int y = 0;
//        int direction = menu.player[Integer.parseInt(menu.humanPlayer)].getDirection();
//        System.out.println("INITIAL DIRECTION IS" + direction);
//        if (queue.size() >= 2) {
//            node = queue.remove(queue.size() - 1);
//            x = node.x;
//            y = node.y;
//
//            System.out.println("removed" + node.x + "," + node.y);
//
//            NodeSquare current;
//
//            current = queue.remove(queue.size() - 1);
//
//            do {
//                System.out.println("removed" + current.x + "," + current.y + "to compare with" + x + "," + y);
//                if (x < current.x) {
//                    if (direction == 1) {
//                        path += "1";
//                    } else {
//                        path += "11";
//                    }
//                    direction = 2;
//                }
//                if (x > current.x) {
//                    if (direction == 3) {
//                        path += "3";
//                    } else {
//                        path += "33";
//                    }
//
//                    direction = 0;
//                }
//                if (y < current.y) {
//                    if (direction == 1) {
//                        path += "1";
//                    } else {
//                        path += "11";
//                    }
//                    direction = 1;
//                }
//                if (y < current.y) {
//                    if (direction == 0) {
//                        path += "0";
//                    } else {
//                        path += "00";
//                    }
//                    direction = 3;
//                }
//
//                x = current.x;
//                y = current.y;
//
//                System.out.println("SIZE IS" + queue.size());
//                System.out.println("PATH STRING:" + this.path);
//                current = queue.remove(queue.size() - 1);
//
//            } while (!queue.isEmpty());
//        }
//        return path;
    }

    public ArrayList<NodeSquare> breadthFirstSearch(ArrayList<NodeSquare> queue) {
        
        a++;
        if(a>1000){a=0;return result;}


        NodeSquare node = null;
        //NodeSquare node=new NodeSquare(menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate(),menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate() ,null);
        if (!queue.isEmpty()) {

            node = queue.remove(0);
            matrix[node.x][node.y] = -1;

//          
//        } else {
//            


            if (isInside(node.x + 1, node.y) && matrix[node.x + 1][node.y] == 40) {
                NodeSquare current = new NodeSquare(node.x + 1, node.y, node);
                while (current != null) {
                    System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);

                return result;
            }


            if (isInside(node.x - 1, node.y) && matrix[node.x - 1][node.y] == 40) {
                NodeSquare current = new NodeSquare(node.x - 1, node.y, node);
                while (current != null) {
                  //  System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);
                return result;
            }

            if (isInside(node.x, node.y + 1) && matrix[node.x][node.y + 1] == 40) {
                NodeSquare current = new NodeSquare(node.x, node.y + 1, node);
                while (current != null) {
                   // System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);
                return result;
            }


            if (isInside(node.x, node.y - 1) && matrix[node.x][node.y - 1] == 40) {
                NodeSquare current = new NodeSquare(node.x, node.y - 1, node);
                while (current != null) {
                  //  System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);
                return result;
            }
            if (isInside(node.x + 1, node.y) && matrix[node.x + 1][node.y] == 0) {
                //System.out.println("X is " + node.x + "Y is " + node.y + "value is:" + menu.canvas[node.x][node.y]);
                queue.add(new NodeSquare(node.x + 1, node.y, node));

            }
            if (isInside(node.x - 1, node.y) && matrix[node.x - 1][node.y] == 0) {
               // System.out.println("X is " + node.x + "Y is" + node.y + "value is:" + menu.canvas[node.x][node.y]);
                queue.add(new NodeSquare(node.x - 1, node.y, node));

            }
            if (isInside(node.x, node.y + 1) && matrix[node.x][node.y + 1] == 0) {
               // System.out.println("X is" + node.x + "Y is" + node.y + "value is:" + menu.canvas[node.x][node.y]);
                queue.add(new NodeSquare(node.x, node.y + 1, node));

            }
            if (isInside(node.x, node.y - 1) && matrix[node.x][node.y - 1] == 0) {
               // System.out.println("X is" + node.x + "Y is" + node.y + "value is:" + menu.canvas[node.x][node.y]);

                queue.add(new NodeSquare(node.x, node.y - 1, node));

            }


            breadthFirstSearch(queue);
        }
        return result;







    }

    public ArrayList<NodeSquare> breadthFirstSearchLifePacks(ArrayList<NodeSquare> queue) {

a++;
        NodeSquare node = null;
        //NodeSquare node=new NodeSquare(menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate(),menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate() ,null);
        if (!queue.isEmpty()) {

            node = queue.remove(0);
            matrix[node.x][node.y] = -1;

//          
//        } else {
//            


            if (isInside(node.x + 1, node.y) && matrix[node.x + 1][node.y] == 50) {
                NodeSquare current = new NodeSquare(node.x + 1, node.y, node);
                while (current != null) {
                    System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);

                return result;
            }


            if (isInside(node.x - 1, node.y) && matrix[node.x - 1][node.y] == 50) {
                NodeSquare current = new NodeSquare(node.x - 1, node.y, node);
                while (current != null) {
                    System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);
                return result;
            }

            if (isInside(node.x, node.y + 1) && matrix[node.x][node.y + 1] == 50) {
                NodeSquare current = new NodeSquare(node.x, node.y + 1, node);
                while (current != null) {
                    System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);
                return result;
            }


            if (isInside(node.x, node.y - 1) && matrix[node.x][node.y - 1] == 50) {
                NodeSquare current = new NodeSquare(node.x, node.y - 1, node);
                while (current != null) {
                    System.out.println("NODE :::" + current.x + "," + current.y + "," + current.direction);
                    result.add(current);
                    current = current.parent;
                }
                System.out.println("returning" + result);
                return result;
            }
            if (isInside(node.x + 1, node.y) && matrix[node.x + 1][node.y] == 0) {
                System.out.println("X is " + node.x + "Y is " + node.y + "value is:" + menu.canvas[node.x][node.y]);
                queue.add(new NodeSquare(node.x + 1, node.y, node));

            }
            if (isInside(node.x - 1, node.y) && matrix[node.x - 1][node.y] == 0) {
                System.out.println("X is " + node.x + "Y is" + node.y + "value is:" + menu.canvas[node.x][node.y]);
                queue.add(new NodeSquare(node.x - 1, node.y, node));

            }
            if (isInside(node.x, node.y + 1) && matrix[node.x][node.y + 1] == 0) {
                System.out.println("X is" + node.x + "Y is" + node.y + "value is:" + menu.canvas[node.x][node.y]);
                queue.add(new NodeSquare(node.x, node.y + 1, node));

            }
            if (isInside(node.x, node.y - 1) && matrix[node.x][node.y - 1] == 0) {
                System.out.println("X is" + node.x + "Y is" + node.y + "value is:" + menu.canvas[node.x][node.y]);

                queue.add(new NodeSquare(node.x, node.y - 1, node));

            }


            breadthFirstSearchLifePacks(queue);
        }
        return result;







    }

    public boolean isInside(int x, int y) {  //Check whether the x,y values are within the given range 10x10
//        int c_x = nearsetCoins.getxCoordinate();
//        int c_y = nearsetCoins.getyCoordinate();
//        int p_x = menu.player[Integer.parseInt(menu.humanPlayer)].getxCoordinate();
//        int p_y = menu.player[Integer.parseInt(menu.humanPlayer)].getyCoordinate();
//        // boolean p = ((c_x >= p_x & x >= p_x & x <= c_x) | (c_x <= p_x & x <= p_x & x >= c_x)) & ((c_y >= p_y & x >= p_y & x <= c_y) | (c_y <= p_y & x <= p_y & x >= c_y));
        boolean p = (x >= 0 & x <= 10) & (y >= 0 & y <= 10);
        if (p) {
            return true;
        } else {
            return false;
        }
    }

    public void moveUp() {
        outputSocket.sendMessage("UP#");
    }

    public void moveDown() {
        outputSocket.sendMessage("DOWN#");
    }

    public void moveLeft() {
        outputSocket.sendMessage("LEFT#");
    }

    public void moveRight() {
        outputSocket.sendMessage("RIGHT#");
    }

    public void shoot() {
        outputSocket.sendMessage("SHOOT#");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof InputSocket) {
            String serverString = (String) arg;
            System.out.println("Feed Back" + serverString);

            analyseString(serverString);


        }
    }

    private void analyseString(String serverString) {



 if (serverString.startsWith("G") && menu.coins.size() > 0) {

            if (stepsPassed > 6) {
                needToRecreatePath = true;
                path = "";
                line = "";
            }


            if (needToRecreatePath) {
                
                a=0;
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        matrix[i][j] = menu.canvas[i][j];
                        System.out.print("*" + matrix[i][j] + " ");
                    }
                    System.out.println();
                }


                if (menu.player[Integer.parseInt(menu.humanPlayer)].getHealth() <= 40 && menu.lifePacks.size() > 0) {
                    this.findHealthPath();

                } 
                
                else {
                    System.out.println("finding path");
                    findPath();
                }
                this.needToRecreatePath = false;
            } 

           if(path.length()!=0){ getNextMove();}
            stepsPassed++;
        }

    }

    private void getNextMove() {
        System.out.println("PATH &&&&&&&&&&&&&&" + path);
        char c = this.path.charAt(0);

        switch (c) {
            case '2':
                moveUp();
                break;
            case '3':
                moveRight();
                break;
            case '0':
                moveDown();
                break;
            case '1':
                moveLeft();
                break;
            case '5':
                shoot();
                break;
            default:
                break;
        }
        if (path.length() >= 2) {
            path = path.substring(1);
        } else {
            path = path.substring(0);
        }
    }
}
