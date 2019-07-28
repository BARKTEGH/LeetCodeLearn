package designPattern;

public class Singleton {

    //使用一个私有构造函数、一个私有静态变量以及一个公有静态函数来实现。
    //私有构造函数保证了不能通过构造函数来创建对象实例，只能通过公有静态函数返回唯一的私有静态变量。

    /*
    //饿汉模式，线程安全，但失去延迟实例化带来节约资源的好处
    private static Singleton uniqueInstance = new Singleton();
     private  Singleton(){}

     public static Singleton getUniqueInstance(){
         return uniqueInstance;
     }
     */

    /*
    // 懒汉模式，但是线程不安全
    private static Singleton uniqueInstance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(uniqueInstance==null){
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
     */
    /*
    //懒汉模式，线程安全，但是性能差，不推荐
    private static Singleton uniqueInstance;

    private Singleton(){}

    public static  synchronized Singleton getInstance(){
        if(uniqueInstance==null){
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
    */

    /*
    // 双重校验锁-线程安全
    //在 uniqueInstance == null 的情况下，如果两个线程都执行了 if 语句，那么两个线程都会进入 if 语句块内。
    //虽然在 if 语句块内有加锁操作，但是两个线程都会执行 uniqueInstance = new Singleton(); 这条语句，只是先后的问题，那么就会进行两次实例化。
    //因此必须使用双重校验锁，也就是需要使用两个 if 语句。

    //uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
    //为 uniqueInstance 分配内存空间
    //初始化 uniqueInstance
    //将 uniqueInstance 指向分配的内存地址
    //由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
    //例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
    //使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。

    private static   volatile Singleton uniqueInstance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(uniqueInstance==null){
            synchronized (Singleton.class){
                if(uniqueInstance==null)
                    uniqueInstance = new Singleton();
            }
        }
        return uniqueInstance;
    }
    */

    /*
    //静态内部类实现
    //当 Singleton 类加载时，静态内部类 SingletonHolder 没有被加载进内存。
    //只有当调用 getUniqueInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，
    //此时初始化 INSTANCE 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。
    private Singleton(){}

    private static class SingletonHolder{
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
     */

    /*
    //枚举类
    public enum Singleton {
        INSTANCE;

        private String objName;


        public String getObjName() {
            return objName;
        }


        public void setObjName(String objName) {
            this.objName = objName;
        }


        public static void main(String[] args) {

            // 单例测试
            Singleton firstSingleton = Singleton.INSTANCE;
            firstSingleton.setObjName("firstName");
            System.out.println(firstSingleton.getObjName());
            Singleton secondSingleton = Singleton.INSTANCE;
            secondSingleton.setObjName("secondName");
            System.out.println(firstSingleton.getObjName());
            System.out.println(secondSingleton.getObjName());

            // 反射获取实例测试
            try {
                Singleton[] enumConstants = Singleton.class.getEnumConstants();
                for (Singleton enumConstant : enumConstants) {
                    System.out.println(enumConstant.getObjName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    */


}
