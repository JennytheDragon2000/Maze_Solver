package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testConstructor() {
        Node parent = new Node(0, 0, 0, 0, null);
        Node node = new Node(1, 2, 3, 4, parent);

        assertEquals(1, node.row);
        assertEquals(2, node.col);
        assertEquals(3, node.cost);
        assertEquals(4, node.heuristic);
        assertEquals(parent, node.parent);
    }

    @Test
    void testCompareTo() {
        Node node1 = new Node(0, 0, 1, 2, null);
        Node node2 = new Node(0, 0, 2, 1, null);
        Node node3 = new Node(0, 0, 3, 3, null);

        assertTrue(node1.compareTo(node2) < 0);
        assertTrue(node2.compareTo(node1) > 0);
        assertEquals(0, node1.compareTo(node1));
        assertTrue(node1.compareTo(node3) < 0);
    }

    @Test
    void testEquals() {
        Node node1 = new Node(0, 0, 1, 2, null);
        Node node2 = new Node(0, 0, 1, 2, null);
        Node node3 = new Node(1, 0, 1, 2, null);
        Node node4 = null;

        assertTrue(node1.equals(node1));
        assertTrue(node1.equals(node2));
        assertFalse(node1.equals(node3));
        assertFalse(node1.equals(node4));
        assertFalse(node1.equals(new Object()));
    }

    @Test
    void testHashCode() {
        Node node1 = new Node(0, 0, 1, 2, null);
        Node node2 = new Node(0, 0, 1, 2, null);
        Node node3 = new Node(1, 0, 1, 2, null);

        assertEquals(node1.hashCode(), node2.hashCode());
        assertNotEquals(node1.hashCode(), node3.hashCode());
    }

    @Test
    void testToString() {
        Node node = new Node(1, 2, 3, 4, null);
        String expected = "Node{row=1, col=2, cost=3, heuristic=4}";
        assertEquals(expected, node.toString());
    }
}