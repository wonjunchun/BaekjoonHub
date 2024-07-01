import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();

//        [] arr = new long[(int)B + 1];
        boolean[] isPrime = new boolean[(int) Math.sqrt(B) + 1];
        for(int i = 2; i < isPrime.length; i++){
//            arr[(int)i] = i;
            isPrime[i] = true;
        }
        for(int i = 2; i < isPrime.length; i++){
            if(!isPrime[i]) continue; //소수가 아니라면 건너뛰기
            for(int j = 2*i; j < isPrime.length; j+=i){ //i의 배수 지우기
                isPrime[j] = false;
            }
        }
        int count = 0;
        for(int i = 2; i < isPrime.length; i++){
            if(isPrime[i]) { //소수이면
                long powNum = i;
                while(powNum <= (double)B/i){ //소수의 제곱수(거의소수)
                    if(powNum >= (double)A/i)
                        count++;
                    powNum *= i;
                }
            }
        }
        System.out.println(count);
    }
}
