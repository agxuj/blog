package com.faddenyin.www.utils;


import java.io.File;

public abstract class FileWatcher {
    protected String filename;
    private long delay = 60000L;
    private File file;
    private long lastModify = 0L;
    private boolean warnedAlready = false;
    private boolean interrupted = false;

    protected FileWatcher(String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    protected abstract void doOnChange();

    protected void checkAndConfigure() {
        boolean fileExists;
        try {
            fileExists = this.file.exists();
        } catch (SecurityException var4) {
            this.interrupted = true;
            return;
        }

        if (fileExists) {
            long l = this.file.lastModified();
            if (l > this.lastModify) {
                this.lastModify = l;
                this.doOnChange();
                this.warnedAlready = false;
            }
        } else if (!this.warnedAlready) {
            this.warnedAlready = true;
        }
    }

    public void start() {
        for (; !interrupted; this.checkAndConfigure()) {
            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException e) {
            }
        }
    }
}
