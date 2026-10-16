import java.awt.Graphics;
import java.util.Random;

public class Ball implements Runnable {
    protected int x;
    protected int y;
    protected int vx;
    protected int vy;
    protected int speed;
    protected Thread thread;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        Random rand = new Random();
        this.vx = rand.nextInt(7) - 3;
        this.vy = rand.nextInt(7) - 3;

        this.speed = rand.nextInt(11) + 20;

        thread = new Thread(this);
        thread.start();
    }

    public void draw(Graphics g) {

        g.fillOval(x - 5, y - 5, 10, 10);
    }

    @Override
    public void run() {
        while (true) {
            x += vx;
            y += vy;
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}