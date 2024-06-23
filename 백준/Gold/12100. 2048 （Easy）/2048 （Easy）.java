import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int maxValue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int n = 0; n < N; n++){
            map[n] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for(int d = 0; d < 4; d++){
            shift(d, 0, map);
        }
        System.out.println(maxValue);
    }
    static void shift(int dir, int depth, int[][] curMap){
        if(depth == 5) return;

        int[][] copiedMap = copyMap(curMap);
        switch(dir){
            case 0: //왼쪽으로 모음
                for(int n = 0; n < N; n++){
                    Deque<Integer> queue = new LinkedList<>(); //맵에 값 갱신하기 위한 큐
                    Deque<Integer> tempQueue = new LinkedList<>(); //값 안합치고 계속 누적하는 큐
                    for(int m = 0; m < N; m++){
                        if(copiedMap[n][m] != 0){ //map의 칸이 0이 아니라면, 큐에 넣는 과정 시작
                            if(queue.isEmpty()) {
                                queue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                                tempQueue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                            }
                            else{
                                if(tempQueue.peekLast() == copiedMap[n][m] && queue.peekLast() == copiedMap[n][m]){ //큐의 마지막 숫자와 현재 숫자 같으면, 둘이 합해서 넣음
                                    queue.pollLast();
                                    queue.add(copiedMap[n][m] * 2);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                                else{ //큐의 마지막숫자와 현재 숫자 다르다면, 그냥 현재 숫자 큐에 넣음
                                    queue.add(copiedMap[n][m]);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                            }
                        }
                    }
                    for(int m = 0; m < N; m++){
                        copiedMap[n][m] = 0;
                    }
                    int m = 0;
                    while(!queue.isEmpty()){
                        int current = queue.pollFirst();
                        copiedMap[n][m] = current;
                        m++;
                    }
                }
                break;
            case 1: //오른쪽으로 모음
                for(int n = 0; n < N; n++){
                    Deque<Integer> queue = new LinkedList<>(); //맵에 값 갱신하기 위한 큐
                    Deque<Integer> tempQueue = new LinkedList<>(); //값 안합치고 계속 누적하는 큐
                    for(int m = N-1; m >= 0; m--){
                        if(copiedMap[n][m] != 0){ //map의 칸이 0이 아니라면, 큐에 넣는 과정 시작
                            if(queue.isEmpty()) {
                                queue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                                tempQueue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                            }
                            else{
                                if(tempQueue.peekLast() == copiedMap[n][m] && queue.peekLast() == copiedMap[n][m]){ //큐의 마지막 숫자와 현재 숫자 같으면, 둘이 합해서 넣음
                                    queue.pollLast();
                                    queue.add(copiedMap[n][m] * 2);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                                else{ //큐의 마지막숫자와 현재 숫자 다르다면, 그냥 현재 숫자 큐에 넣음
                                    queue.add(copiedMap[n][m]);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                            }
                        }
                    }
                    for(int m = 0; m < N; m++){
                        copiedMap[n][m] = 0;
                    }
                    int m = N-1;
                    while(!queue.isEmpty()){
                        int current = queue.pollFirst();
                        copiedMap[n][m] = current;
                        m--;
                    }
                }
                break;
            case 2: //위쪽으로 모음
                for(int m = 0; m < N; m++){
                    Deque<Integer> queue = new LinkedList<>(); //맵에 값 갱신하기 위한 큐
                    Deque<Integer> tempQueue = new LinkedList<>(); //값 안합치고 계속 누적하는 큐
                    for(int n = 0; n < N; n++){
                        if(copiedMap[n][m] != 0){ //map의 칸이 0이 아니라면, 큐에 넣는 과정 시작
                            if(queue.isEmpty()) {
                                queue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                                tempQueue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                            }
                            else{
                                if(tempQueue.peekLast() == copiedMap[n][m] && queue.peekLast() == copiedMap[n][m]){ //큐의 마지막 숫자와 현재 숫자 같으면, 둘이 합해서 넣음
                                    queue.pollLast();
                                    queue.add(copiedMap[n][m] * 2);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                                else{ //큐의 마지막숫자와 현재 숫자 다르다면, 그냥 현재 숫자 큐에 넣음
                                    queue.add(copiedMap[n][m]);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                            }
                        }
                    }
                    for(int n = 0; n < N; n++){
                        copiedMap[n][m] = 0;
                    }
                    int n = 0;
                    while(!queue.isEmpty()){
                        int current = queue.pollFirst();
                        copiedMap[n][m] = current;
                        n++;
                    }
                }
                break;
            case 3: //아래쪽으로 모음
                for(int m = 0; m < N; m++){
                    Deque<Integer> queue = new LinkedList<>(); //맵에 값 갱신하기 위한 큐
                    Deque<Integer> tempQueue = new LinkedList<>(); //값 안합치고 계속 누적하는 큐
                    for(int n = N-1; n >= 0; n--){
                        if(copiedMap[n][m] != 0){ //map의 칸이 0이 아니라면, 큐에 넣는 과정 시작
                            if(queue.isEmpty()) {
                                queue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                                tempQueue.add(copiedMap[n][m]); //큐 비어있을땐 현재 숫자 넣음
                            }
                            else{
                                if(tempQueue.peekLast() == copiedMap[n][m] && queue.peekLast() == copiedMap[n][m]){ //큐의 마지막 숫자와 현재 숫자 같으면, 둘이 합해서 넣음
                                    queue.pollLast();
                                    queue.add(copiedMap[n][m] * 2);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                                else{ //큐의 마지막숫자와 현재 숫자 다르다면, 그냥 현재 숫자 큐에 넣음
                                    queue.add(copiedMap[n][m]);
                                    tempQueue.add(copiedMap[n][m]);
                                }
                            }
                        }
                    }
                    for(int n = 0; n < N; n++){
                        copiedMap[n][m] = 0;
                    }
                    int n = N-1;
                    while(!queue.isEmpty()){
                        int current = queue.pollFirst();
                        copiedMap[n][m] = current;
                        n--;
                    }
                }
                break;
        }
        maxValue = Math.max(maxValue, getMax(copiedMap)); //최대 값 갱신
        for(int d = 0; d < 4; d++){
            shift(d, depth+1, copiedMap);
        }
    }
    static int[][] copyMap(int[][] originMap){
        int[][] result = new int[N][N];
        for(int n = 0; n < N; n++){
            result[n] = originMap[n].clone();
        }
        return result;
    }
    static int getMax(int[][] originMap){
        int result = 0;
        for(int n = 0; n < N; n++){
            result = Math.max(result, Arrays.stream(originMap[n]).max().getAsInt());
        }
        return result;
    }
}