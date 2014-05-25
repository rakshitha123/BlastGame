/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamePackage;

import AI.HumanController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import socketConnections.InputSocket;
import socketConnections.OutputSocket;

/**
 *
 * @author Compaq
 */
public class Game extends StateBasedGame {

    public static int N = 5;
    public static final String gameName = "Tank Game";
    public static final int menu = 0;
    public static final int play = 1;
    public Menu mainMenu;
    public HumanController humanController;

    public Game(String gameName) {

        super(gameName);
        mainMenu = new Menu(menu);
        this.addState(mainMenu);
        this.addState(new Play(play));



        OutputSocket outputSocket = new OutputSocket("localhost", 6000);




        InputSocket inputSocket = new InputSocket(7000);
        inputSocket.addObserver(mainMenu);

        Thread inputSocketThread = new Thread(inputSocket);
        this.humanController = new HumanController(outputSocket, mainMenu);
        inputSocket.addObserver(humanController);
        inputSocketThread.start();


        outputSocket.sendMessage("JOIN#");
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);

    }

    public static void main(String[] args) {

       //System.out.println("test");


        AppGameContainer appGC;
        try {
            appGC = new AppGameContainer(new Game(gameName));
            appGC.setDisplayMode(600, 600, false);
            appGC.start();
        } catch (SlickException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
