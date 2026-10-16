import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
    private BallPanel ballPanel;
    private Panel buttonPanel;
    private Button addBall;
    private Button shakePanel;

    public MyFrame() {

        ballPanel = new BallPanel();
        addBall = new Button("Add Ball");
        shakePanel = new Button("Shake Balls");
        buttonPanel = new Panel();

        addBall.addActionListener(ballPanel);

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addBall);
        buttonPanel.add(shakePanel);

        setLayout(new BorderLayout());

        add(ballPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        ballPanel.setBackground(Color.YELLOW);
        buttonPanel.setBackground(Color.GREEN);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}