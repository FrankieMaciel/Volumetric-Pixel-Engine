package engine.core;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

import engine.graphics.Renderer;
import engine.graphics.volumetric.Cube;

public class Run {

  private Window window = new Window();
  Renderer gameRenderer = new Renderer(window);

  int camx, camy, camz;
  float camxangle, camyangle, camzangle;
  String gameInfo;

  float cubeInitPosX = 10;
  float cubeInitPosY = 10f;
  float cubeInitPosZ = 40f;
  Cube cube = new Cube(
    cubeInitPosX,
    cubeInitPosY,
    cubeInitPosZ,
    10,
    10,
    10,
    0f,
    0f,
    0f
    );

    Cube cube2 = new Cube(
    -10f,
    -10f,
    40f,
    10,
    10,
    10,
    0f,
    0f,
    0f
    );

    Cube cube3 = new Cube(
    10f,
    -10f,
    40f,
    10,
    10,
    10,
    0f,
    0f,
    0f
    );

    Cube cube4 = new Cube(
    -10f,
    10f,
    40f,
    10,
    10,
    10,
    0f,
    0f,
    0f
    );

    Cube cube5 = new Cube(
    0f,
    0f,
    40f,
    10,
    10,
    10,
    0f,
    0f,
    0f
    );
  
  public Run() {
    SwingUtilities.invokeLater(() -> {

      Timer timer = new Timer(1000 / 30, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          if (!window.controlls.isPaused) {
            window.clear();
          }
          onTick();
        }
        
      });
      timer.start();
    });
  }



  public void onTick() {

    if (window.controlls.isPaused) return;

    window.controlls.updateCamera();

    cube5.rotateX(0.1f);
    // cube2.rotateX(0.1f);
    // cube.rotateY(0.1f);
    // cube.rotateZ(0.1f);
    
    gameRenderer.drawCube(cube);
    gameRenderer.drawCube(cube2);
    gameRenderer.drawCube(cube3);
    gameRenderer.drawCube(cube4);
    gameRenderer.drawCube(cube5);

    camx = window.cam.x;
    camy = window.cam.y;
    camz = window.cam.z;

    camxangle = window.cam.anglex;
    camyangle = window.cam.angley;
    camzangle = window.cam.anglez;

    gameInfo = "X: " + String.valueOf(camx) +
                " | Y: " + String.valueOf(camy) +
                " | Z: " + String.valueOf(camz) +
                " | Xangle: " + String.valueOf(camxangle) +
                " | Yangle: " + String.valueOf(camyangle) +
                " | Zangle: " + String.valueOf(camzangle);

    window.writeText(gameInfo, 15, 20, Color.WHITE);
  }
}
