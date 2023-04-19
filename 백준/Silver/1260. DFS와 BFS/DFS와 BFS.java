import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static boolean[] visited;
	static List<LinkedList<Integer>> edges;
	static int[] dfsList, bfsList;
	static int dfsCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //정점의 개수
		M = Integer.parseInt(st.nextToken()); //간선의 개수
		V = Integer.parseInt(st.nextToken()); //시작점
		
		edges = new ArrayList<LinkedList<Integer>>();
		for(int i = 0; i <= N; i++) { //인덱스는 0부터 시작하지만, 실제로 사용할 리스트는 1부터
			edges.add(new LinkedList<Integer>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			edges.get(src).add(dst); //간선 추가
			edges.get(dst).add(src); //간선 추가
		}
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			edges.get(i).sort((o1, o2)->o1-o2); //정렬 오름차순
		}
		//DFS
		dfsList = new int[N];
		dfsCount = 0;
		dfs(V); //dfs 시작
		for(int d: dfsList) {
			if(d != 0) System.out.print(d+" ");
		}
		System.out.println();
		//BFS
		visited = new boolean[N+1]; //visited 초기화
		bfsList = new int[N];
		bfs(V);
		for(int b: bfsList) {
			if(b != 0) System.out.print(b+" ");
		}
		
	}
	public static void dfs(int currentNode) { //V부터 시작
		if(dfsCount == N) {
			//dfsList는 밖에서 보면 됨
			return;
		}
		if(visited[currentNode]) return; //이미 방문했다면, 종료
		
		dfsList[dfsCount] = currentNode;
		visited[currentNode] = true;
		
		List<Integer> nextNodes = edges.get(currentNode);
		
		for(int n: nextNodes) {
			if(!visited[n]) { //아직 방문하지 않았다면, 다음에 방문
				dfsCount++;
				dfs(n); //다음 방문
			}
		}
	}
	public static void bfs(int currentNode) {
		Deque<Integer> queue = new ArrayDeque<>();
		//큐 명세 {현재 노드}
		queue.add(currentNode);
		int count = 0;
		
		while(!queue.isEmpty()) {
			int current = queue.pollFirst();
			if(visited[current]) continue; //방문했다면 skip
			//아직 방문하지 않은 노드라면
			bfsList[count++] = current; //bfs리스트에 넣음
			visited[current] = true; //방문 처리
			for(int e: edges.get(current)) {
				if(!visited[e]) { //다음 노드들 중 아직 방문하지 않았다면, 큐에 넣음
					queue.add(e);
				}
			}
		}
	}
}