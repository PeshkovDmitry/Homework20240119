package games.model;

import java.util.Arrays;

import static games.resources.Config.*;

public class Field {

    private char[][] map;
    private String gameOverMsg;

    public Field(){
        map = new char[FIELD_SIZE][FIELD_SIZE];
        init();
    }

    public void init(){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++)
            map[i][j] = EMPTY_DOT;
        }
        gameOverMsg = null;
    }

    public char[][] getMap() {
        char[][] newMap = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }


    public boolean isGameOver() { return gameOverMsg != null; }

    public String getGameOverMsg() { return gameOverMsg; }

    public void setDot(int x, int y, char dot) { // set dot and check fill and win
        map[x][y] = dot;
        if (checkWin(HUMAN_DOT, map))
            gameOverMsg = MSG_HUMAN_WON;
        else if (checkWin(AI_DOT, map))
            gameOverMsg = MSG_AI_WON;
        else if (isMapFull())
            gameOverMsg = MSG_DRAW;
    }

    boolean isMapFull() {
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                if (map[i][j] == EMPTY_DOT)
                    return false;
        return true;
    }

    boolean checkWin(char dot, char[][] map) {
        boolean mainDiagonal = true;
        boolean notMainDiagonal = true;
        for (int i = 0; i < FIELD_SIZE; i++) {
            boolean vertical = true;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (map[j][i] !=  dot) {
                    vertical = false;
                }
            }
            if (vertical) {
                return true;
            }
            boolean horizontal = true;
            for (int j = 0; j < FIELD_SIZE; j++) {
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
            if (map[i][FIELD_SIZE - 1 - i] != dot) {
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

    public boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > FIELD_SIZE - 1 || y > FIELD_SIZE - 1)
            return false;
        if (map[x][y] == EMPTY_DOT)
            return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                stringBuilder.append(map[j][i]).append(" ");
            }
            stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }
}
