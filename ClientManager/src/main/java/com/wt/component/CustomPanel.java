package com.wt.component;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName CustomPanel
 * @Description TODO
 * @Author UnKnW
 * @Date 2020/12/29 14:23
 **/
public class CustomPanel extends JPanel {
    private final String path;

    public CustomPanel(String path) {
        this.path = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image bg = null;
        try {
            bg = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }
}
