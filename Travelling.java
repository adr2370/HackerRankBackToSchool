import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Map.Entry;
public class Travelling {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N=Integer.parseInt(st.nextToken());
        double C=Double.parseDouble(st.nextToken());
        double D=Double.parseDouble(st.nextToken());
        int[][] loc=new int[N][2];
        int[] price=new int[N];
        for(int i=0;i<N;i++) {
            st=new StringTokenizer(in.readLine());
            loc[i][0]=Integer.parseInt(st.nextToken());
            loc[i][1]=Integer.parseInt(st.nextToken());
            price[i]=Integer.parseInt(st.nextToken());
        }
        Map map=new TreeMap(Collections.reverseOrder());
        double[] newP=new double[N];
        for(int i=0;i<N;i++) {
            double curr=price[i]-(C+2)*Math.sqrt(loc[i][0]*loc[i][0]+loc[i][1]*loc[i][1]);
            newP[i]=curr;
            if(curr>0) {
                int[] comb=new int[2];
                comb[0]=i;
                comb[1]=-1;
                map.put(curr,comb);
            }
        }
        long startTime=System.currentTimeMillis();
        HashSet<Integer> set=new HashSet<Integer>();
        double mul=1.0;
        int numVisited=0;
        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Entry entry = (Entry) entries.next();
            double d = (Double) entry.getKey();
            int[] v = (int[]) entry.getValue();
            ArrayList<Integer> visiting=new ArrayList<Integer>();
            visiting.add(v[0]);
            if(!set.contains(v[0])) {
                d-=price[v[0]]*(1-mul);
                if(d>0) {
                    double maxScore=d;
                    double newMul=mul;
                    int totalVisits=numVisited;
                    for(;System.currentTimeMillis()-startTime<2500;) {
                        for(int i=0;i<visiting.size();i++) {
                            totalVisits++;
                            if(totalVisits%(N/10)==0) newMul*=D;
                        }
                        ArrayList<Integer> newVisiting=new ArrayList<Integer>(visiting);
                        for(int i=0;i<N&&System.currentTimeMillis()-startTime<2500;i++) {
                            if(!visiting.contains(i)&&!set.contains(i)) {
                                int f=visiting.get(visiting.size()-1);
                                int s=i;
                                int cX=loc[f][0];
                                int cY=loc[f][1];
                                double newPrice=d+Math.sqrt(cX*cX+cY*cY);
                                cX=loc[f][0]-loc[s][0];
                                cY=loc[f][1]-loc[s][1];
                                newPrice+=newMul*price[s]-(C+1)*Math.sqrt(cX*cX+cY*cY);
                                cX=loc[s][0];
                                cY=loc[s][1];
                                newPrice-=Math.sqrt(cX*cX+cY*cY);
                                double otherNewPrice=d+newMul*price[s]-(C+2)*Math.sqrt(cX*cX+cY*cY);
                                if(newPrice>maxScore&&newPrice>otherNewPrice) {
                                    maxScore=newPrice;
                                    if(newVisiting.size()>visiting.size()) {
                                        newVisiting.set(newVisiting.size()-1,i);
                                    } else {
                                        newVisiting.add(i);
                                    }
                                }
                            }
                        }
                        if(newVisiting.size()==visiting.size()) break;
                        else visiting=newVisiting;
                    }
                    if(numVisited>0) System.out.println("0 0");
                    for(int i=0;i<visiting.size();i++) {
                        int curr=visiting.get(i);
                        if(i==0) System.out.println(loc[curr][0]+" "+loc[curr][1]+" "+visiting.size());
                        else System.out.println(loc[curr][0]+" "+loc[curr][1]);
                        set.add(curr);
                    }
                    for(int i=0;i<visiting.size();i++) {
                        numVisited++;
                        if(numVisited%(N/10)==0) mul*=D;
                    }
                }
            }
        }
    }
}