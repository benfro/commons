package net.benfro.commons.bst;

import lombok.Data;
import lombok.ToString;

@Data
public class BinNode<T extends Comparable<T>> {
    private T value;
    @ToString.Exclude
    private BinNode<T> left;
    @ToString.Exclude
    private BinNode<T> right;

    public BinNode(T value) {
        this.value = value;
    }

    public void add(T value) {
        if (value.compareTo(this.value) <= 0) {
            this.left = new BinNode<>(value);
        } else {
            this.right = new BinNode<>(value);
        }
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

}
