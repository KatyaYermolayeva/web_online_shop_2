package Controller.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorMessageUtil {
    public static String SERVER_ERROR = "server_error";
    public static String USER_ALREADY_EXISTS = "user_already_exists";
    public static String ORDER_NOT_FOUND = "order_not_found";
    public static String WRONG_LOGIN_CREDENTIALS = "wrong_login_credentials";
    public static String PAGE_NOT_FOUND = "page_not_found";

    public static String getMessage(String s) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream("C:\\учеба\\6 сем\\Web\\Лабы\\lab5\\src\\Controller\\Utility\\messages.properties");
            prop.load(in);
        }
        catch (IOException e) {
            e.printStackTrace();
            return "Unknown error";
        }
        return prop.getProperty(s);
    }
}
