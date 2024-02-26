import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다각형의 면적 구하기?
 * 다각형에서 3개 점씩 선택해서 삼각형 넓이 누적한 값
 * 삼각형의 외적 구하는법 -> CCW 알고리즘(신발끈 공식)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] polygon = new long[N][2];
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            polygon[n][0] = Integer.parseInt(st.nextToken());
            polygon[n][1] = Integer.parseInt(st.nextToken());
        }
        double width = 0;
        for (int n = 1; n < N - 1; n++) {
            long x1 = polygon[n][0];
            long y1 = polygon[n][1];
            long x2 = polygon[n+1][0];
            long y2 = polygon[n+1][1];
            width += ccw(polygon[0][0], x1, x2, polygon[0][1], y1, y2);
        }
        System.out.printf("%.1f", Math.abs(width));
    }

    //신발끈 공식 이용
    static double ccw(long x0, long x1, long x2, long y0, long y1, long y2) {
        double width = x0 * y1 + x1 * y2 + x2 * y0;
        width -= x0 * y2 + x1 * y0 + x2 * y1;
        return width / 2.0;
    }
}
