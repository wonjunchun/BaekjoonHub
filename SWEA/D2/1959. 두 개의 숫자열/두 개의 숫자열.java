import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        for(int t = 1; t <= T; t++){
            String NM = sc.nextLine();
            String seq1Str = sc.nextLine();
            String seq2Str = sc.nextLine();
            String[] seq1StrArr = seq1Str.split(" ");
            String[] seq2StrArr = seq2Str.split(" ");

            //String[] to int[]
            int[] seq1 = Stream.of(seq1StrArr).mapToInt(Integer::parseInt).toArray();
            int[] seq2 = Stream.of(seq2StrArr).mapToInt(Integer::parseInt).toArray();

            int seq1Len = seq1.length;
            int seq2Len = seq2.length;
            int maximum = 0;

            //슬라이딩윈도우 이동하면서 dot product 수행
            if(seq1Len < seq2Len){
                int dist = seq2Len - seq1Len + 1;
                for(int i = 0; i < dist; i++){
                    int sum = 0;
                    for(int j = 0; j < seq1Len; j++){
                        sum += seq1[j] * seq2[i+j];
                    }
                    if(sum > maximum){
                        maximum = sum;
                    }
                }
            }
            else{
                int dist = seq1Len - seq2Len + 1;
                for(int i = 0; i < dist; i++){
                    int sum = 0;
                    for(int j = 0; j < seq2Len; j++){
                        sum += seq1[i+j] * seq2[j];
                    }
                    if(sum > maximum){
                        maximum = sum;
                    }
                }
            }
            System.out.println("#"+ t + " " + maximum);
        }
    }
}