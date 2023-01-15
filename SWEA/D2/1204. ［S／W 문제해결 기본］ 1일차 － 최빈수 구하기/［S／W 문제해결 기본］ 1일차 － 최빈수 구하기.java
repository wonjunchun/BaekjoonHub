import java.util.Scanner;

public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t < T+1; t++){
            int testcase = sc.nextInt();
            int[] score = new int[101]; // 수학 점수 빈도 저장하는 배열
            for(int n = 0; n < 1000; n++){
                int currentScore = sc.nextInt();
                score[currentScore] += 1;
            }

            int result = 0;
            int maxCount = 0;
            for(int n = 1; n < 101; n++){
                if(score[n] >= maxCount) {
                    result = n;
                    maxCount = score[n];
                }
            }
            System.out.println("#" + t + " " + result);
        }
    }
}
