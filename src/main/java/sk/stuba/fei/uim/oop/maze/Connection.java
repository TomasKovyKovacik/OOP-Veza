package sk.stuba.fei.uim.oop.maze;

import lombok.Data;

@Data
public class Connection {
    private Node node;
    private boolean connected;

    public Connection(Node node) {
        this.node = node;
        this.connected = false;
    }
}
