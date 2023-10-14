package engine.entities;

public class Camera {
  public int x = 0;
  public int y = 0;
  public int z = 0;

  public float anglex = 0;
  public float angley = 0;
  public float anglez = 0;

  public Camera() {

  }

  public void forward(int amount) {
    // Converter ângulos para radianos
    double yawRad = Math.toRadians(angley * 100);
    double pitchRad = Math.toRadians(anglex * 100);

    // Calcular as componentes do vetor de direção
    double dx = -amount * Math.sin(yawRad) * Math.cos(pitchRad);
    double dy = -amount * Math.sin(pitchRad);
    double dz = amount * Math.cos(yawRad) * Math.cos(pitchRad);

    // Atualizar a posição da câmera
    this.x += dx;
    this.y += dy;
    this.z += dz;
  }

  public void backward(int amount) {
    // Converter ângulos para radianos
    double yawRad = Math.toRadians(angley * 100);
    double pitchRad = Math.toRadians(anglex * 100);

    // Calcular as componentes do vetor de direção opostas ao movimento para trás
    double dx = amount * Math.sin(yawRad) * Math.cos(pitchRad);
    double dy = amount * Math.sin(pitchRad);
    double dz = -amount * Math.cos(yawRad) * Math.cos(pitchRad);

    // Atualizar a posição da câmera
    this.x += dx;
    this.y += dy;
    this.z += dz;
  }

  public void left(int amount) {
    // Converter ângulos para radianos
    double yawRad = Math.toRadians(angley * 100);
    double pitchRad = Math.toRadians(anglex * 100);

    // Calcular as componentes do vetor de direção para a esquerda
    double dx = -amount * Math.cos(yawRad);
    double dz = amount * Math.sin(yawRad);

    // Atualizar a posição da câmera
    this.x += dx;
    this.z += dz;
  }

  public void rigth(int amount) {
    // Converter ângulos para radianos
    double yawRad = Math.toRadians(angley * 100);
    double pitchRad = Math.toRadians(anglex * 100);

    // Calcular as componentes do vetor de direção para a direita
    double dx = amount * Math.cos(yawRad);
    double dz = -amount * Math.sin(yawRad);

    // Atualizar a posição da câmera
    this.x += dx;
    this.z += dz;
  }

  public void up(int amount) {
    this.y -= amount;
  }

  public void down(int amount) {
    this.y += amount;
  }

  public void rotateX(float angle) {
    
    anglex = angle;
    if (anglex > 90f) anglex = 90f; 
    if (anglex < -90f) anglex = -90f;
    anglex = anglex / 100;
  }

  public void rotateY(float angle) {

    angley = angle;
    angley = angley / 100;
  }

  public void rotateZ(float angle) {

    anglez = angle;
  }
}
