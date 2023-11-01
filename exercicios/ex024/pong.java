import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongGame extends JPanel implements ActionListener, KeyListener {
    private int ballX = 200, ballY = 200, ballVelX = 1, ballVelY = 1;
    private int paddleX = 150, paddleY = 400, paddleVel = 0;
    private int score = 0;
    private Timer timer;

    public PongGame() {
        timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.WHITE);
        g.fillRect(paddleX, paddleY, 100, 10);
        g.fillOval(ballX, ballY, 20, 20);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 20, 30);
    }

    public void actionPerformed(ActionEvent e) {
        ballX += ballVelX;
        ballY += ballVelY;

        if (ballX <= 0 || ballX >= 780) {
            ballVelX = -ballVelX;
        }
        if (ballY <= 0) {
            ballVelY = -ballVelY;
        }
        if (ballY >= 580) {
            if (ballX >= paddleX && ballX <= paddleX + 100) {
                ballVelY = -ballVelY;
                score++;
            } else {
                ballX = 200;
                ballY = 200;
                ballVelX = 1;
                ballVelY = 1;
                score = 0;
            }
        }
        if (paddleX < 0) {
            paddleX = 0;
        } else if (paddleX > 700) {
            paddleX = 700;
        }
        paddleX += paddleVel;
        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            paddleVel = -2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            paddleVel = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            paddleVel = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Pong Game");
                PongGame game = new PongGame();
                frame.add(game);
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
