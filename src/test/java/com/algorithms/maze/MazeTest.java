package com.algorithms.maze;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class MazeTest {

    private final static Log LOG = LogFactory.getLog(MazeTest.class);

    private Maze maze;

    @Test
    public void testMazeWithInvalidFileShouldThrowException() {
        File maze1 = new File("test.txt");
        try {
            this.maze = new Maze(maze1);
        } catch (FileNotFoundException ex) {
            assertThat(ex.getMessage(), containsString("The system cannot find the file specified"));
        }
    }

    @Test
    public void testMazeHasCorrectStructure(){
        File maze1 = new File("src/test/resources/maze/maze1.txt");
        try {
            String fileText = "";
            try (Scanner input = new Scanner(maze1)) {
                while (input.hasNextLine()) {
                    fileText += input.nextLine() + "\n";
                }
            }
            String[] lines = fileText.split("[\r]?\n");
            assertEquals(lines.length,12);
            this.maze = new Maze(maze1);
            assertNotNull(maze);
            assertEquals(maze.getWidth(), 10);
        } catch (FileNotFoundException ex) {
            LOG.error(ex.getMessage() + ex);
        }
    }
}
