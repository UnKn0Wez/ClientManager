package com.wt.component;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @ClassName RoundButton
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 15:03
 **/
public class RoundBorder implements Border {
    public int radius;
    public Color color;
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1,1,1,1);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,int width, int height) {
        g.setColor(color);
        g.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, radius, radius);
    }
    public RoundBorder(int radius,Color color){
        this.radius=radius;
        this.color=color;
    }
}
