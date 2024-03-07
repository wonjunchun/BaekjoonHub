import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] multiverse = new int[M][N];
        List<Integer>[] sortedMultiverse = new ArrayList[M];
        for(int m = 0; m < M; m++){
            Set<Integer> set = new HashSet<>(); //일단 집합으로 모아서 정렬된 리스트 만듦
            st = new StringTokenizer(br.readLine());
            for(int n = 0; n < N; n++){
                int num = Integer.parseInt(st.nextToken());
                multiverse[m][n] = num;
                set.add(num);
            }
            sortedMultiverse[m] = new ArrayList<>(set);
            Collections.sort(sortedMultiverse[m]);
        }
        for(int m = 0; m < M; m++){
            for(int n = 0; n < N; n++){
                //좌표압축을 통해 multiverse에 원소 크기 순서(인덱스) 저장
                multiverse[m][n] = Collections.binarySearch(sortedMultiverse[m], multiverse[m][n]);
            }
        }
        int result = 0;
        for(int i = 0; i < M; i++){
            for(int j = i+1; j < M; j++){
                if(Arrays.equals(multiverse[i], multiverse[j])){ //두 멀티버스의 좌표압축값이 같다면
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
