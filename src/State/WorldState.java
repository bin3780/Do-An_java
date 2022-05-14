package State;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import Anima.FrameIMG;
import Anima.Loader_CacheData;
import Object.BGMap;
import Object.BossEnemy;
import Object.Cam;
import Object.ManageBullet;
import Object.ManagePartiObject;
import Object.PartiObject;
import Object.PhyMap;
import Object.RobotR;
import Object.DarkRaise;
import Object.FinalBoss;
import Object.RedEyesEnemy;
import Object.Shinobi;
import UI.FrameGame;
import UI.PanelGame;

public class WorldState extends State {

    private BufferedImage bufferedImage;
    private int lastState;

    public ManagePartiObject particularObjectManager;
    public ManageBullet bulletManager;

    public Shinobi shinobi;

    public PhyMap physicalMap;
    public BGMap backgroundMap;
    public Cam camera;

    public static final int finalBossX = 3600;

    public static final int INIT_GAME = 0;
    public static final int TUTORIAL = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;
    public static final int PAUSEGAME = 5;

    public static final int INTROGAME = 0;
    public static final int MEETFINALBOSS = 1;

    public int openIntroGameY = 0;
    public int state = INIT_GAME;
    public int previousState = state;
    public int tutorialState = INTROGAME;

    public int storyTutorial = 0;
    public String[] texts1 = new String[4];

    public String textTutorial;
    public int currentSize = 1;

    private boolean finalbossTrigger = true;
    PartiObject boss;

    FrameIMG avatar = Loader_CacheData.getInstance().getFrameImage("avatar");

    private int numberOfLife = 3;

    public AudioClip bgMusic;

