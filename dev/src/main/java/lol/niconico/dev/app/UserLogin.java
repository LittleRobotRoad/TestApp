package lol.niconico.dev.app;


import lol.niconico.dev.model.viewmodel.User;

/**
 * Created by ZhangQianqian on 2016/9/1  13:31.
 * email 415692689@qq.com
 */
public class UserLogin {

    private static UserLogin mInstance;
    private User mUser;

    public static synchronized UserLogin getInstance() {
        if (mInstance == null) {
            mInstance = new UserLogin();
        }
        return mInstance;
    }

    public User getUser() {
        return this.mUser;
    }

    public void setUser(User user) {
        this.mUser = user;
    }
}
