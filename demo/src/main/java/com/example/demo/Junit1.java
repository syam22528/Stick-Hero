package com.example.demo;

import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class Junit1 {

    @Test
    public void testStickBeyondBlock() throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        GameController gm = new GameController();
        double stick_x = 5;
        double stick_length = 10;
        double nextBlockEnd = 14;
        assertTrue(gm.checkIfOutside(stick_x+stick_length, nextBlockEnd));
    }
}
