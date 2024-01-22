package games.model;

import static games.resources.Config.*;

public class AI {

    private Field field;

    public AI(Field field) {
        this.field = field;
    }

    public void turn() {
        FieldDot recommendedDot =  getRecommendedDot();
        field.setDot(recommendedDot.getX(), recommendedDot.getY(), AI_DOT);
    }

    /**
     * Метод для получения наиболее оптимальной точки
     * @return Оптимальная точка
     */
    private FieldDot getRecommendedDot() {
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
        // Определяем наиболее выгодную точку
        OptimumDot humanDot = getOptimumDotForChar(HUMAN_DOT);
        OptimumDot aiDot = getOptimumDotForChar(AI_DOT);
        if (aiDot.getRating() > humanDot.getRating()) {
            return new FieldDot(aiDot.getX(), aiDot.getY());
        } else {
            return new FieldDot(humanDot.getX(), humanDot.getY());
        }
    }

    /**
     * Метод, возвращающий точку, обеспечивающую выигрыш одним ходом
     * @param dot Тип точки (компьютер/игрок)
     * @return Точка игрового поля
     */

    private FieldDot getWinnerDot(char dot) {
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

    /**
     * Метод для подсчета наибольшего количества символов
     * среди доступных для сборки линий в данной точке
     * @param x Координата x
     * @param y Координата y
     * @return Максимум
     */
    private int checkLine(int x, int y, char dot) {

        int result = -1;
        char[][] map = field.getMap();
        int count = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (map[x][i] == dot) {
                count++;
            }
            if (map[x][i] == EMPTY_DOT) {
                map[x][i] = dot;
            }
        }
        result = (field.checkWin(dot, map) && result < count) ? count : result;

        map = field.getMap();
        count = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (map[i][y] == dot) {
                count++;
            }
            if (map[i][y] == EMPTY_DOT) {
                map[i][y] = dot;
            }
        }
        result = (field.checkWin(dot, map) && result < count) ? count : result;

        if (x == y) {
            map = field.getMap();
            count = 0;
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (map[i][i] == dot) {
                    count++;
                }
                if (map[i][i] == EMPTY_DOT) {
                    map[i][i] = dot;
                }
            }
            result = (field.checkWin(dot, map) && result < count) ? count : result;
        }

        if (x == FIELD_SIZE - 1 - y) {
            map = field.getMap();
            count = 0;
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (map[i][FIELD_SIZE - 1 - y] == dot) {
                    count++;
                }
                if (map[i][FIELD_SIZE - 1 - y] == EMPTY_DOT) {
                    map[i][FIELD_SIZE - 1 - y] = dot;
                }
            }
            result = (field.checkWin(dot, map) && result < count) ? count : result;
        }
        return result;
    }

    /**
     * Метод для получения координат оптимальной точки
     * @param dot Символ
     * @return Координаты оптимума
     */

    private OptimumDot getOptimumDotForChar(char dot) {
        int max = -1;
        OptimumDot maxCoords = new OptimumDot();
        char[][] map = field.getMap();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                int current = checkLine(i, j, dot);
                if (max < current) {
                    max = current;
                    maxCoords = new OptimumDot(i, j, max);
                }
            }
        }
        return maxCoords;
    }

}
