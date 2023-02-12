import java.util.Scanner;

public class Main {
//    /**
//     * 아이디어: 모든 면을 Z형 순회하지 말고,
//     * 사분면으로 쪼개서 합 누적 & 소거시키자
//     * @param R
//     * @param C
//     * @return
//     */

    public static int Z(int size, int R, int C){ //정사각형 한변길이 받음
        if(size == 1)
            return 0;
        if(R < size/2 && C < size/2) //첫번째 사분면일때
            return Z(size>>1, R, C);
        else if(R < size/2 && C >= size/2) //두번째 사분면일때
            //첫번째 사분면의 면적 더하고, 탐색범위 줄임
            return (size*size/4) + Z(size>>1, R, C-(size/2));
        else if(R >= size/2 && C < size/2)//세번째 사분면일때
            //1, 2번째 사분면 면적 더하고 탐색범위 줄임
            return (size*size/4)*2 + Z(size>>1, R-(size/2), C);
        else //네번째 사분면
            return (size*size/4)*3 + Z(size>>1, R-(size/2), C-(size/2));
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int size = 1 << N;
        System.out.println(Z(size, r, c));
    }
}