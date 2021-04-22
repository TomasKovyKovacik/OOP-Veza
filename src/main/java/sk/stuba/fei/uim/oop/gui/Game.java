package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.maze.Direction;

import javax.swing.*;
import java.awt.*;

public class Game {

    public Game() {
        JFrame frame = new JFrame("Rook in a Maze!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(930,670);
        frame.setResizable(false);

        frame.setLayout(new BorderLayout());
        GameLogic logic = new GameLogic();
        frame.addKeyListener(logic);
        frame.add(logic.getRender());

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);
        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);

        sideMenu.setLayout(new GridLayout(2, 3));
        sideMenu.add(logic.getLabel());
        sideMenu.add(new DirectionButton(logic, Direction.UP));
        sideMenu.add(buttonRestart);
        sideMenu.add(new DirectionButton(logic, Direction.LEFT));
        sideMenu.add(new DirectionButton(logic, Direction.DOWN));
        sideMenu.add(new DirectionButton(logic, Direction.RIGHT));
        frame.add(sideMenu, BorderLayout.LINE_END);

        frame.setVisible(true);
    }
}
