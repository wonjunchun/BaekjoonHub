import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /**
     * 세그먼트 트리 : 연속된 구간의 데이터 합을 가장 빠르고 간단하게 구하는 자료구조
     * O(logN)의 시간복잡도 가짐
     * 이 문제에서는 세그먼트 트리에 더 작은 값의 인덱스 저장
     */
    static int[] arr, tree;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tree = new int[4 * N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr[0] = Integer.MAX_VALUE;
        for(int n = 1; n <= N; n++){
            arr[n] = Integer.parseInt(st.nextToken());
        }
        init(1, N, 1);
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(op == 1){
                arr[i] = v; //i 위치 값을 v로 갱신
                update(1, N, 1, i); //i위치 값이 변경됐으니, 최소 값을 갖는 인덱스 정보 갱신해줘야 함
            }
            else{
                sb.append(getMin(1, N, 1, i, v)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    //세그먼트 트리 초기값 설정. node 위치에 제일 작은 값의 인덱스 저장
    static int init(int start, int end, int node){
        if(start == end) return tree[node] = start;
        int mid = (start + end) / 2;
        int left = init(start, mid, node * 2);
        int right = init(mid + 1, end, node * 2 + 1);
        return tree[node] = getIndex(left, right); //해당 위치의 두 수 중 더 작은 값의 인덱스 반환
    }
    //세그먼트 트리 값 수정
    static int update(int start, int end, int node, int idx){
        if(start > idx || end < idx) return tree[node]; //idx를 못찾고 범위 벗어났다면 종료
        if(start == end) return tree[node] = idx; //idx 위치 발견 시 트리 값 갱신 후 반환
        int mid = (start + end) / 2;
        int left = update(start, mid, node * 2, idx);
        int right = update(mid + 1, end, node * 2 + 1, idx);
        return tree[node] = getIndex(left, right); //둘중 더 작은 인덱스로 트리 값 갱신
    }
    //수열에서 크기가 가장 작은 값의 인덱스 반환(이 부분 다시 공부하기..)
    static int getMin(int start, int end, int node, int l, int r){
        if(r < start || l > end) return 0;
        if(l <= start && end <= r){
            return tree[node];
        }
        int mid = (start + end) / 2;
        int left = getMin(start, mid, node * 2, l, r);
        int right = getMin(mid + 1, end, node * 2 + 1, l, r);
        return getIndex(left, right); //둘중 더 작은 인덱스값 반환
    }
    //해당 위치의 두 수 중 더 작은 값의 인덱스 반환
    static int getIndex(int left, int right){
        if(arr[left] == arr[right]) return Math.min(left, right);
        else if(arr[left] < arr[right]) return left;
        else return right;
    }
}
