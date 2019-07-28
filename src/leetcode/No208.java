package leetcode;


public class No208 {

    /** Initialize your data structure here. */
    private class Node{
        Node[] childs = new Node[26];
        boolean isLeaf = false;
    }

    private Node root = new Node();

    public void Trie() {
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(word,root);
    }

    private void insert(String word,Node node){
        if(node==null) return;
        if(word.length()==0){
            node.isLeaf =true;
            return;
        }
        int index = word.charAt(0)-'a';
        if(node.childs[index] ==null){
            node.childs[index] = new Node();
        }
        insert(word.substring(1),node.childs[index]);
    }


    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(word,root);
    }


    private boolean search(String word,Node node){
        if(node==null) return false;
        if(word.length()==0) return node.isLeaf;
        int index = word.charAt(0)-'a';
        return search(word.substring(1),node.childs[index]);
    }


    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
         return startWith(prefix, root);
    }


    private boolean startWith(String prefix, Node node) {
        if (node == null) return false;
        if (prefix.length() == 0) return true;
        int index = prefix.charAt(0) - 'a';
        return startWith(prefix.substring(1), node.childs[index]);
    }
}
