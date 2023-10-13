package engine.core;

import javax.swing.*;

import engine.entities.Camera;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControlls {

    public KeyControlls(JFrame myFrame, Camera camera) {
        myFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == KeyEvent.VK_W) {
                    camera.forward(10);
                } else if (keyCode == KeyEvent.VK_A) {
                    camera.left(10);
                } else if (keyCode == KeyEvent.VK_S) {
                    camera.backward(10);
                } else if (keyCode == KeyEvent.VK_D) {
                    camera.rigth(10);
                } else if (keyCode == KeyEvent.VK_SHIFT) {
                    camera.up(10);
                } else if (keyCode == KeyEvent.VK_CONTROL) {
                    camera.down(10);
                }
                System.out.println(camera.x);
                System.out.println(camera.y);
                System.out.println(camera.z);
            }
        });
    }
}