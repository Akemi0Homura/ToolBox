package com.Akemi0Homura;

/**
 * 负责场景需求的数据格式验证。<br>
 * 比如说验证电子邮箱、手机号等。
 * @author Akemi0Homura
 */
public class RegVal {
    //邮箱格式，必须xxx@xxx才能通过
    private final static String EMAIL_FORMAT="^[^\\s@]+@[^\\s@]+$";

    /**
     * 判断字符串是否为纯？类型<br>
     * 1.纯数字(负数和小数不算)。2.纯小写字母。3.纯大写字母。
     * @param str 需要判断的字符串
     * @param type 判断的属性类型
     * @return 符合规则返回true
     */
    public static boolean sheerString(String str,int type) {
        if(StringTool.NoNull(str))return false;

        //包括0123456789这十种数字组合
        String a="^[0-9]+$";
        //纯abc...xyz的26个小写字母
        String b="^[a-z]+$";
        //纯ABC...XYZ的26个大写字母
        String c="^[A-Z]+$";

        return switch (type) {
            case 1 -> str.matches(a);
            case 2 -> str.matches(b);
            case 3 -> str.matches(c);
            default -> throw new RuntimeException("输入的属性类型 type 错误，type的范围仅允许1~3");
        };
    }

    /**
     * 检查邮箱是否符合规范。<br>
     * 格式必须是：xxx@xxx
     * @param str 邮箱
     * @return 符合规范话返回true，否则返回false
     */
    public static boolean EmailFormat(String str){
        //避免str为null导致抛异常
        if(str==null)return false;

        //如果出现null@null情况下，正则表达式也会通过
        //因此这里做特殊处理
        String[] parts = str.split("@", -1);
        if (parts.length != 2 || "null".equals(parts[0].trim()) || "null".equals(parts[1].trim())) {
            return false;
        }

        return str.matches(EMAIL_FORMAT);
    }
}
