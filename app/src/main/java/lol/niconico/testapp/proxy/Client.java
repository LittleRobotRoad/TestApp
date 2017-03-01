package lol.niconico.testapp.proxy;

/**
 * Created by ZhangQianqian on 2017/2/15  14:15.
 * email 415692689@qq.com
 */

public class Client
{
    public static void main(String[] args)
    {
        //    我们要代理的真实对象
        Subject realSubject = new RealSubject();

        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        Subject subject = (Subject) new IProxy().bind(realSubject);

        subject.test();
        subject.hello("world");
    }
}