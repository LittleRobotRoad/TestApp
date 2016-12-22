package com.ycgwl.carrier.app;

public class Constants {

    public static final String ERROR_PATH = "/srr/error//";
    public static final String PHOTO_PATH = "/srr/photo/";

    public static final int NETWORK_STATE_IDLE = 0;
    public static final int NETWORK_STATE_WIFI = 1;
    public static final int NETWORK_STATE_CMNET = 2;
    public static final int NETWORK_STATE_CMWAP = 3;
    public static final int NETWORK_STATE_CTWAP = 4;
    public static int CURRENT_NETWORK_STATE_TYPE = NETWORK_STATE_IDLE;

    public static final String INTENT_TITLE = "yclibytrack.intent.title";
    public static final String INTENT_ONE = "yclibytrack.intent.one";
    public static final String INTENT_TWO = "yclibytrack.intent.two";
    public static final String INTENT_THREE = "yclibytrack.intent.three";
    public static final String INTENT_URL = "yclibytrack.intent.url";
    public static final String SERVICE_BIND = "yclibytrack.intent.service.SERVICE_BIND";
    public static final String SERVICE_START = "yclibytrack.intent.service.SERVICE_START";

    public static final class SP {
        public static final String IS_PASSWORD = "is_password";
        public static final String IS_AUTO_LOGIN = "is_auto_login";
        public static final String USER = "user_name";
        public static final String PASSWORD = "user_password";
    }

    public static final class RECEIVER {
        public static final String MESSAGE = "receiver_message";
        public static final String INTENT_ALREADY = "yclibytrack.intent.already.message";
        public static final String INTENT_MESSAGE = "yclibytrack.intent.message";
    }
}