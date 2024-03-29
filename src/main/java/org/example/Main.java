package org.example;

public class Main {
    public static void main(String[] args) {
        String filename = "/home/jenny/IdeaProjects/MazeWithTests/src/main/java/org/example/maze.txt"; // Replace with the actual filename
        Grid grid = new Grid(filename);

        Robot robot = new Robot(grid.getStartRow(), grid.getStartCol(), Robot.Orientation.NORTH);

        PathPlanner pathPlanner = new PathPlanner(grid, grid.getStartRow(), grid.getStartCol(), grid.getEndRow(), grid.getEndCol());
        CustomArrayList<Node> path = pathPlanner.findShortestPath();

        Simulation simulation = new Simulation(grid, robot, path);
        simulation.run();
    }
}