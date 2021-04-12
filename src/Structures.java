public enum Structures {
    GLIDER(
            new boolean[][]{
                {false, true, false},
                {false, false, true},
                {true, true, true}
            }
    ),

    PULSAR(
            new boolean[][]{
                    {false, false, true, true, true, false, false, false, true, true, true, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {true, false, false, false, false, true, false, true, false, false, false, false, true},
                    {true, false, false, false, false, true, false, true, false, false, false, false, true},
                    {true, false, false, false, false, true, false, true, false, false, false, false, true},
                    {false, false, true, true, true, false, false, false, true, true, true, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, true, true, true, false, false, false, true, true, true, false, false},
                    {true, false, false, false, false, true, false, true, false, false, false, false, true},
                    {true, false, false, false, false, true, false, true, false, false, false, false, true},
                    {true, false, false, false, false, true, false, true, false, false, false, false, true},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, true, true, true, false, false, false, true, true, true, false, false}
            }
    );

    private final boolean[][] struct;

    public int getWidth() {
        return this.struct[0].length;
    }
    public int getHeight() {
        return this.struct.length;
    }

    public boolean[][] export() {
        return this.struct;
    }

    Structures(boolean[][] struct) {
        this.struct = struct;
    }
}
