package leetcode;

public class No208_2 {


    private class Node{
        Node[] childs = new Node[26];
        boolean isLeaf = false;
    }

     private Node root = new Node();

    public void Trie() {
    }

    public void insert(String word){
        Node node = root;
        for(int i=0;i<word.length();i++){
            int index = word.charAt(i)-'a';
            if(node.childs[index]==null){
                node.childs[index] = new Node();
            }
            node = node.childs[index];
            if(i==word.length()-1) node.isLeaf = true;
        }
    }



     public boolean search(String word){
        Node node = root;
        for(int i=0;i<word.length();i++){
            int index= word.charAt(i)-'a';
            if(node.childs[index]==null) return false;
            node = node.childs[index];
        }
        return node.isLeaf;
     }

     public boolean startsWith(String prefix){
        Node node = root;
        for(int i=0;i<prefix.length();i++){
            int index= prefix.charAt(i)-'a';
            if(node.childs[index]==null) return false;
            node = node.childs[index];
        }
        return true;
     }

    public static void main(String[] args) {
        No208_2 no208_2 = new No208_2();
        no208_2.insert("abcd");
        System.out.println(no208_2.search("abc"));
        System.out.println(no208_2.startsWith("abc"));
    }
}
