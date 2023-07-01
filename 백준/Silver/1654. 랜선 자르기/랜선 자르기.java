import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/**
	 * <문제해석>
	 * 1. k개의 랜선을 잘라 N개의 랜선만들기
	 * 2. 정답이 없는 경우는 없음
	 * 3. N개를 초과하는 것도  N개를 만들수 있는것에 포함
	 * 4. 만들수 있는 최대 랜선의 길이 구하기
	 * => K개의 랜선 잘라서 N개 만드는데, 가능한 최대 길이 구하기
	 * int의 최대 범위가 입력될수 있다 -> long으로 넘어갈 수 있다.
	 * 
	 * <첫번째 고민>
	 * 1. 2^31-1~1까지의 길이를 설정하여 랜선을 만들어보고 성공하는 경우, 해당 랜선 길이 출력
	 * 	K = 1
	 *  랜선의 길이 1,000,000
	 *  N = 1,000,000
	 *  만들수있는 최대 랜선 길이가 1cm인 경우
	 *  약 21억회
	 *  완탐 시간초과 가능성있음
	 * <두번째고민>
	 * 2. 최소길이 1 최대길이 2^31-1 범위로 잡고 이분탐색 수행
	 *  시간복잡도 log2(2^31-1) = 약 31
	 * <프로세스>
	 * 1. 최소길이 1 최대길이 2^31-1을 범위로 잡고 이분탐색 수행
	 *  1) mid값으로 랜선 만드는데 성공하는 경우
	 *   - 현재 mid값을 저장
	 *   - start값을 mid+1로 변경하여 이분탐색
	 *  2) mid값으로 랜선 만드는데 실패하는 경우
	 *   - end값을 mid-1로 변경하여 이분탐색
	 *  3) start > end 면 종료(start <= end동안 반복)
	 * @param args
	 * @throws IOException 
	 */
	static long maxLen;
	static long K, N;
	static long[] cables;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Long.parseLong(st.nextToken());
		N = Long.parseLong(st.nextToken());
		cables = new long[(int) K];
		
		for(int k = 0; k < K; k++) {
			cables[k] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(cables);
		long end = cables[cables.length-1];
		maxLen = 0; //결과로 반환할 변수
		binarySearch(1, end);
		System.out.println(maxLen);
	}
	public static void binarySearch(long start, long end) {
		if(start > end) return;
		long mid = (start+end)/2;
		if(isAvail(mid, cables)) { //현재 선택한 길이(mid)로 만들기 성공한경우
			if(mid > maxLen) maxLen = mid;
			binarySearch(mid+1, end);
		}
		else {
			binarySearch(start, mid-1);
		}
		
	}
	public static boolean isAvail(long mid, long[] cables) { //선택한 길이로 케이블 만들기 성공여부 판별
		long count = 0;
		for(long c: cables) {
			count += c/mid;
		}
		if(count >= N) return true;
		return false;
	}

}