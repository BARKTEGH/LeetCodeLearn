package other.RedBlackTree;



public class RBTreeNode<T extends Comparable<T>> {
    //node value
    private T value;
    //left child pointer
	private RBTreeNode<T> left;

    //right child pointer
	private RBTreeNode<T> right;
	//parent pointer
	private RBTreeNode<T> parent;
    //color is red or not red
	private boolean red;

	public RBTreeNode(){};
	public RBTreeNode(T value){this.value=value;}
	public RBTreeNode(T value,boolean red){ this.value=value;this.red=red;}

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public RBTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    public RBTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RBTreeNode<T> right) {
        this.right = right;
    }

    public RBTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public boolean isBlack(){
	    return !red;
    }

    public boolean isLeaf(){
	    return left==null&&right==null;
    }

    public void makeRed(){
		red=true;
	}
	public void makeBlack(){
		red=false;
	}
	@Override
	public String toString(){
		return value.toString();
	}
}
