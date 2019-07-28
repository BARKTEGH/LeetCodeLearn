package test;

import java.io.RandomAccessFile;
import java.net.URL;
import java.util.concurrent.*;

public class testMain {

    public static void main(String[] args) {
        String url = "https://mirrors.tuna.tsinghua.edu.cn/ubuntu-releases/18.04.2/ubuntu-18.04.2-desktop-amd64.iso";
        String saveFile = "F:/ubuntu18.04.iso";
        int threadNum = 5;
//        ExecutorService executorService = new ThreadPoolExecutor(5,5,0L,
//                TimeUnit.MICROSECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread t = new Thread(r);
//                t.setDaemon(true);
//                System.out.println("create thread" + t);
//                return t;
//            }
//        });

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<5;i++){
            executorService.submit(new DownloadThread());
        }
        try {
            System.out.println("1233");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdownNow();
        }
        System.out.println("Completed!");
    }



    private static class DownloadThread implements Runnable {
        private int startPos;
        private int currentPartSize;
        private RandomAccessFile currPart;
        //该线程已下载的字节数
        private int length;



        @Override
        public void run() {
            try {
                System.out.println(this.getClass().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
