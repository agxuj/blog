package fun.yinwj.www;

import fun.yinwj.www.bean.Parameter;
import fun.yinwj.www.command.Build;
import fun.yinwj.www.command.Help;
import fun.yinwj.www.command.Sort;

/**
 * <a href="https://blog.csdn.net/fesdgasdgasdg/article/details/73556109">intellij idea 导出可执行jar</a>
 * <a href="https://www.cnblogs.com/chenchen-tester/p/7992357.html">idea打包含第三方依赖的jar包</a>
 *
 * -Dfile.encoding=UTF-8
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
