import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
public class Mancala {
    /* Head ends here */
    static void printNextMove(int player, int player1Mancala, int[] player1Marbles, int player2Mancala, int[] player2Marbles) {
        //try each move
        int[] eachScore=new int[6];
        int[] eachLeft=new int[6];
        int[] yours=player1Marbles;
        int[] others=player2Marbles;
        if(player==2) {
            yours=player2Marbles;
            others=player1Marbles;
        }
        int maxScore=-100;
        int maxMove=-1;
        int howComplete=player1Mancala+player2Mancala;
        for(int i=0;i<6;i++) {
            if(yours[i]>0) {
                eachScore[i]=scoreMove(i,yours,others);
                eachLeft[i]=score(i,yours,others);
                if(maxMove==-1||eachScore[i]>maxScore+1||eachScore[i]>=maxScore&&(howComplete<16||eachLeft[i]>eachLeft[maxMove]||(yours[i]<yours[maxMove]&&others[5-i]!=0))) {
                    maxMove=i;
                    maxScore=eachScore[i];
                }
            }
        }
        System.out.println(maxMove+1);
    }
    static int score(int move,int[] yours,int[] others) {
        int[] p1m=new int[6];
        int[] p2m=new int[6];
        for(int j=0;j<6;j++) {
            p1m[j]=yours[j];
            p2m[j]=others[j];
        }
        int curr;
        if(p1m[move]>0) {
            curr=p1m[move];
            p1m[move]=0;
            return putMarbles(curr,move,p1m,p2m);
        }
        return -1;
    }
    
    static int putMarbles(int mLeft,int spot,int[] yours,int[] others) {
        //0-5 is yours, 6 is mancala, 7-12 is theirs
        int score=0;
        for(;mLeft>0;mLeft--) {
            spot++;
            spot%=13;
            if(spot<6) yours[spot]++;
            else if(spot<7) score++;
            else others[spot-7]++;
        }
        if(spot==6) {
            //take another turn
            int max=0;
            for(int i=0;i<6;i++) {
                max=Math.max(max,scoreMove(i,yours,others));
            }
        } else if(spot<6) {
            //check if empty
            if(yours[spot]==1) {
                yours[spot]+=others[5-spot];
                others[5-spot]=0;
            }
        }
        for(int i=0;i<6;i++) {
            score+=yours[i];
            score-=others[i];
        }
        return score;
    }
    static int scoreMove(int move,int[] yours,int[] others) {
        int[] p1m=new int[6];
        int[] p2m=new int[6];
        for(int j=0;j<6;j++) {
            p1m[j]=yours[j];
            p2m[j]=others[j];
        }
        int curr;
        if(p1m[move]>0) {
            curr=p1m[move];
            p1m[move]=0;
            return putMarblesIn(curr,move,p1m,p2m);
        }
        return -1;
    }
    static int putMarblesIn(int mLeft,int spot,int[] yours,int[] others) {
        //0-5 is yours, 6 is mancala, 7-12 is theirs
        int score=0;
        for(;mLeft>0;mLeft--) {
            spot++;
            spot%=13;
            if(spot<6) yours[spot]++;
            else if(spot<7) score++;
            else others[spot-7]++;
        }
        if(spot==6) {
            //take another turn
            int max=0;
            for(int i=0;i<6;i++) {
                max=Math.max(max,scoreMove(i,yours,others));
            }
            score+=max;
        } else if(spot<6) {
            //check if empty
            if(yours[spot]==1) {
                score+=others[5-spot]/2;
                yours[spot]+=others[5-spot];
                others[5-spot]=0;
            }
        }
        return score;
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