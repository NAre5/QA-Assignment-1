package com;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

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
        Map<String, Integer> countDictionary = createStringCountDictionary(size);
        for (Map.Entry<String, Integer> entry : countDictionary.entrySet()) {
            System.out.println(String.format("%s:%s", entry.getKey(), entry.getValue()));
        }
        return countDictionary;
    }

    /**
     * build dictionary where the key is substring of length size and value is number of substring appearances
     * in names' array
     *
     * @param size - the length of substring
     * @return the built dictionary
     */
    private static Map<String, Integer> createStringCountDictionary(int size) {
        Map<String, Integer> counting = new HashMap<>();
        for (String name : names) {
            for (int i = 0; i + size - 1 < name.length(); i++) {
                String subName = name.substring(i, i + size);
                counting.put(subName, counting.getOrDefault(subName, 0) + 1);
            }
        }
        return counting;
    }

    /**
     * Print the substring(s) of length size with maximum appearances
     * @param size - the length of substring
     */
    static List<String> CountMaxString(int size) {
        Map<String, Integer> countDictionary = createStringCountDictionary(size);
        Stream<Map.Entry<String, Integer>> sorted =
                countDictionary.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        Iterator<Map.Entry<String, Integer>> iterator = sorted.iterator();
        if (!iterator.hasNext())
            return new ArrayList<>();

        Map.Entry<String, Integer> max = iterator.next();
        List<String> ans = Arrays.asList(max.getKey());
        Integer maxCount = max.getValue();
        while (iterator.hasNext())
        {
            Map.Entry<String, Integer> current = iterator.next();
            if(current.getValue().equals(maxCount))
                ans.add(current.getKey());
            else
                break;
        }
        return ans;
    }

    

}
