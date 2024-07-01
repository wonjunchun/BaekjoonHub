import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[] inorder, postorder, index;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inorder = new int[N];
        index = new int[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            inorder[n] = Integer.parseInt(st.nextToken());
            index[inorder[n]] = n; //inorder 원소들이 위치한 index 저장
        }
        postorder = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sb = new StringBuilder();

        findPreorder(0, N-1, 0, N-1);
        System.out.println(sb.toString());
    }
    static void findPreorder(int instart, int inend, int poststart, int postend){
        //더 이상 트리 못쪼개는 경우 종료
        if(instart > inend || poststart > postend) return;
        int rootIndex = index[postorder[postend]]; //postorder의 제일 끝 값이 root
        //inorder에서 rootIndex 기준으로 양옆 서브트리 크기 구함
        int leftSize = rootIndex - instart;

        sb.append(inorder[rootIndex]).append(" "); //현재의 root값 출력

        //분할한 왼쪽 서브트리
        findPreorder(instart, rootIndex - 1, poststart, poststart + leftSize - 1);
        //분할한 오른쪽 서브트리
        findPreorder(rootIndex + 1, inend, poststart + leftSize, postend - 1);
    }
}
