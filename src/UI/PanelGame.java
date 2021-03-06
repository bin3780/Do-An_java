package UI;

import javax.swing.JPanel;

import State.State;
import State.StateMenu;
import State.WorldState;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelGame extends JPanel implements Runnable,KeyListener{


        State gameState;
    
        InputController inputManager;
        
        Thread gameThread;
    
        public boolean isRunning = true;
    
        public PanelGame(){
    
            gameState = new StateMenu(this);
            gameState = new WorldState(this);
            
            inputManager = new InputController(gameState);
    
        }
    
        public void startGame(){
            gameThread = new Thread(this);
            gameThread.start();
        }
        @Override
        public void run() {
    
            long previousTime = System.nanoTime();
            long currentTime;
            long sleepTime;
    
            long period = 1000000000/59;
    
            while(isRunning){
    
                gameState.Update();
                gameState.Render();
    
    
                repaint();
    
                currentTime = System.nanoTime();
                sleepTime = period - (currentTime - previousTime);
                try{
    
                        if(sleepTime > 0)
                                Thread.sleep(sleepTime/1000000);
                        else Thread.sleep(period/2000000);
    
                }catch(Exception e){}
    
                previousTime = System.nanoTime();
            }
    
        }
    
        public void paint(Graphics g){
    
            g.drawImage(gameState.getBufferedImage(), 0, 0, this);
    
        }
    
    
        public void setState(State state) {
            gameState = state;
            inputManager.setState(state);
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            inputManager.setPressedButton(e.getKeyCode());
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            inputManager.setReleasedButton(e.getKeyCode());
            
        }
        
    
}
