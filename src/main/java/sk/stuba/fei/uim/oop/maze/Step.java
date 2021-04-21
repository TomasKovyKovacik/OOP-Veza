package sk.stuba.fei.uim.oop.maze;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Step {
    private Node current;
    private Node previous;
}
