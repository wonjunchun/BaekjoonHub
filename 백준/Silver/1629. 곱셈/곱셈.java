import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        long result = multiply(A, B, C);
        System.out.println(result);
    }

    private static long multiply(int A, int B, int C) {
        //A를 B번 곱한수를 C로 나눈 나머지 구하는 함수
        //modulo 연산의 성질 이용하기
        //(a*b)%c == ((a%c)*(b%c))%c
        if(B == 0) return 1;
        else if(B == 1) return A % C;
        else{
            long subresult = multiply(A, B/2, C);
            return (((subresult * subresult) % C) * multiply(A, B%2, C)) % C;
        }
    }
}