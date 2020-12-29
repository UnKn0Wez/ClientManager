package com.wt.component;

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
    private String fileName;
    public void setFileName(String fileName){
        this.fileName=fileName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage();
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),icon.getImageObserver());
    }
}
