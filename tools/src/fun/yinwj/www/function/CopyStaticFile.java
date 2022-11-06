package fun.yinwj.www.function;

import fun.yinwj.www.config.C;
import fun.yinwj.www.utils.FileUtils;

public class CopyStaticFile {
    public void action() throws Exception {
        FileUtils.copyDir(C.template_css, C.css);
        FileUtils.copyDir(C.template_js, C.js);

    }

}
