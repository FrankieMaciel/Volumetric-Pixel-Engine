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
    angleFun += 0.1f;
    if (angleFun >= 360f) angleFun = 0;

    Cube cube = new Cube(200f, 200f, 0f, 100, 100, 100, angleFun, angleFun, 0f);
    gameRenderer.drawCube(cube);
  }
}
