package com.faddenyin.www.config;

import com.faddenyin.www.utils.FileUtils;

import java.io.File;

public class C {


    public final static String rootPath;

    static {
        // * get .jar current path
        String path = FileUtils.getSrcPath();
        if (path == null || path.trim().isEmpty()) {
            path = "/Users/faddenyin/Run/t14z.github.io/tools";
        }
        rootPath = new File(path).getParent();
    }

    public final static String article = rootPath + "/article";
    public final static String css = rootPath + "/css";
    public final static String js = rootPath + "/js";
    public final static String README_MD = rootPath + "/README.md";

    public final static String data = rootPath + "/data";

    public final static String template = rootPath + "/template";
    public final static String template_css = rootPath + "/template/css";
    public final static String template_js = rootPath + "/template/js";
    public final static String template_readme_tp_md = C.rootPath + "/template/README.tp.md";
    public final static String template_readme_item_tp_md = C.rootPath + "/template/README.item.tp.md";
    public final static String template_article_readme_tp_md = C.rootPath + "/template/article/readme.tp.md";


}

