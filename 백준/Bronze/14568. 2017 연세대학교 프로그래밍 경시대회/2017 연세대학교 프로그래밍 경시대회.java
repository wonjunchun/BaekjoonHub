import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int distCase = 0;
        for(int i = 2; i < N; i += 2){
            int remain = N-i;
            for(int j = 1; j < remain; j++){
                int subremain = remain - 2*j;
                if(subremain >= 2){
                    distCase++;
                }
            }
        }
        System.out.println(distCase);
    }
}