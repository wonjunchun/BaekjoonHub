import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int N;
    static List<Integer> list = new LinkedList<>();
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        findPrimeNum(0, 0);
        for(int l : list){
            System.out.println(l);
        }
    }

    /**
     * 신기한소수찾는 방법
     * 한자리 소수부터 찾아서, 해당 소수의 뒷자리에 숫자 하나씩 덧붙여서 소수인지 판별
     * 소수인지 판별은 해당 수의 제곱근값까지만 찾아도 됨
     * @param cnt : 몇자리 수까지 찾았는지
     * @param curNum : 현재 숫자
     */
    public static void findPrimeNum(int cnt, int curNum){
        if(cnt == N){
            list.add(curNum);
            return;
        }
        else if(cnt == 0){
            for(int i = 2; i < 10; i++){
                int divcount = 0;
                for(int j = 1; j <= Math.sqrt(i); j++){
                    if(i % j == 0){
                        divcount++;
                    }
                    if(divcount >= 2) break; //나누어지는 수가 2개 이상이면 소수아니므로
                }
                if(divcount == 1){ //소수이면
                    findPrimeNum(cnt+1, i);
                }
            }
        }
        else{
            int nextNum = curNum*10;
            for(int i = 0; i < 10; i++){
                int nextPrime = nextNum + i;
                int divcount = 0;
                for(int j = 1; j <= Math.sqrt(nextPrime); j++){
                    if(nextPrime % j == 0){
                        divcount++;
                    }
                    if(divcount >= 2) break;
                }
                if(divcount == 1){ //소수이면
                    findPrimeNum(cnt+1, nextPrime);
                }
            }
        }
    }
}
