public class Main {
    public static void main(String[] args) {
        Plane test = new Plane(20, 33);

        //test.importStructure(Structures.PULSAR, 2, 9);


        test.generateRandomPlane();

        //Window testWindow = new Window(new PlaneRenderer(test, 7, 100));

        Window testStats = new Window(new StatisticsRenderer(test, 1200, 1));

    }
}
