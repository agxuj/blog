package com.faddenyin.www.command;

public class Help {
    public void print() {
        System.out.println("Operation(Command) include: add, delete, list, update");
        System.out.println("\t  add   \tadd essay");
        System.out.println("\t  delete  \tdelete essay");
        System.out.println("\t  list   \tlist essay,message include id, title, datetime");
        System.out.println("\t  update   \tupdate essay,change title and datetime by id,you can only change title or datetime");
        System.out.println();
        System.out.println("Parameter include id, t, dt");
        System.out.println("\t  id  \t sign witch essay it is");
        System.out.println("\t  t  \t title of essay");
        System.out.println("\t  dt  \t datetime of essay,it should be format as yyyyMMdd_HHmmss. Such as 20180420_142500");
        System.out.println("\t  tag  \t tag in navigator");
        System.out.println();
        System.out.println("Example:");
        System.out.println("\t  java  -jar  MdTools.jar  add  t  \"Hello word\"  dt  \"20101010_101000\" tag \"life\" \t");
        System.out.println("\t      \tyou don't need to decide datetime,just remove  \"dt  {datetime}\"");
        System.out.println("\t  java  -jar  MdTools.jar  delete  id  \"20101010_101000\"\t");
        System.out.println("\t  java  -jar  MdTools.jar  update  id  \"20101010_101000\"  t  \"Hello word\"  dt  \"20101010_101000\" tag \"life\" \t");
        System.out.println("\t      \tyou can only update title or datetime or tag");

    }
}
