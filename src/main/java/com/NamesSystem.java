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


}
