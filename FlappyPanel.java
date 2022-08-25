import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyPanel extends JPanel implements KeyListener, ActionListener {
    final int WIDTH = 525, HEIGHT = 550;
    final int WALLXVELOCITY = 5;
    final int WALLWIDTH = 50;
    int flappyHeight = HEIGHT / 4;
    int flappyV = 0, flappyA = 8, flappyI = 1;
    int score = 0;
    int[] wallx = {WIDTH, WIDTH + WIDTH / 2};
    int[] gap = {(int) (Math.random() * (HEIGHT - 150)), (int) (Math.random() * (HEIGHT - 100))};
    boolean gameOver = false;
    Timer time = new Timer(40, this);

    public FlappyPanel() {
        setSize(WIDTH, HEIGHT);
        setFocusable(true);
        addKeyListener(this);
        setBackground(Color.BLACK);
        new Timer(40, this).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameOver) {
            g.setColor(Color.YELLOW);
            g.drawString("SCORE: " + score, WIDTH / 2, 10);
            drawWall(g);
            logic();
            drawFlappy(g);
        } else {
            g.setColor(Color.YELLOW);
            g.drawString("SCORE: " + score, WIDTH / 2, 10);
        }

    }

    private void drawFlappy(Graphics g) {

        g.setColor(Color.WHITE);
        //g.drawLine(wallx,gap,wallx[]+WALLWIDTH,gap+100 );
        g.fillRect(75, flappyHeight + flappyV, 25, 25);
    }

    private void drawWall(Graphics g) {
        for (int i = 0; i < 2; i++) {
            g.setColor(Color.RED);
            g.fillRect(wallx[i], 0, WALLWIDTH, HEIGHT);
            g.setColor(Color.BLACK);
            g.fillRect(wallx[i], gap[i], WALLWIDTH, 100);
        }
    }

    private void logic() {
        for (int i = 0; i < 2; i++) {
            if (wallx[i] <= 100 && wallx[i] + WALLWIDTH >= 100 || wallx[i] <= 75 && wallx[i] + WALLWIDTH >= 75) {
                if ((flappyHeight + flappyV) >= 0 && (flappyHeight + flappyV) <= gap[i]
                        || (flappyHeight + flappyV + 25) >= gap[i] + 100 && (flappyHeight + flappyV + 25) <= HEIGHT) {
                    gameOver = true;
                }
            }
            if (flappyHeight + flappyV <= 0 || flappyHeight + flappyV + 25 >= HEIGHT) {
                gameOver = true;
            }
            if (75 > wallx[i] + WALLWIDTH) {
                score++;
            }
            if (wallx[i] + WALLWIDTH <= 00) {
                wallx[i] = WIDTH;
                gap[i] = (int) (Math.random() * (HEIGHT - 150));
            }

        }

    }

    public void actionPerformed(ActionEvent e) {
        flappyA += flappyI;
        flappyV += flappyA;
        wallx[0] -= WALLXVELOCITY;
        wallx[1] -= WALLXVELOCITY;
        repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == e.VK_S) {
            time.start();
        }
        if (code == e.VK_SPACE) {
            flappyA = -10;
        }

        if (code == e.VK_R) {
            time.stop();
            flappyHeight = HEIGHT / 4;
            flappyV = 0;
            flappyA = 8;
            score = 0;
            wallx[0] = WIDTH;
            wallx[1] = WIDTH + WIDTH / 2;
            gap[0] = (int) (Math.random() * (HEIGHT - 150));
            gap[1] = (int) (Math.random() * (HEIGHT - 150));
            gameOver = false;
            repaint();
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
