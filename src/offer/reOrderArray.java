package offer;

import java.util.ArrayList;
import java.util.List;

public class reOrderArray {
   public void reOrderArray(int [] array) {
       List<Integer> even = new ArrayList<>();
       List<Integer> odd = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            if(array[i]%2 ==1) even.add(array[i]);
            else {odd.add(array[i]);}
        }
        int j= 0 ;
        for(int i=0;i<array.length;i++){
            if(i<even.size()){
                array[i] = even.get(i);
            }else {
                array[i] = odd.get(j);
                j++;
            }
        }
        return ;
    }



}
