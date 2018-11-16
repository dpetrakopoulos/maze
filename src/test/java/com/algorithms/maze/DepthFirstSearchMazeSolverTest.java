package com.algorithms.maze;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepthFirstSearchMazeSolverTest {
    private final static Log LOG = LogFactory.getLog(DepthFirstSearchMazeSolverTest.class);
    private Maze maze;

    @Test
    public void testDepthFirstSearchMazeSolver() {
        File maze1 = new File("src/test/resources/maze/maze1.txt");
        try {
            this.maze = new Maze(maze1);
            Coordinate start = maze.getEntry();
            assertEquals(start.x, 0);
            assertEquals(start.y, 0);
            DepthFirstSearchMazeSolver dfs = new DepthFirstSearchMazeSolver();
            List<Coordinate> coords = dfs.solve(maze);
            Assert.assertFalse(coords.isEmpty());
        } catch (FileNotFoundException ex) {
            LOG.error(ex.getMessage() + ex);
        }
    }

    @Test
    public void testNoSolutionReturnsEmptyList() {
        File maze2 = new File("src/test/resources/maze/maze2.txt");
        try {
            this.maze = new Maze(maze2);
            Coordinate start = maze.getEntry();
            assertEquals(start.x, 0);
            assertEquals(start.y, 0);
            DepthFirstSearchMazeSolver dfs = new DepthFirstSearchMazeSolver();
            List<Coordinate> coords = dfs.solve(maze);
            Assert.assertTrue(coords.isEmpty());
        } catch (FileNotFoundException ex) {
            LOG.error(ex.getMessage() + ex);
        }
    }
}
