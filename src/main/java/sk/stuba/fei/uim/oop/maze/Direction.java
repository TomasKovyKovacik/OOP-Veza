package sk.stuba.fei.uim.oop.maze;

import lombok.Getter;

import java.awt.*;

public enum Direction {
    UP(0, 0, 1, 0, "⬆"),
    DOWN(0, 1, 1, 1, "⬇"),
    LEFT(0, 0, 0, 1, "⬅"),
    RIGHT(1, 0, 1, 1, "➡");

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    @Getter
    private String label;

    Direction(int x1, int y1, int x2, int y2, String label) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.label = label;
    }

    public void draw(Graphics g, int x, int y, int size) {
        g.drawLine(x + this.x1 * size, y + this.y1 * size, x + this.x2 * size, y + this.y2 * size);
    }
}
