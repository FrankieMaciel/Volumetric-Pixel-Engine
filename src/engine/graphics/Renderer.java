package engine.graphics;

import java.awt.*;

import engine.core.Window;
import game.objects.Cube;

public class Renderer {

  Window window;

  public Renderer(Window w) {
    window = w;
  }

  private void drawFace(
        float[] v1,
        float[] v2,
        float[] v3,
        float[] v4,
        Color color
) {
    
  drawLine(v1[0], v1[1], v2[0], v2[1], color);
  drawLine(v2[0], v2[1], v3[0], v3[1], color);
  drawLine(v3[0], v3[1], v4[0], v4[1], color);
  drawLine(v4[0], v4[1], v1[0], v1[1], color);
}

  public void drawCube(Cube cube) {

    drawFace(
      cube.v[0],
      cube.v[1],
      cube.v[2],
      cube.v[3],
      Color.GREEN
    );

    drawFace(
      cube.v[1],
      cube.v[5],
      cube.v[6],
      cube.v[2],
      Color.RED
    );

    drawFace(
      cube.v[4],
      cube.v[0],
      cube.v[3],
      cube.v[7],
      Color.BLUE
    );

    drawFace(
      cube.v[5],
      cube.v[4],
      cube.v[7],
      cube.v[6],
      Color.YELLOW
    );

    drawFace(
      cube.v[3],
      cube.v[2],
      cube.v[6],
      cube.v[7],
      Color.PINK
    );

    drawFace(
      cube.v[0],
      cube.v[1],
      cube.v[5],
      cube.v[4],
      Color.WHITE
    );
  }

  private void drawLine(float x1, float y1, float x2, float y2, Color color) {

    int numOfDivision = 4;
    
    float dx = Math.abs(x2 - x1);
    float dy = Math.abs(y2 - y1);

    int xSteps = (int) (dx / numOfDivision);
    int ySteps = (int) (dy / numOfDivision);

    xSteps = x1 < x2 ? xSteps : -xSteps;
    ySteps = y1 < y2 ? ySteps : -ySteps;

    int nx = (int) x1;
    int ny = (int) y1;

    for (int x = 0; x < numOfDivision; x++) {

      window.drawPixel(nx, ny, 8,color);

      nx += xSteps;
      ny += ySteps;
    }
  }
}
