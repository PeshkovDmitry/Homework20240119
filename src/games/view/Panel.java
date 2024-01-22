package games.view;

import games.model.AI;
import games.model.Field;
import games.resources.Config;

import static games.resources.Config.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {

    private Field field;

    public Panel(Field field, AI ai) {

        this.field = field;

        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX()/CELL_SIZE;
                int y = e.getY()/CELL_SIZE;
                if(field.isCellValid(x, y)){
                    if (!field.isGameOver()) field.setDot(x, y, HUMAN_DOT);
                    if (!field.isGameOver()) ai.turn();
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
        paint((Graphics2D) g);
    }

    public void paint(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.lightGray);
        for (int i = 1; i < FIELD_SIZE; i++) {
            g.drawLine(0, i * CELL_SIZE, FIELD_SIZE * CELL_SIZE, i * CELL_SIZE);
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, FIELD_SIZE * CELL_SIZE);
        }

        g.setStroke(new BasicStroke(5));

        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                if (field.getMap()[x][y] == HUMAN_DOT) {
                    g.setColor(Color.blue);
                    g.drawLine(x * CELL_SIZE + CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4,
                            (x + 1) * CELL_SIZE - CELL_SIZE / 4, (y + 1) * CELL_SIZE - CELL_SIZE / 4);
                    g.drawLine(x * CELL_SIZE + CELL_SIZE / 4, (y + 1) * CELL_SIZE - CELL_SIZE / 4,
                            (x + 1) * CELL_SIZE - CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4);
                }
                if (field.getMap()[x][y] == AI_DOT) {
                    g.setColor(Color.red);
                    g.drawOval(x * CELL_SIZE + CELL_SIZE / 4, y * CELL_SIZE + CELL_SIZE / 4,
                            CELL_SIZE / 2,
                            CELL_SIZE / 2);
                }
            }
        }
    }
}
