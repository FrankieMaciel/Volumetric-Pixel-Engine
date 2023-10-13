package engine.graphics.volumetric;

import java.awt.Color;

import engine.entities.Camera;

public class Face {
    public float[] v1;
    public float[] v2;
    public float[] v3;
    public float[] v4;

    public Color color;

    public Face(
      float[] nv1,
      float[] nv2,
      float[] nv3,
      float[] nv4,
      Color ncolor
      ) {
        v1 = nv1;
        v2 = nv2;
        v3 = nv3;
        v4 = nv4;
        color = ncolor;
    }

  public double calculateAverageDistance() {

    float depth = 0;

    depth += v1[2];
    depth += v2[2];
    depth += v3[2];
    depth += v4[2];

    return depth;
  }

  public boolean isFacingCamera(Camera camera) {

    float[] vectorA = {v2[0] - v1[0], v2[1] - v1[1], v2[2] - v1[2]};
    float[] vectorB = {v3[0] - v1[0], v3[1] - v1[1], v3[2] - v1[2]};

    float[] cameraToFace = {
        v1[0] - camera.x,
        v1[1] - camera.y,
        v1[2] - camera.z
    };

    float[] normal = {
        vectorA[1] * vectorB[2] - vectorA[2] * vectorB[1],
        vectorA[2] * vectorB[0] - vectorA[0] * vectorB[2],
        vectorA[0] * vectorB[1] - vectorA[1] * vectorB[0]
    };

    float dotProduct = normal[0] * cameraToFace[0] + normal[1] * cameraToFace[1] + normal[2] * cameraToFace[2];

    return dotProduct > 0;
  }
}

