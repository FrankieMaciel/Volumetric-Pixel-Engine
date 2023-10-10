package engine.graphics;

import java.awt.*;

import engine.core.Window;
import game.objects.Cube;

public class Renderer {

  Window window;
  private int pixelSize = 10;

  public Renderer(Window w) {
    window = w;
  }

  private void drawLine(
        float[] v1,
        float[] v2,
        Color color
  ) {

    float dx = v2[0] - v1[0];
    float dy = v2[1] - v1[1];

    float length = (float) Math.sqrt(dx * dx + dy * dy);
    float angle = (float) Math.atan2(dy, dx);

    for (int i = 0; i < length; i++) {

        int nx = (int) (v1[0] + Math.cos(angle) * i);
        int ny = (int) (v1[1] + Math.sin(angle) * i);

        window.drawPixel(nx, ny, 1, color);
    }
  }

  public void drawFace(
    float[] v1,
    float[] v2,
    float[] v3,
    float[] v4,
    Color color
  ) {

    float dx = v4[0] - v1[0];
    float dy = v4[1] - v1[1];

    float length = (float) Math.sqrt(dx * dx + dy * dy);
    float angle = (float) Math.atan2(dy, dx);

    for (int i = 0; i < length; i++) {

        int nx = (int) (Math.cos(angle) * i);
        int ny = (int) (Math.sin(angle) * i);

        float[] vv1 = {v1[0] + nx, v1[1] + ny}; 
        float[] vv2 = {v2[0] + nx, v2[1] + ny};

        drawLine(vv1, vv2, color);
    }
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

    // drawFace(
    //   cube.v[3],
    //   cube.v[2],
    //   cube.v[6],
    //   cube.v[7],
    //   Color.PINK
    // );

    // drawFace(
    //   cube.v[0],
    //   cube.v[1],
    //   cube.v[5],
    //   cube.v[4],
    //   Color.WHITE
    // );
  } 

  private int RoundPixel(float value) {
    return (int) (Math.floor(value / pixelSize) * pixelSize);
  }

}