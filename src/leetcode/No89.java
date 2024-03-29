package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No89 {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        int cur;
        for(int i=0;i<n;i++){
            int change = 1<<i;
            cur = res.size() -1;
            while (cur>=0){
                res.add(res.get(cur)^change);
                cur--;
            }
        }
        return res;
    }

    @Test
    public void test(){
        System.out.println(grayCode(2));
    }

}
