package engine.graphics;

import java.awt.*;

import engine.core.Window;
import game.objects.Cube;

public class Renderer {

  Window window;

  public Renderer(Window w) {
    window = w;
  }

  public void drawCube(Cube cube) {

    for (int i = 0; i < 8; i++) {

      int xPos = (int) cube.v[i][0];
      int yPos = (int) cube.v[i][1];
      int zPos = (int) cube.v[i][2];

      window.drawPixel(xPos, yPos, 10, Color.GREEN);
    }
  }
}
