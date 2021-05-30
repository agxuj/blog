package com.faddenyin.www.function;

import com.faddenyin.www.config.C;
import com.faddenyin.www.utils.FileUtils;

import java.io.File;

public class DeleteOut {

    public void action() {
        FileUtils.deleteFile(C.article);
        FileUtils.deleteFile(C.css);
        FileUtils.deleteFile(C.js);
        FileUtils.deleteFile(C.README_MD);
    }


}
