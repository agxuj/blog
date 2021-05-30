package com.faddenyin.www.command;

import com.faddenyin.www.bean.Article;
import com.faddenyin.www.config.C;
import com.faddenyin.www.utils.DateUtils;
import com.faddenyin.www.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.*;

public class Sort {

    List<Article> articles = new ArrayList<>();
    Gson gson = new GsonBuilder().setDateFormat(DateUtils.dateStringType2).create();

    public void action() throws Exception {
        System.out.println("sort");
        File[] files = new File(C.data).listFiles();

        for (File file : files) {
            String name = file.getName();
            String[] strs = name.split("_");

            if (strs.length < 2) {
                continue;
            }

            Article article = gson.fromJson(FileUtils.readFile(new File(file.getPath(),"info.json").getPath()),Article.class);
            article.number = strs[0];
            article.title = strs[1];
            articles.add(article);
        }

        Collections.sort(articles, new ArticleComparator());

        int i = 0;
        for (Article article : articles) {
            i++;
            String number = i < 10 ? ("0"+i +"00") : i +"00";

            File oldFile = new File(C.data, article.number + "_" + article.title);
            File newFile = new File(C.data, number + "_" + article.title);

            System.out.println(oldFile.getAbsolutePath());

            oldFile.renameTo(newFile);
        }

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
