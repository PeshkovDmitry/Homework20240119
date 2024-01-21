package games.view;

import games.model.AI;
import games.model.Field;
import games.presenter.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {

    private Field field;

    public Panel(Field field, AI ai, int cell_size) {

        this.field = field;

        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX()/cell_size;
                int y = e.getY()/cell_size;
                if(field.isCellValid(x, y)){
                    if (!field.isGameOver()) field.setDot(x, y, field.getHumanDot());
                    if (!field.isGameOver()) ai.turn(field);
                }
                repaint();
                if (field.isGameOver())
                    JOptionPane.showMessageDialog(
                            null, field.getGameOverMsg());
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        field.paint((Graphics2D) g);
    }
}
