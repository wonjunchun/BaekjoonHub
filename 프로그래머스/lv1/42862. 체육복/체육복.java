import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        int answer = n; //전체 학생 수 대입
        for(int l = 0; l < lost.length; l++){
            for(int r = 0; r < reserve.length; r++){
                if(lost[l] == reserve[r]){ //만약 도난당한 학생이 여벌 가진 학생이면
                    lost[l] = -n;
                    reserve[r] = -n*2;
                    break;
                }
            }
        }
        for(int l = 0; l < lost.length; l++){
            for(int r = 0; r < reserve.length; r++){
                
                if(lost[l]-1 == reserve[r] || lost[l]+1 == reserve[r]){
                    //만약 빌릴 수 있는 체육복이 있으면, 
                    //도난당한 학생과 여벌있는 학생 리스트에서 제거
                    lost[l] = -n; //더이상 탐색에 사용되지 않도록 음수로 변환
                    reserve[r] = -n*2; //이미 음수처리된 lost와 함께 탐색되지 않도록
                    //break;
                }
            }
        }
        for(int l = 0; l < lost.length; l++){
            if(lost[l] > 0) --answer; //아직 lost에 남아있는 학생 수 만큼 -1
        }
        return answer;
    }
}