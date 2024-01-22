package games.model;

public class OptimumDot extends FieldDot {

    private int rating;

    public OptimumDot(int x, int y, int rating) {
        super(x,y);
        this.rating = rating;
    }

    public OptimumDot() {
        this(-1,-1,-1);
    }

    public int getRating() {
        return rating;
    }

}
