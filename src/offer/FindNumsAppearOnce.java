package offer;

public class FindNumsAppearOnce {
    public void findNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int diff = 0;
        for(int num:array){
            diff ^= num;
        }
        num1[0] = 0;
        num2[0] = 0;
        diff &= -diff;
        for(int num:array){
            if((num&diff)==0){
                num1[0] ^= num;
            }else{
                num2[0] ^= num;
            }
        }


    }
}
