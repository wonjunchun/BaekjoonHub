import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int channel = 100;
        int min = Math.abs(100 - N); //채널 N까지 최소 버튼 횟수
        boolean[] broken = new boolean[10];
        if(M != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int m = 0; m < M; m++){
                int brokenButton = Integer.parseInt(st.nextToken());
                broken[brokenButton] = true;
            }
        }

        CHANNEL: for(int n = 0; n < 1000000; n++){
            String[] currentChannel = Integer.toString(n).split("");
            //부서진 버튼을 포함하는 숫자인지 체크
            for(String c: currentChannel){
                if(broken[Integer.parseInt(c)]) continue CHANNEL; //부서진 버튼이 포함된 숫자라면 다음숫자 탐색;
            }
//            min = Math.min(min, Math.abs(n - N)); //기존 min값과 현재 숫자의 버튼누르는 횟수 비교하여 최소값 갱신
            if ((Math.abs(n - N) + currentChannel.length) < min) {
                channel = n;
                min = Math.abs(n - N) + currentChannel.length;
            }
        }
//        System.out.println(channel);
        System.out.println(min);
    }
}
