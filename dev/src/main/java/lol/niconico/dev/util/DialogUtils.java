package lol.niconico.dev.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by ZhangQianqian on 2016/7/28  09:32.
 * email 415692689@qq.com
 * 默认dialgo调用的utils，可以简单调用提示类dialgo 优化代码
 */
public class DialogUtils {

    private AlertDialog dialog;
    private static DialogUtils mInstance;


//    public static DialogUtils getInstance() {
//        if (mInstance == null) {
//            synchronized (DialogUtils.class) {
//                if (mInstance == null) {
//                    mInstance = new DialogUtils();
//                }
//            }
//        }
//        return mInstance;
//    }

    public static synchronized DialogUtils getInstance() {
        return mInstance == null ? new DialogUtils() : mInstance;
    }


    public void showList(Context context, String title, String[] content, String button) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(content, null)
                .setNegativeButton(button, null);
        dialog = builder.show();
    }

    public void showList(Context context, String title, String[] content, DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(content, listener);
        dialog = builder.show();
    }

    public void showDialog(Context context, String title, String content, String button, DialogInterface.OnClickListener listener, DialogInterface.OnDismissListener dismisslistener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setNegativeButton(button, listener)
                .setOnDismissListener(dismisslistener)
                .setMessage(content);
        dialog = builder.show();
    }

    public void showDialog(Context context, String title, String content, String button, DialogInterface.OnClickListener listener) {
        showDialog(context, title, content, button, listener, null);
    }

    public static void showDialog(Context context, String content) {
        showDialog(context, "提示", content, "确定");
    }

    public static void showDialog(Context context, String title, String content, String button) {
        getInstance().showDialog(context, title, content, button, null);
    }
}
