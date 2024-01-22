package games.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import games.model.AI;
import games.model.Field;
import games.view.Panel;
import static games.resources.Config.*;

public class MainWindow extends JFrame {

    private Panel panel;

    public MainWindow(Field field, AI ai){

        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_SIZE + WINDOW_DX, WINDOW_SIZE + WINDOW_DY);
        setLocationRelativeTo(null); // to the center
        setResizable(false);

        panel = new Panel(field, ai);

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
    }

    public void showMainWindow() {
        setVisible(true);
    }



}
