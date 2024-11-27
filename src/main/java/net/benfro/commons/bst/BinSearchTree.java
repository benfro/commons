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

    public BinSearchTree<V> insert(BinNode<V> value) {
        if (Objects.isNull(root)) {
            root = value;
        } else {
            next(root, value.getValue());
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

    public boolean contains(V value) {
        return contains(root, value);
    }

    private boolean contains(BinNode<V> currentNode, V value) {
        if(root != null) {
            if(root.getValue().equals(value)) {
                return true;
            } else {
                return (currentNode.hasLeft() && contains(currentNode.getLeft(), value))
                    || (currentNode.hasRight() && contains(currentNode.getRight(), value));
            }
        }
        return false;
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

    public List<V> inOrderWalk(BinNode<V> node) {
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

    private BinNode<V> balance(List<V> elements, int start, int end) {
        if (start > end)  return null;
        int mid = start + (end - start) / 2;
        BinNode<V> root = new BinNode(elements.get(mid));

        root.setLeft(balance(elements, start, mid - 1));
        root.setRight(balance(elements, mid + 1, end));
        return root;
    }

    public BinSearchTree<V> balance() {
        List<V> vs = inOrderWalk(root);
        BinNode<V> balanced = balance(vs, 0, vs.size() - 1);
        BinSearchTree<V> tree = new BinSearchTree<>();
        return tree.insert(balanced);
    }

}
