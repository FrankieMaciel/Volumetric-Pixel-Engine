package game.objects;

public class Cube {

  private int xSize;
  private int ySize;
  private int zSize;

  public float[][] v;
  
  public Cube(
          float x,
          float y,
          float z,
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

    rotateX(v, angleX);
    rotateY(v, angleY);
    rotateZ(v, angleZ);

    for (int i = 0; i < v.length; i++) {
      v[i][0] = v[i][0] + x;
      v[i][1] = v[i][1] + y;
      v[i][2] = v[i][2] + z;
    }
  }

  public void rotateX(float[][] vertices, float angle) {
    float cosA = (float) Math.cos(angle);
    float sinA = (float) Math.sin(angle);
    
    for (int i = 0; i < vertices.length; i++) {
        float y = vertices[i][1];
        float z = vertices[i][2];
        vertices[i][1] = y * cosA - z * sinA;
        vertices[i][2] = y * sinA + z * cosA;
    }
  }

  public void rotateY(float[][] vertices, float angle) {
    float cosA = (float) Math.cos(angle);
    float sinA = (float) Math.sin(angle);
    
    for (int i = 0; i < vertices.length; i++) {
        float x = vertices[i][0];
        float z = vertices[i][2];
        vertices[i][0] = x * cosA + z * sinA;
        vertices[i][2] = -x * sinA + z * cosA;
    }
  }

  public void rotateZ(float[][] vertices, float angle) {
    float cosA = (float) Math.cos(angle);
    float sinA = (float) Math.sin(angle);
    
    for (int i = 0; i < vertices.length; i++) {
        float x = vertices[i][0];
        float y = vertices[i][1];
        vertices[i][0] = x * cosA - y * sinA;
        vertices[i][1] = x * sinA + y * cosA;
    }
  }
}
