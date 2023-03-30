import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 바이러스 유출
	 * 확산 막기위해 벽 세움, 연구소크기 N*M
	 * 세울수 있는 벽 개수 3개(무조건 3개 세워야)
	 * 확산 후 남아있는 0 => 안전영역
	 * 안전영역 크기의 최대값?
	 * 
	 * <조건>
	 * 3 <= N, M <= 8
	 * 빈칸개수 >= 3
	 * 2<= 최초바이러스 수 <= 10
	 * 
	 * => 빈칸개수 최대 62개, 벽 세우는 경우 62C3 = 37820가지 경우의수
	 * 
	 * <해결방법>
	 * 1. 일단 벽 3개 세우는 경우의 수 DFS(조합)
	 * 2. 벽 세우고 바이러스 BFS확산 시킴
	 * 3. 안전영역 개수 count, 최대값이면 최대값갱신(전역변수로)
	 * 
	 * @param args
	 */
	static int N, M;
	static int[][] map;
	static int[][] newMap;
	static int maxCount = 0; //안전영역 최대 크기
	static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] maxMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		newMap = new int[N][M];
		maxMap = new int[N][M];
		//맵 값 입력받음
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		//1. 일단 벽 3개 세우는 경우의 수 DFS로 찾음(경우의 수)
		createWall(0, 0, 0);
		System.out.println(maxCount);
		
//		for(int n = 0; n < N; n++) {
//			for(int m = 0; m < M; m++) {
//				System.out.print(maxMap[n][m]+"\t");
//			}
//			System.out.println();
//		}
	}
	private static void createWall(int cnt, int startR, int startC) {
		if(cnt == 3) {
			//조합 선택 완료되었으니, 바이러스 확산 시작(BFS) - 바이러스 : 2
			
			//map 초기화를 깊은복사 말고 얕은 복사로
//			newMap = map; //초기화
			for(int n = 0; n < N; n++) {
				newMap[n] = map[n].clone();
			}
			Deque<int[]> queue = new ArrayDeque<>(); //큐 명세 {r, c}
			for(int n = 0; n < N; n++) {
				for(int m = 0; m < M; m++) {
					if(newMap[n][m] == 2) {
						queue.add(new int[] {n, m});
					}
				}
			}
			while(!queue.isEmpty()) {
				int[] current = queue.pollFirst();
				int curR = current[0];
				int curC = current[1];
				
				for(int[] d: directions) {
					int nextR = curR + d[0];
					int nextC = curC + d[1];
					if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || newMap[nextR][nextC] != 0) {
						//좌표가 유효하지 않거나 이동할 수 있는 공간이 아니면 continue
						continue;
					}
					else {
						newMap[nextR][nextC] = 2;
						queue.add(new int[] {nextR, nextC});
					}
				}
			}
//			for(int n = 0; n < N; n++) {
//				for(int m = 0; m < M; m++) {
//					System.out.print(newMap[n][m]+"\t");
//				}
//				System.out.println();
//			}
//			System.out.println("==============================");
			
			//확산 끝나고 안전영역 크기 셈
			int count = 0;
			for(int n = 0; n < N; n++) {
				for(int m = 0; m < M; m++) {
					if(newMap[n][m] == 0) {
						count++;
					}
				}
			}
			//기존 안전영역 크기보다 크다면, 갱신
			if(count > maxCount) {
				maxCount = count;
				//최대일때 맵 상태 확인용
				for(int n = 0; n < N; n++) {
					maxMap[n] = newMap[n].clone();
				}
			}
			//종료
			return;
		}
		//조합 탐색
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(map[n][m] == 0) { //해당 좌표가 빈공간이면
					map[n][m] = 1; //벽 세움(선택)
					createWall(cnt+1, n, m); //다음 벽 찾아 나섬
					map[n][m] = 0; //벽 선택 해제
				}
			}
		}
	}
}
