import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> map = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			int key = Integer.parseInt(st.nextToken());
			if(!map.containsKey(key)) { //맵에 해당 키가 없다면
				map.put(key, 1); //새로 한장 추가해줬으니 value를 1로
			}
			else { //이미 키가 있다면, 기존 value+1 해주기
				map.replace(key, map.get(key)+1);
			}
		}
		
		int M = Integer.parseInt(br.readLine()); //몇개 가지고 있는 숫자카드인지 구해야 할 M개
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int m = 0; m < M; m++) {
			int key = Integer.parseInt(st.nextToken());
			if(!map.containsKey(key)) { //맵에 해당 키가 없다면 0 출력
				sb.append(0).append(" ");
			}
			else { //맵에 해당 키 있다면, value(해당 카드의 개수) 출력
				sb.append(map.get(key)).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
}