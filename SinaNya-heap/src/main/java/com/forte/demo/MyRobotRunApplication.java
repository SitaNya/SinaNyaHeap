package com.forte.demo;

import com.forte.qqrobot.anno.depend.AllBeans;
import com.forte.qqrobot.anno.depend.Beans;
import com.forte.qqrobot.beans.messages.result.GroupList;
import com.forte.qqrobot.beans.types.CacheTypes;
import com.forte.qqrobot.component.forhttpapi.HttpApp;
import com.forte.qqrobot.component.forhttpapi.HttpApplication;
import com.forte.qqrobot.component.forhttpapi.HttpConfiguration;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;

/**
 * 这个是整个机器人的启动器，也是mian函数所在的位置。
 * 此demo使用HTTP API组件进行示例。<br>
 * 1、实现{@link com.forte.qqrobot.component.forhttpapi.HttpApp} 接口 ,
 * 并实现接口中的两个方法:
 *  {@link #before(HttpConfiguration)}  <br>
 *  {@link #after(CQCodeUtil, MsgSender)}   <br>
 *  其中，before方法是对参数的配置，通过方法中传递的{@link HttpConfiguration}进行配置    <br>
 *  after方法是在启动成功后，提供了一个回调函数以供测试    <br>
 *
 *  configuration中的配置，每个不同的组件之间存在一些差异，也存在一些相似之处。
 *
 *
 * @author ForteScarlet <[email]ForteScarlet@163.com>
 * @since JDK1.8
 **/
@AllBeans(value = "com.forte.demo.robot.timetask", beans = @Beans(allDepend = true, single = false))
public class MyRobotRunApplication implements HttpApp {

    /**
     * main函数，用于执行程序
     */
    public static void main(String[] args) {
        //此处新建一个我们实现了HttpApp接口的启动器对象
        MyRobotRunApplication myRobotRunApplication = new MyRobotRunApplication();
        //新建一个Http所提供的启动器对象
        HttpApplication httpApplication = new HttpApplication();
        /*
            每个组件所提供的启动器接口与启动器类都是不一样的，但是大部分来讲，他们的名称有着共同点
            例如HTTP API组件的启动器接口叫做 HttpApp, 启动器类叫做HttpApplication
            而Lemoc组件的启动器接口叫做 LemocApp, 启动器类叫做LemocApplication
         */

        //调用Http启动器，将我们实现好的启动器对象实例放入
        //我们实现的启动器对象就像是一把钥匙，用来开启机器人服务
        httpApplication.run(myRobotRunApplication);

        /*
            以上就是流程最短的启动过程。
            每个启动器中还存在一些API，例如Close()、getDependGetter()之类的，更加详细的功能请查阅文档或者入群咨询
         */

    }

    /**
     * 启动前的配置
     * @param configuration 配置对象
     */
    @Override
    public void before(HttpConfiguration configuration) {
        configuration.setScannerPackage("com.forte.demo.robot.timetask");
        configuration.setIp("62.234.90.130");
        configuration.setServerPath("/coolq/demo.php");
        configuration.setJavaPort(60079);
        configuration.setServerPort(10004);
    }

    /**
     * 启动成功之后，通过缓存获取全部的群列表
     *
     * 缓存功能由核心版本1.3-BETA后开始支持，你也可以选择不使用缓存
     * 关于新加入的缓存转化详情请查阅文档
     *
     * @param cqCodeUtil    CQCodeUtil工具类
     * @param sender        送信器
     */
    @Override
    public void after(CQCodeUtil cqCodeUtil, MsgSender sender) {

    }
}
