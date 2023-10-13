package engine.graphics.volumetric;

import java.util.ArrayList;
import java.util.List;

public interface Object {

  public List<Cube> cubes = new ArrayList<Cube>();

  public float x = 0;
  public float y = 0;
  public float z = 0;

  public float anglex = 0;
  public float angley = 0;
  public float anglez = 0;

  public float scalex = 1;
  public float scaley = 1;
  public float scalez = 1;

  public void onStart();

  public void onUpdate();

}
