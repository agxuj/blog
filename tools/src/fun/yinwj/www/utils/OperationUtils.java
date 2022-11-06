package fun.yinwj.www.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OperationUtils {

    /**
     * reference: https://blog.csdn.net/zp357252539/article/details/77896257
     *
     * @param url
     */
    public static void openURL(String url) {
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                // 创建一个URI实例
                java.net.URI uri = java.net.URI.create(url);
                // 获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    // 获取系统默认浏览器打开链接
                    dp.browse(uri);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void exec(String cmd) throws Exception {
        System.out.println("java cmd: " + cmd);
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader strCon = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = strCon.readLine()) != null) {
            System.out.println("java return: " + line);
        }
    }
}
