package com.wt.thread;

import lombok.SneakyThrows;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

/**
 * @ClassName XiWordThread
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 14:19
 **/
public class XiWordThread implements Runnable {
    public JLabel xiLabel;
    public void setXiLabel(JLabel xiLabel){
        this.xiLabel=xiLabel;
    }
    @SneakyThrows
    @Override
    public void run() {
        File file1 = new File("xiWord.txt");
        while (true) {
            Scanner sc = new Scanner(file1);
            while (sc.hasNextLine()) {
                xiLabel.setText(sc.nextLine());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
