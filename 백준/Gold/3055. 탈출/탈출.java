import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static String[][] map;
	static boolean[][] visited;
	
	static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//맵 입력 받음
		Deque<int[]> spreadQueue = new ArrayDeque<>(); //물 확산 큐
		Deque<Object[]> moveQueue = new ArrayDeque<>(); //고슴도치 이동 큐
		
		map = new String[R][C];
		visited = new boolean[R][C]; //고슴도치 이동 경로
		for(int r = 0; r < R; r++) {
			String[] currentInput = br.readLine().split("");
			for(int c = 0; c < C; c++) {
				map[r][c] = currentInput[c];
				if(map[r][c].equals("*")) {
					//확산 큐 명세 {hour, r, c}
					spreadQueue.add(new int[] {0, r, c});
				}
				else if(map[r][c].equals("S")) {
					//이동 큐 명세 {hour, r, c, visited}
					visited[r][c] = true;
					moveQueue.add(new Object[] {0, r, c, visited});
				}
			}
		}
//		print(map);
		int hour = 0;
		boolean isEscaped = false;
		A: while(!spreadQueue.isEmpty() || !moveQueue.isEmpty()) {
			while(!spreadQueue.isEmpty()) {
				//해당되는 시간이 아니면 지금 확산 돌지 않음
				if(spreadQueue.peekFirst()[0] != hour) break;
				int[] current = spreadQueue.pollFirst();
				int currentHour = current[0];
				int currentR = current[1];
				int currentC = current[2];
				
				for(int[] d: directions) {
					int nextR = currentR+d[0];
					int nextC = currentC+d[1];
					//좌표가 범위를 벗어나거나 빈칸이 아니라면 물 확산 불가
					if(nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || !map[nextR][nextC].equals(".")) continue;
					map[nextR][nextC] = "*"; //물 그림
					spreadQueue.add(new int[] {currentHour+1, nextR, nextC});
				}
			}
			while(!moveQueue.isEmpty()) {
				//해당되는 시간이 아니면 돌지 않음
				if(((int)moveQueue.peekFirst()[0]) != hour) break;
				Object[] current = moveQueue.pollFirst();
				int currentHour = (int)current[0];
				int currentR = (int)current[1];
				int currentC = (int)current[2];
				boolean[][] curVisited = (boolean[][]) current[3];
				
				for(int[] d: directions) {
					int nextR = currentR+d[0];
					int nextC = currentC+d[1];
					//좌표가 범위를 벗어나거나 이미 방문했던 곳이거나 빈칸이 아니라면 이동 불가
					if(nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || !(map[nextR][nextC].equals(".") || map[nextR][nextC].equals("D")) || curVisited[nextR][nextC] || visited[nextR][nextC]) continue;
					if(map[nextR][nextC].equals("D")) { //도착 지점이라면 탐색 종료
						isEscaped = true;
						hour++;
						break A;
					}
					boolean[][] nextVisited = copyVisited(curVisited);
//					nextVisited[nextR][nextC] = true; //방문 체크
					visited[nextR][nextC] = true;
					moveQueue.add(new Object[] {currentHour+1, nextR, nextC, nextVisited});
				}
				
			}
			hour++; //시간 지남
		}
		if(isEscaped) {
			System.out.println(hour);
		}
		else {
			System.out.println("KAKTUS");
		}
	}
	
	private static boolean[][] copyVisited(boolean[][] src) {
		boolean[][] dst = new boolean[R][C];
		for(int r = 0; r < R; r++) {
			dst[r] = src[r].clone();
		}
		return dst;
	}
	
	private static String[][] copyMap(String[][] src) {
		String[][] dst = new String[R][C];
		for(int r = 0; r < R; r++) {
			dst[r] = src[r].clone();
		}
		return dst;
	}
	//출력용 메서드
	private static void print(String[][] currentMap) {
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				System.out.print(currentMap[r][c]);
			}
			System.out.println();
		}
	}
}
