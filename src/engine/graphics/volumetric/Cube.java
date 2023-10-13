package engine.graphics.volumetric;

import java.util.Arrays;

import engine.core.Window;
import engine.entities.Camera;

public class Cube {

  private int xSize;
  private int ySize;
  private int zSize;

  private float x;
  private float y;
  private float z;

  private float[][] v;
  public float[][] cv;
  
  public Cube(
          float xpos,
          float ypos,
          float zpos,
          int sizeX,
          int sizeY,
          int sizeZ,
          float angleX,
          float angleY,
          float angleZ
  ) {
    xSize = sizeX;
    ySize = sizeY;
    zSize = sizeZ;
    x = xpos;
    y = ypos;
    z = zpos;

    float xHalf = xSize / 2;
    float yHalf = ySize / 2;
    float zHalf = zSize / 2;

    v = new float[][] {
      {-xHalf, -yHalf ,-zHalf}, // 0
      {+xHalf, -yHalf ,-zHalf}, // 1
      {+xHalf, +yHalf ,-zHalf}, // 2
      {-xHalf, +yHalf ,-zHalf}, // 3

      {-xHalf, -yHalf ,+zHalf}, // 4
      {+xHalf, -yHalf ,+zHalf}, // 5
      {+xHalf, +yHalf ,+zHalf}, // 6
      {-xHalf, +yHalf ,+zHalf}, // 7
    };

    cv = new float[v.length][v[0].length];

    for (int i = 0; i < v.length; i++) {
        for (int j = 0; j < v[i].length; j++) {
            cv[i][j] = v[i][j];
        }
    }
  }

  public void setPosition(float x, float y, float z) {
    for (int i = 0; i < v.length; i++) {
      cv[i][0] = v[i][0] + x;
      cv[i][1] = v[i][1] + y;
      cv[i][2] = v[i][2] + z;
    }
  }

  public void rotateX(float angle) {
    float cosA = (float) Math.cos(angle);
    float sinA = (float) Math.sin(angle);
    
    for (int i = 0; i < v.length; i++) {
        float y = v[i][1];
        float z = v[i][2];
        v[i][1] = y * cosA - z * sinA;
        v[i][2] = y * sinA + z * cosA;
    }
  }

  public void rotateY(float angle) {
    float cosA = (float) Math.cos(angle);
    float sinA = (float) Math.sin(angle);
    
    for (int i = 0; i < v.length; i++) {
        float x = v[i][0];
        float z = v[i][2];
        v[i][0] = x * cosA + z * sinA;
        v[i][2] = -x * sinA + z * cosA;
    }
  }

  public void rotateZ(float angle) {
    float cosA = (float) Math.cos(angle);
    float sinA = (float) Math.sin(angle);
    
    for (int i = 0; i < cv.length; i++) {
        float x = v[i][0];
        float y = v[i][1];
        v[i][0] = x * cosA - y * sinA;
        v[i][1] = x * sinA + y * cosA;
    }
  }

  private float transform2DTo3D(float xy, float z) {
    float angleRadians = (float ) ((45.0 / 180) * Math.PI);
    return (float) (xy / z * Math.tan(angleRadians / 2));
  }

  public float[][] getPoints(Camera cam, Window w) {

    float[][] ccv = new float[v.length][3];

    for (int i = 0; i < ccv.length; i++) {

      float dx = (v[i][0] + x) - cam.x;
      float dy = (v[i][1] + y) - cam.y;
      float dz = (v[i][2] + z) - cam.z;

      ccv[i][0] = transform2DTo3D(dx, dz);
      ccv[i][1] = transform2DTo3D(dy, dz);
      ccv[i][2] = dz;

      ccv[i][0] += (ccv[i][0] * w.altura) + (w.largura / 2);
      ccv[i][1] += (ccv[i][1] * w.altura) + (w.altura / 2);
    }

    return ccv;
  }
}


