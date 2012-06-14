// For java, make sure the main class name is golden_cornucopia

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class golden_cornucopia {

    public static void main(String[] args) throws IOException {
        FileInputStream fstream = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int numElements = Integer.parseInt(br.readLine());
        while (numElements > 0) {
            strLine = br.readLine();
            nums.add(Integer.parseInt(strLine));
            numElements--;
        }
        in.close();

        map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            int ret = calculate_corn(i);
            System.out.printf("%d\n", ret);
        }
    }

    static HashMap<Integer, Integer> map;

    private static int calculate_corn(int seed) {
        if (seed < 0)
            return 0;
        if (map.containsKey(seed))
            return map.get(seed);

        int sum = seed;
        for (int i = 1; i <= 7; i++) {
            sum = (sum + calculate_corn(seed - i)) % 1000000007;
        }
        map.put(seed, sum);
        return sum;
    }
}
