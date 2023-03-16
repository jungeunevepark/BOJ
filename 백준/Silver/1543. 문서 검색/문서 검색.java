import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   String document = bf.readLine();
	   String word = bf.readLine();
	   int count = 0;
	   f:  for(int i=0; i<document.length(); i++) {
		   if(i+word.length() > document.length()) break;
		   for(int j=0; j<word.length(); j++) {
			   if(document.charAt(i+j) != word.charAt(j)) continue f;
		   }
		   count++;
		   i+=word.length()-1;
	   }
	   System.out.println(count);
	}
}