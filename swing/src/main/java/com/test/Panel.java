package com.test;

import javax.swing.*;

/**
 * @author SitaNya
 * @date 2019-08-13
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class Panel {
    public Panel() {

    }

    public JPanel init(JFrame jFrame, String title, int x, int y, int width, int height) {
        JPanel jp = new JPanel();
        jp.setBorder(BorderFactory.createTitledBorder(title));
        jp.setBounds(x, y, width, height);
        jFrame.add(jp);
        return jp;
    }
}
