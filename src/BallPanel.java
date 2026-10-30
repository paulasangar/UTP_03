
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BallPanel extends Panel implements Runnable, ActionListener {

    private List<Ball> ballList;
    private Thread thread;

    public BallPanel() {
        ballList = new ArrayList<>();

        // Start the repainting thread
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw all balls
        for (Ball ball : ballList) {
            ball.draw(g);
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();

            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Create a new ball at the center of the panel
        Ball ball = new Ball(getWidth() / 2, getHeight() / 2) {

            @Override
            public boolean collidingX() {
                return x + vx < 5
                        || x + vx > getWidth() - 5;
            }

            @Override
            public boolean collidingY() {
                return y + vy < 5
                        || y + vy > getHeight() - 5;
            }
        };

        ballList.add(ball);
    }

    public void shake() {
        synchronized (Ball.MONITOR) {
            Ball.MONITOR.notifyAll();
        }
    }
}
