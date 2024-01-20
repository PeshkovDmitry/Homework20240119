public class Advisor {

    /**
     * Метод для получения наиболее оптимальной точки
     * @param field Игровое поле
     * @return Оптимальная точка
     */
    public static FieldDot getRecommendedDot(Field field) {
        FieldDot recommendedDot;
        // Проверяем, есть ли точка, обеспечивающая выигрыш одним ходом
        recommendedDot = getWinnerDot(field.getMap(), field.getAIDot(), field.getEmptyDot(), field.getSize());
        if (!recommendedDot.isUndefined()) {
            return recommendedDot;
        }
        // Проверяем, есть ли точка, обеспечивающая выигрыш человека одним ходом
        recommendedDot = getWinnerDot(field.getMap(), field.getHumanDot(), field.getEmptyDot(), field.getSize());
        if (!recommendedDot.isUndefined()) {
            return recommendedDot;
        }
        return new FieldDot();
    }

    /**
     * Метод, возвращающий точку, обеспечивающую выигрыш одним ходом
     * @param map Игровое поле
     * @param dot Тип точки (компьютер/игрок)
     * @param field_size Размер поля
     * @return Точка игрового поля
     */

    public static FieldDot getWinnerDot(char[][] map, char dot, char emptyDot, int field_size) {
        for (int i = 0; i < field_size; i++) {
            for (int j = 0; j < field_size; j++) {
                if (map[i][j] == emptyDot) {
                    map[i][j] = dot;
                    if (checkWin(map, dot, field_size)) {
                        return new FieldDot(i, j);
                    }
                }
            }
        }
        return new FieldDot();
    }

    /**
     * Метод, определяющий, есть ли выигрыш на данном поле
     * @param map Игровое поле
     * @param dot Тип точки (компьютер/игрок)
     * @param field_size Размер поля
     * @return Наличие выигрыша
     */

    public static boolean checkWin(char[][] map, char dot, int field_size) {
        boolean mainDiagonal = true;
        boolean notMainDiagonal = true;
        for (int i = 0; i < field_size; i++) {
            boolean vertical = true;
            for (int j = 0; j < field_size; j++) {
                if (map[j][i] !=  dot) {
                    vertical = false;
                }
            }
            if (vertical) {
                return true;
            }
            boolean horizontal = true;
            for (int j = 0; j < field_size; j++) {
                if (map[i][j] !=  dot) {
                    horizontal = false;
                }
            }
            if (horizontal) {
                return true;
            }
            if (map[i][i] != dot) {
                mainDiagonal = false;
            }
            if (map[i][field_size - 1 - i] != dot) {
                notMainDiagonal = false;
            }
        }
        if (mainDiagonal) {
            return true;
        }
        if (notMainDiagonal) {
            return true;
        }
        return false;
    }
}
