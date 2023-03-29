import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	static int N, M;
	static int[] cards;
	static int[] comb;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			cards[n] = Integer.parseInt(st.nextToken());
		}
		result = 0;
		comb = new int[3];
		combination(0, 0);
		System.out.println(result);
	}
	
	public static void combination(int cnt, int start) {
		if(cnt == 3) {
			int sum = comb[0]+comb[1]+comb[2];
			if(sum <= M && sum > result) {
				result = sum;
			}
			return;
		}
		for(int i = start; i < N; i++) {
			comb[cnt] = cards[i];
			combination(cnt+1, i+1);
		}
	}
}