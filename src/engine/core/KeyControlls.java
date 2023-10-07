package engine.core;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.objects.Camera;

public class KeyControlls {

    Camera cam = new Camera();

    public KeyControlls(JFrame myFrame) {
        myFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_W) {
                    cam.forward(1);
                } else if (keyCode == KeyEvent.VK_A) {
                    cam.left(1);
                } else if (keyCode == KeyEvent.VK_S) {
                    cam.backward(1);
                } else if (keyCode == KeyEvent.VK_D) {
                    cam.rigth(1);
                } else if (keyCode == KeyEvent.VK_SHIFT) {
                    cam.up(1);
                } else if (keyCode == KeyEvent.VK_CONTROL) {
                    cam.down(1);
                }
                System.out.println(cam.x);
                System.out.println(cam.y);
                System.out.println(cam.z);
            }
        });
    }
}