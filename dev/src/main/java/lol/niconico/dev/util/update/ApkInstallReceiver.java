package lol.niconico.dev.util.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import lol.niconico.dev.util.SpUtil;
import lol.niconico.dev.util.ToastUtil;


public class ApkInstallReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            long id = SpUtil.getInstance().getLong("downloadId", -1L);
            if (downloadApkId == id) {
                ApkUpdateUtils.startInstall(context);
            }else{
                ToastUtil.show_short("更新apk: DownloadManager ID 错误");
            }
        }
    }
}
