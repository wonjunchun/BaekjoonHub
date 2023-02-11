import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] directions = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
	public static Queue<int[]> queue = new LinkedList<>();
	public static void solve(int[][][] map, int H, int N, int M) {
		//i) 일단 map 순회하면서 익은 토마토가 있는 부분을 찾음
		
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				for(int m = 0; m < M; m++) {
					if(map[h][n][m] == 1) { //익은 토마토가 있다면 bfs
						queue.add(new int[] {h, n, m});// 현재 좌표 큐에 넣음
					}
				}
			}
		}
		bfs(map, H, N, M);
	}
	public static void bfs(int[][][] map, int H, int N, int M) {
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int curH = current[0];
			int curN = current[1];
			int curM = current[2];
			for(int[] d: directions) {
				int nextH = curH + d[0];
				int nextN = curN + d[1];
				int nextM = curM + d[2];
				if(nextH >= 0 && nextH < H && nextN >= 0 && nextN < N && nextM >= 0 && nextM < M) {
					//올바른 좌표값이면
					if(map[nextH][nextN][nextM] == 0) {
						queue.add(new int[] {nextH, nextN, nextM});
						map[nextH][nextN][nextM] = map[curH][curN][curM] + 1; //방문 표시, 일수 표시도 동시에
					}
				}
			}
		}
	}
	public static int day(int[][][] map, int H, int N, int M) {
		int day = 0;
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				for(int m = 0; m < M; m++) {
					if(map[h][n][m] == 0) return -1; //아직 안익은 토마토가 있다면 -1
					if(map[h][n][m] > day) day = map[h][n][m];
				}
			}
		}
		return day - 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] tomatoes = new int[H][N][M];
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for(int m = 0; m < M; m++) {
					tomatoes[h][n][m] = Integer.parseInt(st.nextToken());
				}
			}
		}
		solve(tomatoes, H, N, M);
		System.out.println(day(tomatoes, H, N, M));
	}
}