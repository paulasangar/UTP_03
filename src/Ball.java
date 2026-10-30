
import java.awt.*;
import java.util.Random;

public abstract class Ball implements Runnable {

    // Shared monitor for all ball threads
    public static final Object MONITOR = new Object();

    protected int x;
    protected int y;
    protected int vx;
    protected int vy;
    protected int speed;
    protected Thread thread;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        Random random = new Random();

        // Random velocity between -3 and 3
        vx = random.nextInt(7) - 3;
        vy = random.nextInt(7) - 3;

        // Random speed between 20 and 30 milliseconds
        speed = random.nextInt(11) + 20;

        // Start the ball thread
        thread = new Thread(this);
        thread.start();
    }

    public void draw(Graphics g) {
        g.fillOval(x - 5, y - 5, 10, 10);
    }

    public abstract boolean collidingX();

    public abstract boolean collidingY();

    @Override
    public void run() {
        while (true) {
            x += vx;
            y += vy;

            try {
                Thread.sleep(speed);

                // Check for a horizontal collision
                if (collidingX()) {
                    vx *= -1;
                    x += vx;

                    synchronized (MONITOR) {
                        MONITOR.wait();
                    }
                }

                // Check for a vertical collision
                if (collidingY()) {
                    vy *= -1;
                    y += vy;

                    synchronized (MONITOR) {
                        MONITOR.wait();
                    }
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void wakeUp() {
        synchronized (MONITOR) {
            MONITOR.notifyAll();
        }
    }
}
