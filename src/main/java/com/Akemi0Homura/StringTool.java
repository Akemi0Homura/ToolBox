package com.Akemi0Homura;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 负责操作String字符串类的操作。<br>
 * 比如说json序列化、随机字符串等。
 * @author Akemi0Homura
 */
public class StringTool {
    //日志对象
    private static final Logger logger= LoggerFactory.getLogger(StringTool.class);
    //用来装大小写字母和十个数字，在RandomString方法上使用
    private static final char[] ALPHA_NUMERIC=new char[62];
    //Json序列化对象
    private static final ObjectMapper objectMapper=new ObjectMapper();

    /**
     * 判断字符串是否为空，包括null、""、空格、\t、\n等
     * @param str 需要判断的字符串
     * @return 如果为空，则返回true
     */
    public static boolean NoNull(String str) {
        //包括""、空格、\t、\n等的正则表达式
        String a="\\s*";

        if(str==null)return true;
        return str.matches(a);
    }

    /**
     * 生成一个随机字符串<br>
     * 1.纯数字。2.纯大小写字母。3.数字+大小写字母。
     * @param length 字符串长度
     * @param type 生成的字符串类型
     * @return 随机字符串
     */
    public static String RandomString(int length,int type) {
        if(length==0)throw new RuntimeException("输入的字符串长度 length 的值为0。因此返回的随机字符串长度为0，无意义的调用。");

        //随机数对象，比Random更加安全
        SecureRandom random = new SecureRandom();
        //最后返回的随机字符串
        StringBuilder str= new StringBuilder();

        switch (type) {
            //纯数字生成
            case 1:{
                for (int i=0;i<length;i++) {
                    int x;
                    //随机数开头为0，并不符合现实，因此这里设置开头不能为0
                    if(i==0){
                        x=random.nextInt(9)+1;
                    }else{
                        x = random.nextInt(10);
                    }
                    str.append(x);
                }
                return str.toString();
            }
            //纯大小写字母
            case 2:{
                for (int i=0;i<length;i++) {
                    char x;
                    //判断生成的字母是大写还是小写
                    if(random.nextInt(2)==0){
                        x=(char)(random.nextInt(26)+'a');
                    }else{
                        x=(char)(random.nextInt(26)+'A');
                    }
                    str.append(x);
                }
                return str.toString();
            }
            //数字+大小写字母
            case 3:{
                //先检查ALPHA_NUMERIC数组有没有值
                //有值的话直接用，没值就初始化
                if(ALPHA_NUMERIC[61]!='z'){
                    //全局变量ALPHA_NUMERIC初始化上数据
                    //装26个大写字母
                    for(int i=0;i<26;i++)ALPHA_NUMERIC[i]=(char)(i+'A');
                    //装十个数字
                    for(int i=0;i<10;i++)ALPHA_NUMERIC[26+i]=(char)(i+'0');
                    //装26个小写字母
                    for(int i=0;i<26;i++)ALPHA_NUMERIC[36+i]=(char)(i+'a');
                }
                //开始生成随机大小写字母和数字
                for(int i=0;i<length;i++){
                    char x=ALPHA_NUMERIC[random.nextInt(62)];
                    str.append(x);
                }
                return str.toString();
            }
            default:throw new RuntimeException("输入的属性类型 type 错误，type的范围仅允许1~3");
        }
    }

    /**
     * 数据脱敏<br>
     * 指定字符串的下标，开始到指定长度内的字符，替换为指定字符<br>
     * 字符串下标从1开始<br>
     * 比如说下标为3，字符串是"0123456"的话，是从字符串中的2开始被替换
     * @param str 被替换的字符串。如果为null，则返回值也为null
     * @param index 下标
     * @param length 长度
     * @param notation 指定替换的字符
     * @return 数据脱敏后的字符串
     * @throws RuntimeException 如果需要替换的字符下标超过文本本身，就会抛出该异常
     */
    public static String DataMasking(String str,int index,int length,char notation){
        if(str==null)return null;

        int strAllLength=index+length-1;

        if(strAllLength>str.length())throw new RuntimeException("要替换的下标超过文本本身");
        if(index-1<0)throw new RuntimeException("下标不能从0开始");

        char[] chars=str.toCharArray();
        for(int i=index-1;i<strAllLength;i++){
            chars[i]=notation;
        }

        return new String(chars);
    }

    /**
     * 采用SHA256加密文本<br>
     * 哈希加密可能会出现两个不一样的值，但是加密后的密文是相同的情况。
     * 如果出现了，那就是SHA256本身的问题，而不是StringTool的问题<br>
     * @param str 文本
     * @return 加密后的哈希
     */
    public static String SHA256Encrypt(String str){
        try{
            //指定加密算法
            MessageDigest digest=MessageDigest.getInstance("SHA-256");

            byte[] encoded=digest.digest(str.getBytes());

            //将哈希值转换成十六进制
            StringBuilder hexString=new StringBuilder();
            for(byte b:encoded){
                String hex=Integer.toHexString(0xff & b);
                if(hex.length()==1)hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            logger.error(String.valueOf(e));
            return null;
        }
    }

    /**
     * SHA256密文与明文判断<br>
     * @param codedText SHA256加密后的密文
     * @param str 明文
     * @return 如果正确返回true，否则返回false。
     */
    public static boolean SHA256Equivalent(String codedText,String str){
        if(codedText==null || str==null)return false;

        String pwd=SHA256Encrypt(str);
        return codedText.equals(pwd);
    }

    /**
     * 接受一个对象，返回Json字符串
     * @param obj 类对象
     * @return 字符串
     */
    public static String SetJson(Object obj){
        try {
            String str=objectMapper.writeValueAsString(obj);
            if(str==null || str.isEmpty()){
                throw new RuntimeException("转换后，Json字符串为null");
            }else{
                return str;
            }
        }catch (Exception e){
            logger.error(String.valueOf(e));
        }
        return null;
    }

    /**
     * 该方法接受json字符串和对象class，然后反序列化返回java对象
     * @param json Json字符串
     * @param clazz 类对象
     * @return java对象
     * @param <T> 泛型
     */
    public static <T>T GetJson(String json, Class<T> clazz){
        try {
            return objectMapper.readValue(json,clazz);
        }catch (Exception e){
            logger.error(String.valueOf(e));
        }
        return null;
    }
}
