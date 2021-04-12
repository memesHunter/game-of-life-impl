import java.util.Arrays;
import java.util.Random;

public class Plane {
    private boolean[][] bitRepresentation;
    private Random randomSource = new Random();
    private final int width;
    private final int height;
    private int generation;
    private int aliveCells;

    public int getGeneration() {
        return generation;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getAliveCells() {
        return aliveCells;
    }

    public Plane(int w, int h) {
        this.bitRepresentation = new boolean[w][h];
        for (int i = 0; i < w; i++) {
            Arrays.fill(this.bitRepresentation[i], false);
        }
        this.width = w;
        this.height = h;
        this.aliveCells = 0;
        this.generation = 0;
    }

    public boolean isAlive(int x, int y) {
        return this.bitRepresentation[x][y];
    }

    public void generateRandomPlane() {
        aliveCells = 0;
        generation = 0;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                boolean current = randomSource.nextBoolean();
                aliveCells += (current) ? 1 : 0;
                this.bitRepresentation[j][i] = current;
            }
        }
    }

    public void changeCell(int x, int y) {
        aliveCells += (this.bitRepresentation[x][y]) ? -1 : 1;
        this.bitRepresentation[x][y] = !this.bitRepresentation[x][y];
    }

    public void drawPlaneToConsole() {
        System.out.println("--------");
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print((this.bitRepresentation[x][y]) ? '\u2B1B' : '\u2B1C');
            }
            System.out.println();
        }
    }

    public void importStructure(Structures struct, int startX, int startY) {
        boolean[][] toImport = struct.export();
        for (int y = 0; y < struct.getHeight(); y++) {
            for (int x = 0; x < struct.getWidth(); x++) {
                if (this.bitRepresentation[(this.width + startX + x) % this.width][(this.height + startY + y) % this.height] && !toImport[y][x]) {
                    aliveCells--;
                } else if (!this.bitRepresentation[(this.width + startX + x) % this.width][(this.height + startY + y) % this.height] && toImport[y][x]) {
                    aliveCells++;
                }
                this.bitRepresentation[(this.width + startX + x) % this.width][(this.height + startY + y) % this.height] = toImport[y][x];
            }
        }
    }

    public void lifeCycles(int gens) {
        for (int i = 0; i < gens; i++) {
            lifeCycle();
        }
    }

    public void lifeCycle() {
        generation ++;
        IntList changes = analysePlane();
        for (int i = 0; i < changes.size(); i += 2) {
            //System.err.println("Changing cell " + changes.get(i) + "." + changes.get(i+1));
            changeCell(changes.get(i), changes.get(i+1));
        }
    }

    private IntList analysePlane() {
        IntList coordsToChange = new IntList();
        for (int h = 0; h < this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                if (updateCell(w, h)) {
                    coordsToChange.append(w);
                    coordsToChange.append(h);
                }
            }
        }
        return coordsToChange;
    }

    private boolean updateCell(int x, int y) {
        int aliveNeighbours = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //System.err.println("ANALYSING CELL " + x + "." + y);
                if (i == 0 && j == 0) {
                    //System.err.println("SKIP CELL ITSELF");
                    continue;
                }
                aliveNeighbours += (this.bitRepresentation
                        [(this.width - j + x) % this.width]
                        [(this.height - i + y) % this.height]) ? 1 : 0;
            }
        }
        //System.err.println("ALIVE NEIGHBOURS FOR CELL " + x + "." + y + " = " + aliveNeighbours);
        if (!this.bitRepresentation[x][y] && aliveNeighbours == 3) {
            return true;
        } else if (this.bitRepresentation[x][y]) {
            if (aliveNeighbours < 2) {
                return true;
            } else return aliveNeighbours > 3;
        }
        return false;
    }
}
