import java.util.Scanner;

/**
 * 외적의 성질 => 벡터 A를 기준으로 벡터 B가 얼마나 회전하려는 지 나타내는 척도
 * AXB = A*B*sin(a)
 * 양수일 때 : 벡터 B 선분의 위치가 시계방향에 놓임
 * 0 일 때 : 일직선
 * 음수일 때 : 반시계방향에 놓임
 */
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int x3 = sc.nextInt();
        int y3 = sc.nextInt();
        if ((x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3) > 0) {
            System.out.println(1);
        }
        else if((x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3) < 0){
            System.out.println(-1);
        }
        else{
            System.out.println(0);
        }
    }
}
