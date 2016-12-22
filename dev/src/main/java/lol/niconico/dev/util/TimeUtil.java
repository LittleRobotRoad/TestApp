package lol.niconico.dev.util;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/14.
 */
public class TimeUtil {
    public static String getStandardDate(String strs) {
        StringBuffer sb = new StringBuffer();

        long t = getStringToDate(strs);

        long time = System.currentTimeMillis() - t;
        long mill = time / 1000;//秒前

        long minute = time / 60 / 1000;// 分钟前

        long hour = time / 60 / 60 / 1000;// 小时

        long day = time / 24 / 60 / 60 / 1000;// 天前


        if (day > 0) {
            Date d = new Date(t);
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
            return sf.format(d);
//            sb.append(day + "天");
        } else if (hour > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();

    }


    public static String getCurrentTime() {
        return getDateToString(System.currentTimeMillis());
    }

    public static String getCurrentDate() {
        Date d = new Date(System.currentTimeMillis());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(String times) {
        Long time = Long.valueOf(times);
        return getDateToString(time);
    }

    /**
     * Coin专用
     */
    public static void countDown(int time, final TextView v) {
        v.setEnabled(false);
        Runnable runnable = new SMSRunable(time, v);
        v.postDelayed(runnable, 1000);
    }


    private static class ProductRunbale implements Runnable {

        int time;
        TextView v;

        public ProductRunbale(int time, TextView v) {
            this.time = time;
            this.v = v;
        }

        @Override
        public void run() {
            time--;
            if (time <= 0) {
                v.setText("等待开奖");
            } else {
                v.setText(getStringBuilder(time));
                v.postDelayed(this, 1000);
            }
        }

    }
    public static class ProductExRunbale implements Runnable {

        int time;
        TextView v;
        CountDownListener l;

        public ProductExRunbale(int time,CountDownListener l, TextView v) {
            this.time = time;
            this.v = v;
            this.l=l;
        }

        @Override
        public void run() {
            time--;
            if (time <= 0) {
                l.countdownEnd(v);
            } else {
                v.setText(getStringBuilder(time));
                v.postDelayed(this, 1000);
            }
        }

    }

    /**
     * Coin专用 product
     */
    public static void countDownProduct(String nowData, String endData, final TextView v) {
        long now = getStringToDate(nowData);
        final long end = getStringToDate(endData);
        final long phone = System.currentTimeMillis();
        if (phone > now) {
            now = phone;
        }
        final int difference = (int) ((end - now) / 1000);
        if (difference < 1) {
            v.setText("等待开奖");
        }

        Runnable runnable = new ProductRunbale(difference, v);
        v.postDelayed(runnable, 1000);
    }

    public interface CountDownListener {
        void countdownEnd(TextView v);
    }

    /**
     * Coin专用 product
     */
    public static void countDownProduct(String nowData, String endData,TextView v,CountDownListener l) {
        long now = getStringToDate(nowData);
        final long end = getStringToDate(endData);
        final long phone = System.currentTimeMillis();
        if (phone > now) {
            now = phone;
        }
        final int difference = (int) ((end - now) / 1000);
        if (difference < 1) {
            l.countdownEnd(v);
        }
        Runnable runnable = new ProductExRunbale(difference, l,v);
        v.postDelayed(runnable, 1000);
    }


    private static class SMSRunable implements Runnable {

        int time;
        TextView v;

        public SMSRunable(int time, TextView v) {
            this.time = time;
            this.v = v;
        }

        @Override
        public void run() {
            time--;
            if (time <= 0) {
                v.setText("重新发送");
                v.setEnabled(true);
            } else {
                v.setText(time + "s后重新获取");
                v.postDelayed(this, 1000);
            }
        }
    }

    private static String getStringBuilder(int time) {
        long day = time / 24 / 60 / 60;

        long minute = time / 60;

        long hour = time / 60 / 60;

        long sec = time % 60;
        StringBuilder sb = new StringBuilder();
        if (hour > 0) {
            if (hour > 9) {
                sb.append(hour);
            } else {
                sb.append("0").append(hour);
            }
        } else {
            sb.append("00");
        }
        sb.append(":");
        if (minute > 0) {
            if (minute > 9) {
                sb.append(minute);
            } else {
                sb.append("0").append(minute);
            }
        } else {
            sb.append("00");
        }
        sb.append(":");
        if (sec > 9) {
            sb.append(sec);
        } else {
            sb.append("0").append(sec);
        }
        return sb.toString();
    }

}
