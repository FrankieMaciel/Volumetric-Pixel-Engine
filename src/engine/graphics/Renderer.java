package engine.graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import engine.core.Window;
import engine.graphics.volumetric.Cube;
import engine.graphics.volumetric.Face;

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

  public void drawTrapezoid(
    float[] topLeft,
    float[] topRight,
    float[] bottomLeft,
    float[] bottomRight,
    Color color
  ) {
    // Calculate the midpoints of the top and bottom edges
    float[] topMid = {(topLeft[0] + topRight[0]) / 2, (topLeft[1] + topRight[1]) / 2};
    float[] bottomMid = {(bottomLeft[0] + bottomRight[0]) / 2, (bottomLeft[1] + bottomRight[1]) / 2};

    // Draw the two triangles that form the trapezoid
    drawTriangle(topLeft, topRight, bottomRight, color);
    drawTriangle(topLeft, bottomLeft, bottomRight, color);
  }

  public void drawTriangle(
    float[] v1,
    float[] v2,
    float[] v3,
    Color color
  ) {
      drawLine(v1, v2, color);
      drawLine(v2, v3, color);
      drawLine(v3, v1, color);
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

    List<Face> faces = new ArrayList<>();
    float[][] cv = cube.getPoints(window.cam, window);

    if (cube.isOffScreen) return;

    faces.add(new Face(cv[0],cv[1],cv[2],cv[3],Color.GREEN));
    faces.add(new Face(cv[5],cv[4],cv[7],cv[6],Color.YELLOW));

    faces.add(new Face(cv[2],cv[6],cv[5],cv[1],Color.BLUE));
    faces.add(new Face(cv[7],cv[3],cv[0],cv[4],Color.RED));

    faces.add(new Face(cv[2],cv[3],cv[7],cv[6],Color.PINK));
    faces.add(new Face(cv[0],cv[4],cv[5],cv[1],Color.WHITE));

    Collections.sort(faces, new CameraDistanceComparator());

    for (Face face : faces) {
      // if (face.isFacingCamera(window.cam)) {

        if (face.v1[2] < 20 && face.v2[2] < 20 & face.v3[2] < 20 & face.v4[2] < 20) continue;

        drawTrapezoid(face.v1, face.v2, face.v3, face.v4, face.color);
      // }
    }
  } 

  static class CameraDistanceComparator implements Comparator<Face> {
    @Override
    public int compare(Face face1, Face face2) {
        double distance1 = face1.calculateAverageDistance();
        double distance2 = face2.calculateAverageDistance();

        if (distance1 < distance2) {
            return 1;
        } else if (distance1 > distance2) {
            return -1;
        } else {
            return 0;
        }
    }
  }

  private int RoundPixel(float value) {
    return (int) (Math.floor(value / pixelSize) * pixelSize);
  }

}