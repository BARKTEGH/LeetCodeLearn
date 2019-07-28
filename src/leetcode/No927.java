package leetcode;

public class No927 {

    /**
     * 直接构造。为了保证三段表示的二进制数各自相等，显然需要保证每一段中1的数量相等；
     * 那么，可以先统计1的数量，如果这个数模3不为0则显然没有合法的解；否
     * 则就可以得到每一段中应有的1的数量了。
     * 然后就可以构造最后一段了：从后向前遍历数组，直到找到相应数量的1为止（因为再向前找只能找到前导0；否则1的数量就不合法了）。
     * 此时就可以知道二进制数的长度了，可以构造第一段；
     * 然后判断中间的一段和两边是否相等即可。
     * @param A
     * @return
     */
    public int[] threeEqualParts(int[] A) {
        int oneCount = 0;
        int n =A.length;
        StringBuffer sb = new StringBuffer();
        for(int a:A){
            if(a==1) oneCount++;
            sb.append(a);
        }


        if(oneCount==0) return new int[]{0,n-1};
        if(oneCount%3!=0) return new int[]{-1,-1};

        //构造最后一段
        oneCount = oneCount/3;
        int cnt=0;
        int start3=n-1;
        String str3="";
        for (int i=n-1;i>=0;i--){
            cnt+=A[i];
            if(cnt==oneCount){
                start3 = i;
                str3=sb.substring(i);
                break;
            }
        }
        //去掉0的长度，不一定是第三段的长度
        int len = str3.length();

        //构造第一段
        int start1 = 0;
        while(start1<n&&A[start1]==0) start1++;
        String str1 = sb.substring(start1,start1+ len);
        if(!str1.equals(str3)) return new int[]{-1,-1};

        //构造第二段
        int start2 = start1+len;
        while(start2<n&&A[start2]==0) start2++;
        //长度不够
        if(start2+str1.length()>start3) return new int[]{-1,-1};

        String str2 = sb.substring(start2,start2+len);
        if(!str3.equals(str2)) return new int[]{-1,-1};

        //确保从第二段与第三段之间全为0
        for(int i=start2+len;i<start3;i++){
            if(A[i]!=0) return new int[]{-1,-1};
        }
        System.out.println(start1+len-1);
        System.out.println(start2+len);
        return new int[]{start1+len-1,start2+len};
    }

    public static void main(String[] args) {

        No927 no927 = new No927();
        int[] A = {1,0,1,0,1};
        System.out.println(no927.threeEqualParts(A));

        String s = "abc";
        System.out.println(s.indexOf('d'));
    }
}
