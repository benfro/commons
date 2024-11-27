package net.benfro.commons.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

public class BinSearchTree<V extends Comparable<V>> {

    @Getter
    private BinNode<V> root;

    public BinSearchTree<V> insert(V value) {
        if (Objects.isNull(root)) {
            root = new BinNode(value);
        } else {
            next(root, value);
        }
        return this;
    }

    private BinSearchTree<V> next(BinNode<V> currentNode, V nextValue) {
        if (nextValue.compareTo(currentNode.getValue()) <= 0) {
            if (currentNode.getLeft() != null) {
                next(currentNode.getLeft(), nextValue);
            } else {
                currentNode.setLeft(new BinNode<>(nextValue));
            }
        } else {
            if (currentNode.getRight() != null) {
                next(currentNode.getRight(), nextValue);
            } else {
                currentNode.setRight(new BinNode<>(nextValue));
            }
        }
        return this;
    }

    public BinSearchTree<V> remove(V value) {
        return null;
    }

    public V search(V value) {
        return null;
    }

    public int size() {
        return height(root);
    }

    private int height(BinNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + (int)Math.max(height(root.getLeft()), height(root.getRight()));
        }
    }

    List<V> inOrderWalk(BinNode<V> node) {
        List<V> out = new ArrayList<>();
        if (Objects.nonNull(node.getLeft())) {
            out.addAll(inOrderWalk(node.getLeft()));
        }
        out.add(node.getValue());
        if (Objects.nonNull(node.getRight())) {
            out.addAll(inOrderWalk(node.getRight()));
        }
        return out;
    }

}
