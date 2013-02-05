import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Stonegame
{
    public static void main (String [] args) throws Exception 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int[] p=new int[n];
        for(int i=0;i<n;i++) {
            p[i]=Integer.parseInt(st.nextToken());
        }
        int[] sub=new int[n];
        long total=0;
        for(;;) {
            if(check(p,sub)) {
                if(somethingSame(sub)) {
                    total++;
                }
            }
            sub=addOne(sub,0,p);
            if(sub.length==0) break;
        }
        System.out.println((int)(total%1000000007));
    }
    private static boolean somethingSame(int[] b) {
        for(int i=0;i<b.length;i++) {
            if(b[i]==0) return true;
        }
        return false;
    }
    private static int[] addOne(int[] sub,int which,int[] max) {
        if(which>=sub.length) return new int[0];
        sub[which]++;
        if(sub[which]>max[which]) {
            sub[which]=0;
            return addOne(sub,which+1,max);
        }
        return sub;
    }
    private static boolean check(int[] nums,int[] sub) {
        int total=0;
        for(int i=0;i<nums.length;i++) total^=nums[i]-sub[i];
        return total==0;
    }
    private static int mul(long a,long b) {
        long both=a*b;
        both%=1000000007;
        return (int)both;
    }
}