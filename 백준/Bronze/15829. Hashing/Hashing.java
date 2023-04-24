import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    /**
     * ascii 코드표에 따르면 a:97 ~ z:122
     * a : 1 ~ z : 26 으로 대치하려면
     * charAt() 한 후 -96 시켜주기
     * 31^50 = 매우 큼(int, long 범위 초과) => BigInteger 사용하기
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int L = Integer.parseInt(sc.nextLine());
        String input = sc.nextLine();
        BigInteger result = new BigInteger("0"); //값 누적
        for(int i = 0; i < L; i++){
            int alpha = input.charAt(i) -96;
            BigInteger ri = new BigInteger("31").pow(i); //31^i
            ri = ri.multiply(new BigInteger(Integer.toString(alpha)));
            result = result.add(ri); //값 누적
        }
        result = result.mod(new BigInteger("1234567891"));
        System.out.println(result.toString());

    }
}