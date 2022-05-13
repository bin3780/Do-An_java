package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Anima.Animation;
import Anima.Loader_CacheData;
import State.WorldState;

public class RobotRBullet extends Bullet {

    private Animation forwardAnim, backAnim;

    public RobotRBullet(float x, float y, WorldState gameWorld) {
        super(x, y, 60, 30, 1.0f, 10, gameWorld);
        forwardAnim = Loader_CacheData.getInstance().getAnimation("robotRbullet");
        backAnim = Loader_CacheData.getInstance().getAnimation("robotRbullet");
        backAnim.flipAllImage();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        if (getSpeedX() > 0) {
            forwardAnim.Update(System.nanoTime());
            forwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                    (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        } else {
            backAnim.Update(System.nanoTime());
            backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                    (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }
        // drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        super.Update();
    }

    @Override
    public void attack() {
    }

}
