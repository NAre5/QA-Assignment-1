package com;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * This class responsibility is to provide functions that show information about names list from
 * the file "namesList.txt"
 */
class NamesSystem {
    private static String[] names = null;//array contains names for the class

    //This code will initiate the names array from the names' file (once the NameSystem been called)
    static {
        String path = "namesList.txt";
        readNames(path);
    }

    /**
     * This function import the names from a file placed in the given path
     * @param path - path of the names' file
     */
    private static void readNames(String path) {
        InputStream inputStream;
        BufferedReader reader = null;
        try {
            inputStream = NamesSystem.class.getResourceAsStream(path);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            List<String> names_list = new ArrayList<>();
            String name;
            //iterate each name and add it to the list
            while ((name = reader.readLine()) != null) {
                names_list.add(name);
            }
            //convert names_list from list to array
            names = new String[names_list.size()];
            names = names_list.toArray(names);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
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
     * For each substring (in length of size) from our names' array we print
     * the substring and his correspond number of appearances in the names' array.
     *
     * @param size - length of substrings we inspect
     */
    static Map<String, Integer> CountAllStrings(int size) {
        Map<String, Integer> countingMap = createStringcountingMap(size, false);
        for (Map.Entry<String, Integer> entry : countingMap.entrySet()) {
            System.out.println(String.format("%s:%s", entry.getKey(), entry.getValue()));
        }
        return countingMap;
    }

    /**
     * build dictionary where the key is substring of length size and value is number of substring appearances
     * in names' array
     *
     * @param size - the length of substring
     * @param ignoreCase - if this function will ignore case in the name
     * @return the built dictionary
     */
    private static Map<String, Integer> createStringcountingMap(int size, boolean ignoreCase) {
        Map<String, Integer> countingMap = new HashMap<>();
        for (String name : names) {
            name = ignoreCase ? name.toLowerCase() : name;
            for (int i = 0; i + size - 1 < name.length(); i++) {
                String subName = name.substring(i, i + size);
                //if it's the first occurrence of the string, the assigned value will be 1,
                //else one plus the current value 
                countingMap.put(subName, countingMap.getOrDefault(subName, 0) + 1);
            }
        }
        return countingMap;
    }

    /**
     * Print the substring(s) of length size with maximum appearances
     *
     * @param size - the length of substring
     */
    static List<String> CountMaxString(int size) {
        Map<String, Integer> countingMap = createStringcountingMap(size, true);
        Stream<Map.Entry<String, Integer>> sorted =
                countingMap.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        Iterator<Map.Entry<String, Integer>> iterator = sorted.iterator();
        if (!iterator.hasNext())
            return new ArrayList<>();

        Map.Entry<String, Integer> max = iterator.next();
        List<String> ans = Arrays.asList(max.getKey());
        Integer maxCount = max.getValue();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> current = iterator.next();
            if (current.getValue().equals(maxCount))
                ans.add(current.getKey());
            else
                break;
        }
        for (String name : ans) {
            System.out.println(name);
        }
        return ans;
    }

    static List<String> AllIncludesString(String s) {
        s = s.toLowerCase();
        List<String> ans = new ArrayList<>();
        for (String name : names) {
            name = name.toLowerCase();
            if (s.contains(name))
                ans.add(name);
        }
        for (String name : ans) {
            System.out.println(name);
        }
        return ans;
    }

}
