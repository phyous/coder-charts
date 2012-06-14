import java.io.*;

public class magic_words_decrypter {
    public static void main(String[] args) throws IOException {
        java.util.HashMap<String, String> wordDictionary = new java.util.HashMap<String, String>();

        FileInputStream fstream = new FileInputStream(args[1]);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            strLine = strLine.toLowerCase();
            String key = generateKey(strLine);
            if (wordDictionary.containsKey(key)) {
                if (strLine.compareTo(wordDictionary.get(key)) < 0) {
                    wordDictionary.put(key, strLine);
                }
            } else {
                wordDictionary.put(key, strLine);
            }
        }

        fstream = new FileInputStream(args[0]);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        while ((strLine = br.readLine()) != null) {
            for (String s : strLine.split(" ")) {
                String inputKey = generateKey(s);
                System.out.print(wordDictionary.get(inputKey) + " ");
            }
            if (br.ready()) System.out.print("\n");
        }
    }

    private static String generateKey(String input) {
        return String.format("%c%c%s", input.charAt(0), input.charAt(input.length() - 1), sortString(input));
    }

    private static String sortString(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }
}
