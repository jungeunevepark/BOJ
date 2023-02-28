import java.io.*;
import java.util.*;

public class Main {
	static String[][] d;
	static String seq1, seq2;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		seq1 = bf.readLine();
		seq2 = bf.readLine();
		d = new String[seq1.length()][seq2.length()];
		for(int i=0; i<seq1.length(); i++)
			for(int j=0; j<seq2.length(); j++)
				d[i][j] = null;
		String result = LCS(seq1.length()-1, seq2.length()-1);
		System.out.println(result.length());
		System.out.println(result);
	}
	static String LCS(int i, int j) {
		if(i<0 || j<0) return "";
		if(d[i][j] != null) return d[i][j];
		if(seq1.charAt(i) == seq2.charAt(j)) {
			d[i][j] = LCS(i-1, j-1)+ seq1.charAt(i);
		}
		else {
			String a = LCS(i-1, j); String b = LCS(i, j-1);
			if(a.length() > b.length()) d[i][j] = a;
			else d[i][j] = b;
		}
		return d[i][j];
	}
}