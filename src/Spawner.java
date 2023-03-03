import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {

    public int timer = 0;
    public List<RectObj> rectangles = new ArrayList<>();
    public List<Particula> particulas = new ArrayList<>();

    public void update() {
        timer++;
        if (timer % 40 == 0) {
            rectangles.add(new RectObj(0, new Random().nextInt(480 - 40), 40, 40));
        }

        for (int i = 0; i < rectangles.size(); i++) {
            RectObj current = rectangles.get(i);
            rectangles.get(i).update();

            if (current.x > Main.WIDTH) {
                rectangles.remove(current);
                Main.cont--;
            }

            if (Main.clicado) {
                if (Main.mx >= current.x && Main.mx < current.x + current.width) {
                    if (Main.my >= current.y && Main.my < current.y + current.height) {
                        rectangles.remove(current);
                        Main.pontuacao++;
                        Main.clicado = false;


                        for (int j = 0; j < 50; j++) {
                            particulas.add(new Particula(current.x, current.y, 8, 8, current.color));
                        }
                    }
                }
            }
        }


        for (int i = 0; i < particulas.size(); i++) {
            particulas.get(i).update();

            Particula particula = particulas.get(i);
            if (particula.timer >= 60) {
                particulas.remove(particula);
            }
        }

    }

    public void render(Graphics g) {
        for (int i = 0; i < rectangles.size(); i++) {
            RectObj current = rectangles.get(i);

            Graphics2D g2 = (Graphics2D) g;
            g2.rotate(Math.toRadians(current.rotation), current.x + current.width / 2, current.y + current.height / 2);

            g.setColor(current.color);
            g.fillRect(current.x, current.y, current.width, current.height);

            g2.rotate(Math.toRadians(-current.rotation), current.x + current.width / 2, current.y + current.height / 2);
        }
        for (int i = 0; i < particulas.size(); i++) {
            particulas.get(i).render(g);
        }
    }

}
