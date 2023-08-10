import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        String[] words = new String[N];
        int maxLength = 0;
        for(int i=0; i<N; i++) {
            words[i] = bf.readLine();
            maxLength = Math.max(words[i].length(), maxLength);
        }
        Map<Character, Integer> word = new HashMap<>();
        for(int m = maxLength; m>0; m--){
            for(int i=0; i<N; i++){
                if(words[i].length() < m) continue;
                int index = words[i].length()-m;
                if(word.containsKey(words[i].charAt(index))){
                    int count = word.get(words[i].charAt(index));
                    word.put(words[i].charAt(index), count+1);
                }
                else word.put(words[i].charAt(index), 1); // 키 값 확인
            }
            for(Character c: word.keySet()){
                word.put(c, word.get(c)*10);
            }
        }
        Map<Character, Integer> result = new HashMap<>();
        List<Character> set = new ArrayList<>(word.keySet());
        set.sort((o1, o2)-> -Integer.compare(word.get(o1), word.get(o2)));
        int n = 9;
        for(Character w : set){
            result.put(w, n);
            n--;
        }
        int ans = 0;
        for(int i=0; i<N; i++){
            int l = words[i].length();
            for(int k=l; k>0; k--){
                int num = result.get(words[i].charAt(l-k));
                ans += num * (int)Math.pow(10, k-1);
            }
        }
        System.out.println(ans);
    }
}