import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	/**
	 * 탈주범이 있을 수 있는 위치 개수 계산
	 * 
	 * 
	 * @param args
	 */
	static int[][] map;
	static boolean[][] visited;
	
	static int count;
	static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
	private static List<LinkedList<int[]>> structure;
	public static void main(String[] args) throws NumberFormatException, IOException {
		structure = new LinkedList<>();
		for(int i = 0; i <= 7; i++) {
			structure.add(new LinkedList<int[]>());
		}
		for(int d = 0; d < 4; d++) {
			structure.get(1).add(directions[d]);
		}
		for(int d = 0; d < 2; d++) {
			structure.get(2).add(directions[d]);
		}
		for(int d = 2; d < 4; d++) {
			structure.get(3).add(directions[d]);
		}
		structure.get(4).add(directions[0]);
		structure.get(4).add(directions[3]);
		structure.get(5).add(directions[1]);
		structure.get(5).add(directions[3]);
		structure.get(6).add(directions[1]);
		structure.get(6).add(directions[2]);
		structure.get(7).add(directions[0]);
		structure.get(7).add(directions[2]);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //세로
			int M = Integer.parseInt(st.nextToken()); //가로
			int R = Integer.parseInt(st.nextToken()); //맨홀의 세로좌표
			int C = Integer.parseInt(st.nextToken()); //맨홀의 가로좌표
			int L = Integer.parseInt(st.nextToken()); //탈출후 소요된시간
			
			count = 0;
			
			map = new int[N][M];
			visited = new boolean[N][M];
			count++;
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for(int m = 0; m < M; m++) {
					map[n][m] = Integer.parseInt(st.nextToken());
				}
			}
			
			Deque<int[]> queue = new ArrayDeque<>();
			
			//큐 명세 {현재R, 현재C, 현재 L}
			queue.add(new int[] {R, C, L});
			visited[R][C] = true;
			
			while(!queue.isEmpty()) {
				int[] current = queue.pollFirst();
				int curR = current[0];
				int curC = current[1];
				int curL = current[2];
				int curStr = map[curR][curC];
				//이미 탈주범이 맨홀 들어간 시점부터가 1시간 경과된 시점이므로
				if(curL-1 == 0) continue;
				
				for(int[] dir: structure.get(curStr)) {
					int nextR = curR + dir[0];
					int nextC = curC + dir[1];
					//유효한 좌표이고 아직 방문하지 않았다면
					if(nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && !visited[nextR][nextC]) {
						int nextStr = map[nextR][nextC];
						boolean isAvail = false;
						for(int[] dir2: structure.get(nextStr)) {
//							if(nextR-curR == dir2[0] && nextC-curC == dir2[1]) {
							if(curR-nextR == dir2[0] && curC-nextC == dir2[1]) {
								isAvail = true;
								break;
							}
						}
						//갈 수 있는 방향이면
						if(isAvail) {
							queue.add(new int[] {nextR, nextC, curL-1});
							visited[nextR][nextC] = true;
							count++;
						}
					}
				}
			}
			System.out.println("#"+t+" "+count);
//			for(int n = 0; n < N; n++) {
//				for(int m = 0; m < M; m++) {
//					System.out.print(visited[n][m]+"\t");
//				}
//				System.out.println();
//			}
		}
	}
}