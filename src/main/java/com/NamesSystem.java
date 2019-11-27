package com;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NamesSystem {
    private static String[] names = null;

    static {
        String path = "namesList.txt";
        readNames(path);
    }

    private static void readNames(String path) {
        try {
            InputStream inputStream = NamesSystem.class.getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> names_list = new ArrayList<String>();
            String name;
            while ((name = reader.readLine()) != null) {
                names_list.add(name);
            }
            names = new String[names_list.size()];
            names = names_list.toArray(names);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param s - a string
     * @return how many names from the names' array contains the string s as a substring
     */
    static int CountSpecificString(String s) {
        int count = 0;
        for (String name : names) {
            if (name.contains(s))
                count++;
        }

        System.out.println(count);
        return count;
    }

    /**
     * @param size - length of substrings we inspect
     * @return for each substring (in length of size) from our names' array we print
     * the substring and his correspond number of appearances in the names' array.
     */
    static Map<String, Integer> CountAllStrings(int size) {
        Map<String, Integer> counting = new HashMap<>();
        for (String name : names) {
            for (int i = 0; i + size - 1 < name.length(); i++) {
                String subName = name.substring(i, i + size);
                counting.put(subName, counting.getOrDefault(subName, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : counting.entrySet()) {
            System.out.println(String.format("%s:%s", entry.getKey(),entry.getValue()));
        }
        return counting;
    }
}
