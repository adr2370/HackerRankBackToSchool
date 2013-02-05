import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Map.Entry;
public class Stocks {
    /* Head ends here */
    static void printTransactions(double m, int k, int d, String[] name, int[] owned, double[][] prices) {
        ArrayList<String> lines=new ArrayList<String>();
        for(int i=0;i<k;i++) {
            if(owned[i]>0) {
                if(prices[i][4]>prices[i][3]) {
                    lines.add(name[i]+" SELL "+owned[i]);
                }
            }
        }
        TreeMap map=new TreeMap(Collections.reverseOrder());
        for(int i=0;i<k;i++) {
            if(prices[i][4]<prices[i][3]) {
                double dec=((double)prices[i][3]-prices[i][4])/prices[i][4];
                if(dec>0.04) map.put(dec,i);
            }
        }
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Entry entry = (Entry) entries.next();
            int i = (Integer) entry.getValue();
            int maxBuy=(int)((double)m/prices[i][4]);
            m-=maxBuy*prices[i][4];
            if(maxBuy>0) lines.add(name[i]+" BUY "+maxBuy);
            else break;
        }
        System.out.println(lines.size());
        for(int i=0;i<lines.size();i++) {
            System.out.println(lines.get(i));
        }
    }
    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        double _m;
        _m = in.nextDouble();
        
        int _k;
        _k = in.nextInt();
        
        int _d;
        _d = in.nextInt();
        
        String[] _name = new String[_k];
        int[] _owned = new int[_k];
        double[][] _prices = new double[_k][5];
        
        String _name_item;
        int _owned_item;
        double _prices_item_item;
        
        for(int _i = 0; _i < _k; _i++) {
            _name_item = in.next();
            _name[_i] = _name_item;
            
            _owned_item = in.nextInt();
            _owned[_i] = _owned_item;
            
            for(int _j = 0; _j<5; _j++) {
                _prices_item_item = in.nextDouble();
                _prices[_i][_j] = _prices_item_item;                
            }
        }
        printTransactions(_m, _k, _d, _name, _owned, _prices);
    }
}