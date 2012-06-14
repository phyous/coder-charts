import java.io.*;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: pyoussef
 * Date: 4/10/12
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class dalton_buildings {
    
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

        for(int i: nums){
            Sequence seq = linear_search_seq(i);
            System.out.printf("%d %d\n", seq.begin, seq.end);
        }
    }

    private static Sequence validIncreasingLine(int startVal, int target) {
        int sum = 0;
        int increment = startVal;
        while (sum < target) {
            sum += increment++;
        }

        if (target == sum)
            return new Sequence(startVal, increment-1);
        else
            return null;
    }
    
    private static Sequence linear_search_seq(int searchTotal){
        if (searchTotal <=0)
            return new Sequence(searchTotal, searchTotal);

        boolean bFound = false;
        int curIndex = 1;
        while(!bFound && curIndex < searchTotal){
            Sequence s = validIncreasingLine(curIndex, searchTotal);
            if(s != null)
                return s;
            curIndex++;
        }
        
        return new Sequence(searchTotal, searchTotal);
    }
}

class Sequence{
    Sequence(int b, int e){
        begin = b;
        end = e;
    }
    
    public int begin;
    public int end;
}