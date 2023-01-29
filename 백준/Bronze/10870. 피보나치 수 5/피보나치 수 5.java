import java.io.*;

public class Main {
    public static int fibonacci(int num){
        if(num < 2){
            return num;
        }
        else{
            return fibonacci(num-1) + fibonacci(num-2);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(fibonacci(n)));
        bw.close();
    }
}
