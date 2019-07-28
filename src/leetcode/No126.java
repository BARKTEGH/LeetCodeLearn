package leetcode;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class No126 {


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //采用bfs来便利找到最短的

        List<List<String>>  lists = new ArrayList<List<String>>();

        if (null == beginWord || null == endWord || !wordList.contains(endWord)) {
                return lists;
        }

        HashSet<String> wordLists = new HashSet<>(wordList);

        bfs(beginWord,endWord,lists,wordLists);

        return lists;
    }

    public void bfs(String beginword,String endword,List<List<String>>  lists,
               HashSet<String> wordList){
        List<String> queue= new LinkedList<>();
        queue.add(beginword);
        List<ArrayList<String>> ListsAll = new LinkedList<>();
        ArrayList<String> temp = new ArrayList<>();
        temp.add(beginword);
        ListsAll.add(temp);
        boolean flag = true;
        HashSet<String> usedWord = new HashSet<>();

        while(flag && !queue.isEmpty()) {
            int queueSize = queue.size();
            usedWord.clear();
            for (int i = 0; i < queueSize; i++) {
                String wordQueue = queue.remove(0);
                temp = ListsAll.remove(0);
                if(wordQueue.equals(endword)){
                    flag = false;
                    lists.add(temp);
                    //为了保证存在多个，在这次循环里还要继续执行
                    continue;
                }
                for (String word : wordList) {
                    if (diffTwoStr(word, wordQueue)) {
                        ArrayList<String> temp2 = new ArrayList<>(temp);
                        temp2.add(word);
                        ListsAll.add(temp2);
                        queue.add(word);
                        usedWord.add(word);
                    }
                }
            }
            wordList.removeAll(usedWord);
        }

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



    @Test
    public void test(){
//        String[] strings = new String[] {"hot","dot","dog","lot","log","cog"};
//        ArrayList<String> wordlist = new ArrayList<>();
//        wordlist.addAll(Arrays.asList(strings));
//        findLadders("hit","cog",wordlist);
//        Character.isLetter();
        int[] nums = new int[] {1};
        System.out.println();

    }
}
