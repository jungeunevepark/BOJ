import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int L;
	static int map[][];
	static int result;
	static int row[][];
	static int col[][];
	static char lr[][];
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		row = new int[N][N];
		col = new int[N][N];
		lr = new char[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		set_col();
		set_row();
		System.out.println(result);
	}
	static void set_col(){
		for(int i=0; i<N; i++) {
			f:for(int j=1; j<N; j++) {
				if(map[i][j-1] == map[i][j] + 1) {
					if(j+L>N) continue f;
					for(int k = j; k<j+L; k++) {
						if((k != j+L-1 && map[i][k+1] != map[i][k]) || col[i][k] == 1) {
							for(int u=k-1; u>=j; u--) {
								col[i][u] -= 1;
							}
							j = k; //k인지 k-1인지 아마 k-1일것이다.
							continue f;
						}
						col[i][k] += 1;
					}
					lr[i][j] = 'l';
					j = j+L-1;
					continue f;
				}
				else if(map[i][j-1] +1 == map[i][j]) {
					if(j-L < 0) continue f;
					for(int k = j-1; k>=j-L; k--) {
						if((k> 0 && k-1 >= j-L && map[i][k-1] != map[i][k]) || col[i][k] == 1) {
							for(int u=k+1; u<j; u++) {
								col[i][u] -= 1;
							}
							continue f;
						}
						col[i][k] += 1;
					}
					lr[i][j-1] = 'r';
					continue f;					
				}
			}
		}
		// 출력
		f: for(int i=0; i<N; i++) {
			for(int j=1; j<N; j++) {
				if(map[i][j-1] != map[i][j] && map[i][j-1] != map[i][j] + col[i][j] && map[i][j-1]+col[i][j-1] != map[i][j]) continue f;
				if(map[i][j-1] != map[i][j]) {
					if(map[i][j-1] == map[i][j] + col[i][j] && lr[i][j] != 'l') continue f;
					else if(lr[i][j-1] != 'r' && map[i][j-1]+col[i][j-1] == map[i][j]) continue f;
					}
				}
			result++;
		}
	}
	static void set_row(){
		for(int j=0; j<N; j++) {
			f:for(int i=1; i<N; i++) {
				if(map[i-1][j] == map[i][j] + 1) {
					if(i+L>N) continue f;
					for(int k = i; k<i+L; k++) {
						if((k != i+L-1 && map[k+1][j] != map[k][j]) || row[k][j] == 1) {
							for(int u=k-1; u>=j; u--) {
								row[u][j] -= 1;
							}
							i = k; //k인지 k-1인지 아마 k-1일것이다.
							continue f;
						}
						row[k][j] += 1;
					}
					lr[i][j] = 'l';
					i = i+L-1;
					continue f;
				}
				else if(map[i-1][j] +1 == map[i][j]) {
					if(i-L < 0) continue f;
					for(int k = i-1; k>=i-L; k--) {
						if((k> 0 && k-1 >= i-L && map[k-1][j] != map[k][j]) || row[k][j] == 1) {
							for(int u=k+1; u<i; u++) {
								row[u][j] -= 1;
							}
							continue f;
						}
						row[k][j] += 1;
					}
					lr[i-1][j] = 'r';
					continue f;					
				}
			}
		}
		f: for(int j=0; j<N; j++) {
			for(int i=1; i<N; i++) {
				if(map[i-1][j] != map[i][j] && map[i-1][j] != map[i][j] + row[i][j] && map[i-1][j]+row[i-1][j] != map[i][j]) continue f;
				if(map[i-1][j] != map[i][j]) {
					if(map[i-1][j] == map[i][j] + row[i][j] && lr[i][j] != 'l') continue f;
					else if(lr[i-1][j] != 'r' && map[i-1][j]+row[i-1][j] == map[i][j]) continue f;
					}
				}
			result++;
		}
//		for(int[] a: row) System.out.println(Arrays.toString(a));
//		System.out.println();
	}
}