package com.faddenyin.www.utils;

import java.io.*;

public class FileUtils {

    public static void copyDir(String src, String des) throws IOException {
        //初始化文件复制
        File file1 = new File(src);
        //把文件里面内容放进数组
        File[] fs = file1.listFiles();
        //初始化文件粘贴
        File file2 = new File(des);
        //判断是否有这个文件有不管没有创建
        if (!file2.exists()) {
            file2.mkdirs();
        }
        //遍历文件及文件夹
        for (File f : fs) {
            if (f.isFile()) {
                //文件
                copyFile(f.getPath(), des + "/" + f.getName()); //调用文件拷贝的方法
            } else if (f.isDirectory()) {
                //文件夹
                copyDir(f.getPath(), des + "/" + f.getName());//继续调用复制方法      递归的地方,自己调用自己的方法,就可以复制文件夹的文件夹了
            }
        }
    }

    public static void copyFile(String from, String to) throws IOException {
        copyFile(new File(from), new File(to));
    }

    public static void copyFile(File fromFile, File toFile) throws IOException {
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n = 0;
        while ((n = ins.read(b)) != -1) {
            out.write(b, 0, n);
        }

        ins.close();
        out.close();
    }

    public static String readFile(File file) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileReader fr = new FileReader(file);
        char[] chars = new char[1024];
        int len;
        while ((len = fr.read(chars)) != -1) {
            sb.append(new String(chars, 0, len));
        }

        fr.close();
        return sb.toString();
    }

    public static String readFile(String path) throws IOException {
        return readFile(new File(path));
    }

    public static void writeFile(String path, String content) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.delete();
        }

        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }

        FileWriter fw = new FileWriter(path);
        fw.write(content);
        fw.flush();
        fw.close();
    }

    /**
     * Reference:
     *
     * @return
     * @see <a href="https://blog.csdn.net/baidu_18607183/article/details/51890293">获取Java程序运行的路径 | 获取当前jar包的路径</a>
     */
    public static String getSrcPath() {
        java.net.URL url = FileUtils.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;

        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        else
            return null;

        java.io.File file = new java.io.File(filePath);

        filePath = file.getAbsolutePath();

        return filePath;
    }


    public static void deleteFile(String path) {
        File file = new File(path);
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            File[] files = file.listFiles();


            for (File f : files) {
                deleteFile(f.getAbsolutePath());
            }
            file.delete();
        } else {
            file.delete();
        }
    }
}