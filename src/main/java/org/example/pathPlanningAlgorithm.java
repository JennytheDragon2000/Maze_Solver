package org.example;


import java.util.Objects;

class CustomArrayList<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        ensureCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = item;
        size++;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        T item = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null; // Prevent memory leak
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        if (capacity > data.length) {
            int newCapacity = Math.max(capacity, data.length * 2);
            T[] newData = (T[]) new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }
}
class CustomHashMap<K, V> {
    private Entry<K, V>[] table;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = new Entry<>(key, value, null);
            size++;
        } else {
            while (entry != null) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            table[index] = new Entry<>(key, value, table[index]);
            size++;
        }
        if (size > table.length * LOAD_FACTOR) {
            rehash();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (key == null && entry.key == null || key != null && key.equals(entry.key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (key == null && entry.key == null || key != null && key.equals(entry.key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }
    private int getIndex(K key) {
        int hashCode = (key == null) ? 0 : key.hashCode();
        return Math.abs(hashCode) % table.length;
    }
    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;
        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}

class CustomPriorityQueue {

    private CustomArrayList<Node> queue;
    public CustomArrayList<Node> getQueue() {
        return queue;
    }

    public CustomPriorityQueue() {
        this.queue = new CustomArrayList<>();
    }

    public void offer(Node node) {
        int i = 0;
        for (; i < queue.size(); i++) {
            if (queue.get(i).compareTo(node) > 0) {
                break;
            }
        }
        queue.add(i, node);
    }

    public Node poll() {
        if (isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

class Node implements Comparable<Node> {
    int row, col;
    int cost;
    int heuristic;
    Node parent;

    Node(int row, int col, int cost, int heuristic, Node parent) {
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    public int compareTo(Node other) {
        if (this.cost < other.cost) {
            return -1;
        } else if (this.cost > other.cost) {
            return 1;
        } else {
            if (this.heuristic < other.heuristic) {
                return -1;
            } else if (this.heuristic > other.heuristic) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        return row == other.row && col == other.col && cost == other.cost && heuristic == other.heuristic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, cost, heuristic);
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", cost=" + cost +
                ", heuristic=" + heuristic +
                '}';
    }
}
class PathPlanner {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // North, South, West, East

    private Grid grid;
    private int startRow, startCol;
    private int endRow, endCol;
    private CustomHashMap<Integer, CustomArrayList<Node>> adjacencyList;

    public PathPlanner(Grid grid, int startRow, int startCol, int endRow, int endCol) {
        this.grid = grid;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.adjacencyList = new CustomHashMap<>();
    }

    private int getKey(int row, int col) {
        return row * grid.getCols() + col;
    }

    private void buildAdjacencyList() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                if (!grid.isObstacle(row, col)) {
                    CustomArrayList<Node> neighbors = new CustomArrayList<>();
                    for (int[] direction : DIRECTIONS) {
                        int newRow = row + direction[0];
                        int newCol = col + direction[1];
                        if (isValidCell(newRow, newCol) && !grid.isObstacle(newRow, newCol)) {
                            neighbors.add(neighbors.size(), new Node(newRow, newCol, 1, 0, null));
                        }
                    }
                    adjacencyList.put(getKey(row, col), neighbors);
                }
            }
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < grid.getRows() && col >= 0 && col < grid.getCols();
    }

    private int heuristic(int row, int col) {
        return Math.abs(row - endRow) + Math.abs(col - endCol);
    }

    public CustomArrayList<Node> findShortestPath() {
        buildAdjacencyList();

        CustomPriorityQueue pq = new CustomPriorityQueue();
        CustomHashMap<Integer, Integer> costMap = new CustomHashMap<>();
        CustomHashMap<Integer, Node> parentMap = new CustomHashMap<>();

        int startKey = getKey(startRow, startCol);
        costMap.put(startKey, 0);
        pq.offer(new Node(startRow, startCol, 0, heuristic(startRow, startCol), null));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.row == endRow && current.col == endCol) {
                CustomArrayList<Node> path = new CustomArrayList<>();
                Node node = current;
                while (node != null) {
                    path.add(0, node);
                    node = parentMap.get(getKey(node.row, node.col));
                }
                return path;
            }

            CustomArrayList<Node> neighbors = adjacencyList.get(getKey(current.row, current.col));
            if (neighbors != null) {
                for (int i = 0; i < neighbors.size(); i++) {
                    Node neighbor = neighbors.get(i);
                    int newCost = costMap.get(getKey(current.row, current.col)) + neighbor.cost;
                    int neighborKey = getKey(neighbor.row, neighbor.col);
                    if (!costMap.containsKey(neighborKey) || newCost < costMap.get(neighborKey)) {
                        costMap.put(neighborKey, newCost);
                        int priority = newCost + heuristic(neighbor.row, neighbor.col);
                        neighbor.cost = newCost;
                        neighbor.heuristic = heuristic(neighbor.row, neighbor.col);
                        neighbor.parent = current;
                        pq.offer(neighbor);
                        parentMap.put(neighborKey, current);
                    }
                }
            }
        }

        return new CustomArrayList<>(); // No path found
    }
}