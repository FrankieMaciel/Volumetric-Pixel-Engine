package engine.graphics;

import java.awt.*;
import java.util.Random;

import engine.core.Window;

public class Renderer {

  public Renderer() {}

  public static void desenharCirculo(Window w, double x, double y, int raio) {
    double PI = Math.PI;
    double angle, x1, y1;

    Random random = new Random();
    int randomNumber = random.nextInt(100 + 1 - 1) + 1;

    for (angle = 0; angle < 360; angle += 0.1) {
        x1 = randomNumber * Math.cos(angle * PI / 180);
        y1 = randomNumber * Math.sin(angle * PI / 180);

        int nx = (int) (x + x1);
        int ny = (int) (y + y1);
        

        w.drawPixel(nx, ny, Color.RED);
    }
  }

  public void drawFace(Window w, int v1, int v2, int v3, int v4) {

    

  }
}
