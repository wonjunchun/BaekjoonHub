import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<int[]> order = new ArrayList<>(); //순서를 담는 리스트 {src, dst}
    public static int hanoi(int N, int src, int dst, int via){
        if(N == 1){
            order.add(new int[] {src, dst}); //원판이 하나일때는 바로 src에서 dst로 이동
            return 1;
        }
        int K = 0; //옮긴 횟수
        K += hanoi(N-1, src, via, dst); //N-1개의 원판을 src에서 via로(제일 밑 원판을 dst로 보내야하므로)
        order.add(new int[] {src, dst}); //제일 큰 원판을 src에서 dst로
        K++;//제일 큰 원판 이동했으므로
        K += hanoi(N-1, via, dst, src); //N-1개의 원판을 via에서 src를 경유하여 dst로 이동
        return K;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //매번 출력은 I/O가 많이 발생하여, StringBuilder로 출력값 모았다가 한번에 출력
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int K = hanoi(N, 1, 3, 2);
        sb.append(K+"\n");
        for(int[] o: order){
            sb.append(o[0]+" "+o[1]+"\n");
        }
        System.out.printf(sb.toString());
    }
}
