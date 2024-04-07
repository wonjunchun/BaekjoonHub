import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int NA = Integer.parseInt(st.nextToken());
        int NB = Integer.parseInt(st.nextToken());
        Set<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < NA; n++){
            A.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < NB; n++){
            B.add(Integer.parseInt(st.nextToken()));
        }
        //대칭차집합 = 합집합 - 교집합
        Set<Integer> union = new HashSet<>(A);
        union.addAll(B);
        Set<Integer> intersection = new HashSet<>(A);
        intersection.retainAll(B);
        Set<Integer> result = new HashSet<>(union);
        result.removeAll(intersection);
        System.out.println(result.size());
    }
}
