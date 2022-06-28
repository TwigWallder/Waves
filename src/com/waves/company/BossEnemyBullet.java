package com.waves.company;

import java.awt.*;
import java.util.Random;

public class BossEnemyBullet extends GameObject{

    private Handler handler;
    Random r = new Random();

    public BossEnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        //if(y <= 0 || y >= Game.HEIGHT - 54) velY *= -1;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        //Trail effect
        handler.addObject(new Trail((int) x, (int) y, ID.Trail,Color.YELLOW, 16, 16, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval((int)x, (int) y, 16, 16);
    }
}
