/*
 * All rights Reserved, Designed By baowei
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.demo.stage2.chapter11;

/**
 * @author yan.zhang
 * @date 2019/11/24 10:57
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 当有新内容写入，当没有新内容不会执行写入操作，类比保存Ctrl + s
 */
public class BalkingData {
    private final String fileName;
    private String content;
    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    /**
     * changed()后必须save()否则会丢失数据
     */
    public synchronized void changed(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    /**
     * 轮询检查是否文件发生变化
     */
    public synchronized void save() throws IOException {
        if (!changed) {
            //未发生改变
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + "call do save, content: " + content);
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
