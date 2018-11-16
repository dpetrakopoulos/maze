package com.algorithms.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BFSWrite {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public List<Coordinate> solve(Maze maze) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>(); //the queue
        Coordinate start = maze.getEntry();
        nextToVisit.add(start); //add start node to queue

        while(!nextToVisit.isEmpty()){
            Coordinate cur = nextToVisit.remove(); //pop node from queue

            //if node outside maze or already visited skip iteration
            if(!maze.isValidLocation( cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())){
                continue;
            }

            //if node is wall add to visited and skip iteration
            if(maze.isWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            //if exit backtrack to find shortest path
            if(maze.isExit(cur.getX(), cur.getY())){
                return backtrackPath(cur);
            }

            //add all immediate neighbors to queue
            for(int[] direction : DIRECTIONS){
                Coordinate coordinate = new Coordinate(cur.getX(), cur.getY(), cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(Coordinate cur){
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while(iter != null){
            path.add(iter);
            iter = iter.parent;
        }
        return path;
    }

}