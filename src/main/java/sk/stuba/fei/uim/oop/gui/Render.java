package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.maze.Maze;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel {
    private Maze maze;
    public Render(Maze maze) {
        this.maze = maze;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        maze.draw(g);
    }
}
