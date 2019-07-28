package offer;

import java.util.Stack;

public class IsPopOrder {
    public static boolean isPopOrde(int[] pushL,int[] popL){
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for(int i=0;i<pushL.length;i++){
            stack.push(pushL[i]);
            while(!stack.isEmpty()&&popIndex<popL.length){
                if(stack.peek()==popL[popIndex]){
                    popIndex++;
                    stack.pop();
                }else {
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] push = {1,2,3,4,5};
        int[] pop = {4,5,3,2,1};
        System.out.println(isPopOrde(push,pop));
    }
}
