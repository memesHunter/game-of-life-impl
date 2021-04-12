import javax.swing.*;
import java.awt.*;

public class StatisticsRenderer extends JPanel {
    private Plane plane;
    private int drawOffset = 20;
    private int[] gens;
    private int[] cells;

    public StatisticsRenderer(Plane plane, int totalGenerations, int step) {
        this.plane = plane;
        gens = new int[totalGenerations / step];
        cells = new int[totalGenerations / step];
        for (int i = 0; i < totalGenerations / step; i++) {
            gens[i] = i*step + drawOffset;
            cells[i] = (plane.getWidth() * plane.getHeight() - plane.getAliveCells()) + drawOffset;
            plane.lifeCycles(step);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(3));

        drawAxis(g2d);
        g2d.setColor(Color.RED);
        g2d.drawPolyline(gens, cells, gens.length);



        this.repaint();
    }

    private void drawAxis(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(drawOffset, drawOffset, drawOffset, plane.getWidth() * plane.getHeight() + drawOffset);
        g2d.drawLine(drawOffset, plane.getWidth() * plane.getHeight() + drawOffset, gens[gens.length - 1] - drawOffset, plane.getWidth() * plane.getHeight() + drawOffset);
    }
}
