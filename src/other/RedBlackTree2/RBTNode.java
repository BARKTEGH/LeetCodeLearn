package other.RedBlackTree2;

public class RBTNode<T extends Comparable<T>> {

    boolean color;
    T key;
    RBTNode<T> left;
    RBTNode<T> right;
    RBTNode<T> parent;

     public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
     }
}
