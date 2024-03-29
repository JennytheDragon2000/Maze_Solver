package org.example;

public class Simulation {
    private Grid grid;
    private Robot robot;
//    private List<Node> path;
    private CustomArrayList<Node> path;

    public Simulation(Grid grid, Robot robot, CustomArrayList<Node> path) {
        this.grid = grid;
        this.robot = robot;
        this.path = path;
    }

    public void run() {
        if (path.isEmpty()) {
            System.out.println("No path found.");
            return;
        }

        System.out.println("Initial state:");
        printGrid();

        for (int i = 0; i < path.size(); i++) {
            Node node = path.get(i);
            robot.setPosition(node.row, node.col);
            System.out.println("\nMoving to (" + node.row + ", " + node.col + ")");
            printGrid();
            try {
                Thread.sleep(500); // Pause for 0.5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nReached the goal!");
    }

    private void printGrid() {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                if (row == robot.getRow() && col == robot.getCol()) {
                    System.out.print("R "); // Robot symbol
                } else if (grid.isObstacle(row, col)) {
                    System.out.print("# "); // Obstacle symbol
                } else {
                    System.out.print(". "); // Empty cell symbol
                }
            }
            System.out.println();
        }
    }


}