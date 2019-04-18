package org.vincent.res;

import java.net.URL;

/**
 * @author PengRong
 * @package org.vincent.res
 * @ClassName ResourceLoader.java
 * @date 2019/3/25 - 7:20
 * @ProjectName Multthread-in-action
 * @Description: JDK 资源加载
 */
public class ResourceLoader {
    public static void main(String[] args) {

        ClassLoader classLoader = ResourceLoader.class.getClassLoader();

        URL resource = classLoader.getResource("spring/123.md");
        System.out.println(resource);
        resource = classLoader.getResource("");
        System.out.println(resource);
        resource = classLoader.getResource("/");
        System.out.println("/ "+resource);
        resource = classLoader.getSystemResource("spring/123.md");
        System.out.println(resource);
        /** classLoader 加载资源路径 不能以 / 字符开始 */
        resource = classLoader.getResource("/spring/123.md");
        System.out.println(resource);

        /** Class类加载资源文件：可以以 / 字符开始，那么他的 实际寻找路径被resolveName方法处理后变成classpath下 spring/123.md*/
        resource = ResourceLoader.class.getResource("/spring/123.md");
        System.out.println(resource);
        /** Class类加载资源文件： 可以不以 / 字符开始，那么被resolveName方法处理后 实际寻找路径也是classpath下，只是会增加上当前类package全路径 org/vincent/res/123.md*/
        resource = ResourceLoader.class.getResource("123.md");
    }
}
