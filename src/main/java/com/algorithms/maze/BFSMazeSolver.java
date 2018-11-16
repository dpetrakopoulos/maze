package com.algorithms.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Whereas in BFS, we’ll explore all the immediate children before moving on to the grandchildren.
 * This will ensure that all nodes at a particular distance from the parent node, are explored at the same time.
 *
 * The algorithm can be outlined as follows:
 * Add the starting node in queue
 * While the queue is not empty, pop a node, do following:
 * If we reach the wall or the node is already visited, skip to next iteration
 * If exit node is reached, backtrack from current node till start node to find the shortest path
 * Else, add all immediate neighbors in the four directions in queue
 * One important thing here is that the nodes must keep track of their parent, i.e. from where they were added to the queue.
 * This is important to find the path once exit node is encountered.
 */
public class BFSMazeSolver {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    /**
     * We’ll just make one slight modification.
     * Instead of recursive traversal, we’ll use a FIFO data structure to track neighbors and iterate over them
     *
     */
    public List<Coordinate> solve(Maze maze) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>(); //the queue
        Coordinate start = maze.getEntry();
        nextToVisit.add(start); //Add the starting node in queue

        while(!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();//While the queue is not empty, pop a node

            //If node is outside the maze or already visited, skip to next iteration
            if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            //If we reach the wall set it to visited and skip to next iteration
            if (maze.isWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            //If exit node is reached, backtrack from current node till start node to find the shortest path
            if (maze.isExit(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            //Else, add all immediate neighbors in the four directions in queue
            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    /**
     * Lets first define a utility method to backtrack from a given node to its root.
     * This will be used to trace the path once exit is found
     *
     */
    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while(iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}
