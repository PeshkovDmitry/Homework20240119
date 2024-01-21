package games.presenter;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import games.model.AI;
import games.model.Field;
import games.view.Panel;


public class TicTacToe extends JFrame {
    final String TITLE_OF_PROGRAM = "Крестики-нолики";
    final int WINDOW_SIZE = 330;
    final int WINDOW_DX = 7;
    final int WINDOW_DY = 55;
    final int FIELD_COUNT = 3;
    final int CELL_SIZE = WINDOW_SIZE / FIELD_COUNT;
    final String BTN_INIT = "Новая игра";
    final String BTN_EXIT = "Выход";

    private Panel panel;
    private Field field;
    private AI ai;

    public TicTacToe(){

        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setLocationRelativeTo(null); // to the center
        setResizable(false);

        field = new Field(FIELD_COUNT, CELL_SIZE);
        ai = new AI(field.getAIDot());
        panel = new Panel(field, ai, CELL_SIZE);


        JButton init = new JButton(BTN_INIT);
        init.addActionListener(e -> {
            field.init();
            panel.repaint();
        });

        JButton exit = new JButton(BTN_EXIT);
        exit.addActionListener(e -> System.exit(0));

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new GridLayout());
        panelBtn.add(init);
        panelBtn.add(exit);

        setLayout(new BorderLayout());
        add(panelBtn, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }



}
