import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			numArr[n] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int[] testArr = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M; m++) {
			testArr[m] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numArr);
		for(int t: testArr) {
			System.out.println(search(numArr, 0, numArr.length-1, t));
		}
		
	}
	public static int search(int[] arr, int start, int end, int find) {
		if(start > end) return 0;
		int mid = (start+end)/2;
		if(find<arr[mid]) return search(arr, start, mid-1, find);
		else if(find > arr[mid]) return search(arr, mid+1, end, find);
		else return 1; //찾았으면 1 반환
	}
}