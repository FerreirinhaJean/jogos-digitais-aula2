import java.awt.*;
import java.util.Random;

public class Particula extends Rectangle {
    public Color color;
    public int speed = 0;
    public double dx, dy;
    public double xa, ya;
    public int timer = 0;

    public Particula(int x, int y, int width, int heigth, Color color) {
        super(x, y, width, heigth);

        xa = x;
        ya = y;

        this.color = color;

        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();

        speed = 8;
    }

    public void update() {
        xa += dx * speed;
        ya += dy * speed;

        timer++;
    }

    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillRect((int) xa, (int) ya, width, height);
    }
}
