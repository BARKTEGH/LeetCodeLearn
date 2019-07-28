package offer;

import java.util.Stack;

public class TwoStackToQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        int a;
        if(stack2.empty()){
            while(!stack1.empty()){
                a = stack1.peek();
                stack2.push(a);
                stack1.pop();
            }
        }

        a = stack2.peek();
        stack2.pop();
        return a;
    }
}