    public WorldState(PanelGame gamePanel) {
        super(gamePanel);
        // TODO Auto-generated constructor stub
        texts1[0] = "Nhân danh bin đẹp trai và hoàng đẹp gái \nFROM\n19DTHC3....";
        texts1[1] = "Chúng em sẽ giải cứu bạn Vũ Trung Nguyên 3Đ \n"
                + "và sau đó sẽ giúp bạn ấy thẳng raaa....";
        texts1[2] = "thử thách 6 ngày 6 đêm cứu bạn Nguyên...."
                + "\nai cản cũng không cứu!!";
        texts1[3] = "      GÉT GÔOOOOOOOOOOO!.....";
        textTutorial = texts1[0];

        bufferedImage = new BufferedImage(FrameGame.SCREEN_WIDTH, FrameGame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        //shinobi = new Shinobi(600, 1500, this); //vi tri goc
        shinobi = new Shinobi(1800, 900, this); 

        // shinobi = new Shinobi(2200, 900, this); //vi tri gate boss
       // shinobi = new Shinobi(2000, 900, this); // vi tri test intro boss

        physicalMap = new PhyMap(0, 0, this);
        backgroundMap = new BGMap(0, 0, this);
        camera = new Cam(0, 50, FrameGame.SCREEN_WIDTH, FrameGame.SCREEN_HEIGHT, this);
        bulletManager = new ManageBullet(this);

        particularObjectManager = new ManagePartiObject(this);
        particularObjectManager.addObject(shinobi);

        initEnemies();

        bgMusic = Loader_CacheData.getInstance().getSound("bgmusic");

    }

    private void initEnemies() {
        //1
        PartiObject Robot1 = new RobotR(1500, 1300, this);
        //Robot1.setDirection(PartiObject.RIGHT_DIR);
        Robot1.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Robot1);
        //2
        PartiObject Robot2 = new RobotR(2200, 1920, this);
        Robot2.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Robot2);
        //3
        PartiObject RedBat1 = new DarkRaise(2700, 1760, this);
        RedBat1.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(RedBat1);
        //4
        PartiObject Death1 = new RedEyesEnemy(3200, 1760, this);
        Death1.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Death1);
        //5
        PartiObject Robot3 = new RobotR(3800, 1760, this);
        Robot3.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Robot3);
        //6
        PartiObject RedBat2 = new DarkRaise(3500, 1650, this);
        RedBat2.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(RedBat2);
        //7
        PartiObject Death2 = new RedEyesEnemy(4400, 1950, this);
        Death2.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Death2);
        //8
        PartiObject RedBat3 = new DarkRaise(5000, 1650, this);
        RedBat3.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(RedBat3);
        //9
        PartiObject Death3 = new RedEyesEnemy(4900, 1450, this);
        Death3.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Death3);
        //10
        PartiObject RedBat4 = new DarkRaise(4100, 1280, this);
        RedBat4.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(RedBat4);
        //11
        PartiObject RedBat5 = new DarkRaise(4970, 1200, this);
        RedBat5.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(RedBat5);
        //12
        PartiObject Death4 = new RedEyesEnemy(5100, 900, this);
        Death4.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Death4);
        //13
        PartiObject Death5 = new RedEyesEnemy(3600, 460, this);
        Death5.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Death5);
        //14
        PartiObject Death6 = new RedEyesEnemy(2750, 460, this);
        Death6.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Death6);
        //15
        PartiObject Robot4 = new RobotR(1800, 460, this);
        Robot4.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Robot4);
        //16
        PartiObject Death7 = new RedEyesEnemy(1000, 460, this);
        Death7.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Death7); 
        //17
        PartiObject RedBat6 = new DarkRaise(1000, 900, this);
        RedBat6.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(RedBat6);
        //18
        PartiObject RedBat7 = new DarkRaise(1600, 750, this);
        RedBat7.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(RedBat7);
        //19
        PartiObject Robot5 = new RobotR(1750, 950, this);
        Robot5.setTeamType(PartiObject.ENEMY_TEAM);
        particularObjectManager.addObject(Robot5);
    }

    public void switchState(int state) {
        previousState = this.state;
        this.state = state;
    }

    private void TutorialUpdate() {
        switch (tutorialState) {
            case INTROGAME:

                if (storyTutorial == 0) {
                    if (openIntroGameY < 450) {
                        openIntroGameY += 50;
                    } else
                        storyTutorial++;

                } else {

                    if (currentSize < textTutorial.length())
                        currentSize++;
                }
                break;
            case MEETFINALBOSS:
                if (storyTutorial == 0) {
                    if (openIntroGameY >= 450) {
                        openIntroGameY -= 1;
                    }
                    if (camera.getPosX() < finalBossX) {
                        camera.setPosX(camera.getPosX() + 2);
                    }

                    if (shinobi.getPosX() < finalBossX + 150) {
                        shinobi.setDirection(PartiObject.RIGHT_DIR);
                        shinobi.run();
                        shinobi.Update();
                    } else {
                        shinobi.stopRun();
                    }

                    if (openIntroGameY < 450 && camera.getPosX() >= finalBossX
                            && shinobi.getPosX() >= finalBossX + 150) {
                        camera.lock();
                        storyTutorial++;
                        shinobi.stopRun();
                        physicalMap.phys_map[14][120] = 1;
                        physicalMap.phys_map[15][120] = 1;
                        physicalMap.phys_map[16][120] = 1;
                        physicalMap.phys_map[17][120] = 1;

                        backgroundMap.map[14][120] = 17;
                        backgroundMap.map[15][120] = 17;
                        backgroundMap.map[16][120] = 17;
                        backgroundMap.map[17][120] = 17;
                    }

                } else {

                    if (currentSize < textTutorial.length())
                        currentSize++;
                }
                break;
        }
    }

    private void drawString(Graphics2D g2, String text, int x, int y) {
        for (String str : text.split("\n"))
            g2.drawString(str, x, y += g2.getFontMetrics().getHeight());
    }

    private void TutorialRender(Graphics2D g2) {
        switch (tutorialState) {
            case INTROGAME:
                int yMid = FrameGame.SCREEN_HEIGHT / 2 - 15;
                int y1 = yMid - FrameGame.SCREEN_HEIGHT / 2 - openIntroGameY / 2;
                int y2 = yMid + openIntroGameY / 2;

                if (storyTutorial >= 1) {
                    g2.drawImage(avatar.getImage(), 600, 350, null);
                    g2.setColor(Color.white);
                    g2.fillRect(150, 450, 460, 100);

                    g2.setColor(Color.BLACK);
                    String text = textTutorial.substring(0, currentSize - 1);
                    drawString(g2, text, 170, 490);
                }

                break;
            case MEETFINALBOSS:
                yMid = FrameGame.SCREEN_HEIGHT / 2 - 15;
                y1 = yMid - FrameGame.SCREEN_HEIGHT / 2 - openIntroGameY / 2;
                y2 = yMid + openIntroGameY / 2;

                g2.setColor(Color.BLACK);
                g2.fillRect(0, y1, FrameGame.SCREEN_WIDTH, FrameGame.SCREEN_HEIGHT / 2);
                g2.fillRect(0, y2, FrameGame.SCREEN_WIDTH, FrameGame.SCREEN_HEIGHT / 2);
                break;
        }
    }

    @Override
    public void Render() {
        // TODO Auto-generated method stub
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();

        if (g2 != null) {

            // NOTE: two lines below make the error splash white screen....
            // need to remove this line
            // g2.setColor(Color.WHITE);
            // g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);

            // physicalMap.draw(g2);

            switch (state) {
                case INIT_GAME:
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, FrameGame.SCREEN_WIDTH, FrameGame.SCREEN_HEIGHT);
                    g2.setColor(Color.WHITE);
                    g2.drawString("MAKE OTAKU SAVE THE WORLD!!", 400, 280);
                    g2.drawString("Press enter to continue", 425, 300);
                    break;
                case PAUSEGAME:
                    g2.setColor(Color.BLACK);
                    g2.fillRect(300, 260, 500, 70);
                    g2.setColor(Color.WHITE);
                    g2.drawString("PAUSED", 520, 290);
                    g2.drawString("Press enter to continue!", 485, 305);
                    break;
                case TUTORIAL:
                    backgroundMap.draw(g2);
                    if (tutorialState == MEETFINALBOSS) {
                        particularObjectManager.draw(g2);
                    }
                    TutorialRender(g2);

                    break;
                case GAMEWIN:
                case GAMEPLAY:
                    backgroundMap.draw(g2);
                    particularObjectManager.draw(g2);
                    bulletManager.draw(g2);

                    g2.setColor(Color.GRAY);
                    g2.fillRect(19, 59, 102, 22);
                    g2.setColor(Color.red);
                    g2.fillRect(160, 20, ((int)(shinobi.getBlood()*2.5)), 30);
                    
                    g2.drawImage(Loader_CacheData.getInstance().getFrameImage("uihp").getImage(), 0,0,
                                null);
                    for (int i = 0; i < numberOfLife; i++) {
                        g2.drawImage(Loader_CacheData.getInstance().getFrameImage("hearth").getImage(), 180 + i * 40, 70,
                                null);
                    }

                    if (state == GAMEWIN) {
                        g2.drawImage(Loader_CacheData.getInstance().getFrameImage("gamewin").getImage(), 300, 300,
                                null);
                    }

                    break;
                case GAMEOVER:
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, FrameGame.SCREEN_WIDTH, FrameGame.SCREEN_HEIGHT);
                    g2.setColor(Color.WHITE);
                    g2.drawString("GAME OVER!", 450, 300);
                    break;

            }

        }
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub
        switch (state) {
            case INIT_GAME:

                break;
            case TUTORIAL:
                TutorialUpdate();

                break;
            case GAMEPLAY:
                particularObjectManager.UpdateObjects();
                bulletManager.UpdateObjects();

                physicalMap.Update();
                camera.Update();
        // shinobi = new Shinobi(2200, 900, this); //vi tri gate boss
       // shinobi = new Shinobi(2000, 900, this); // vi tri test intro boss
                if(shinobi.getPosX() > finalBossX && finalbossTrigger){
                finalbossTrigger = false;
                switchState(TUTORIAL);
                tutorialState = MEETFINALBOSS;
                storyTutorial = 0;
                openIntroGameY = 550;

                //boss = new BossEnemy(finalBossX - 3400, 400, this);
                boss = new BossEnemy(finalBossX - 2000, 400, this);
                boss.setTeamType(PartiObject.ENEMY_TEAM);
                boss.setDirection(PartiObject.LEFT_DIR);
                particularObjectManager.addObject(boss);

                }

                if (shinobi.getState() == PartiObject.DEATH) {
                    numberOfLife--;
                    if (numberOfLife >= 0) {
                        shinobi.setBlood(100);
                        shinobi.setPosY(shinobi.getPosY() - 50);
                        shinobi.setState(PartiObject.NOBEHURT);
                        particularObjectManager.addObject(shinobi);
                    } else {
                        switchState(GAMEOVER);
                        bgMusic.stop();
                    }
                }
                if (!finalbossTrigger && boss.getState() == PartiObject.DEATH)
                    switchState(GAMEWIN);

                break;
            case GAMEOVER:

                break;
            case GAMEWIN:

                break;
        }
    }

    @Override
    public BufferedImage getBufferedImage() {
        // TODO Auto-generated method stub
        return bufferedImage;
    }

    @Override
    public void setPressedButton(int code) {
        // TODO Auto-generated method stub
        switch (code) {

            case KeyEvent.VK_D:
                shinobi.setDirection(shinobi.RIGHT_DIR);
                shinobi.run();
                break;

            case KeyEvent.VK_A:
                shinobi.setDirection(shinobi.LEFT_DIR);
                shinobi.run();
                break;

            case KeyEvent.VK_ENTER:
                if (state == WorldState.INIT_GAME) {
                    if (previousState == WorldState.GAMEPLAY)
                        switchState(WorldState.GAMEPLAY);
                    else
                        switchState(WorldState.TUTORIAL);

                    bgMusic.loop();
                }
                if (state == WorldState.TUTORIAL && storyTutorial >= 1) {
                    if (storyTutorial <= 3) {
                        storyTutorial++;
                        currentSize = 1;
                        textTutorial = texts1[storyTutorial - 1];
                    } else {
                        switchState(WorldState.GAMEPLAY);
                    }

                    // for meeting boss tutorial
                    if (tutorialState == WorldState.MEETFINALBOSS) {
                        switchState(WorldState.GAMEPLAY);
                    }
                }
                break;

            case KeyEvent.VK_SPACE:
                shinobi.jump();
                break;

            case KeyEvent.VK_L:
                shinobi.attack();
                break;
            case KeyEvent.VK_K:
            shinobi.attack2();
            break;

        }
    }

    @Override
    public void setReleasedButton(int code) {
        // TODO Auto-generated method stub
        switch (code) {

            case KeyEvent.VK_D:
                if (shinobi.getSpeedX() > 0)
                    shinobi.stopRun();
                break;

            case KeyEvent.VK_A:
                if (shinobi.getSpeedX() < 0)
                    shinobi.stopRun();
                break;

            case KeyEvent.VK_ENTER:
                if (state == GAMEOVER || state == GAMEWIN) {
                    gamePanel.setState(new StateMenu(gamePanel));
                } else if (state == PAUSEGAME) {
                    state = lastState;
                }
                break;

            case KeyEvent.VK_SPACE:

                break;

            case KeyEvent.VK_ESCAPE:
                lastState = state;
                state = PAUSEGAME;
                break;

        }
    }

}
