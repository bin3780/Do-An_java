package Object;

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Anima.Animation;
import Anima.Loader_CacheData;
import State.WorldState;

public class RedEyesEnemy extends PartiObject {

    private Animation forwardAnim, backAnim;
    private float x1, x2, y1, y2;
    private long startTimeToShoot;

    private AudioClip shooting;

    public RedEyesEnemy(float x, float y, WorldState gameWorld) {
        super(x, y, 127, 89, 0, 100, gameWorld);
        backAnim = Loader_CacheData.getInstance().getAnimation("redeye");
        forwardAnim = Loader_CacheData.getInstance().getAnimation("redeye");
        forwardAnim.flipAllImage();
        startTimeToShoot = 0;
        setTimeForNoBehurt(300000000);
        setDamage(10);

        x1 = x - 100;
        x2 = x + 100;
        y1 = y - 50;
        y2 = y + 50;

        setSpeedX(1);
        setSpeedY(1);

        shooting = Loader_CacheData.getInstance().getSound("redeyeshooting");
    }

    @Override
    public void attack() {

        shooting.play();
        Bullet bullet = new BulletRedEye(getPosX(), getPosY(), getGameWorld());

        if (getDirection() == LEFT_DIR)
            bullet.setSpeedX(5);
        else
            bullet.setSpeedX(-5);
        bullet.setTeamType(getTeamType());
        getGameWorld().bulletManager.addObject(bullet);

    }

    public void Update() {
        super.Update();

        if (getPosX() - getGameWorld().shinobi.getPosX() > 0)
            setDirection(PartiObject.RIGHT_DIR);
        else
            setDirection(PartiObject.LEFT_DIR);

        if (getPosX() < x1)
            setSpeedX(1);
        else if (getPosX() > x2)
            setSpeedX(-1);
        setPosX(getPosX() + getSpeedX());

        if (getPosY() < y1)
            setSpeedY(1);
        else if (getPosY() > y2)
            setSpeedY(-1);
        setPosY(getPosY() + getSpeedY());

        if (System.nanoTime() - startTimeToShoot > 1000 * 10000000 * 1.5) {
            attack();
            startTimeToShoot = System.nanoTime();
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x += 20;
        rect.width -= 40;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (!isObjectOutOfCameraView()) {
            if (getState() == NOBEHURT && (System.nanoTime() / 10000000) % 2 != 1) {
                // plash...
            } else {
                if (getDirection() == LEFT_DIR) {
                    backAnim.Update(System.nanoTime());
                    backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int) (getPosY() - getGameWorld().camera.getPosY()), g2);
                } else {
                    forwardAnim.Update(System.nanoTime());
                    forwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()),
                            (int) (getPosY() - getGameWorld().camera.getPosY()), g2);
                }
            }
        }
        // drawBoundForCollisionWithEnemy(g2);
    }

}
