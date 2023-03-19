import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static class MaxHeap{
        static int[] data; //인덱스 1부터 데이터 들어감
        static int size;
        public static void insert(int X){ //상향식으로
            data[++size] = X; //가장 끝 인덱스에 새로운 원소 넣어줌(인덱스 1부터 들어감)
            int current = size; //현재 X가 있는 인덱스
            while((current != 1) && (X > data[current/2])){ //X가 root에 아직 도달하지 않았거나 현재 넣으려는 X보다 부모노드가 작은 동안 반복
                int temp = data[current/2]; //부모노드값 임시 저장(swap용)
                data[current/2] = data[current]; //부모 자리에 X 넣음
                data[current] = temp; //X자리에 부모 넣음
                current /= 2; //X위치 갱신
            }
        }
        public static int delete(){ //하향식으로
            if(size == 0) return 0; //배열이 비어있는데 delete한 경우, 0 반환
            int max = data[1]; //root값(반환용)
            data[1] = data[size--]; //root에 인덱스 제일 끝값 넣음
            data[size+1] = 0; //기존 제일 끝값 초기화
            int current = 1; //현재 root에 임시로 삽입되었던 노드의 위치
            while(current <= size){
                int child; //두 자식 중 더 큰 값을 비교용으로 사용
                int childIdx;
                if(current*2 > size) break; //자식이 사이즈보다 크면 break
                else if(current*2+1 > size){ //왼쪽 자식만 있다면
                    child = data[current*2]; //왼쪽 자식 채택
                    childIdx = current*2;
                }
                else{ //두 자식 다 있다면
                    int lChild = data[current*2]; //왼쪽 자식
                    int rChild = data[current*2+1]; //오른쪽 자식

                    if(lChild > rChild){ //왼쪽 자식이 더 크다면
                        child = lChild;//왼쪽 자식 채택
                        childIdx = current*2;
                    }
                    else{ //오른쪽 자식이 더 크다면
                        child = rChild;//오른쪽 자식 채택
                        childIdx = current*2+1;
                    }
                }
                
                if(data[current] >= child){ //current의 값보다 child가 작거나 같다면 break
                    break;
                }
                //swap
                int temp = data[current];
                data[current] = data[childIdx];
                data[childIdx] = temp;
                current = childIdx;
            }
            return max;
        }
        public MaxHeap(){
            data = new int[100001];
            size = 0;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        MaxHeap maxHeap = new MaxHeap();
        for(int n = 0; n < N; n++){
            int op = Integer.parseInt(br.readLine()); //명령어
            if(op > 0){ //op가 자연수이면
                maxHeap.insert(op);
            }
            else{
                int item = maxHeap.delete();
                sb.append(item).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
