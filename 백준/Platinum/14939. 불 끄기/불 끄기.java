import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1) 모든 스위치는 최대 1번만 상태 바뀌어야함(한번 누른 스위치 다시 누를 일 없음)
 * 2) 윗줄부터 순차적으로 탐색하며 내려옴 -> 현재 좌표보다 한 행 위의 전구 확인하여 켜고 꺼야 함
 * 3) 마지막 줄까지 스위치 on/off 마치고, 마지막줄에 아직 안 꺼진 전구 없는지 체크
 */
public class Main {
    static int[][] directions = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map, tempMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[10][10];
        tempMap = new int[10][10];
        for(int i = 0; i < 10; i++){
            String[] line = br.readLine().split("");
            for(int j = 0; j < 10; j++){
                map[i][j] = line[j].equals("#") ? 0 : 1; //#이면 0, O이면 1
            }
        }
        int result = Integer.MAX_VALUE;
        //2^10(10개의 전구스위치 켜고 끄는 경우의 수)
        for(int s = 0; s < 1024; s++){ //맨 첫째줄 켜고 끄는 모든 경우의 수 탐색
            int switchCount = 0; //switchCount 초기화
            mapSetting();
            for(int i = 9; i >= 0; i--){
                int currentBit = (s >> i) & 1;
                if(currentBit == 1){
                    switchCount++;
                    onoff(0, 9-i);
                }
            }
            for(int i = 1; i < 10; i++){ //둘째줄부터 탐색 시작
                for(int j = 0; j < 10; j++){
                    if(tempMap[i-1][j] == 1){ //만약 현재 좌표보다 한 행 위의 전구 켜져있는 상태라면
                        onoff(i, j); //전구 켜고 끔
                        switchCount++;
                    }
                }
            }
            boolean isPossible = true; //전구를 다 끌수 있는 경우인지
            for(int i = 0; i < 10; i++){
                if(tempMap[9][i] == 1){ //전구가 하나라도 켜있다면
                    isPossible = false;
                    break;
                }
            }
            if(isPossible){
                result = Math.min(result, switchCount);
            }
        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
    public static void onoff(int row, int col){ //현재 좌표와 주변 4방향 전구 상태 변화
        for(int[] d: directions){
            int nextRow = row+d[0];
            int nextCol = col+d[1];

            if(nextRow < 0 || nextRow >= 10 || nextCol < 0 || nextCol >= 10) continue;
            tempMap[nextRow][nextCol] ^= 1; //상태 바꿔줌(XOR)
        }
    }
    public static void mapSetting(){ //맵 초기화
        for(int i = 0; i < 10; i++){
            tempMap[i] = map[i].clone();
        }
    }
}
