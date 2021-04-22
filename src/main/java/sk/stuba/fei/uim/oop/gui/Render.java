package sk.stuba.fei.uim.oop.gui;
import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel {
    private Maze maze;
    private Player player;
    public Render(Maze maze, Player player) {
        this.maze = maze;
        this.player = player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.maze.draw(g);
        this.player.draw(g);
    }
}
