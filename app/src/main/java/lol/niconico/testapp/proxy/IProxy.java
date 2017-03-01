package lol.niconico.testapp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ZhangQianqian on 2017/2/15  14:06.
 * email 415692689@qq.com
 */

public class IProxy implements InvocationHandler {

    //　这个就是我们要代理的真实对象
    private Object subject;

    public Object bind(Object subject) {
        this.subject = subject;
        return Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        //　　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before rent house");

        System.out.println("Method:" + method);

        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");

        return null;
    }
}

