import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Queue queue = new Queue();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            switch (cmd){
                case "push":
                    int param = Integer.parseInt(st.nextToken());
                    queue.push(param);
                    break;
                case "pop":
                    sb.append(queue.pop()+"\n");
                    break;
                case "size":
                    sb.append(queue.size()+"\n");
                    break;
                case "empty":
                    sb.append(queue.empty()+"\n");
                    break;
                case "front":
                    sb.append(queue.front()+"\n");
                    break;
                case "back":
                    sb.append(queue.back()+"\n");
                    break;
                default:
                    break;
            }
        }
        System.out.printf(sb.toString());
    }
}

class Queue{
    private int[] queue = new int[10000];
    private int size = 0;
    private int front = 0;
    private int tail = 0;

    public void push(int X){
        this.queue[this.tail] = X;
        this.tail++;
        this.size++;
    }
    public int pop(){
        if(this.size == 0){
            return -1;
        }
        int result = this.queue[this.front];
        this.front++;
        this.size--;
        return result;
    }
    public int size(){
        return this.size;
    }
    public int empty(){
        if(this.size == 0) return 1;
        else return 0;
    }
    public int front(){
        if(this.size == 0) return -1;
        return this.queue[this.front];
    }
    public int back(){
        if(this.size == 0) return -1;
        return this.queue[this.tail-1];
    }
}