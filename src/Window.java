import javax.swing.*;

public class Window extends JFrame {

    public Window(PlaneRenderer planeRenderer) {
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(planeRenderer);
        this.setVisible(true);
    }

    public Window(StatisticsRenderer statisticsRenderer) {
        this.setSize(1300, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(statisticsRenderer);
        this.setVisible(true);
    }
}