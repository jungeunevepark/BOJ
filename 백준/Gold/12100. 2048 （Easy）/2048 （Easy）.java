import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		int[][] game = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<N; j++)
				game[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0, game);
		System.out.println(result);
	}
	static void dfs(int cnt, int[][] first) {
		if(cnt == 5) {
			int max = 0;
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(max < first[i][j])
						max = first[i][j];
//			for(int[]m:first)System.out.println(Arrays.toString(m));
//			System.out.println();
			if(result < max)
				result = max;
				
			return;
		}
		for(int d =0; d<4; d++) { //범위!
			int[][] temp = new int[N][N];
			for(int i=0; i<N; i++) temp[i] = Arrays.copyOf(first[i], N);
			temp = move(d, temp);
			dfs(cnt+1,temp);
		}
	}
	static int[][] move(int dir, int[][] move){
		if(dir == 0) move_one(move);
		else if(dir == 1) move_two(move);
		else if(dir == 2) move_three(move);
		else move_four(move);
		return move;
	}
	static int[][] move_one(int[][] move){
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			for(int j=N-1; j>=0; j--)
				if(move[i][j] != 0) {
					stack.offer(move[i][j]);}
			int idx = -1;
			while(idx++ < N-1) {
				if(stack.isEmpty()) move[i][idx] = 0;
				else {
					int t = stack.pollLast();
					if(stack.isEmpty()) move[i][idx] = t;
					else {
						if(stack.peekLast() == t) {
							stack.pollLast();
							move[i][idx] = 2*t;
						}
						else move[i][idx] = t;
					}
				}
			}
		}
		return move;
	}
	static int[][] move_two(int[][] move){
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				if(move[i][j] != 0)
					stack.offer(move[i][j]);
			int idx = N;
			while(idx-- > 0) {
				if(stack.isEmpty()) move[i][idx] = 0;
				else {
					int t = stack.pollLast();
					if(stack.isEmpty()) move[i][idx] = t;
					else {
						if(stack.peekLast() == t) {
							stack.pollLast();
							move[i][idx] = 2*t;
						}
						else move[i][idx] = t;
					}
				}
			}
		}
		return move;
	}
	static int[][] move_three(int[][] move){
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int j=0; j<N; j++) {
			for(int i=0; i<N; i++)
				if(move[i][j] != 0)
					stack.offer(move[i][j]);
			int idx =N;
			while(idx-- > 0) {
				if(stack.isEmpty()) move[idx][j] = 0;
				else {
					int t = stack.pollLast();
					if(stack.isEmpty()) move[idx][j] = t;
					else {
						if(stack.peekLast() == t) {
							stack.pollLast();
							move[idx][j] = 2*t;
						}
						else move[idx][j] = t;
					}
				}
			}
		}
		return move;
	}
	static int[][] move_four(int[][] move){
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int j=0; j<N; j++) {
			for(int i=N-1; i>=0; i--)
				if(move[i][j] != 0)
					stack.offer(move[i][j]);
			int idx =-1;
			while(idx++ < N-1) {
				if(stack.isEmpty()) move[idx][j] = 0;
				else {
					int t = stack.pollLast();
					if(stack.isEmpty()) move[idx][j] = t;
					else {
						if(stack.peekLast() == t) {
							stack.pollLast();
							move[idx][j] = 2*t;
						}
						else move[idx][j] = t;
					}
				}
			}
		}
		return move;
	}
}