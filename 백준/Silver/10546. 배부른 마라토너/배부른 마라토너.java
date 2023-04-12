import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<String> set = new HashSet<>();
		int N = Integer.parseInt(br.readLine());
		for(int n = 0; n < 2*N-1; n++) {
			String input = br.readLine();
			if(set.contains(input)) {
				set.remove(input);
			}
			else {
				set.add(input);
			}
		}
		Iterator<String> iter = set.iterator();
		System.out.println(iter.next());

	}
}
