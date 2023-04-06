import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static int[] parents;
	
	static PriorityQueue<Edge> edgeList = new PriorityQueue<>();
	
	static class Edge implements Comparable<Edge>{
		int from, to;
		int weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public Edge() {
			
		}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		//map입력
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if(map[n][m] == 1) map[n][m] = -1; //섬 라벨링하기 위해
			}
		}
		
		//1. 섬 라벨링
		int labelCnt = 0;
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(map[n][m] == -1) {
					labelCnt++;
					labeling(n, m, labelCnt);
				}
			}
		}
		
//		print();
		//맵 출력
		
		
		//2. 다리 만들기
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				//섬이면, 다리 만들기 시작
				if(map[n][m] != 0) makeBridge(n, m);				
			}
		}
		
//		printEdgeList();
		
		//3. kruskal을 통해 MST 만들기
		parents = new int[labelCnt+1];
		int cost = getMSTCost(labelCnt);
		System.out.println(cost);
	}
	
	private static void printEdgeList() {
		//edgeList 출력해보기
		while(!edgeList.isEmpty()) {
			System.out.println(edgeList.poll());
		}
		
	}

	//지도 출력용 메서드
	private static void print() {
		for(int n = 0; n < N; n++) {
			for(int m = 0; m <M; m++) {
				System.out.print(map[n][m]+"\t");
			}
			System.out.println();
		}
	}

	//kruskal을 통해 최저 비용 찾기
	private static int getMSTCost(int islandCnt) {
		//일단 최초의 parents 만들기
		makeSet();
		int count = 0; //섬을 연결한 다리의 개수 세기
		int weightSum = 0; //가중치 누적
		while(!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if(union(edge.from, edge.to)) { //합집합 연산 성공 시
				count++;
				weightSum += edge.weight;
			}
			//모든 섬 다 연결했다면 종료
			if(count == islandCnt-1) return weightSum;
		}
		return -1;
	}
	
	
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		
		parents[rootB] = rootA;
		return true;
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}

	private static void makeSet() {
		//최초의 parents 배열 만드는 메서드
		for(int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}

	private static void makeBridge(int r, int c) {
		for(int[] d: directions) { //4방향 탐색
			int nextR = r+d[0];
			int nextC = c+d[1];
			
			//좌표 범위를 벗어났거나 바다 칸이 아니라면 skip 하고 다음방향 찾아봄
			if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || map[nextR][nextC] != 0) continue;
			//해당 방향으로 직진하면서 다리 만들어봄
			int bridgeLength = 1;
			
			while(true) {
				nextR += d[0];
				nextC += d[1];
				
				//좌표 범위를 벗어났다면 종료
				if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) break;
				
				//다음 좌표가 섬이라면, 출발점과 같은 섬인지 체크, 길이 2이상인지 체크 후 종료
				if(map[nextR][nextC] != 0) {
					if(map[nextR][nextC] != map[r][c] && bridgeLength >= 2) {
						edgeList.add(new Edge(map[r][c], map[nextR][nextC], bridgeLength));
					}
					break; //출발점과 같은 섬이어도 종료(edgeList에는 추가 안함)
				}
				
				//다음 좌표가 바다라면 전진
				else {
					bridgeLength++;
				}				
			}
		}
	}
	private static void labeling(int r, int c, int labelCnt) {
		map[r][c] = labelCnt;
		
		for(int[] d: directions) {
			int nextR = r+d[0];
			int nextC = c+d[1];
			
			//좌표 범위를 벗어났거나 아직 라벨링되지 않은 섬이 아닌 경우
			if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || map[nextR][nextC] != -1) continue;
			
			labeling(nextR, nextC, labelCnt);
		}
	}
}