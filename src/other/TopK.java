package other;

import java.util.Arrays;

public class TopK {

    public static void main(String[] args)
    {
        // 源数据
        int[] data = {56,275,12,6,45,478,41,1236,456,12,546,45,1236};

        // 获取Top5
        int[] top5 = topK(data, 5);
        int[] topmin5 = topminK(data,5);
        System.out.println(Arrays.toString(top5));
        System.out.println(Arrays.toString(topmin5));
        for(int i=0;i<5;i++)
        {
            System.out.println(top5[i]);
            System.out.println(topmin5[i]);
        }
        
    }

    // 从data数组中获取最大的k个数
    private static int[] topK(int[] data,int k)
    {
        // 先取K个元素放入一个数组topk中
        int[] topk = new int[k];
        for(int i = 0;i< k;i++)
        {
            topk[i] = data[i];
        }

        // 转换成最小堆
        Heap heap = new Heap(topk);

        // 从k开始，遍历data
        for(int i= k;i<data.length;i++)
        {
            int root = heap.getRoot();

            // 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆
            if(data[i] > root)
            {
                heap.setRoot(data[i]);
            }
        }

        return topk;
    }


    /**
     * 从data数组中获取最xiao的k个数
      */
    private static int[] topminK(int[] data,int k)
    {
        // 先取K个元素放入一个数组topk中
        int[] topk = new int[k];
        for(int i = 0;i< k;i++)
        {
            topk[i] = data[i];
        }

        // 转换成最小堆
        MaxHeap heap = new MaxHeap(topk);

        // 从k开始，遍历data
        for(int i= k;i<data.length;i++)
        {
            int root = heap.getRoot();
            if(data[i] < root)
            {
                heap.setRoot(data[i]);
            }
        }

        return topk;
    }

}
