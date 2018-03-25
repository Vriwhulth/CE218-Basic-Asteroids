package game1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ap16718.
 */

public class Keys extends KeyAdapter implements Controller {
    Action action;

    Timer t = new Timer();
    TimerTask tt;

    public Keys() {
        action = new Action();
    }

    public Action action() {
        // this is defined to comply with the standard interface
        return action;
    }

    // method that controls what happens when the key is pressed
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 1;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = -1;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = +1;
                break;
            case KeyEvent.VK_SPACE:
                if(tt != null){
                    return;
                }
                tt = new TimerTask() {
                    @Override
                    public void run() {
                        action.shoot = true;
                    }
                };
                t.scheduleAtFixedRate(tt,0,500);
                break;
        }
    }
    // method that controls what happens when the key is released
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 0;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = 0;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = 0;
                break;
            case KeyEvent.VK_SPACE:
                tt.cancel();
                tt = null;
                action.shoot = false;
                break;
        }
    }
}