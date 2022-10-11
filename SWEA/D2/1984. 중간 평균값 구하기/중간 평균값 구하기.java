import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int[] arr = new int[10];
            int result;
            for(int i = 0; i < 10; i++){
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            //int min = arr[0];
            //int max = arr[9];
            int sum = 0;


            for(int i = 1; i < 9; i++){
                sum += arr[i];
            }

            double avg = sum / 8d;

            result = (int)Math.round(avg);

            System.out.println("#" + test_case + " " + result);

		}
	}
}