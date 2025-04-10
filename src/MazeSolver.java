/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack<MazeCell> path = new Stack<>();
        MazeCell currentCell = maze.getEndCell();
        // While the current cell isn't null add it to the stack and make the new cell its parent cell
        while (currentCell != null) {
            path.push(currentCell);
            currentCell = currentCell.getParent();
        }
        ArrayList<MazeCell> solution = new ArrayList<>();
        // While the stack has elements, add the parent cells into the arraylist LIFO so it starts at first point
        while (!path.empty()) {
            solution.add(path.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> search = new Stack<>();
        search.push(maze.getStartCell());
            // While the stack isn't empty, remove a cell to check and see if it is the solution
            // if it isn't check the cells in order of NESW to see if they are walls or already explored
            // if they aren't then add it to the stack to search
            while (!search.empty()) {
            MazeCell currentCell = search.pop();
            if (currentCell == maze.getEndCell()) {
                return getSolution();
            }
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            MazeCell nextCell;
            // Check North
            if (row - 1 >= 0 && maze.isValidCell(row - 1, col)) {
                nextCell = maze.getCell(row - 1, col);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.push(nextCell);
            }
            // Check East
            if (col + 1 >= 0 && maze.isValidCell(row, col + 1)) {
                nextCell = maze.getCell(row, col + 1);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.push(nextCell);
            }
            // Check South
            if (row + 1 >= 0 && maze.isValidCell(row + 1, col)) {
                nextCell = maze.getCell(row + 1, col);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.push(nextCell);
            }
            // Check West
            if (col - 1 >= 0 && maze.isValidCell(row, col - 1)) {
                nextCell = maze.getCell(row, col - 1);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.push(nextCell);
            }
        }
        // Return the solution in form of an Arraylist (using getSolution)
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        Queue<MazeCell> search = new LinkedList<>();
        search.add(maze.getStartCell());

        // While the queue isn't empty, remove a cell to check and see if it is the solution
        // if it isn't check the cells in order of NESW to see if they are walls or already explored
        // if they aren't then add it to the queue to search
        while (!search.isEmpty()) {
            MazeCell currentCell = search.remove();
            if (currentCell == maze.getEndCell()) {
                return getSolution();
            }
            int row = currentCell.getRow();
            int col = currentCell.getCol();
            MazeCell nextCell;
            // Check North
            if (row - 1 >= 0 && maze.isValidCell(row - 1, col)) {
                nextCell = maze.getCell(row - 1, col);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.add(nextCell);
            }
            // Check East
            if (col + 1 >= 0 && maze.isValidCell(row, col + 1)) {
                nextCell = maze.getCell(row, col + 1);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.add(nextCell);
            }
            // Check South
            if (row + 1 >= 0 && maze.isValidCell(row + 1, col)) {
                nextCell = maze.getCell(row + 1, col);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.add(nextCell);
            }
            // Check West
            if (col - 1 >= 0 && maze.isValidCell(row, col - 1)) {
                nextCell = maze.getCell(row, col - 1);
                nextCell.setExplored(true);
                nextCell.setParent(currentCell);
                search.add(nextCell);
            }
        }

        return getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
