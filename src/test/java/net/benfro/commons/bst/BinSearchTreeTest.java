package net.benfro.commons.bst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BinSearchTreeTest {

    @Test
    void testEmptyCreation() {
        BinSearchTree<String> tree = new BinSearchTree<>();
        tree.insert("A");

        assertEquals(1, tree.size());
    }

    @Test
    void testEmptyCreation2() {
        BinSearchTree<String> tree = new BinSearchTree<>();
        tree.insert("A");
        tree.insert("B");

        assertEquals(2, tree.size());
    }

    @Test
    void testEmptyCreation3() {
        BinSearchTree<String> tree = new BinSearchTree<>();
        tree.insert("B");
        tree.insert("A");
        tree.insert("C");

        assertEquals(2, tree.size());
    }

    @Test
    void testEmptyCreation4() {
        BinSearchTree<String> tree = new BinSearchTree<>();
        tree.insert("A");
        tree.insert("B");
        tree.insert("C");

        assertEquals(3, tree.size());
    }

    @ParameterizedTest
    @CsvSource (value = {
        "A,B,C | A, B, C",
        "B, A, C| A, B, C",
        "C,A,B| A, B, C",
        "C,A,B,E,D| A, B, C, D, E",
    }, delimiter = '|')
    void testInOrder4(String input, String expected) {
        BinSearchTree<String> tree = new BinSearchTree<>();
        Stream.of(input.split(",")).map(String::trim).forEach(tree::insert);
        List<String> strings = tree.inOrderWalk(tree.getRoot());
        var exp = Stream.of(expected.split(",")).map(String::trim).toList();
        assertEquals(exp, strings);
    }

    @ParameterizedTest
    @CsvSource (value = {
        "A,B,C | 3 | 2",
        "A, B, C, D, E| 5 | 3",
    }, delimiter = '|')
    void testBalance(String input, int unbalancedSize, int expectedHeight) {
        BinSearchTree<String> tree = new BinSearchTree<>();
        Stream.of(input.split(",")).map(String::trim).forEach(tree::insert);
        assertEquals(unbalancedSize, tree.size());
        BinSearchTree<String> result = tree.balance();
        assertEquals(expectedHeight, result.size());
    }


    @ParameterizedTest
    @CsvSource (value = {
        "A,B,C | A | true",
        "A,B,C | D | false",
//        "A, B, C, D, E| 5 | 3",
    }, delimiter = '|')
    void testContains(String input, String contains, boolean expected) {
        BinSearchTree<String> tree = new BinSearchTree<>();
        Stream.of(input.split(",")).map(String::trim).forEach(tree::insert);
        assertEquals(expected, tree.contains(contains));
    }
}
