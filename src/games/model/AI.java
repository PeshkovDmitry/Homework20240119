package games.model;

import java.util.Random;

import static games.resources.Config.*;

public class AI {

    private Field field;

    public AI(Field field) {
        this.field = field;
    }

    public void turn() {
        int x, y;
        FieldDot recommendedDot =  getRecommendedDot();
        System.out.println(recommendedDot);
        if (recommendedDot.isUndefined()) {
            do {
                x = new Random().nextInt(FIELD_SIZE);
                y = new Random().nextInt(FIELD_SIZE);
            } while (!field.isCellValid(x, y));
        } else {
            x = recommendedDot.getX();
            y = recommendedDot.getY();
        }
        field.setDot(x, y, AI_DOT);
    }

    /**
     * Метод для получения наиболее оптимальной точки
     * @return Оптимальная точка
     */
    public FieldDot getRecommendedDot() {
        FieldDot recommendedDot;
        // Проверяем, есть ли точка, обеспечивающая выигрыш одним ходом
        recommendedDot = getWinnerDot(AI_DOT);
        if (!recommendedDot.isUndefined()) {
            return recommendedDot;
        }
        // Проверяем, есть ли точка, предотвращающая выигрыш человека следующим ходом
        recommendedDot = getWinnerDot(HUMAN_DOT);
        if (!recommendedDot.isUndefined()) {
            return recommendedDot;
        }
        return new FieldDot();
    }

    /**
     * Метод, возвращающий точку, обеспечивающую выигрыш одним ходом
     * @param dot Тип точки (компьютер/игрок)
     * @return Точка игрового поля
     */

    public FieldDot getWinnerDot(char dot) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                char[][] map = field.getMap();
                if (map[i][j] == EMPTY_DOT) {
                    map[i][j] = dot;
                    if (field.checkWin(dot, map)) {
                        return new FieldDot(i, j);
                    }
                }
            }
        }
        return new FieldDot();
    }
}
