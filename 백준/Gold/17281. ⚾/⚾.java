import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, results[][], orders[];
    static boolean[] isSelected;
    static int max = 0;
    static int recurCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        results = new int[N][9]; //각 타자가 각 이닝에서 얻을 결과
        orders = new int[9]; //타순 저장하는 배열
        StringTokenizer st;
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < 9; m++) {
                results[n][m] = Integer.parseInt(st.nextToken());
            }
        }
        //i) 순열을 통해 타순 뽑음
        isSelected = new boolean[9];
        permutation(0);
        //ii) 나온 타순대로 게임 진행 -> 총점 계산
        //iii) 총점의 최대값 출력
        System.out.println(max);
    }
    // 순열을 통해 타순 뽑는 메서드
    public static void permutation(int cnt){
        if(cnt >= 9){
            int result = playBaseball();
            max = Math.max(max, result);
//            System.out.println(++recurCnt + " : " + max);
            return;
        }
        else if(cnt == 3){ //4번타자는 이미 1번 선수로 고정되어있으므로
            orders[3] = 0;
            permutation(cnt+1);
        }
        for(int i = 1; i < 9; i++){
            if(!isSelected[i]){
                isSelected[i] = true; //선택
                orders[cnt] = i; //cnt번째 타자는 i번 선수로
                permutation(cnt+1); //다음 타자 선택하러 감
                isSelected[i] = false; //선택해제
            }
        }
    }

    //나온 타순대로 게임 진행해서 총점 얻는 메서드
    public static int play(){
        int score = 0;
        int current = 0; //몇번째 타자인지
        for(int n = 0; n < N; n++){ //n이닝 동안 반복
            int out = 0;
            Deque<Integer> ground = new LinkedList<>(); //출루 상황 나타내는 큐(0:없음, 1: 있음)
            for(int i = 0; i < 3; i++)
                ground.add(0);
            while(out < 3){ //아웃카운트가 3 미만인 동안 이닝 반복
                int curScore = results[n][orders[current]]; //n번째 이닝의 해당 타자 결과
                if(curScore == 0){ //아웃이면
                    out++;
                    current = (current + 1) % 9;
                    continue;
                }
                else{ //아웃이 아니면
                    ground.add(1); //진루
                    for(int i = 0; i < curScore; i++){ //n루타 친 만큼 출루한 주자들 홈으로 돌아옴
                        score += ground.pollFirst();
                    }
                    for(int i = 0; i < curScore-1; i++){ //진루 후 빈 공간 채움
                        ground.add(0);
                    }
                    current = (current + 1) % 9;
                }
            }
        }
        return score;
    }
    public static int playBaseball(){
        int score = 0;
        int current = 0; //몇번째 타자인지
        for(int n = 0; n < N; n++){
            int out = 0;
            int base1 = 0;
            int base2 = 0;
            int base3 = 0;
            while(out < 3){
                switch(results[n][orders[current]]){
                    case 0:
                        out++;
                        break;
                    case 1:
                        score += base3;
                        base3 = base2;
                        base2 = base1;
                        base1 = 1;
                        break;
                    case 2:
                        score += base3 + base2;
                        base3 = base1;
                        base2 = 1;
                        base1 = 0;
                        break;
                    case 3:
                        score += base3 + base2 + base1;
                        base1 = base2 = 0;
                        base3 = 1;
                        break;
                    default:
                        score += base3 + base2 + base1 + 1;
                        base1 = base2 = base3 = 0;
                }
                current = (current + 1) % 9; //다음 타자
            }
        }
        return score;
    }
}