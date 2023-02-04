import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 백트래킹
 */
public class Main {
    static List<List<Integer>> sequences = new ArrayList<>(); //수열들의 목록
    public static void solve(int N, int M, int current, List<Integer> seq){
        if(M == 0){ //수열의 길이에 도달하였다면
            //더이상 seq에 추가안하고 현재 수열 sequences에 넣고 종료
            sequences.add(seq);
            return;
        }
        for(int i = current; i <= N; i++){
            //수열은 사전순으로 증가하는 순서로만 올수 있으므로
            //현재 값보다 큰값만 다음 원소로 seq에 넣어야함
            boolean isExist = false;
            for(int s: seq){ //최대 7회 순회
                if(i == s) isExist = true; //seq에 이미 있는 숫자는 넣지 않기위해
            }
            if(isExist) continue; //만약 seq 에 i가 들어있다면 넣지않음
            List<Integer> newSeq = new ArrayList<>();
            newSeq.addAll(seq);
            newSeq.add(i);
            solve(N, M-1, i, newSeq); //현재 수열에 앞으로 M-1개 만큼 더 넣음
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        for(int i = 1; i <= N; i++){
            List<Integer> seq = new ArrayList<>();
            seq.add(i);
            solve(N, M-1, i, seq);
        }
        StringBuilder sb = new StringBuilder();
        for(List<Integer> s: sequences){
            for(int nums: s){
                sb.append(nums+" ");
            }
            sb.append("\n");
        }
        System.out.printf(sb.toString());
    }
}