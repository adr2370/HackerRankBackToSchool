import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class MancalaClean {
    /* Head ends here */
    static void printNextMove(int player, int player1Mancala, int[] player1Marbles, int player2Mancala, int[] player2Marbles) {
        //try each move
        int[] a=player1Marbles;
        int aM=player1Mancala;
        int[] b=player2Marbles;
        int bM=player2Mancala;
        if(player==2) {
            a=player2Marbles;
            aM=player2Mancala;
            b=player1Marbles;
            bM=player1Mancala;
        }
        ArrayList<Integer> starting=new ArrayList<Integer>();
        for(int i=0;i<6;i++) starting.add(a[i]);
        starting.add(aM);
        for(int i=0;i<6;i++) starting.add(b[i]);
        starting.add(bM);
        starting.add(1);
        ArrayList<ArrayList<Integer>> eachOutcome=new ArrayList<ArrayList<Integer>>();//0-5 a 6 aM 7-12 b 13 bM 14 go again 15+ moves
        eachOutcome.add(starting);
        boolean anyChanged=true;
        while(anyChanged) {
            anyChanged=false;
            for(int i=0;i<eachOutcome.size();i++) {
                if(eachOutcome.get(i).get(14)>0) {
                    anyChanged=true;
                    //remove and run it
                    ArrayList<Integer> c=eachOutcome.remove(i);
                    for(int j=0;j<6;j++) {
                        if(c.get(j)>0) {
                            eachOutcome.add(simulate(j,c));
                        }
                    }
                }
            }
        }
        double maxScore=-10;
        int maxMove=0;
        for(ArrayList<Integer> c:eachOutcome) {
            //get Score
            double s=score(c);
            if(s>maxScore) {
                maxScore=s;
                maxMove=c.get(15);
            }
        }
        System.out.println(maxMove+1);
    }
    static ArrayList<Integer> simulate(int m,ArrayList<Integer> old) {
        ArrayList<Integer> c=new ArrayList<Integer>(old);
        int starting=c.get(m);
        c.set(m,0);
        int spot=m+1;
        spot%=13;
        while(starting>0) {
            starting--;
            c.set(spot,c.get(spot)+1);
            spot++;
            spot%=13;
        }
        spot+=12;
        spot%=13;
        if(spot==6) {
            c.set(14,1);
        } else {
            c.set(14,0);
        }
        if(spot<6) {
            if(c.get(spot)==1) {
                c.set(spot,c.get(spot)+c.get(12-spot));
                c.set(12-spot,0);
            }
        }
        c.add(m);
        return c;
    }
    static double score(ArrayList<Integer> c) {
        int mySide=c.get(6);
        int hisSide=0;
        for(int i=0;i<6;i++) {
            if(c.get(12-i)>0) {
                mySide+=c.get(i);
            } else {
                mySide+=c.get(i)/2;
            }
            hisSide+=c.get(12-i);
        }
        if(mySide==0) {
            if(c.get(6)>c.get(13)+hisSide) {
                return 1000000000;
            } else {
                return 0;
            }
        }
        int inMan=c.get(6)+c.get(13);
        if(inMan<10) { //start of game
            return c.get(6)+(mySide-hisSide)/8.0;
        } else if(inMan<25) { //mid game
            return c.get(6)+(mySide-hisSide)/3.0;
        } else { //end game
            return c.get(6)+(mySide-hisSide);
        }
    }
    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int _player;
        _player = in.nextInt();
        int _player1Mancala;
        _player1Mancala = in.nextInt();
        int _player1Marbles_size = 6;
        int[] _player1Marbles = new int[_player1Marbles_size];
        int _player1Marbles_item;
        for(int _player1Marbles_i = 0; _player1Marbles_i < _player1Marbles_size; _player1Marbles_i++) {
            _player1Marbles_item = in.nextInt();
            _player1Marbles[_player1Marbles_i] = _player1Marbles_item;
        }
        int _player2Mancala;
        _player2Mancala = in.nextInt();
        int _player2Marbles_size = 6;
        int[] _player2Marbles = new int[_player2Marbles_size];
        int _player2Marbles_item;
        for(int _player2Marbles_i = 0; _player2Marbles_i < _player2Marbles_size; _player2Marbles_i++) {
            _player2Marbles_item = in.nextInt();
            _player2Marbles[_player2Marbles_i] = _player2Marbles_item;
        }
        printNextMove(_player, _player1Mancala, _player1Marbles, _player2Mancala, _player2Marbles);
    }
}