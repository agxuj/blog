package com.faddenyin.www;

import com.faddenyin.www.bean.Parameter;
import com.faddenyin.www.command.*;

/**
 * <a href="https://blog.csdn.net/fesdgasdgasdg/article/details/73556109">intellij idea 导出可执行jar</a>
 * <a href="https://www.cnblogs.com/chenchen-tester/p/7992357.html">idea打包含第三方依赖的jar包</a>
 */

public class Main {
    public static void main(String args[]) throws Exception {

        if (args.length < 1) {
            System.out.println("Command is empty");
            return;
        }

        Parameter parameter = new Parameter(args);

        if ("build".equals(parameter.cmd)) {
            new Build().action();
        } else if ("sort".equals(parameter.cmd)) {
            new Sort().action();
        } else if ("help".equals(parameter.cmd)) {
            new Help().print();
        } else {
            System.out.println("Unknown command");
        }

    }

}
