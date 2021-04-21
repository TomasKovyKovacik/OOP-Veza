package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.maze.Maze;

import javax.swing.*;
import java.awt.*;

public class Game {

    public Game() {
        JFrame frame = new JFrame("Rook in a Maze!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1140,670);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        Maze maze = new Maze();
        frame.add(new Render(maze));
        frame.setVisible(true);
    }
}
