import java.awt.*;
import java.util.Random;

public class RectObj extends Rectangle {

    public Color color;
    public int speed = 0;
    public int rotation = 0;


    public RectObj(int x, int y, int width, int heigth) {
        super(x, y, width, heigth);

        color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));

        speed = new Random().nextInt(8 - 2) + 4;
    }

    public void update() {
        x += speed;
        rotation++;
        if (rotation > 360) {
            rotation = 0;
        }
    }


}
