import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Ancestor{
        int person; //사람 인덱스
        int generation; //몇세대인지

        public Ancestor(int person, int generation) {
            this.person = person;
            this.generation = generation;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] people = br.readLine().split(" "); //인덱스로 사람이름 접근
        LinkedList<Integer>[] adjLists = new LinkedList[N];
        Map<String, Integer> nameToIndex = new HashMap<>(); //이름으로 인덱스 가져옴
        int[] degree = new int[N]; //진입차수
        boolean[] visited = new boolean[N];
        Map<String, LinkedList<String>> childs = new HashMap<>(); //직계 자식 저장용
        for(int n = 0; n < N; n++){
            adjLists[n] = new LinkedList<>();
            nameToIndex.put(people[n], n);
            childs.put(people[n], new LinkedList<>());
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            String X = st.nextToken(); //후손
            String Y = st.nextToken(); //조상
            adjLists[nameToIndex.get(Y)].add(nameToIndex.get(X)); //조상의 후손목록에 추가
            degree[nameToIndex.get(X)]++; //후손의 진입차수 1증가
        }
        LinkedList<String> ancestors = new LinkedList<>(); //시조
        Deque<Ancestor> queue = new LinkedList<>();
        for(int n = 0; n < N; n++){
            if(degree[n] == 0){ //진입차수 0인 사람들 큐에 넣음
                queue.add(new Ancestor(n, 0));
                visited[n] = true;
                ancestors.add(people[n]);
            }
        }
        while(!queue.isEmpty()){
            Ancestor current = queue.pollFirst();
            int curPerson = current.person;
            int curGen = current.generation;

            for(int nextPerson: adjLists[curPerson]){
                //i) 현재 세대의 후손들 진입차수 하나씩 줄임
                //이미 방문한(큐에 들어간) 후손이면 continue
                if(visited[nextPerson]) continue;
                degree[nextPerson]--;
                //ii) 진입차수가 0이 된 후손들은 자식이므로 다음세대로 큐에 넣음
                if(degree[nextPerson] == 0){
                    queue.add(new Ancestor(nextPerson, curGen+1));
                    visited[nextPerson] = true;
                    childs.get(people[curPerson]).add(people[nextPerson]); //현재 사람의 직계 자식 저장
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Collections.sort(ancestors); //시조 가나다순 정리
        Arrays.sort(people); //사람들 목록 가나다순 정리
        sb.append(ancestors.size()).append("\n");
        for(String a: ancestors){
            sb.append(a).append(" ");
        }
        sb.append("\n");
        for(int n = 0; n < N; n++){ //사람 가나다 순으로 자식 목록 보여줌
            sb.append(people[n]).append(" ").append(childs.get(people[n]).size()).append(" ");
            Collections.sort(childs.get(people[n])); //자식들 가나다순 정리
            for(String c: childs.get(people[n])){ //현재 사람의 자식
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
