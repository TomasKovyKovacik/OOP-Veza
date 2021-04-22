package sk.stuba.fei.uim.oop.gui;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.maze.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionButton extends JButton implements ActionListener {
    private GameLogic logic;
    private Direction direction;

    public DirectionButton(GameLogic logic, Direction direction) {
        super(direction.getLabel());
        this.logic = logic;
        this.direction = direction;
        this.setFocusable(false);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.logic.getPlayer().move(this.direction);
        this.logic.setPlayerClicked(false);
        this.logic.repaint();
    }
}
