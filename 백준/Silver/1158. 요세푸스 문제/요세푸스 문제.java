import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		Queue<Integer> list = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			list.add(i);
		}

		LinkedList<Integer> josephus = new LinkedList<>();
		int count = 1;
		int idx = 0;
		while(!list.isEmpty()) {
			if(count == K) {
				josephus.add(list.poll());
				count = 0;
			}
			else {
				list.add(list.poll());
			}
			count++;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int j = 0; j < josephus.size(); j++) {
			if(j != josephus.size()-1) {
				sb.append(josephus.get(j)+", ");
			}
			else {
				sb.append(josephus.get(j)+">");
			}
		}
		System.out.println(sb.toString());
		sc.close();
	}
}