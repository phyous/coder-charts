// For java, make sure the main class name is half_scalar

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class ScalarPair {
    public ArrayList<Integer> first;
    public ArrayList<Integer> second;
    
    ScalarPair(ArrayList<Integer> f, ArrayList<Integer> s) {
        first = f;
        second = s;
    }
    
    void swap(){
        ArrayList<Integer> tmp = first;
        first = second;
        second = tmp;
    }
}

public class half_scalar {
    public static void main(String[] args) throws IOException {
        FileInputStream fstream = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        ArrayList<ScalarPair> tests = new ArrayList<ScalarPair>();
        int numTests = Integer.parseInt(br.readLine());
        while (numTests > 0) {
            int vectorSize = Integer.parseInt(br.readLine());
            String[] splitsOne = br.readLine().split(" ");
            ArrayList<Integer> vectorOne = new ArrayList<Integer>();
            for (String s : splitsOne) {
                vectorOne.add(Integer.parseInt(s));
            }
            String[] splitsTwo = br.readLine().split(" ");
            ArrayList<Integer> vectorTwo = new ArrayList<Integer>();
            for (String s : splitsTwo) {
                vectorTwo.add(Integer.parseInt(s));
            }
            Collections.sort(vectorOne);
            Collections.sort(vectorTwo);
            tests.add(new ScalarPair(vectorOne, vectorTwo));
            numTests--;
        }
        in.close();

        for (ScalarPair s : tests) {
            int ret = calculate_half_scalar(s);
            System.out.printf("%d\n", ret);
        }
    }
    
    static int calculate_half_scalar(ScalarPair s){
        int product_count = 0;
        int scalarProduct = 0;

        if(containsMoreNegative(s.second, s.first))
            s.swap();

        while (product_count <= s.first.size()/2){
            int num = s.first.remove(0);
            if(num < 0){
                // take the highest number from second
                num *= s.second.remove(s.second.size()-1);
            } else if( num == 0){
                // take middle ??
                num *= s.second.remove((s.second.size()-1)/4-1);
            } else {// number > 0, take lowest
                num *= s.second.remove((s.second.size()/2)-1);
            }
            scalarProduct += num;
            product_count++;
        }
        return scalarProduct;
    }

    static boolean containsMoreNegative(ArrayList<Integer> one, ArrayList<Integer> two){
        int oneNegCount = 0;
        int twoNegCount = 0;

        for(int i: one){
            if(i<0) oneNegCount++;
        }
        for(int i: two){
            if(i<0) twoNegCount++;
        }

        return (oneNegCount >= twoNegCount);
    }

}
