package org.vincent.singleton;


/**
 * @author PengRong
 * @package org.vincent.singleton
 * @ClassName EnumSingleton.java
 * @date 2019/4/27 - 17:08
 * @ProjectName Multthread-in-action
 * @Description: 使用枚举实现单例模式
 */
public enum EnumSingleton {

    /**  定义一个单个枚举引用代表 单例实例.  */
    INSTANCE;
    private Object data;
    {
        System.out.println("asdlfkjasdfkjsd");
    }
    static {
        System.out.println("static asdlfkasjdf");
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 获取 单例实例
     */
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(EnumSingleton.INSTANCE);
        EnumSingleton singleton = EnumSingleton.getInstance();
        EnumSingleton singleton1 = EnumSingleton.getInstance();
        EnumSingleton singleton2 = EnumSingleton.getInstance();
        EnumSingleton singleton3 = EnumSingleton.getInstance();
        EnumSingleton singleton4 = EnumSingleton.getInstance();
        EnumSingleton singleton5 = EnumSingleton.getInstance();
        if (singleton == singleton1 && singleton1 == singleton2 && singleton2 == singleton3
                && singleton3 == singleton4 && singleton4 == singleton5){
            System.out.println("实现单例");
        }
        singleton.setData("hangzhou");
        System.out.println(singleton.getData());
    }
}
