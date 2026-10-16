import java.awt.Panel;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

public class BallPanel extends Panel implements Runnable, ActionListener {
    private List<Ball> ballList;

    public BallPanel() {
        ballList = new ArrayList<>();

        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        synchronized (ballList) {
            for (Ball ball : ballList) {
                ball.draw(g);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int startX = getWidth() > 0 ? getWidth() / 2 : 250;
        int startY = getHeight() > 0 ? getHeight() / 2 : 250;

        synchronized (ballList) {
            ballList.add(new Ball(startX, startY));
        }
    }

}