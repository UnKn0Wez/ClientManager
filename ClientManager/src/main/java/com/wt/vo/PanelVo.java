package com.wt.vo;

import javax.swing.*;

/**
 * @ClassName PanelVo
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 10:36
 **/
public class PanelVo {
    private static JPanel requestContentPanel;
    private static JPanel requestBodyPanel;
    private static JPanel orderContentPanel;
    private static JPanel orderBodyPanel;

    public static JPanel getorderContentPanel() {
        return orderContentPanel;
    }

    public static void setorderContentPanel(JPanel orderContentPanel) {
        PanelVo.orderContentPanel = orderContentPanel;
    }
    public static JPanel getorderBodyPanel() {
        return requestBodyPanel;
    }

    public static void setorderBodyPanel(JPanel orderBodyPanel) {
        PanelVo.orderBodyPanel = orderBodyPanel;
    }

    public static JPanel getrequestContentPanel() {
        return requestContentPanel;
    }

    public static void setrequestContentPanel(JPanel requestContentPanel) {
        PanelVo.requestContentPanel = requestContentPanel;
    }
    public static JPanel getrequestBodyPanel() {
        return requestBodyPanel;
    }

    public static void setrequestBodyPanel(JPanel requestBodyPanel) {
        PanelVo.requestBodyPanel = requestBodyPanel;
    }
}
