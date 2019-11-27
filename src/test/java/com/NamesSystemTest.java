package com;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class NamesSystemTest {

    @Test
    public void countSpecificString() {
        String s = "As";
        int expected = 16;
        int actual = NamesSystem.CountSpecificString(s);
        assertEquals(expected, actual);
    }
}