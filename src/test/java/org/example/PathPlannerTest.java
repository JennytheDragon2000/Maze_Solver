package org.example;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathPlannerTest {

    @Test
    public void testFindShortestPath(@TempDir Path tempDir) throws IOException {
        // Test case 4: Grid with obstacles - one Path
        String gridData = "######\n"
                + "#@...#\n"
                + "####.#\n"
                + "#..#.#\n"
                + "#..G.#\n"
                + "######";

            Path file = tempDir.resolve("grid.txt");
            Files.writeString(file, gridData);
            Grid grid = new Grid(file.toString());

            Robot robot = new Robot(grid.getStartRow(), grid.getStartCol(), Robot.Orientation.NORTH);
            PathPlanner pathPlanner = new PathPlanner(grid, grid.getStartRow(), grid.getStartCol(), grid.getEndRow(), grid.getEndCol());
            CustomArrayList<Node> path = pathPlanner.findShortestPath();
            assertEquals(8, path.size());


        // Test case 6: Grid with obstacles - two Paths
        gridData = "######\n"
                + "#@...#\n"
                + "#.##.#\n"
                + "#..#.#\n"
                + "#..G.#\n"
                + "######";

        file = tempDir.resolve("grid.txt");
        Files.writeString(file, gridData);
        grid = new Grid(file.toString());

        robot = new Robot(grid.getStartRow(), grid.getStartCol(), Robot.Orientation.NORTH);
        pathPlanner = new PathPlanner(grid, grid.getStartRow(), grid.getStartCol(), grid.getEndRow(), grid.getEndCol());
        path = pathPlanner.findShortestPath();
        assertEquals(6, path.size());

        // Test case 1: Start and end positions are just 1 block away
        gridData = "@G";

        file = tempDir.resolve("grid.txt");
        Files.writeString(file, gridData);
        grid = new Grid(file.toString());

        robot = new Robot(grid.getStartRow(), grid.getStartCol(), Robot.Orientation.NORTH);
        pathPlanner = new PathPlanner(grid, grid.getStartRow(), grid.getStartCol(), grid.getEndRow(), grid.getEndCol());
        path = pathPlanner.findShortestPath();
        assertEquals(2, path.size());

        // Test case 2: Start and end positions are the same
        gridData = "@";

        file = tempDir.resolve("grid.txt");
        Files.writeString(file, gridData);
        grid = new Grid(file.toString());

        robot = new Robot(grid.getStartRow(), grid.getStartCol(), Robot.Orientation.NORTH);
        pathPlanner = new PathPlanner(grid, grid.getStartRow(), grid.getStartCol(), grid.getEndRow(), grid.getEndCol());
        path = pathPlanner.findShortestPath();
        assertEquals(1, path.size());

        // Test case 3: No obstacles, start and end positions are different
        String noObstaclesGridData = "@..\n...\nG..";
        file = tempDir.resolve("grid.txt");
        Files.writeString(file, noObstaclesGridData);
        grid = new Grid(file.toString());

        robot = new Robot(grid.getStartRow(), grid.getStartCol(), Robot.Orientation.NORTH);
        pathPlanner = new PathPlanner(grid, grid.getStartRow(), grid.getStartCol(), grid.getEndRow(), grid.getEndCol());
        path = pathPlanner.findShortestPath();
        assertEquals(3, path.size());

        // Test case 5: No path exists
        String noPathGridData = "@##\n#.#\n#G.";
        file = tempDir.resolve("grid.txt");
        Files.writeString(file, noPathGridData);
        grid = new Grid(file.toString());

        robot = new Robot(grid.getStartRow(), grid.getStartCol(), Robot.Orientation.NORTH);
        pathPlanner = new PathPlanner(grid, grid.getStartRow(), grid.getStartCol(), grid.getEndRow(), grid.getEndCol());
        path = pathPlanner.findShortestPath();
        assertEquals(0, path.size());
    }


}

