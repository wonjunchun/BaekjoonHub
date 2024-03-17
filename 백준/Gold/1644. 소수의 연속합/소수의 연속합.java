import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        int[] prime = new int[N];
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i <= N; i++){ //에라토스테네스의 체
            for(int j = i * 2; j <= N; j += i){ //i의 배수들은 전부 false
                isPrime[j] = false;
            }
        }
        int primeLen = 0;
        for(int i = 2; i <= N; i++){
            if(isPrime[i]) prime[primeLen++] = i;
        }
        //투포인터를 이용한 구간합 구하기
        int subtotal = prime[0];
        int cnt = 0;
        int end = 0;
        for(int start = 0; start < primeLen; subtotal-=prime[start], start++){
            while(end < primeLen-1 && subtotal < N){
                end++;
                subtotal += prime[end];
            }
            if(subtotal == N) cnt++;
        }
        System.out.println(cnt);
    }
}
