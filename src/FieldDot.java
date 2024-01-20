public class FieldDot {

    private int x;

    private int y;

    private boolean isUndefined;

    public FieldDot(int x, int y) {
        this.x = x;
        this.y = y;
        this.isUndefined = false;
    }

    public FieldDot() {
        this(-1,-1);
        this.isUndefined = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isUndefined() {
        return isUndefined;
    }
}
