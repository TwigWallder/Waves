package com.waves.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            //Play button
            if (mouseOver(mx, my, 210, 150, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }

            //Help button
            if (mouseOver(mx, my, 210, 250, 200, 64)) {
                game.gameState = Game.STATE.Help;
            }


            //Quit button
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(1);
            }
        }

        //Back button for help
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }

        //Retry button
        if (game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                game.gameState = Game.STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }
        }
    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Waves", 235, 70);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Exit", 270, 390);
        } else if (game.gameState == Game.STATE.Help){
            Font fnt3 = new Font("arial", 1, 15);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt = new Font("arial", 1, 50);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use the 'WASD' keys to move around in this fast phased game.", 100, 90);
            g.drawString("W = Up, A = Left, S = Down, D = Right.", 100, 110);
            g.drawString("In this game your goal is to dodge enemies and get as many", 100, 130);
            g.drawString("points as possible. Every 10 levels there is a boss enemy.", 100, 150);
            g.setFont(fnt2);
            g.setColor(Color.blue);
            g.drawString("ENJOY!", 250, 190);

            g.setColor(Color.white);
            g.setFont(fnt2);
            g.drawString("Back", 270, 390);
            g.drawRect(210, 350, 200, 64);

        } else if (game.gameState == Game.STATE.End){
            Font fnt3 = new Font("arial", 1, 15);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt = new Font("arial", 1, 50);

            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("GameOver", 180, 70);

            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Your score: " + hud.getScore(), 245, 200);

            g.setColor(Color.white);
            g.setFont(fnt2);
            g.drawString("Retry ?", 255, 390);
            g.drawRect(210, 350, 200, 64);

        }
    }
}
