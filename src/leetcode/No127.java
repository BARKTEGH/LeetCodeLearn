package leetcode;

import org.junit.Test;

import java.util.*;

public class No127 {

    //超时，未用广度优先
    private  int size = Integer.MAX_VALUE;
    public int  findLadders(String beginWord, String endWord, List<String> wordList) {
        boolean flag = false;
        for (String word:wordList
             ) {if(word.equals(endWord)){
                 flag=true;
                break;
             }
        }
        if(flag==false){
            return 0;
        }
        boolean[] used = new boolean[wordList.size()];
        Arrays.fill(used,false);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(beginWord);
        dfs(beginWord,endWord,wordList,used,strings);

        return size==Integer.MAX_VALUE?0:size;
    }

    public void dfs(String beginword,String endword,
               List<String> wordList,boolean[] used,List<String> temp){
        if(beginword.equals(endword)){
            if (temp.size()< size) {
                size = temp.size();
            }
            return;
        }
        if(temp.size() >= size){
            return;
        }
        for(int i=0;i<wordList.size();i++){
            String word = wordList.get(i);
            if(!used[i] && diffTwoStr(word,beginword)  ){

                used[i] = true;
                temp.add(word);
                dfs(word,endword,wordList,used,temp);
                temp.remove(temp.size()-1);
                used[i] = false;
            }
        }
        return;

    }

    public boolean diffTwoStr(String str1,String str2){
        int j= 0;
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i) != str2.charAt(i)){
                j++;
            }

        }
        return j==1 ? true:false;
    }

    /*
    【笔记】两端搜索。本题是需要从beginWord转换为endWord。上一份笔记严格按照这个要求，进行转换，结果为88ms。为了对此进行优化。采用两端搜索，也就是一头从beginWord转换为endWord，另外一头从endWord转换为beginWord。为什么要这么做呢？有什么意义呢？

    假设从beginWord转换为endWord，存在于字典中的，第一个中间结果有30个。

    而，从endWord转换为beginWord，存在于字典中的，第一个中间结果有2个。

    那么，很显然。从endWord开始会更快。所以，每次都从个数少的那块开始替换一位。

    因为，我们只需要路人甲、路人乙相遇，而不管你怎么走...

    以下优化结果16ms。
     */

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
	    Set<String> wordlist = new LinkedHashSet<>(wordList);
		if(!wordlist.contains(endWord))
			return 0;
		if(wordlist.contains(beginWord))
			wordlist.remove(beginWord);
		int res = 2;

		Set<String> forward = new LinkedHashSet<>();
		forward.add(beginWord);
		Set<String> backward = new LinkedHashSet<>();
		backward.add(endWord);
		String zm = "qwertyuioplkjhgfdsazxcvbnm";
		char[] letters = zm.toCharArray();
		int length = endWord.length();
		Set<String> temp = null;
		String x,y,z;
		while(forward.size() != 0)
		{
			if(forward.size() > backward.size())
			{
				temp = forward;
				forward = backward;
				backward = temp;
			}
			temp = new LinkedHashSet<>();
			for (String word : forward) {
				for(int i = 0; i < length; i++)
				{
					x = word.substring(0, i);
					y = word.substring(i+1);
					for(char c : letters)
					{
						z = x + c + y;
						if(backward.contains(z))
							return res;
						if(wordlist.contains(z))
						{
							temp.add(z);
							wordlist.remove(z);
						}
					}
				}

			}
            res++;
			forward = temp;
		}
		return 0;
	}


    @Test
    public void test(){
        String[] strings = new String[] {"hot","dot","dog","lot","log","cog"};
        ArrayList<String> wordlist = new ArrayList<>();
        wordlist.addAll(Arrays.asList(strings));
        findLadders("hit","cog",wordlist);
    }
}
