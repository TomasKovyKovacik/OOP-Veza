package sk.stuba.fei.uim.oop.maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Maze {
    private static final int MAZE_SIZE = 15;
    private Node[][] maze;

    public Maze() {
        this.generateMaze();
    }

    public Node getStartNode() {
        return this.maze[0][0];
    }

    public Node getNode(int x, int y) {
        if (x >= MAZE_SIZE || x < 0 || y >= MAZE_SIZE || y < 0) {
            return null;
        }
        return this.maze[y][x];
    }

    public void generateMaze() {
        this.initializeMaze();
        HashSet<Node> visitedNodes = new HashSet<>();
        ArrayList<Step> stack = new ArrayList<>();
        stack.add(new Step(this.maze[0][0], null));

        while (!stack.isEmpty()) {
            Step step = stack.remove(0);
            Node currentNode = step.getCurrent();
            if (visitedNodes.contains(currentNode)) {
                continue;
            }
            if (step.getPrevious() != null) {
                currentNode.connectWith(step.getPrevious());
            }
            ArrayList<Node> allNeighbours = currentNode.getAllNeighbour();
            Collections.shuffle(allNeighbours);
            allNeighbours.forEach(neighbour -> {
                if (!visitedNodes.contains(neighbour)) {
                    stack.add(0, new Step(neighbour, currentNode));
                }
            });
            visitedNodes.add(currentNode);
        }
    }

    private void initializeMaze() {
        this.maze = new Node[MAZE_SIZE][MAZE_SIZE];
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++)  {
                this.maze[i][j] = new Node(j, i);
            }
        }
        this.maze[MAZE_SIZE-1][MAZE_SIZE-1].setFinish(true);
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++)  {
                if (i != 0) {
                    this.maze[i][j].addNeighbour(Direction.UP, this.maze[i-1][j]);
                }
                if (i != MAZE_SIZE - 1) {
                    this.maze[i][j].addNeighbour(Direction.DOWN, this.maze[i+1][j]);
                }
                if (j != 0) {
                    this.maze[i][j].addNeighbour(Direction.LEFT, this.maze[i][j-1]);
                }
                if (j != MAZE_SIZE - 1) {
                    this.maze[i][j].addNeighbour(Direction.RIGHT, this.maze[i][j+1]);
                }
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++)  {
                this.maze[i][j].draw(g);
            }
        }
        g.drawRect(Node.OFFSET, Node.OFFSET, MAZE_SIZE * Node.NODE_SIZE, MAZE_SIZE * Node.NODE_SIZE);
    }
}
