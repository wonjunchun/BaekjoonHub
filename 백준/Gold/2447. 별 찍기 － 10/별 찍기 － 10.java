import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static List<String> star(int num){
        if(num == 1){
            List<String> stars = new ArrayList<>();
            stars.add("*");
            return stars;
        }

        List<String> stars = star(num/3);
        List<String> result = new ArrayList<>();

        for(String s: stars){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < 3; i++){
                sb.append(s);
            }
            result.add(sb.toString());
        }
        for(String s: stars){
            StringBuffer sb = new StringBuffer();
            sb.append(s);
            for(int i = 0; i < num/3; i++){
                sb.append(" ");
            }
            sb.append(s);
            result.add(sb.toString());
        }
        for(String s: stars){
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < 3; i++){
                sb.append(s);
            }
            result.add(sb.toString());
        }
        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<String> result = star(N);
        for(String r: result){
            System.out.println(r);
        }
    }
}