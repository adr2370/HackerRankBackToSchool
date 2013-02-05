import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Beadornaments
{
    public static void main (String [] args) throws Exception 
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int T=Integer.parseInt(st.nextToken());
        while(T-->0) {
            st=new StringTokenizer(in.readLine());
            int N=Integer.parseInt(st.nextToken());
            int[] b=new int[N];
            int ans=1;
            st=new StringTokenizer(in.readLine());
            int sum=0;
            for(int i=0;i<N;i++) {
                b[i]=Integer.parseInt(st.nextToken());
                ans=mul(ans,pow(b[i],b[i]-2));
                ans=mul(ans,b[i]);
                sum+=b[i];
            }
            ans=mul(ans,pow(sum,N-2));
            System.out.println(ans);
        }
    }
    private static int mul(long a,long b) {
        long both=a*b;
        both%=1000000007;
        return (int)both;
    }
    private static int pow(long x,long n) {
        if(n<=0) return 1;
        else {
            long res=pow(x,n/2);
            res*=res;
            if(n%2==1) res*=x;
            res%=1000000007;
            return (int)res;
        }
    }
}