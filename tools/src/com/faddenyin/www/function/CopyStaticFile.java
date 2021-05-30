package com.faddenyin.www.function;

import com.faddenyin.www.config.C;
import com.faddenyin.www.utils.FileUtils;

import java.io.File;

public class CopyStaticFile {
    public void action() throws Exception {
        FileUtils.copyDir(C.template_css, C.css);
        FileUtils.copyDir(C.template_js, C.js);

    }

}
