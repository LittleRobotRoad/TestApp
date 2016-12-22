package lol.niconico.dev.util.update;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import lol.niconico.dev.util.SpUtil;
import lol.niconico.dev.util.ToastUtil;


public class ApkInstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            long id = SpUtil.getInstance().getLong("downloadId", -1L);
            if (downloadApkId == id) {
                installApk(context, downloadApkId);
            }
        }
    }

    private static void installApk(Context context, long downloadApkId) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP_MR1){
            ToastUtil.show_short("请打开通知栏安装");
            return;
        }
        DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Intent install = new Intent(Intent.ACTION_VIEW);
        Uri downloadFileUri = dManager.getUriForDownloadedFile(downloadApkId);
        if (downloadFileUri != null) {
            Log.d("DownloadManager", downloadFileUri.toString());
            install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(install);
        } else {
            Log.e("DownloadManager", "下载失败");
        }
    }
}
