import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable, MouseListener {

    public static final int WIDTH = 640, HEIGTH = 480;
    public static int cont = 100;
    public static int pontuacao = 0;
    public static int mx, my;
    public static boolean clicado = false;
    public static boolean gameOver = false;
    public Spawner spawner;

    public static void main(String[] args) {
        Main main = new Main();
        JFrame jFrame = new JFrame("Meu Jogo - Jogos Digitais");
        jFrame.add(main);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);

        new Thread(main).start();
    }

    public Main() {
        Dimension dimension = new Dimension(WIDTH, HEIGTH);
        this.setPreferredSize(dimension);
        this.addMouseListener(this);

        spawner = new Spawner();
    }

    public void update() {
        if (gameOver == false) {
            spawner.update();

            if (cont <= 0) {
                cont = 100;
                gameOver = true;
            }
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGTH);

        if (gameOver == false) {

            g.setColor(Color.green);
            g.fillRect(WIDTH / 2 - 145, 20, cont * 3, 20);
            g.setColor(Color.white);
            g.drawRect(WIDTH / 2 - 145, 20, 300, 20);


            spawner.render(g);
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", WIDTH / 2 - 150, HEIGTH / 2);
            g.drawString("Your Final Score: "+pontuacao, WIDTH / 2 - 200, HEIGTH / 2 + 40);

        }
        bs.show();
    }

    @Override
    public void run() {
        while (true) {

            update();
            render();

            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicado = true;
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}