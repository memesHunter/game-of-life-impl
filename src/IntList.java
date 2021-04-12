import java.util.Arrays;
public class IntList {
    private int[] ints = new int[16];
    private int len = 0;

    private void lenCheck(int index) {
        if (index >= ints.length) {
            ints = Arrays.copyOf(ints, index * 2);
        }
    }

    public int size() {
        return len;
    }

    public void replace(int index, int num) {
        lenCheck(index);
        ints[index] = num;
    }

    public void append(int num) {
        lenCheck(len);
        ints[len] = num;
        len++;
    }

    public int get(int index) {
        if (!(index < 0 || index >= len)) {
            return ints[index];
        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < len - 1; i++) {
            out.append(ints[i]);
            out.append(' ');
        }
        out.append(ints[len - 1]);
        return out.toString();
    }
}
