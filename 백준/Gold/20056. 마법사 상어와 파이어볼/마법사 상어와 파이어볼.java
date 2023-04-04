import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 
	 * @param args
	 */
	private static int N, M, K;
	private static int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	private static List<Fireball>[][] map;
	public static class Fireball{
		public int r; //행
		public int c; //열
		public int m; //질량
		public int s; //속력
		public int d; //방향(index)
		
		public Fireball(int m, int s, int d) {
			super();
//			this.r = r;
//			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
		public Fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}

		public Fireball() {
			
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new LinkedList[N+1][N+1]; //행, 열이 1부터 시작이므로
		for(int n = 1; n <= N; n++) {
			for(int m = 1; m <= N; m++) {
				map[n][m] = new LinkedList<>();
			}
		}
		//최초의 파이어볼들 map에 넣어줌
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c].add(new Fireball(r, c, m, s, d));
		}
		
//		//map 출력용 메서드
//		for(int n = 1; n <= N; n++) {
//			for(int m = 1; m <= N; m++) {
//				System.out.print(!map[n][m].isEmpty()+"\t");
//			}
//			System.out.println();
//		}
		
		int totalMass = 0;
		Deque<Fireball> queue = new ArrayDeque<>();
		for(int k = 0; k <= K; k++) { //명령 K번(K번 루틴반복)
			//1. 이동
			//1-1. 기존 맵에 있던 공들 다음위치로 보내줌
			for(int n = 1; n <= N; n++) {
				for(int m = 1; m <= N; m++) {
					if(!map[n][m].isEmpty()) { //맵의 현재 좌표에 공이 있다면
						Iterator<Fireball> iter = map[n][m].iterator();
						while(iter.hasNext()) {
							Fireball current = iter.next();
							//Fireball의 정보 읽어서 다음 위치로 보내줌(좌표정보 갱신하고 큐에 넣음)
							int[] direction = directions[current.d];
							current.r += direction[0]*current.s; //방향과 속력 곱한 값 더해줌
							current.c += direction[1]*current.s;
							
							
							//새 좌표 보정해줌(범위 벗어날때)
							//좌표 보정시 r mod N연산
							//r이 음수일때? 
							//일단 abs(r) mod N 구하고
							//그 결과값을 음수로 만들고 N과 더함
							if(current.r - 1 < 0) {
								int result = Math.abs(current.r-1) % N;
								result = -result + N + 1;
								current.r = result;
							}
							current.r = ((current.r-1)%N)+1;
							
							
							if(current.c - 1 < 0) {
								int result = Math.abs(current.c-1) % N;
								result = -result + N + 1;
								current.c = result;
							}
							current.c = ((current.c-1)%N)+1;
							
							
							queue.add(current); //다음위치로 이동시키기 위해 큐에 추가
							iter.remove(); //현재 위치에서 fireball 제거
							
						}
					}
				}
			}
			totalMass = 0; //결과로 출력할 파이어볼 질량 합
			
			//1-2. 큐에 있는 공들 map에 넣어줌
			while(!queue.isEmpty()) {
				Fireball current = queue.pollFirst();
				int r = current.r;
				int c = current.c;
				totalMass += current.m; //결과값 누적
				map[r][c].add(current);
			}
			
			if(k == K) break; //마지막 이동 마쳤다면 종료
			
			//2. 이동 마쳤다면, 같은 위치에 있는 공 합치고 나눔
			for(int n = 1; n <= N; n++) {
				for(int m = 1; m <= N; m++) {
					if(map[n][m].size() > 1) { //공이 2개 이상 있을 때 합쳐주는 과정 필요
						Iterator<Fireball> iter = map[n][m].iterator();
						int ballCnt = 0;
						int mass = 0;
						int speed = 0;
						int oddCnt = 0;
						int evenCnt = 0;
						
						while(iter.hasNext()) {
							Fireball current = iter.next();
							mass += current.m;
							speed += current.s;
							ballCnt++;
							if(current.d%2==0) evenCnt++;
							else oddCnt++;
							iter.remove();
						}
						
						for(int i = 0; i < 4; i++) {
							Fireball fireball = new Fireball();
							fireball.r = n;
							fireball.c = m;
							if(evenCnt == ballCnt || oddCnt == ballCnt) {
								//모두 홀수거나 모두 짝수였다면 0,2,4,6
								fireball.d = i*2;
							}
							else {
								//아녔다면 1,3,5,7
								fireball.d = i*2 + 1;
							}
							fireball.m = mass/5;
							fireball.s = speed/ballCnt;
							if(fireball.m == 0) break; //질량이 0이면 소멸
							map[n][m].add(fireball);
						}
						
					}
				}
			}
			
//			//map 출력용 메서드
//			for(int n = 1; n <= N; n++) {
//				for(int m = 1; m <= N; m++) {
//					System.out.print(!map[n][m].isEmpty()+"\t");
//				}
//				System.out.println();
//			}
			
		}
		System.out.println(totalMass);
	}
}