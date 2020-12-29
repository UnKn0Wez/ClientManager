package com.wt.component;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @ClassName RoundButton
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 15:03
 **/
public class RoundButton implements Border {

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
        g.setColor(Color.black);
        g.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 10, 10);
    }
}
