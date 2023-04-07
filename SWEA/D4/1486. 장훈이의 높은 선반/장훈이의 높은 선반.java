import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, B;
	static int[] staffs;
	static boolean[] isSelected;
	static int minimum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			staffs = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n < N; n++) {
				staffs[n] = Integer.parseInt(st.nextToken());
			}
			
			isSelected = new boolean[N];
			minimum = Integer.MAX_VALUE;
			Arrays.sort(staffs);
			subset(0);
//			System.out.println(minimum);
			int result = minimum - B;
			System.out.println("#"+t+" "+result);
		}
	}

	private static void subset(int cnt) {
		if(cnt == N) {
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if(isSelected[i]) { //선택했다면 값 누적
					sum += staffs[i];
				}
			}
			if(sum >= B && sum < minimum) {
				minimum = sum;
			}
			return;
		}
		isSelected[cnt] = true;
		subset(cnt+1);
		isSelected[cnt] = false;
		subset(cnt+1);
	}
}