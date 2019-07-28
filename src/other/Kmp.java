package other;

import java.util.Arrays;

public class Kmp {

    public void getNext(String pattern, int next[]) {
		int j = 0;
		int k = -1;
		int len = pattern.length();
		next[0] = -1;

		while (j < len - 1) {
			if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {

				j++;
				k++;
				next[j] = k;
			} else {

				// 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
				// p[0——k-1]的前缀和后缀的最长共有长度，所接下来可以直接比较p[next[k]]和p[j]
				k = next[k];
			}
		}
        System.out.println(Arrays.toString(next));

	}

	public int kmp(String s,String pattern){
        int slen = s.length();
        int plen = pattern.length();
        int[] next = new int[plen];
        getNext(pattern,next);

        int i=0,j=0;
        while(i<slen&&j<plen){
            if(s.charAt(i)==pattern.charAt(j)){
                i++;
                j++;
            }else {
                if(next[j]==-1){
                    i++;
                    j=0;
                }else {
                    j = next[j];
                }
            }

            if(j==plen){
                return i-j;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Kmp kmp = new Kmp();
        String s = "ABCDABD";
        int[] next = new int[s.length()];
        kmp.getNext(s,next);
        System.out.println(Arrays.toString(next));
        System.out.println(kmp.kmp("ADSADABCDABDE",s));



    }



}
