package com.test;

import javax.swing.*;
import java.awt.*;

/**
 * @author SitaNya
 * @date 2019-08-13
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class Tools extends MakeText {
    static JCheckBox jCheckBox;

    int x = 10;
    int y = 10;

    public Tools() {
    }

    void init() {
        JFrame jFrame = new Frame("个性化配置", 1200, 965).init();


//        JPanel botSwitch = new Panel().init(jFrame, "开关", 510, 10, 400, 200);
//        botSwitch.setLayout(new GridLayout(0, 1));

        initText(jFrame);
//        save(jFrame);
    }

    private void initText(JFrame jFrame) {
        JPanel infoPanel = new Panel().init(jFrame, "骰娘信息", 0, 0, 400, 70);
        infoPanel.setLayout(new GridLayout(0, 2));
        info(infoPanel);
        jFrame.add(infoPanel);

        JPanel botInfoPanel = new Panel().init(jFrame, "机器人基本信息", 0, 70, 400, 210);
        botInfoPanel.setLayout(new GridLayout(0, 2));
        botInfo(botInfoPanel);

        JPanel cardInfoPanel = new Panel().init(jFrame, "车卡信息", 0, 280, 400, 140);
        cardInfoPanel.setLayout(new GridLayout(0, 2));
        cardInfo(cardInfoPanel);

        JPanel propInfoPanel = new Panel().init(jFrame, "人物卡信息", 0, 420, 400, 150);
        propInfoPanel.setLayout(new GridLayout(0, 2));
        propInfo(propInfoPanel);

        JPanel teamPanel = new Panel().init(jFrame, "小队信息", 0, 570, 400, 100);
        teamPanel.setLayout(new GridLayout(0, 2));
        team(teamPanel);

        JPanel dndInfoPanel = new Panel().init(jFrame, "dnd信息", 0, 670, 400, 100);
        dndInfoPanel.setLayout(new GridLayout(0, 2));
        dndInfo(dndInfoPanel);


        JPanel logInfoPanel = new Panel().init(jFrame, "日志信息", 400, 0, 400, 280);
        logInfoPanel.setLayout(new GridLayout(0, 2));
        logInfo(logInfoPanel);

        JPanel sanCheckPanel = new Panel().init(jFrame, "理智检定信息", 400, 280, 400, 210);
        sanCheckPanel.setLayout(new GridLayout(0, 2));
        sanCheck(sanCheckPanel);

        JPanel otherPanel = new Panel().init(jFrame, "其余常用信息", 400, 490, 400, 125);
        otherPanel.setLayout(new GridLayout(0, 2));
        other(otherPanel);

        JPanel antagonizeInfoPanel = new Panel().init(jFrame, "对抗信息", 400, 615, 400, 175);
        antagonizeInfoPanel.setLayout(new GridLayout(0, 2));
        antagonizeInfo(antagonizeInfoPanel);

        JPanel errorInfoPanel = new Panel().init(jFrame, "其余报错信息", 400, 790, 400, 175);
        errorInfoPanel.setLayout(new GridLayout(0, 2));
        errorInfo(errorInfoPanel);

        JPanel diceReturnPanel = new Panel().init(jFrame, "骰点返回信息", 800, 0, 400, 965);
        diceReturnPanel.setLayout(new GridLayout(0, 2));
        diceReturn(diceReturnPanel);

    }

    private void save(JFrame jFrame) {
        Button button = new Button();
        button.createButton(jFrame, "测试按钮", x * 100 - getLength("测试按钮") / 2, y * 100 - 50);
    }


    private void initRadioButton(JPanel jPanel) {
        RadioButton radioButton = new RadioButton();
        jCheckBox = radioButton.createRadioButton(jPanel, "禁言退群", x, y + 400);
    }

    public static int getLength(String text) {
        return text.length() * 12;
    }
}
