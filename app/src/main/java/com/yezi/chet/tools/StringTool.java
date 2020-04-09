package com.yezi.chet.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 * @author yezi
 */
public class StringTool {

    /**
     * 检测用户名是否符合规则
     * @param account 用户名
     * @return 符合则返回true
     */
    public static boolean checkAccount(String account){
        String rule = "^[a-zA-Z0-9]{5,16}$";
        Pattern pattern = Pattern.compile(rule);
        Matcher m = pattern.matcher(account);
        return m.matches();
    }

    /**
     * 检测密码是否符合规则
     * @param passworld 密码
     * @return 符合则返回true
     */
    public static boolean checkPassworld(String passworld){
        String rule = "^[a-zA-Z0-9]{5,16}$";
        Pattern pattern = Pattern.compile(rule);
        Matcher m = pattern.matcher(passworld);
        return m.matches();
    }

}
