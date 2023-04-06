
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder();
        s.append(br.readLine());
        t.append(br.readLine());
        while(s.length() != t.length()){
            if(t.toString().charAt(t.length()-1) == 'A') t.deleteCharAt(t.length()-1);
            else{

                t.deleteCharAt(t.length()-1).reverse();
            }
        }
        if(s.toString().equals(t.toString())){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}
