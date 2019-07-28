package other;

public class Tire {
    class Node{
        Node[] child= new Node[26];
        boolean isLeaf=false;
    }

    private Node root;

    public Tire(){
        root = new Node();
    }

    public void insert(String s){
        insert(s,root);
    }

    public void insert(String s,Node root){
        if(root==null) return;
        if(s.length()==0){
            root.isLeaf = true;
            return;
        }
        int index = s.charAt(0)-'a';
        if(root.child[index]==null){
            root.child[index] = new Node();
        }
        insert(s.substring(1),root.child[index]);
    }

    public boolean select(String s){
        return select(s,root);
    }

    public boolean select(String s,Node root){
        if(root==null) return false;
        if(s.length()==0) {
            return root.isLeaf;
        }
        return select(s.substring(1),root.child[s.charAt(0)-'a']);
    }


    public static void main(String[] args) {

        Tire tire = new Tire();
        tire.insert("abc");
        tire.insert("abcd");
        System.out.println(tire.select("a"));
    }


}
