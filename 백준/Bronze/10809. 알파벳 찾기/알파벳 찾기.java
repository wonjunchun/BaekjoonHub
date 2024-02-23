import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, -1);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        for(int s = 0; s < str.length(); s++){
            char c = str.charAt(s);
            int index = c - 'a';
            if(alphabet[index] == -1)
                alphabet[index] = s;
        }
        for(int alpha: alphabet)
            System.out.print(alpha + " ");
    }
}
