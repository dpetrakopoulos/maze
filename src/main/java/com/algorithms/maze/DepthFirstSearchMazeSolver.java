package com.algorithms.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSearchMazeSolver {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public List<Coordinate> solve(Maze maze) {
        List<Coordinate> path = new ArrayList<>();
        if (explore(maze, maze.getEntry()
            .getX(),
            maze.getEntry()
                .getY(),
            path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Maze maze, int row, int col, List<Coordinate> path) {
        //If we’re at the wall or an already visited node, return failure
        if (!maze.isValidLocation(row, col) || maze.isWall(row, col) || maze.isExplored(row, col)) {
            return false;
        }

        path.add(new Coordinate(row, col));
        maze.setVisited(row, col, true);

        //if we’re at the exit node, then return success
        if (maze.isExit(row, col)) {
            return true;
        }

        // add the node in path list and recursively travel in all four directions.
        // If failure is returned, remove the node from the path and return failure.
        // Path list will contain a unique path when exit is found
        for (int[] direction : DIRECTIONS) {
            Coordinate coordinate = getNextCoordinate(row, col, direction[0], direction[1]);
            if (explore(maze, coordinate.getX(), coordinate.getY(), path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private Coordinate getNextCoordinate(int row, int col, int i, int j) {
        return new Coordinate(row + i, col + j);
    }
}
