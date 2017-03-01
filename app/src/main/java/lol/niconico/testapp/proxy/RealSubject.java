package lol.niconico.testapp.proxy;

/**
 * Created by ZhangQianqian on 2017/2/15  14:11.
 * email 415692689@qq.com
 */

public class RealSubject implements  Subject{
    @Override
    public void test() {
        System.out.println("test");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}
