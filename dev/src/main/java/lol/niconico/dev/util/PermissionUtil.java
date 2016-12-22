package lol.niconico.dev.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


/**
 * Created by ZhangQianqian on 2016/7/20  15:31.
 * email 415692689@qq.com
 */
public class PermissionUtil {

    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @TargetApi(value = Build.VERSION_CODES.M)
    public static void requstPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity,
                permission)
                == PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission)) {
                Toast.makeText(activity, "扫码需要相机权限，请授予", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }
    }


    @TargetApi(value = Build.VERSION_CODES.M)
    public static void requestPermissionNormal(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity,
                permission)
                == PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    permission)) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }
    }

    @TargetApi(value = Build.VERSION_CODES.M)
    public static void requestPermissionNormal(Activity activity) {

        String[] permission = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        if (ContextCompat.checkSelfPermission(activity, permission[1]) == PackageManager.PERMISSION_DENIED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission[1])) {

                ActivityCompat.requestPermissions(activity, permission, MY_PERMISSIONS_REQUEST_CALL_PHONE);

            } else {

                ActivityCompat.requestPermissions(activity, permission, MY_PERMISSIONS_REQUEST_CALL_PHONE);

            }
        }
    }
}
