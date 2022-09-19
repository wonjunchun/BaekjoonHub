import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
class Connection{
    //BFS
    public int connectionCount(int[] pair, int[][] connectionMatrix, int N){
        int src = pair[0];
        int dst = pair[1];

        Deque<int[]> queue = new ArrayDeque<int[]>();
        //queue 명세 {사람번호, 촌수}


        int result = -1;
        queue.add(new int[]{src, 0}); //자기자신의 촌수는 0
        while(!queue.isEmpty()){
            int[] currentPerson = queue.pollFirst();
            int count = currentPerson[1]+1;//count++;
            if(currentPerson[0] == dst){ //촌수를 구하고자 하는 대상이면
                result = count-1;
                break;
            }
            else{
                for(int n = 0; n < N; n++){
                    if(connectionMatrix[currentPerson[0]][n] == 1){
                        queue.add(new int[]{n, count});
                        connectionMatrix[currentPerson[0]][n] = -1; //재방문하지 않게
                        connectionMatrix[n][currentPerson[0]] = -1;
                    }
                }
            }

        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); //사람수
        StringTokenizer st = new StringTokenizer(br.readLine());

        //촌수 계산할 서로 다른 두 사람 쌍
        int[] pair = {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
        int M = Integer.parseInt(br.readLine());
        int[][] connections = new int[M][2];
        int[][] connectionMatrix = new int[N][N];
        for(int m = 0; m < M; m++){
            StringTokenizer conn = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(conn.nextToken())-1;
            int person2 = Integer.parseInt(conn.nextToken())-1;
            connections[m] = new int[]{person1, person2};
            connectionMatrix[person1][person2] = 1;
            connectionMatrix[person2][person1] = 1;
        }
        Connection connection = new Connection();
        System.out.println(connection.connectionCount(pair, connectionMatrix, N));


    }
}