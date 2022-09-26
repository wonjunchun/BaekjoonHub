import java.util.ArrayList;
class Solution {
    public int solution(int n, int[][] computers) {
        //컴퓨터 개수만큼의 길이를 갖는 visited 배열 생성
        //ArrayList<Integer> visited = new ArrayList<Integer>(n); 
        int[] visited = new int[n];
        int network = 0; //네트워크의 개수
        
        for(int i=0; i<n; i++){
            if(visited[i] == 0){ //아직 방문하지 않았으면
                DFS(visited, computers, i); //방문되지 않는 노드만 DFS로 탐색
                network++;
            }
        }
        
        return network;
    }
    public void DFS(int[] visited, int[][] computers, int currentCom){
        if(visited[currentCom] == 1) return; //이미 방문한 노드라면, 종료
        
        visited[currentCom] = 1;
        for(int i = 0; i < computers[currentCom].length; i++){ 
            //현재 방문한 컴퓨터에 연결된 컴퓨터들 순회함
            
            if(computers[currentCom][i] == 1 && i != currentCom){
                DFS(visited, computers, i);
            }
        }
    }
}