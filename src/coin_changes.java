import java.io.*;
import java.util.ArrayList;


public class coin_changes {
    public static void main(String[] args) throws IOException {
        for (int i : getNums(args[0])) {
            System.out.println(coinChangeDivide(i));
        }
    }

    private static ArrayList<Integer> getNums(String file) throws IOException {
        FileInputStream fstream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while ((strLine = br.readLine()) != null) {
            nums.add(Integer.parseInt(strLine));
        }
        in.close();

        return nums;
    }

    private static int coinChangeDivide(int i) {
        int combos = 0;

        for (int q = 0; q <= i / 25; q++) {
            int total_less_q = i - q * 25;
            for (int d = 0; d <= total_less_q / 10; d++) {
                int total_less_q_d = total_less_q - d * 10;
                for (int n = 0; n <= total_less_q_d / 5; n++) {
                    int p = total_less_q_d - n * 5;
                    //System.out.println(String.format("%d\t%d\t%d\t%d\n", q, d, n, p));
                    combos++;
                }
            }
        }
        return combos;
    }
}

