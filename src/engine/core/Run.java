package engine.core;

import javax.swing.*;
import java.awt.event.*;

import engine.graphics.Renderer;
import game.objects.Cube;

public class Run {

  private Window window = new Window();
  Renderer gameRenderer = new Renderer(window);

  float angleFun = 0;
  
  public Run() {
    SwingUtilities.invokeLater(() -> {

      Timer timer = new Timer(1000 / 30, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          window.clear();
          onTick();
        }
        
      });
      timer.start();
    });
  }

  public void onTick() {
    angleFun += 0.05f;
    if (angleFun >= 360f) angleFun = 0;

    float cubeInitPosX = 200f;
    float cubeInitPosY = 200f;
    float cubeInitPosZ = 0f;

    float cubePosx = cubeInitPosX - window.cam.x;
    float cubePosy = cubeInitPosY - window.cam.y;
    float cubePosz = cubeInitPosZ - window.cam.z;
    
    Cube cube = new Cube(cubePosx, cubePosy, cubePosz, 100, 100, 100, angleFun, angleFun, angleFun);
    gameRenderer.drawCube(cube);
  }
}
