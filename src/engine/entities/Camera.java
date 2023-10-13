package engine.entities;

public class Camera {
  public int x = 0;
  public int y = 0;
  public int z = 0;

  public Camera() {

  }

  public void forward(int amount) {
    this.z += amount;
  }

  public void backward(int amount) {
    this.z -= amount;
  }

  public void left(int amount) {
    this.x -= amount;
  }

  public void rigth(int amount) {
    this.x += amount;
  }

  public void up(int amount) {
    this.y += amount;
  }

  public void down(int amount) {
    this.y -= amount;
  }

  public void rotate(int angle) {
    
  }
}
