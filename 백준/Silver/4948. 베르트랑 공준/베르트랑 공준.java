import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean[] notPrimenum = new boolean[246913];
        notPrimenum[0] = true;
        notPrimenum[1] = true;
        for(int i = 2; i < 246913; i++){
            if(!notPrimenum[i]){
                for(int j = i+i; j < 246913; j+= i){
                    notPrimenum[j] = true;
                }
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            int input = Integer.parseInt(br.readLine());
            if(input == 0) break;
            int count = 0;
            for(int i = input+1; i <= input*2; i++){
                if(!notPrimenum[i]) count++;
            }
            sb.append(count+"\n");
        }
        System.out.println(sb.toString());
    }
}