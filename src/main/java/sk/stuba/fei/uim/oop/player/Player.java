package sk.stuba.fei.uim.oop.player;
import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.maze.Connection;
import sk.stuba.fei.uim.oop.maze.Direction;
import sk.stuba.fei.uim.oop.maze.Node;

import java.awt.*;

public class Player {
    @Getter
    private Node currentNode;

    private GameLogic logic;

    public Player(GameLogic logic) {
        this.logic = logic;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
        if (currentNode.isFinish()) {
            this.logic.gameWon();
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(83, 205, 132));
        g.fillOval(currentNode.getX() + 2 , currentNode.getY() + 2, Node.NODE_SIZE - 4, Node.NODE_SIZE - 4);
        g.setColor(Color.BLACK);
    }

    public void move(Direction direction) {
        Connection connection = this.currentNode.getNeighbours().get(direction);
        if (connection != null && connection.isConnected()) {
            this.setCurrentNode(connection.getNode());
        }
    }

    public boolean canReachNode(Node node) {
        return this.scanDirection(Direction.UP, node) ||
                this.scanDirection(Direction.DOWN, node) ||
                this.scanDirection(Direction.LEFT, node) ||
                this.scanDirection(Direction.RIGHT, node);
    }

    private boolean scanDirection(Direction direction, Node node) {
        Node currentNode = this.currentNode;
        while (currentNode != null) {
            if (currentNode == node) {
                return true;
            }
            Connection con = currentNode.getNeighbours().get(direction);
            if (con == null) {
                return false;
            }
            currentNode = con.isConnected() ? con.getNode() : null;
        }
        return false;
    }
}
