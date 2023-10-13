package engine.core;

import javax.swing.*;
import java.awt.event.*;

import engine.graphics.Renderer;
import engine.graphics.volumetric.Cube;

public class Run {

  private Window window = new Window();
  Renderer gameRenderer = new Renderer(window);

  float cubeInitPosX = 10;
  float cubeInitPosY = 10f;
  float cubeInitPosZ = 20f;
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
    20f,
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
    20f,
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
    20f,
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
    20f,
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
          window.clear();
          onTick();
        }
        
      });
      timer.start();
    });
  }



  public void onTick() {

    cube5.rotateX(0.1f);
    // cube2.rotateX(0.1f);
    // cube.rotateY(0.1f);
    // cube.rotateZ(0.1f);
    
    gameRenderer.drawCube(cube);
    gameRenderer.drawCube(cube2);
    gameRenderer.drawCube(cube3);
    gameRenderer.drawCube(cube4);
    gameRenderer.drawCube(cube5);
  }
}
