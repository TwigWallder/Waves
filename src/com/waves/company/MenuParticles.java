package com.waves.company;

import java.awt.*;
import java.util.Random;

public class MenuParticles extends GameObject{

    private Handler handler;

    Random r = new Random();

    private Color col;

    int dir = 0;

    public MenuParticles(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(7 - -7) + -7);
        velY = (r.nextInt(7 - -7) + -7);
        if(velX == 0) velX = 1;
        if(velY == 0) velY = 1;

        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        if(y <= 0 || y >= Game.HEIGHT - 54) velY *= -1;

        //Trail effect
        handler.addObject(new Trail((int) x, (int) y, ID.Trail,col, 16, 16, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(col);
        g.fillOval((int) x, (int) y, 16, 16);

    }
}
