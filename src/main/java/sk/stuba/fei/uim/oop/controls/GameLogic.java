package sk.stuba.fei.uim.oop.controls;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.gui.Render;
import sk.stuba.fei.uim.oop.maze.Direction;
import sk.stuba.fei.uim.oop.maze.Maze;
import sk.stuba.fei.uim.oop.maze.Node;
import sk.stuba.fei.uim.oop.player.Player;

import javax.swing.*;
import java.awt.event.*;

public class GameLogic extends UniversalAdapter {
    @Getter
    private Maze maze;
    @Getter
    private Player player;
    @Getter
    private Render render;
    @Getter
    private int counter;
    @Getter
    private JLabel label;
    @Setter
    private boolean playerClicked;

    public GameLogic() {
        this.maze = new Maze();
        this.player = new Player(this);
        this.player.setCurrentNode(this.maze.getStartNode());
        this.render = new Render(this.maze, this.player);
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        this.counter = 0;
        this.label = new JLabel();
        this.updateCounterLabel();
        this.playerClicked = false;
    }

    public void repaint() {
        this.render.repaint();
    }

    public void gameWon() {
        this.maze.generateMaze();
        this.player.setCurrentNode(this.maze.getStartNode());
        this.counter++;
        this.updateCounterLabel();
    }

    public void gameRestart() {
        this.gameWon();
        this.counter = 0;
        this.updateCounterLabel();
    }

    private void updateCounterLabel() {
        this.label.setText("WINS: " + this.counter);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.player.move(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.player.move(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                this.player.move(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.player.move(Direction.RIGHT);
                break;
        }
        this.playerClicked = false;
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameRestart();
        this.playerClicked = false;
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Node current = this.maze.getNode(convertPosition(e.getX()), convertPosition(e.getY()));
        if (current == null) {
            return;
        }
        if (this.playerClicked) {
            if (this.player.canReachNode(current)) {
                this.player.setCurrentNode(current);
                this.repaint();
                this.playerClicked = false;
            }
        } else {
            if (this.player.getCurrentNode() == current) {
                this.playerClicked = true;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Node current = this.maze.getNode(convertPosition(e.getX()), convertPosition(e.getY()));
        if (current == null) {
            return;
        }
        if (this.playerClicked) {
            if (this.player.canReachNode(current)) {
                current.setHighlight(true);
            }
            this.repaint();
        }
    }

    private int convertPosition(int screenPosition) {
        return ((screenPosition - Node.OFFSET) < 0) ? -1 : (screenPosition - Node.OFFSET) / Node.NODE_SIZE;
    }
}
