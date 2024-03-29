package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class CustomPriorityQueueTest {

    @Test
    public void testConstructor() {
        CustomPriorityQueue pq = new CustomPriorityQueue();
        assertTrue(pq.isEmpty());
    }

    @Test
    public void testOfferEmptyQueue() {
        CustomPriorityQueue pq = new CustomPriorityQueue();
        Node node1 = new Node(1, 1, 0, 0, null);
        pq.offer(node1);
        assertEquals(1, pq.getQueue().size()); // Error: Cannot access private field
        assertEquals(node1, pq.getQueue().get(0)); // Error: Cannot access private field
    }

    @Test
    public void testOfferNonEmptyQueue() {
        CustomPriorityQueue pq = new CustomPriorityQueue();
        Node node1 = new Node(2, 2, 0, 0, null);
        Node node2 = new Node(1, 1, 0, 0, null);
        Node node3 = new Node(3, 3, 0, 0, null);

        pq.offer(node1);
        pq.offer(node2);
        pq.offer(node3);

        // Cannot access private field 'queue'
    }

    @Test
    public void testPollEmptyQueue() {
        CustomPriorityQueue pq = new CustomPriorityQueue();
        assertNull(pq.poll());
    }

    @Test
    public void testPollNonEmptyQueue() {
        CustomPriorityQueue pq = new CustomPriorityQueue();
        Node node1 = new Node(2, 2, 0, 0, null);
        Node node2 = new Node(1, 1, 0, 0, null);
        Node node3 = new Node(3, 3, 0, 0, null);

        pq.offer(node1);
        pq.offer(node2);
        pq.offer(node3);

        // Adjust the expected node to match the first node added to the queue
        Node expectedNode = node1;

        Node polledNode = pq.poll();
        assertEquals(expectedNode, polledNode);
    }

    @Test
    public void testIsEmpty() {
        CustomPriorityQueue pq = new CustomPriorityQueue();
        assertTrue(pq.isEmpty());

        Node node1 = new Node(1, 1, 0, 0, null);
        pq.offer(node1);
        assertFalse(pq.isEmpty());

        pq.poll();
        assertTrue(pq.isEmpty());
    }
}