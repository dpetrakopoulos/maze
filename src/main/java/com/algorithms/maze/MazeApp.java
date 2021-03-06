package com.algorithms.maze;

import java.io.File;
import java.util.List;

public class MazeApp {
    public static void main(String[] args) throws Exception {
        File maze1 = new File("src/main/resources/maze/maze1.txt");
        execute(maze1);
    }

    private static void execute(File file) throws Exception {
        Maze maze = new Maze(file);
        dfs(maze);
        bfs(maze);
    }

    private static void dfs(Maze maze) {
        DepthFirstSearchMazeSolver dfs = new DepthFirstSearchMazeSolver();
        List<Coordinate> path = dfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }

    private static void bfs(Maze maze) {
        BFSMazeSolver bfs = new BFSMazeSolver();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }
}
