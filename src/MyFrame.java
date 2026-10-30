
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends Frame {

    private BallPanel ballPanel;
    private Panel buttonPanel;
    private Button addBall;
    private Button shakePanel;

    public MyFrame() {

        // Create the ball panel
        ballPanel = new BallPanel();

        // Create the buttons
        addBall = new Button("Add Ball");
        shakePanel = new Button("Shake Balls");

        // Create the button panel
        buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(addBall);
        buttonPanel.add(shakePanel);

        // Set background colors
        ballPanel.setBackground(Color.YELLOW);
        buttonPanel.setBackground(Color.GREEN);

        // Set the frame layout
        setLayout(new BorderLayout());

        add(ballPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Register the Add Ball listener
        addBall.addActionListener(ballPanel);

        // Register the Shake Balls listener
        shakePanel.addActionListener(e -> ballPanel.shake());

        // Set the window size
        setSize(500, 500);

        // Close the application when the window is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Display the window
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
