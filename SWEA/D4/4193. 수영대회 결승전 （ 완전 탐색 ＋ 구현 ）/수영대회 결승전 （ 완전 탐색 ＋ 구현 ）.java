import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] start, end;
	static boolean isArrived;
	static int result1, result2; //소용돌이 고려 안한경우, 한 경우 도달 시간
	static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}}; //4방 + 가만히있기
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for(int m = 0; m < N; m++) {
					map[n][m] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			start = new int[2];
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			end = new int[2];
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			isArrived = false;
			
			visited = new boolean[N][N];
			int result2 = swimWithTornado();
			System.out.println("#"+t+" "+result2);
		}
	}

	private static int swimWithTornado() { //소용돌이 고려한 경우
		//소용돌이는 time을 3으로 mod연산한 나머지가 2인 경우 잠잠해짐
		int time = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0, start[0], start[1]});
		//큐 명세 {time, r, c}
		visited[start[0]][start[1]] = true;
		
		while(!queue.isEmpty()) {
			int[] current = queue.pollFirst();
			int curTime = current[0];
			int curR = current[1];
			int curC = current[2];
			
			if(curTime > N*N) break; //소요시간이 맵 크기보다 커지면 종료
			
			for(int d = 0; d < 5; d++) { //4방탐색+제자리
				int nextR = curR + directions[d][0];
				int nextC = curC + directions[d][1];
				
				//좌표 범위를 벗어났다면 continue
				if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;
				
				if(nextR == end[0] && nextC == end[1]) {
					return curTime + 1; //도착지점 도달했으면 소요시간 반환
					
				}
				
				if(d == 4) { //제자리 연산일 때
					queue.add(new int[] {curTime+1, nextR, nextC});
					continue;
				}
				//이미 방문했거나 못지나가는 장애물일때 continue
				else if(visited[nextR][nextC] || map[nextR][nextC] == 1) {
					continue;
				}
				//소용돌이인 경우, 잠잠할때 지나가고 아니면 못지나감
				else if(map[nextR][nextC] == 2) {
					if((curTime) % 3 == 2) {
						queue.add(new int[] {curTime+1, nextR, nextC});
						visited[nextR][nextC] = true;
					}
					else {
						continue;
					}
				}
				
				else { //이외의 경우 그냥 이동
					queue.add(new int[] {curTime+1, nextR, nextC});
					visited[nextR][nextC] = true;
				}
			}
		}
		return -1; //도달 할 수 없다면 -1 종료
	}
	
	private static int swim() {
		int time = 0;
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0, start[0], start[1]});
		//큐 명세 {time, r, c}
		visited[start[0]][start[1]] = true;
		
		while(!queue.isEmpty()) {
			int[] current = queue.pollFirst();
			int curTime = current[0];
			int curR = current[1];
			int curC = current[2];
			
			for(int d = 0; d < 4; d++) { //4방탐색만 함
				int nextR = curR + directions[d][0];
				int nextC = curC + directions[d][1];
				
				//좌표 범위를 벗어났거나 빈칸이 아니거나 이미 방문했다면 continue
				if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || visited[nextR][nextC] || map[nextR][nextC] != 0) continue;
				
				if(nextR == end[0] && nextC == end[1]) {
					return curTime + 1; //도착지점 도달했으면 소요시간 반환
					
				}
				else {
					queue.add(new int[] {curTime+1, nextR, nextC});
					visited[nextR][nextC] = true;
				}
			}
		}
		return -1; //도달 할 수 없다면 -1 종료
	}
}