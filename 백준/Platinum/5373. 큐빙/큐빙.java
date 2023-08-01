import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * 윗면 : (3, 3) ~ (5, 5) - w
     * 아랫면 : (9, 3) ~ (11, 5) - y
     * 앞면 : (6, 3) ~ (8, 5) - r
     * 뒷면 : (0, 3) ~ (2, 5) - o
     * 왼면 : (3, 0) ~ (5, 2) - g
     * 오른면 : (3, 6) ~ (5, 8) - b
     */
    public static String[][] cube;
    public static void main(String[] args) throws IOException {
        cube = new String[12][9];
//        for(int i = 0; i < 3; i++){
//            for(int j = 0; j < 3; j++){
//                cube[i+3][j+3] = "w"; //윗면 초기화(흰색)
//                cube[i+9][j+3] = "y"; //아랫면 초기화(노란색)
//                cube[i+6][j+3] = "r"; //앞면 초기화(빨강)
//                cube[i][j+3] = "o"; //뒷면 초기화(오렌지)
//                cube[i+3][j] = "g"; //왼면 초기화(초록)
//                cube[i+3][j+6] = "b"; //오른면 초기화(파랑)
//            }
//        }
        initCube(); //큐브 초기화

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");
            for(String input: inputs){
                String[] token = input.split("");
                String side = token[0]; //회전하는 면
                String direction = token[1]; //회전하는 방향
                turnByDirection(side, direction);
            }

            System.out.print(printUpper());
            initCube();
        }
    }
    public static void initCube(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cube[i+3][j+3] = "w"; //윗면 초기화(흰색)
                cube[i+9][j+3] = "y"; //아랫면 초기화(노란색)
                cube[i+6][j+3] = "r"; //앞면 초기화(빨강)
                cube[i][j+3] = "o"; //뒷면 초기화(오렌지)
                cube[i+3][j] = "g"; //왼면 초기화(초록)
                cube[i+3][j+6] = "b"; //오른면 초기화(파랑)
            }
        }
    }
    public static void turnByDirection(String side, String direction){
        if(direction.equals("+")){ //시계방향 회전
            turnSide(side);
        }
        else if(direction.equals("-")){ //반시계방향 회전
            for(int i = 0; i < 3; i++){ //반시계방향은 시계방향 * 3회
                turnSide(side);
            }
        }
    }

    public static void turnSide(String side){
        switch(side){
            case "U":
                turnUpper();
                break;
            case "D":
                turnDown();
                break;
            case "F":
                turnFront();
                break;
            case "B":
                turnBack();
                break;
            case "L":
                turnLSide();
                break;
            case "R":
                turnRSide();
                break;
        }
    }

    public static String printUpper(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                sb.append(cube[i+3][j+3]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    //시계방향으로 돌리는 것을 기준으로 삼음
    public static void turnUpper(){
        String[][] tmp = new String[5][5];
        String[][] result = new String[5][5];
        //일단 5*5 임시 행렬에 회전할 부분 넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tmp[i+1][j+1] = cube[i+3][j+3];
            }
        }
        for(int i = 0; i < 3; i++){
            tmp[0][i+1] = cube[2][i+3];
            tmp[i+1][4] = cube[i+3][6];
            tmp[4][i+1] = cube[6][i+3];
            tmp[i+1][0] = cube[i+3][2];
        }

        //시계방향으로 회전. 회전결과는 result에
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                result[j][4-i] = tmp[i][j];
            }
        }

        //회전결과를 큐브에 붙여넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cube[i+3][j+3] = result[i+1][j+1];
            }
        }
        for(int i = 0; i < 3; i++){
            cube[2][i+3] = result[0][i+1];
            cube[i+3][6] = result[i+1][4];
            cube[6][i+3] = result[4][i+1];
            cube[i+3][2] = result[i+1][0];
        }
    }

    public static void turnDown(){
        String[][] tmp = new String[5][5];
        String[][] result = new String[5][5];
        //일단 5*5 임시 행렬에 회전할 부분 넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tmp[i+1][j+1] = cube[i+9][j+3];
            }
        }
        /**
         * 주의요망
         */
        for(int i = 0; i < 3; i++){
            tmp[0][i+1] = cube[8][i+3];
            tmp[i+1][4] = cube[5-i][8];
            tmp[4][i+1] = cube[0][i+3];
            tmp[i+1][0] = cube[5-i][0];
        }

        //시계방향으로 회전. 회전결과는 result에
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                result[j][4-i] = tmp[i][j];
            }
        }

        //회전결과를 큐브에 붙여넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cube[i+9][j+3] = result[i+1][j+1];
            }
        }
        for(int i = 0; i < 3; i++){
            cube[8][i+3] = result[0][i+1];
            cube[5-i][8] = result[i+1][4];
            cube[0][i+3] = result[4][i+1];
            cube[5-i][0] = result[i+1][0];
        }
    }

    private static void turnFront() {
        String[][] tmp = new String[5][5];
        String[][] result = new String[5][5];
        //일단 5*5 임시 행렬에 회전할 부분 넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tmp[i+1][j+1] = cube[i+6][j+3];
            }
        }
        for(int i = 0; i < 3; i++){
            tmp[0][i+1] = cube[5][i+3];
            tmp[i+1][4] = cube[5][i+6];
            tmp[4][i+1] = cube[9][i+3];
            tmp[i+1][0] = cube[5][2-i];
        }

        //시계방향으로 회전. 회전결과는 result에
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                result[j][4-i] = tmp[i][j];
            }
        }

        //회전결과를 큐브에 붙여넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cube[i+6][j+3] = result[i+1][j+1];
            }
        }
        for(int i = 0; i < 3; i++){
            cube[5][i+3] = result[0][i+1];
            cube[5][i+6] = result[i+1][4];
            cube[9][i+3] = result[4][i+1];
            cube[5][2-i] = result[i+1][0];
        }
    }

    private static void turnBack() {
        String[][] tmp = new String[5][5];
        String[][] result = new String[5][5];
        //일단 5*5 임시 행렬에 회전할 부분 넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tmp[i+1][j+1] = cube[i][j+3];
            }
        }
        for(int i = 0; i < 3; i++){
            tmp[0][i+1] = cube[11][i+3];
            tmp[i+1][4] = cube[3][8-i];
            tmp[4][i+1] = cube[3][i+3];
            tmp[i+1][0] = cube[3][i];
        }

        //시계방향으로 회전. 회전결과는 result에
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                result[j][4-i] = tmp[i][j];
            }
        }

        //회전결과를 큐브에 붙여넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cube[i][j+3] = result[i+1][j+1];
            }
        }
        for(int i = 0; i < 3; i++){
            cube[11][i+3] = result[0][i+1];
            cube[3][8-i] = result[i+1][4];
            cube[3][i+3] = result[4][i+1];
            cube[3][i] = result[i+1][0];
        }
    }

    private static void turnLSide() {
        String[][] tmp = new String[5][5];
        String[][] result = new String[5][5];
        //일단 5*5 임시 행렬에 회전할 부분 넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tmp[i+1][j+1] = cube[i+3][j];
            }
        }
        for(int i = 0; i < 3; i++){
            tmp[0][i+1] = cube[i][3];
            tmp[i+1][4] = cube[i+3][3];
            tmp[4][i+1] = cube[8-i][3];
            tmp[i+1][0] = cube[11-i][3];
        }

        //시계방향으로 회전. 회전결과는 result에
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                result[j][4-i] = tmp[i][j];
            }
        }

        //회전결과를 큐브에 붙여넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cube[i+3][j] = result[i+1][j+1];
            }
        }
        for(int i = 0; i < 3; i++){
            cube[i][3] = result[0][i+1];
            cube[i+3][3] = result[i+1][4];
            cube[8-i][3] = result[4][i+1];
            cube[11-i][3] = result[i+1][0];
        }
    }

    private static void turnRSide() {
        String[][] tmp = new String[5][5];
        String[][] result = new String[5][5];
        //일단 5*5 임시 행렬에 회전할 부분 넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tmp[i+1][j+1] = cube[i+3][j+6];
            }
        }
        for(int i = 0; i < 3; i++){
            tmp[0][i+1] = cube[2-i][5];
            tmp[i+1][4] = cube[11-i][5];
            tmp[4][i+1] = cube[i+6][5];
            tmp[i+1][0] = cube[i+3][5];
        }

        //시계방향으로 회전. 회전결과는 result에
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                result[j][4-i] = tmp[i][j];
            }
        }

        //회전결과를 큐브에 붙여넣음
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cube[i+3][j+6] = result[i+1][j+1];
            }
        }
        for(int i = 0; i < 3; i++){
            cube[2-i][5] = result[0][i+1];
            cube[11-i][5] = result[i+1][4];
            cube[i+6][5] = result[4][i+1];
            cube[i+3][5] = result[i+1][0];
        }
    }
}
