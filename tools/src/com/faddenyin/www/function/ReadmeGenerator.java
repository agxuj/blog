package com.faddenyin.www.function;

import com.faddenyin.www.bean.Article;
import com.faddenyin.www.command.Sort;
import com.faddenyin.www.config.C;
import com.faddenyin.www.utils.DateUtils;
import com.faddenyin.www.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.*;

public class ReadmeGenerator {


    List<Article> articles = new ArrayList<>();
    Gson gson = new GsonBuilder().setDateFormat(DateUtils.dateStringType2).create();

    public void action() throws Exception {
        File file = new File(C.data);
        readDirectory(file);
        Collections.sort(articles, new ArticleComparator());
        createIndexPage();
    }

    private void readDirectory(File file) throws Exception {

        File[] list = file.listFiles();
        for (File f : list) {

            String[] strs = f.getName().split("_");
            if (f.isDirectory() && strs.length > 1) {
                if (strs.length > 3) {
                    continue;
                }

                Article article = gson.fromJson(FileUtils.readFile(new File(f.getPath(),"info.json").getPath()),Article.class);
                article.number = strs[0];
                article.title = strs[1];

                this.articles.add(article);
            }

            if (f.isDirectory()) {
                readDirectory(f);
            }
        }
    }

    private void createIndexPage() throws Exception {
        //Collections.sort(articles, new ArticleComparator());
        //* read template file
        String templateStr = FileUtils.readFile(C.template_readme_tp_md);

        //* read item file
        String itemStr = FileUtils.readFile(C.template_readme_item_tp_md);

        //* replace index item {{title}} {{datetime}} {{id}} and add to itemsStr
        StringBuffer sb = new StringBuffer();
        for (Article i : articles) {
            String id = i.id;
            String title = i.title;
            String datetime = DateUtils.dateToString(i.datetime, DateUtils.dateStringType2);

            sb.append(itemStr.replace("{{title}}", title).replace("{{id}}", id).replace("{{datetime}}", datetime));
        }
        String itemsStr = sb.toString();

        // * replace index {{content}} to itemsStr;
        templateStr = templateStr.replace("{{content}}", itemsStr);

        // * write indexIndexTemplateStr to /index.html
        FileUtils.writeFile(C.README_MD, templateStr);
    }

    private static class ArticleComparator implements Comparator<Article> {

        @Override
        public int compare(Article a1, Article a2) {
            long t1 = Integer.valueOf(a1.number);
            long t2 = Integer.valueOf(a2.number);

            return (int) (t1 - t2);
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }


}
