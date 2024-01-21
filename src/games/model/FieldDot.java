package games.model;

public class FieldDot {

    private int x;

    private int y;

    public FieldDot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public FieldDot() {
        this(-1,-1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isUndefined() {
        return x == -1 && y == -1;
    }
}
