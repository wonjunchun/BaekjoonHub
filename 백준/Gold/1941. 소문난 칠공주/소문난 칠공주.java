import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static String[][] map = new String[5][5];
	static String[] comb;
	static int[][] combCoordinate;
	static int[][] tempMap; //인접한 애들인지 체크하기 위해(bfs용)
	static int resultCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int n = 0; n < 5; n++) {
			String[] inputStr = br.readLine().split("");
			for(int m = 0; m < 5; m++) {
				map[n][m] = inputStr[m];
			}
		}
		resultCnt = 0;
		comb = new String[7];
		combCoordinate = new int[7][2];
		combination(0, 0);
		
		System.out.println(resultCnt);
	}
	private static void combination(int cnt, int start) {
		if(cnt == 7) {
			//i) 7개 좌표 인접여부 체크하기
			tempMap = new int[5][5];
			for(int[] c: combCoordinate) {
				tempMap[c[0]][c[1]] = -1; //선택된 애들 -1 마킹
			}
			
			int result = adjacentCnt();
			if(result > 1) return; //7명이 인접하지 않다면 종료
			
			//ii) 뽑은 7명중 4명 이상이 S인지 확인
			int sCnt = 0;
			for(String c: comb) {
				if(c.equals("S")) sCnt++;
			}
			//만약 "S"파가 4명 미만이면 그냥 종료
			if(sCnt < 4) return;
			
			//4명 이상이면 resultCnt++ 후 종료
			else resultCnt++;
			return;
		}
		for(int i = start; i < 25; i++) {
			comb[cnt] = map[i/5][i%5];
			combCoordinate[cnt][0] = i/5;
			combCoordinate[cnt][1] = i%5;
			combination(cnt+1, i+1);
		}
	}
	private static int adjacentCnt() {
		int count = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(tempMap[i][j] == -1) {
					count++;
					bfs(i, j, count); //맵에 기록
				}
			}
		}
		return count;
	}
	private static void bfs(int i, int j, int count) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {i, j});
		tempMap[i][j] = count;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int curR = current[0];
			int curC = current[1];
			
			for(int[] d: directions) {
				int nextR = curR+d[0];
				int nextC = curC+d[1];
				//좌표 범위 벗어났다면 continue
				if(nextR < 0 || nextR >= 5 || nextC < 0 || nextC >= 5) continue;
				if(tempMap[nextR][nextC] == -1) { //아직 마킹 안됐다면
					tempMap[nextR][nextC] = count;
					queue.add(new int[] {nextR, nextC});
				}
			}
		}
	}
}
