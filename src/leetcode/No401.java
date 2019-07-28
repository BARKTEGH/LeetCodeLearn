package leetcode;

import org.junit.Test;

import java.util.*;

public class No401 {

    int[] time = new int[] {1,2,4,8,16,32,60,120,240,480};
    int length ;
    Set<Integer> counts = new HashSet<>();

    public List<String> readBinaryWatch(int num) {
        length = C2(10,num);
        dfs(0,num,0,0);
        List<String> strings = new ArrayList<String>();
        for(Integer count:counts){
            int hour = count/60;
            int min = count%60;
            String str = String.valueOf(hour) +":"+ String.format("%02d",min);
            strings.add(str);
            System.out.println(str);
        }
        return strings;
    }

    public void dfs(int pos,int N,int count,int sum){
        if(counts.size() == length||pos> 10) return;
        if(count == N) {
            counts.add(sum);
            return ;
        }
        if(pos<=9) {
            dfs(pos+1,N,count+1,sum+time[pos]);
        }
         dfs(pos+1,N,count,sum);


    }

    // 求排列数 A(n,m) n>m
	public static int A(int n, int m)
	{
		int result = 1;
		// 循环m次,如A(6,2)需要循环2次，6*5
		for (int i = m; i > 0; i--)
		{
			result *= n;
			n--;// 下一次减一
		}
		return result;
	}
	// 求组合数，这个也不需要了。定义式，不使用互补率
	public static int C2(int n, int m)
	{
		// int denominator=factorial(up);//分母up的阶乘
		// 分母
		int denominator = A(m, m);// A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
		// 分子
		int numerator = A(n, m);// 分子的排列数
		return numerator / denominator;
	}


    @Test
    public void test(){
        readBinaryWatch(1);
    }
}
