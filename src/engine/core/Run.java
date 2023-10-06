package engine.core;

import javax.swing.*;
import java.awt.event.*;

import engine.graphics.Renderer;

public class Run {

  private Window window = new Window();
  
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
    Renderer.desenharCirculo(window, 200, 200, 100);
  }
}
