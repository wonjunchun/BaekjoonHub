import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Time{
        int start, end;
        public Time(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Time[] times = new Time[N];
        Set<Integer> compression = new TreeSet<>(); //좌표압축을 위해 각 시간 오름차순 저장
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times[n] = new Time(start, end); //현재 모기 출몰 시간대 저장
            compression.add(start); //출몰 시작 시각 저장
            compression.add(end); //종료 시각 저장
        }
        //좌표압축을 위해 set을 list로 변환
        List<Integer> compressed = new ArrayList<>(compression);
        int[] mosquitoes = new int[compressed.size()]; //현재 시간대에 존재하는 모기 마리수
        for(int n = 0; n < N; n++){
            int startIdx = Collections.binarySearch(compressed, times[n].start);
            int endIdx = Collections.binarySearch(compressed, times[n].end);
            for(int i = startIdx; i < endIdx; i++){ //해당 구간에 모기 마리수 증가
                mosquitoes[i]++;
            }
        }
        //모기가 최대인 시간대 찾기
        int max = 0;
        int startIdx = 0;
        int endIdx = 0;
        for(int i = 0; i < mosquitoes.length; i++){
            if(max < mosquitoes[i]){
                max = mosquitoes[i]; //최대 마리수
                startIdx = i; //시작 시각 인덱스
                endIdx = i; //종료 시각 인덱스
            }
            if(mosquitoes[i] == max && i-1 == endIdx){ //이전 시각이랑 여전히 모기 최대 상태 이어지면
                endIdx = i; //endIdx 갱신
            }
        }
        System.out.println(max);
        System.out.println(compressed.get(startIdx) + " " + compressed.get(endIdx+1));
    }
}
