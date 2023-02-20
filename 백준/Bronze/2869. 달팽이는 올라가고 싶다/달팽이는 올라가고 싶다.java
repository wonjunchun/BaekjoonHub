import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int V = sc.nextInt();

        double count = (double)(V-B)/(double)(A-B);

        System.out.println((int)Math.ceil(count));
    }
}
