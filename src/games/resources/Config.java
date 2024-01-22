package games.resources;

public interface Config {

    int WINDOW_SIZE = 330;
    int WINDOW_DX = 7;
    int WINDOW_DY = 55;
    int FIELD_SIZE = 3;
    int CELL_SIZE = WINDOW_SIZE / FIELD_SIZE;

    char HUMAN_DOT = 'x';
    char AI_DOT = 'o';
    char EMPTY_DOT = '.';

    String TITLE_OF_PROGRAM = "Крестики-нолики";
    String BTN_INIT = "Новая игра";
    String BTN_EXIT = "Выход";
    String MSG_DRAW = "Видимо, ничья...";
    String MSG_HUMAN_WON = "Вы победили!";
    String MSG_AI_WON = "Победил компьютер!";
}
