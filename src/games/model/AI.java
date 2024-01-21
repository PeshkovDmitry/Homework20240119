package games.model;

import games.model.Advisor;
import games.model.FieldDot;

import java.util.Random;

public class AI {
    private final char DOT;
    private Random random;

    public AI(char ch){
        DOT = ch;
        random = new Random();
    }

    public void turn(Field field) {
        int x, y;
        FieldDot recommendedDot =  Advisor.getRecommendedDot(field);
        if (recommendedDot.isUndefined()) {
            do {
                x = random.nextInt(field.getSize());
                y = random.nextInt(field.getSize());
            } while (!field.isCellValid(x, y));
        } else {
            x = recommendedDot.getX();
            y = recommendedDot.getY();
        }
        field.setDot(x, y, DOT);
    }
}
