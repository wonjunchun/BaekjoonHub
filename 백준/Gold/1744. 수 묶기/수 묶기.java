import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> positive = new PriorityQueue<>(Comparator.reverseOrder()); //양수는 내림차순
        PriorityQueue<Integer> negative = new PriorityQueue<>();
        for(int n = 0; n < N; n++){
            int input = Integer.parseInt(br.readLine());
            if(input > 0){
                positive.add(input);
            }
            else{
                negative.add(input);
            }
        }
        int sum = 0;
        while(positive.size() >= 2){
            int num1 = positive.poll();
            int num2 = positive.poll();
            if(num1 != 1 && num2 != 1){
                sum += num1 * num2;
            }
            else{ //둘중 하나가 1이라면, 곱하지 말고 그냥 더하는 것이 더 결과값이 큼
                sum += num1 + num2;
            }
        }
        if(!positive.isEmpty()){
            sum += positive.poll();
        }
        while(negative.size() >= 2){
            int num1 = negative.poll();
            int num2 = negative.poll();
            sum += num1 * num2;
        }
        if(!negative.isEmpty()){
            sum += negative.poll();
        }
        System.out.println(sum);
    }
}
