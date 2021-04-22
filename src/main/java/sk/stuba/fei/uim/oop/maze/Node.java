package sk.stuba.fei.uim.oop.maze;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {
    @Getter
    private Map<Direction, Connection> neighbours;
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    @Setter
    private boolean finish;
    @Setter
    private boolean highlight;
    public static final int NODE_SIZE = 40;
    public static final int OFFSET = 20;

    public Node(int x, int y) {
        this.x = x * NODE_SIZE + OFFSET;
        this.y = y * NODE_SIZE + OFFSET;
        this.finish = false;
        this.neighbours = new HashMap<>();
    }

    public void addNeighbour(Direction direction, Node node) {
        this.neighbours.put(direction, new Connection(node));
    }

    public ArrayList<Node> getAllNeighbour() {
        ArrayList<Node> all = new ArrayList<>();
        this.neighbours.values().forEach(connection -> all.add(connection.getNode()));
        return all;
    }

    public void connectWith(Node node) {
        for (Map.Entry<Direction, Connection> entry : this.neighbours.entrySet()) {
            if (entry.getValue().getNode() != node) {
                continue;
            }
            if (!entry.getValue().isConnected()) {
                entry.getValue().setConnected(true);
                node.connectWith(this);
            }
            return;
        }
    }

    public void draw(Graphics g) {
        if (this.finish) {
            g.setColor(Color.RED);
            g.fillRect(this.x, this.y, Node.NODE_SIZE, Node.NODE_SIZE);
            g.setColor(Color.BLACK);
        }
        if (highlight) {
            g.setColor(Color.BLUE);
            g.fillOval(this.x + 5 , this.y + 5, Node.NODE_SIZE - 10, Node.NODE_SIZE - 10);
            g.setColor(Color.BLACK);
            this.highlight = false;
        }
        for (Map.Entry<Direction, Connection> entry : this.neighbours.entrySet()) {
            if (entry.getValue().isConnected()) {
                continue;
            }
            entry.getKey().draw(g, this.x, this.y, NODE_SIZE);
        }
    }
}
