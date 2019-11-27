package com;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class NamesSystemTest {

    @Test
    public void countSpecificString() {
        String s = "As";
        int expected = 16;
        int actual = NamesSystem.CountSpecificString(s);
        assertEquals(expected, actual);
    }

    @Test
    public void countAllStringsChristabelExample() {
        int n = 10;
        Integer actual = NamesSystem.CountAllStrings(n).get("Christabel");
        Integer expected = 3;
        assertEquals(expected, actual);
    }

    @Test

    public void countAllStrings() {
        int n = 100;
        Map<String, Integer> actual = NamesSystem.CountAllStrings(n);
        Map<String, Integer> expected = new HashMap<>();
        assertEquals(expected, actual);
    }

    @Test
    public void countMaxString() {
        int n = 10;
        List<String> actual = NamesSystem.CountMaxString(n);
        List<String> expected = Arrays.asList("Christabel");
        assertEquals(expected, actual);
    }

    @Test
    public void countMaxStringCheckMultiple() {
        int n = 10;
        List<String> actual = NamesSystem.CountMaxString(n);
        List<String> expected = Arrays.asList("Christabel");
        //TODO find adn check for list bigger then 1 object
        //        assertEquals(expected, actual);
    }
}