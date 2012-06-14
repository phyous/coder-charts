import java.io.*;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: pyoussef
 * Date: 4/10/12
 * Time: 7:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class currency_converter {
    public static void main(String[] args) throws IOException {
        FileInputStream fstream = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;

        // Parse conversion rates
        int conversionRateCount = Integer.parseInt(br.readLine());
        HashMap<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("Dollar", 1.0);
        while (conversionRateCount > 0) {
            strLine = br.readLine();
            String[] splits = strLine.split(" ");
            double first = Double.parseDouble(splits[0]);
            double second = Double.parseDouble(splits[3]);
            if (splits[1].equals("Dollar")) {
                conversionRates.put(splits[4], second / first);
            } else {
                conversionRates.put(splits[1], first / second);
            }
            conversionRateCount--;
        }

        // Prase conversions & calculate
        int conversions = Integer.parseInt(br.readLine());
        while (conversions > 0) {
            strLine = br.readLine();
            String[] splits = strLine.split(" ");
            double amount = Double.parseDouble(splits[0]);
            String fromCurrency = splits[1];
            String toCurrency = splits[3];
            // convert fromCurrency => dollar
            amount /= conversionRates.get(fromCurrency);
            // convert dollar => toCurrency
            amount *= conversionRates.get(toCurrency);
            System.out.printf("%.4f\n", amount);
            conversions--;
        }
        in.close();
    }
}
