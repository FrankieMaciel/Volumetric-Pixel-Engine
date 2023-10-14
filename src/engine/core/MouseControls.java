package engine.core;

import javax.swing.*;

import engine.entities.Camera;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseControls {
    private JFrame frame;
    private Camera cam;

    private int lastX, lastY;
    private int currentX, currentY;

    private boolean isMousePressed = false;

    public MouseControls(JFrame myFrame, Camera camera, KeyControlls kc) {
        frame = myFrame;
        cam = camera;

        // Crie um cursor invisível
        Image cursorInvisivel = Toolkit.getDefaultToolkit().createImage(new byte[0]);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorInvisivel, new java.awt.Point(0, 0), "invisibleCursor");

        lastX = frame.getWidth() / 2;
        lastY = frame.getHeight() / 2;

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

              if (kc.isPaused) return;

              int centerX = frame.getWidth() / 2;
              int centerY = frame.getHeight() / 2;

              if (isMousePressed) {

                currentX = e.getX();
                currentY = e.getY();

                int deltaX = currentX - centerX;
                int deltaY = currentY - centerY;

                cam.rotateX(deltaY);
                cam.rotateY(-deltaX);
              }
            }
        });

        frame.addMouseListener(new MouseAdapter() {
          @Override
            public void mousePressed(MouseEvent e) {

                if (kc.isPaused) return;

                isMousePressed = true;

                frame.setCursor(cursor);

                try {
                  Robot robot = new Robot();

                  robot.mouseMove(lastX, lastY);

                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (kc.isPaused) return;

                isMousePressed = false;
                frame.setCursor(Cursor.getDefaultCursor());
                lastX = e.getXOnScreen();
                lastY = e.getYOnScreen();
            }
        });
    }
}