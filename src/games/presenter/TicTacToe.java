package games.presenter;

import games.model.AI;
import games.model.Field;
import games.view.MainWindow;

import static games.resources.Config.CELL_SIZE;
import static games.resources.Config.FIELD_SIZE;

public class TicTacToe {

    public TicTacToe() {

        Field field = new Field();
        AI ai = new AI(field);
        MainWindow mainWindow = new MainWindow(field, ai);
        mainWindow.showMainWindow();

    }

}
