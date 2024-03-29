package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {

    @Test
    public void testConstructor() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();
        assertEquals(0, map.size()); // Use the public size() method
    }

    @Test
    public void testPutAndGet() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Test adding a new key-value pair to an empty table
        map.put("apple", 1);
        assertEquals(1, (int) map.get("apple"));

        // Test adding a new key-value pair to a non-empty table
        map.put("banana", 2);
        assertEquals(2, (int) map.get("banana"));

        // Test updating the value for an existing key
        map.put("apple", 3);
        assertEquals(3, (int) map.get("apple"));

        // Test adding multiple key-value pairs to trigger rehashing (indirectly)
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }
        assertEquals(19, (int) map.get("key19"));

        // Test adding null as the key and value
        map.put(null, null);
        assertNull(map.get(null));
    }

    @Test
    public void testContainsKey() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);

        // Test checking for an existing key
        assertTrue(map.containsKey("apple"));

        // Test checking for a non-existing key
        assertFalse(map.containsKey("orange"));

        // Test checking for a key when the table is empty
        CustomHashMap<String, Integer> emptyMap = new CustomHashMap<>();
        assertFalse(emptyMap.containsKey("any"));

        // Test checking for null as the key
        map.put(null, null);
        assertTrue(map.containsKey(null));
    }

    @Test
    public void testRehashIndirectly() {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Test rehashing when the load factor is exceeded (indirectly)
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }
        assertTrue(map.size() > 16); // Assume the rehash() method works correctly

        // Test rehashing when the table is empty (indirectly)
        CustomHashMap<String, Integer> emptyMap = new CustomHashMap<>();
        emptyMap.put("key", 1);
        emptyMap.put("key", 2); // No rehashing should occur

        // Test rehashing when the table contains one element (indirectly)
        CustomHashMap<String, Integer> oneElementMap = new CustomHashMap<>();
        oneElementMap.put("key1", 1);
        oneElementMap.put("key2", 2);
        assertTrue(oneElementMap.size() > 1); // Assume the rehash() method works correctly
    }

    @Test
    public void testEdgeCases() {
        CustomHashMap<Integer, String> map = new CustomHashMap<>();

        // Test adding and retrieving a large number of key-value pairs
        for (int i = 0; i < 1000000; i++) {
            map.put(i, "value" + i);
        }
        for (int i = 0; i < 1000000; i++) {
            assertEquals("value" + i, map.get(i));
        }

        // Test keys with the same hash code
        Integer sameHashKey1 = 16;
        Integer sameHashKey2 = 48;
        map.put(sameHashKey1, "value1");
        map.put(sameHashKey2, "value2");
        assertEquals("value1", map.get(sameHashKey1));
        assertEquals("value2", map.get(sameHashKey2));
    }
}