package com.test;

import javax.swing.*;

import static com.test.Tools.getLength;

/**
 * @author SitaNya
 * @date 2019-08-13
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class Lable {
    public void createLable(JPanel jPanel, String text) {
        JLabel jLabel = new JLabel(text);
        jPanel.add(jLabel);
    }
}
