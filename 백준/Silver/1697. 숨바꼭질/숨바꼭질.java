import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
class Seek{
    public int seek(int N, int K){
        Deque<int[]> queue = new ArrayDeque<>();
        int result = 0;
        int[] isVisited = new int[100001];
        int maximum = Math.max(N, K);
        
        //큐 명세 {수빈의 현재 위치, 지금까지 걸린 시간}
        queue.add(new int[]{N, 0});
        //숨바꼭질 범위 : 0 ~ 100,000
        while(!queue.isEmpty()){
            int[] current = queue.pollFirst();
            int currentTime = current[1]+1; //한칸 움직일때마다 1초씩 증가
            isVisited[current[0]] = 1; //방문 표시
            if(current[0] == K){ //만약 찾던 좌표이면, 종료
                result = currentTime-1; //첫 시작점부터 1 더해져서, 그만큼 빼줌
                break;
            }
            else{
                //수빈이 다음에 갈 좌표
                int[] next = {current[0]-1, current[0]+1, current[0]*2};
                for(int n : next){
                    if(n < 0 || n > 100000){
                        //정상 범위를 넘어선 값이면, pass
                        continue;
                    }
                    else if(isVisited[n] == 1){ //이미 방문했으면, pass
                        continue;
                    }
                    else{
                        queue.add(new int[]{n, currentTime});
                    }
                }
            }
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //수빈이 위치
        int K = Integer.parseInt(st.nextToken()); //동생 위치
        Seek seek = new Seek();
        System.out.println(seek.seek(N, K));

    }
}
