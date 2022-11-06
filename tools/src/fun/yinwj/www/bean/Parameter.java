package fun.yinwj.www.bean;

import fun.yinwj.www.utils.DateUtils;
import fun.yinwj.www.utils.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class Parameter {

    public String cmd;
    public String t;
    public String dt;
    public String id;
    public String tag;

    public Parameter(String[] args) {

        if (args.length < 1) {
            throw new RuntimeException("no command");
        }

        cmd = args[0];

        Map<String, String> map = new HashMap<>();

        for (int i = 3; i < args.length + 1; i += 2) {
            map.put(args[i - 2], args[i - 1]);
        }

        t = map.get("t");
        dt = map.get("dt");
        id = map.get("id");
        tag = map.get("tag");

        if (!TextUtils.isEmpty(dt)) {
            dt = DateUtils.dateToString(DateUtils.stringToDate(dt, "yyyyMMdd_HHmmss"), "yyyyMMdd_HHmmss");
        }
    }

}
