// For java, make sure the main class name is its_raining_anagrams

import java.io.*;

public class its_raining_anagrams {
    public static void main(String[] args) throws IOException {
        java.util.HashMap<String, java.util.ArrayList<String>> sortedDictionary = new java.util.HashMap<String, java.util.ArrayList<String>>();

        FileInputStream fstream = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            String sorted = sortString(strLine);
            if (sortedDictionary.containsKey(sorted)) {
                java.util.ArrayList<String> ar = sortedDictionary.get(sorted);
                ar.add(strLine);
            } else {
                java.util.ArrayList<String> ar = new java.util.ArrayList<String>();
                ar.add(strLine);
                sortedDictionary.put(sorted, ar);
            }
        }

        fstream = new FileInputStream(args[1]);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        while ((strLine = br.readLine()) != null) {
            java.util.ArrayList<String> answers = sortedDictionary.get(sortString(strLine));
            System.out.println(answers.size());
            for (String a : answers) {
                System.out.println(a);
            }
        }
    }

    private static String sortString(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }
}
