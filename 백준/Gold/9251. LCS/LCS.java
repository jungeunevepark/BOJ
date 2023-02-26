import java.io.*;
import java.util.*;

public class Main {
	static int[][] d;
	static String seq1, seq2;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		seq1 = bf.readLine();
		seq2 = bf.readLine();
		d = new int[seq1.length()][seq2.length()];
		for(int i=0; i<seq1.length(); i++)
			for(int j=0; j<seq2.length(); j++)
				d[i][j] = -1;
		System.out.println(LCS(seq1.length()-1, seq2.length()-1));
	}
	static int LCS(int i, int j) {
		if(i<0 || j<0) return 0;
		if(d[i][j] != -1) return d[i][j];
		if(seq1.charAt(i) == seq2.charAt(j)) {
			d[i][j] = LCS(i-1, j-1)+ 1;
		}
		else {
			d[i][j] = Math.max(LCS(i-1, j), LCS(i, j-1));
		}
		return d[i][j];
	}
}