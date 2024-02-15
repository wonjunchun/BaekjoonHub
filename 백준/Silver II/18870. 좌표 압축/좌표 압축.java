import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] originArr, sortedArr;
    static int num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        originArr = new int[N];
        for(int n = 0; n < N; n++){
            int curNum = Integer.parseInt(st.nextToken());
            pq.add(curNum);
            originArr[n] = curNum;
        }
        sortedArr = new int[N];
        sortedArr[0] = pq.poll();
        num = 1;
        while(!pq.isEmpty()){
            int curNum = pq.poll();
            if(curNum != sortedArr[num-1]){ //원소가 중복되지 않게 넣음
                sortedArr[num] = curNum;
                num++;
            }
        }
        StringBuilder sb = new StringBuilder();
        //originArr의 원소가 sortedArr의 어느 인덱스에 있는지 알면, 압축한 좌표값 나옴
        for(int n = 0; n < N; n++){
            int result = binSearch(originArr[n]);
            sb.append(result).append(" ");
        }
        System.out.println(sb.toString());
    }

    //number가 sortedArr의 어느 인덱스에 있는지 반환
    static int binSearch(int number) {
        int start = 0;
        int end = num-1;
        int mid = -1;
        while(start <= end){
            mid = (start + end) / 2;
            if(sortedArr[mid] == number){
                break;
            }
            else if(sortedArr[mid] < number){
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
        return mid;
    }
}
