package engine.core;

import javax.swing.*;

import engine.entities.Camera;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControlls {

    int WalkingSpeed = 5;
    int RunnigSpeed = 10;
    int moveSpeed = WalkingSpeed;

    public boolean isPaused = false;

    private Camera cam;

    private boolean movingForward = false;
    private boolean movingBackward = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    public KeyControlls(JFrame myFrame, Camera camera) {

        cam = camera;
        myFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W) {
                    movingForward = true;
                } else if (keyCode == KeyEvent.VK_A) {
                    movingLeft = true;
                } else if (keyCode == KeyEvent.VK_S) {
                    movingBackward = true;
                } else if (keyCode == KeyEvent.VK_D) {
                    movingRight = true;
                } else if (keyCode == KeyEvent.VK_SPACE) {
                    movingUp = true;
                } else if (keyCode == KeyEvent.VK_SHIFT) {
                    movingDown = true;
                } else if (keyCode == KeyEvent.VK_CONTROL) {
                    moveSpeed = RunnigSpeed;
                } else if (keyCode == KeyEvent.VK_ESCAPE) {
                    isPaused = !isPaused;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W) {
                    movingForward = false;
                } else if (keyCode == KeyEvent.VK_A) {
                    movingLeft = false;
                } else if (keyCode == KeyEvent.VK_S) {
                    movingBackward = false;
                } else if (keyCode == KeyEvent.VK_D) {
                    movingRight = false;
                } else if (keyCode == KeyEvent.VK_SPACE) {
                    movingUp = false;
                } else if (keyCode == KeyEvent.VK_SHIFT) {
                    movingDown = false;
                } else if (keyCode == KeyEvent.VK_CONTROL) {
                    moveSpeed = WalkingSpeed;
                }
            }
        });
    }

    public void updateCamera() {
        if (movingForward) {
            cam.forward(moveSpeed);
        }
        if (movingLeft) {
            cam.left(moveSpeed);
        }
        if (movingBackward) {
            cam.backward(moveSpeed);
        }
        if (movingRight) {
            cam.rigth(moveSpeed);
        }
        if (movingUp) {
            cam.up(moveSpeed);
        }
        if (movingDown) {
            cam.down(moveSpeed);
        }
    }
}