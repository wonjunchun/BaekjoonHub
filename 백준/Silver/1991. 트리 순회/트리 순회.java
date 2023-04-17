import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Node{
       public String item;
       public Node left;
       public Node right;

       public Node(String item){
           this.item = item;
           this.left = null;
           this.right = null;
       }
    }
    //입력이 A부터 주어짐?

    public static class Tree{
        Node root;
        public Tree(){
            this.root = null;
        }
        public void insert(String data, String leftData, String rightData){
            //root가 null인 경우, 새 트리 생성
            if(root == null){
                if(!data.equals(".")){
                    root = new Node(data);
                }
                if(!leftData.equals(".")){
                    root.left = new Node(leftData);
                }
                if(!rightData.equals(".")){
                    root.right = new Node(rightData);
                }
            }
            else{ //root에 이미 값이 있다면, 다른 넣을곳 탐색
                search(root, data, leftData, rightData);

            }
        }

        private void search(Node root, String data, String leftData, String rightData) {
            if(root==null){ //이동한 후의 root가 비어있다면, 종료
                return;
            }
            else if(root.item.equals(data)){ //이동한 후의 root가 내가 찾던 값이라면
                //왼쪽, 오른쪽에 자식 넣기
                if(!leftData.equals(".")){
                    root.left = new Node(leftData);
                }
                if(!rightData.equals(".")){
                    root.right = new Node(rightData);
                }
            }
            else{
                search(root.left, data, leftData, rightData); //왼쪽자식으로 이동함
                search(root.right, data, leftData, rightData); //오른쪽 자식으로 이동

            }

        }
        public void preorder(Node root){ //전위순회
            System.out.print(root.item);
            if(root.left != null) preorder(root.left);
            if(root.right != null) preorder(root.right);
        }

        public void inorder(Node root){ //중위순회
            if(root.left != null) inorder(root.left);
            System.out.print(root.item);
            if(root.right != null) inorder(root.right);
        }
        public void postorder(Node root){ //후위순회
            if(root.left != null) postorder(root.left);
            if(root.right != null) postorder(root.right);
            System.out.print(root.item);

        }
    }
    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            String data = st.nextToken();
            String leftData = st.nextToken();
            String rightData = st.nextToken();
            tree.insert(data, leftData, rightData);
        }
        tree.preorder(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        System.out.println();
        tree.postorder(tree.root);

    }
}
