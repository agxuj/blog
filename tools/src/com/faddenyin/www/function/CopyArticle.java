package com.faddenyin.www.function;

import com.faddenyin.www.bean.Article;
import com.faddenyin.www.config.C;
import com.faddenyin.www.utils.DateUtils;
import com.faddenyin.www.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;

public class CopyArticle {
    public String template;
    Gson gson = new GsonBuilder().setDateFormat(DateUtils.dateStringType2).create();

    public CopyArticle() throws Exception {
        template = FileUtils.readFile(C.template_article_readme_tp_md);
    }

    public void action() throws Exception {
        File file = new File(C.data);
        copyDirectory(file);
    }

    private void copyDirectory(File file) throws Exception {
        File[] list = file.listFiles();

        for (File f : list) {
            String[] strs = f.getName().split("_");
            if (f.isDirectory()&&strs.length > 1) {
                if (strs.length > 2 && "ignore".equals(strs[2])) {
                    continue;
                }

                Article article = gson.fromJson(FileUtils.readFile(new File(f.getPath(),"info.json").getPath()), Article.class);
                article.number = strs[0];
                article.title = strs[1];
                copy(f.getAbsolutePath(), C.article + "/"+article.id, article.title);
            }

            if(f.isDirectory()){
                copyDirectory(f);
            }

        }
    }

    //复制方法
    public void copy(String src, String des, String title) throws Exception {
        File sourceFile = new File(src);

        File[] fs = sourceFile.listFiles();
        File targetFile = new File(des);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //遍历文件及文件夹
        for (File f : fs) {
            if (f.isFile()) {
                //文件
                String name = f.getName();
                String[] strs = name.split("\\.");
                if (strs.length > 1 && "md".equals(strs[strs.length - 1])) {
                    copyMd(f.getPath(), des + "/" + f.getName(), title);
                } else {
                    FileUtils.copyFile(f.getPath(), des + "/" + f.getName()); //调用文件拷贝的方法
                }
            } else if (f.isDirectory()) {
                //文件夹
                copy(f.getPath(), des + "/" + f.getName(), title);//继续调用复制方法      递归的地方,自己调用自己的方法,就可以复制文件夹的文件夹了
            }
        }
    }

    public void copyMd(String fromFile, String toFile, String title) throws IOException {
        String content = FileUtils.readFile(fromFile);
        String result = template.replace("{{title}}", title).replace("{{content}}", content);
        FileUtils.writeFile(toFile, result);
    }
}
