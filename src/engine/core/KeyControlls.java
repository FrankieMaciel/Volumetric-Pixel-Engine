package engine.core;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControlls {
  public KeyControlls(JFrame myFrame) {
      myFrame.addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
              int keyCode = e.getKeyCode();

              if (keyCode == KeyEvent.VK_W) {
                  // Tecla "W" pressionada, faça algo
                  System.out.println("Tecla W pressionada");
              } else if (keyCode == KeyEvent.VK_A) {
                  // Tecla "A" pressionada, faça algo
                  System.out.println("Tecla A pressionada");
              } else if (keyCode == KeyEvent.VK_S) {
                  // Tecla "S" pressionada, faça algo
                  System.out.println("Tecla S pressionada");
              } else if (keyCode == KeyEvent.VK_D) {
                  // Tecla "D" pressionada, faça algo
                  System.out.println("Tecla D pressionada");
              }
          }
      });
  }
}