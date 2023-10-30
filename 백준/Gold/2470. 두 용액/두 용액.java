import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] liquids = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            liquids[n] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(liquids);
        int minSum = 2000000000;
        //일단 pivot값 n으로 잡음
        int ptr1 = 0;
        int ptr2 = N-1;
        int answer1 = 0, answer2 = 0;
        while(ptr1 < ptr2){
//            int mid = (ptr1+ptr2)/2;
            int subSum = liquids[ptr1]+liquids[ptr2];
            if(Math.abs(subSum) < minSum){ //
                minSum = Math.abs(subSum);
                answer1 = liquids[ptr1];
                answer2 = liquids[ptr2];

                if(minSum == 0) break;
            }
            if(subSum < 0){
                ptr1++;
            }else{
                ptr2--;
            }
        }
        System.out.print(answer1 + " " + answer2);
    }
}
