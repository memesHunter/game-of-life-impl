import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PlaneRenderer extends JPanel implements ActionListener {
    private Plane plane;
    private int cellSize;
    private Timer timer;

    public PlaneRenderer(Plane plane, int cellSize, int timeStep) {
        this.plane = plane;
        this.cellSize = cellSize;
        timer = new Timer(timeStep, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);

        renderGrid(g2d, plane.getHeight(), plane.getWidth());
        renderAlive(g2d);
        renderInfo(g2d);
    }

    private void renderGrid(Graphics2D g2d, int width, int height) {
        g2d.setStroke(new BasicStroke(cellSize / 10));

        for (int y = 0; y <= (width * cellSize); y += cellSize) {
            g2d.drawLine(0, y, height * cellSize, y);
        }

        for (int x = 0; x <= (height * cellSize); x += cellSize) {
            g2d.drawLine(x, 0, x, width * cellSize);
        }
    }

    private void renderAlive(Graphics2D g2d) {
        for (int y = 0; y < plane.getHeight(); y++) {
            for (int x = 0; x < plane.getWidth(); x++) {
                if (plane.isAlive(x, y)) {
                    g2d.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private void renderInfo(Graphics2D g2d) {
        g2d.drawString(String.format("Generation: %d", plane.getGeneration()), cellSize * (plane.getWidth() + 1), cellSize);
        g2d.drawString(String.format("Alive cells: %d", plane.getAliveCells()), cellSize * (plane.getWidth() + 1), cellSize * 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        plane.lifeCycle();
        repaint();
    }
}