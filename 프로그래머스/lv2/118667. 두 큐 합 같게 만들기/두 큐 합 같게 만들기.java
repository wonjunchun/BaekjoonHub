import java.util.*;
class Solution {
    //[3, 2, 7, 2]
    //[4, 6, 5, 1]
    //두 큐간에 insert, pop 반복하여도, 두 큐 원소의 순서는 변하지 않음
    //[3, 2, 7, 2, 4, 6, 5, 1]
    
    public int solution(int[] queue1, int[] queue2) {
        int queueLength = queue1.length;
        
        Deque<Integer> deque1 = new LinkedList<>();
        Deque<Integer> deque2 = new LinkedList<>();
        
        long sum1 = 0; //queue1의 원소 총합
        long sum2 = 0; //queue2의 원소 총합
        long total; //전체 합계
        for(int i = 0; i < queueLength; i++){
            deque1.add(queue1[i]);
            deque2.add(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        } 
        total = sum1 + sum2;
        
        if(total % 2 != 0) return -1; //총합이 홀수이면 두 큐의 합을 같게 할 수 없음
        
        int ptr1 = 0, ptr2 = 0;
        while(true){
            //이미 다른 큐 돌고 제자리로 돌아왔다면 종료
            if(ptr1 > queueLength * 2 || ptr2 > queueLength * 2) break;
            
            //두 큐의 합이 같게 됐다면, 두 큐에서 발생한 pop 횟수의 합 반환
            if(sum1 == total/2) return ptr1 + ptr2;
            
            if(sum1 < total/2){ //큐1의 합이 더 작다면, 큐2에서 pop하여 큐1에 넣음
                sum1 += deque2.peek();
                sum2 -= deque2.peek();
                deque1.add(deque2.pollFirst());
                ptr2++; //큐2의 시작지점 이동
            }
            else{
                sum1 -= deque1.peek();
                sum2 += deque1.peek();
                deque2.add(deque1.pollFirst());
                ptr1++;
            }
        }
        //큐의 원소들이 다른 큐 갔다가 원래대로 돌아왔음에도 두 큐 합이 같은 경우가 없다면
        return -1;
    }
}