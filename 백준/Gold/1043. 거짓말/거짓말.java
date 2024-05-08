import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer> knowTruthPeople;
    static List<Integer>[] parties;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int knowTruth = Integer.parseInt(st.nextToken());
        if(knowTruth == 0){
            System.out.println(M);
            return;
        }
        knowTruthPeople = new LinkedList<>(); //진실을 아는 사람들 목록
        for(int k = 0; k < knowTruth; k++){
            knowTruthPeople.add(Integer.parseInt(st.nextToken()));
        }
        parties = new List[M];
        //union-find를 위한 parent 배열 초기화
        parent = new int[N + 1];
        for(int n = 1; n <= N; n++){
            parent[n] = n;
        }
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            parties[m] = new LinkedList();
            int partyMemNum = Integer.parseInt(st.nextToken());
            int person = Integer.parseInt(st.nextToken());
            parties[m].add(person);
            for(int p = 1; p < partyMemNum; p++){
                int anotherPerson = Integer.parseInt(st.nextToken());
                parties[m].add(anotherPerson);
                union(person, anotherPerson);
            }
        }
        int availPartyCnt = 0;
        for(int m = 0; m < M; m++){
            boolean isAvailParty = true;
            for(int person: parties[m]){
                if(knowTruthPeople.contains(find(person))){
                    //해당 파티에 한명이라도 진실 아는 사람 있다면, 과장된 이야기 불가
                    isAvailParty = false;
                    break;
                }
            }
            if(isAvailParty){
                availPartyCnt++;
            }
        }
        System.out.println(availPartyCnt);
    }
    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        else{
            //find 하면서 만난 모든 값의 부모노드를 root로 만들음(경로압축)
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int x, int y){
        //각 원소가 속한 트리의 루트 노드 탐색
        x = find(x);
        y = find(y);
        //진실 아는 사람의 그룹으로 속하도록 만듦
        if(knowTruthPeople.contains(y)){
            int temp = y;
            y = x;
            x = temp;
        }
        parent[y] = x;
    }
}