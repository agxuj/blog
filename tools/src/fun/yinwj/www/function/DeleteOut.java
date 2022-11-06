package fun.yinwj.www.function;

import fun.yinwj.www.config.C;
import fun.yinwj.www.utils.FileUtils;

public class DeleteOut {

    public void action() {
        FileUtils.deleteFile(C.article);
        FileUtils.deleteFile(C.css);
        FileUtils.deleteFile(C.js);
        FileUtils.deleteFile(C.README_MD);
    }


}
