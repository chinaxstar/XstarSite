package cn.xstar.site;

public class Const {
    public static final String SESSION_USER = "cn.xstar.site.user";


    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = -1;
    public static final int USER_EXSIT_CODE = -1002;

    public static String defaultMsg(int code) {
        String msg = "服务器处理错误！";
        switch (code) {
            case USER_EXSIT_CODE:
                msg = "用户名已存在！";
        }
        return msg;
    }
}
