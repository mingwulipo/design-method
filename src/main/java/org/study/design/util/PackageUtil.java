package org.study.design.util;

import org.study.design.strategy.ConsumeStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-10-17 09:56
 */
public class PackageUtil {
    /**
     * 路径分隔符
     */
    private static final String PATH_SEPARATOR = "/";

    /**
     * 解析包下的所有类，返回全类名结合
     * @param packageName
     * @return
     */
    public static List<String> resolveAllClass(String packageName) {
        String packagePath = resolvePackagePath(packageName);
        return parseClassName(packagePath, packageName);
    }
    
    /**
     * 解析包的全路径(比你的包路径为cn.ishow.test)
     * @param webPackage
     * @return
     */
    private static String resolvePackagePath(String webPackage) {
        // 扫码所有的包并把其放入到访问关系和方法放入到内存中
        File f = new File(PackageUtil.class.getResource(PATH_SEPARATOR).getPath());
        String totalPath = f.getAbsolutePath();

        String pageName = PackageUtil.class.getPackage().getName().replace(".", PATH_SEPARATOR);
        totalPath = totalPath.replace(pageName, "");

        String packagePath = webPackage.replace(".", PATH_SEPARATOR);
        totalPath = totalPath + PATH_SEPARATOR + packagePath;
        return totalPath;
    }

    /**
     * 解析包下面的所有类
     * @param packagePath 上一步获取包的全路径
     * @param webPackage  包(cn.ishow.test)
     * @return
     */
    private static List<String> parseClassName(String packagePath, String webPackage) {
        List<String> array = new ArrayList<>();
        File root = new File(packagePath);
        resolveFile(root, webPackage, array);
        return array;

    }

    /**
     * 解析包下的类，递归调用，全类名存到集合中
     * @param root
     * @param webPackage
     * @param classNames
     */
    private static void resolveFile(File root, String webPackage, List<String> classNames) {
        if (!root.exists()) {
            return;
        }

        File[] files = root.listFiles();
        if (files == null || files.length == 0) {
            return;
        }

        for (File child : files) {
            if (child.isDirectory()) {
                String parentPath = child.getParent();
                String childPath = child.getAbsolutePath();
                String temp = childPath.replace(parentPath, "");
                temp = temp.replace(PATH_SEPARATOR, "");
                resolveFile(child, webPackage + "." + temp, classNames);

            } else {
                String fileName = child.getName();
                if (fileName.endsWith(".class")) {
                    String name = fileName.replace(".class", "");
                    //保存全限定名
                    String className = webPackage + "." + name;
                    classNames.add(className);
                }
            }
        }

    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {

        String packageName = ConsumeStrategy.class.getPackage().getName() + ".impl";
        List<String> list = resolveAllClass(packageName);

        System.out.println(list);

    }
}
