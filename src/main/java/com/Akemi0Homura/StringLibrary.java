package com.Akemi0Homura;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * 封装常见的字符串类型的操作<br>
 * 包括数据格式验证、脱敏、编码转换、随机生成、加密等
 * @author Akemi0Homura
 */
public class StringLibrary {
    //日志对象
    private static final Logger logger= LoggerFactory.getLogger(StringLibrary.class);
    private static final Random random=new Random();
    private static final char[] notationArr=new char[32];

    //纯数字
    private final static String PURE_DIGITAL="\\d+";
    //只要包含任意大小写字母+数字，就能通过
    private final static String ANY_ALPHABET_NUMBERS ="^(?=.*[a-zA-Z])(?=.*\\d).+$";
    //只要包含大写和小写字母+数字，就能通过
    private final static String MAX_LETTERS_AND_MIN_LETTERS_AND_NUMERALS="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";

    /**
     * 字符串是否为空<br>
     * 包括null、""、空格
     * @param str 需要判断的字符串
     * @return 是的话返回true,否则返回false
     */
    public static boolean NoNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 字符串是否包含空格<br>
     * 包括开头空格，结尾空格，和中间空格<br>
     * 如果是null对象或者""，则返回false<br>
     * @param str 需要判断的字符串
     * @return 是的话返回true,否则返回false
     */
    public static boolean NoSpace(String str) {
        if(str==null)return false;

        if(str.isEmpty())return false;

        return str.contains(" ");
    }

    /**
     * 字符串是否为纯数字<br>
     * 如果是null对象或者""或者带空格的，均返回false
     * @param str 需要判断的字符串
     * @return 是的话返回true,否则返回false
     */
    public static boolean PureDigital(String str) {
        if(str==null)return false;

        return str.matches(PURE_DIGITAL);
    }

    /**
     * 字符串是任意大小写字母+数字<br>
     * 如果是null对象或者""，均返回false<br>
     * 如果带空格，但是满足任意大小写字母+数字，返回true
     * @param str 需要判断的字符串
     * @return 是的话返回true,否则返回false
     */
    public static boolean AnyAlphabetNumbers(String str){
        if(str==null)return false;

        return str.matches(ANY_ALPHABET_NUMBERS);
    }

    /**
     * 字符串是大写字母+小写字母+数字<br>
     * 如果是null对象或者""，均返回false<br>
     * 如果带空格，但是满足大写字母+小写字母+数字，返回true
     * @param str 需要判断的字符串
     * @return 是的话返回true,否则返回false
     */
    public static boolean MaxAndMixLettersAndNumerals(String str){
        if(str==null)return false;

        return str.matches(MAX_LETTERS_AND_MIN_LETTERS_AND_NUMERALS);
    }

    /**
     * 计算字符串长度，然后判断是否符号预期
     * @param str 计算的字符串
     * @param length 预期长度
     * @return 符号预期返回true，否则返回false
     */
    public static boolean StringLength(String str,Integer length){
        return str.length()==length;
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
    public static String Desensitize(String str,int index,int length,char notation){
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
     * 文本编码转换
     * @param str 需要转换的字符串
     * @param encoding 文本编码格式
     * @return 返回转好的字符串
     */
    public static String CodeConversion(String str,String encoding){
        if(str==null)return null;

        try {
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            OutputStreamWriter osw=new OutputStreamWriter(baos,encoding);

            osw.write(str);
            osw.flush();
            osw.close();

            return baos.toString(encoding);

        }catch (IOException e){
            logger.error(String.valueOf(e));
            return null;
        }
    }

    /**
     * 生成随机字符<br>
     * 1.数字。2.大小写字母。3.小写字母<br>
     * 4.大写字母。5.特殊符号
     * @param charType 字符种类
     * @return 随机字符
     */
    public static char RandomChar(int charType){
        //这里的数据参考ASCII码值
        switch (charType){
            //纯数字
            case 1:return (char) (random.nextInt(10)+48);
            //大小写字母
            case 2:{
                if(random.nextInt(2)==0){
                    return (char) (random.nextInt(26)+97);
                }else{
                    return (char) (random.nextInt(26)+65);
                }
            }
            //小写字母
            case 3:return (char) (random.nextInt(26)+97);
            //大写字母
            case 4:return (char) (random.nextInt(26)+65);
            //特殊符号
            case 5:{
                //因为特殊符号有连续四组，为了分布均匀，需要先把它们装进一个数组
                //当然如果装进一次数组，下次直接使用即可，不需要再装一遍
                if(notationArr[31]=='\u0000'){
                    int i=0;
                    for(int j=33;j<=47;j++){
                        notationArr[i]=(char)j;
                        i++;
                    }
                    for(int j=58;j<=64;j++){
                        notationArr[i]=(char)j;
                        i++;
                    }
                    for(int j=91;j<=96;j++){
                        notationArr[i]=(char)j;
                        i++;
                    }
                    for(int j=123;j<=126;j++){
                        notationArr[i]=(char)j;
                        i++;
                    }
                }
                return notationArr[random.nextInt(notationArr.length-1)];
            }
            default:throw new RuntimeException("没有对应的随机字符种类");
        }
    }

    /**
     * 随机文本生成<br>
     * 生成类型以下：
     * 1.数字+字母。2.纯数字。3.字母+特殊符号<br>
     * 4.数字+特殊符号。5.数字+字母+特殊符号。6.纯字母。7.纯特殊符号<br>
     * 随机内容均为ASCII可显示字符表，特殊符号不包括空格<br>
     * 注意！目前数字和字母和特殊符号它们生成的概率分布并不均匀
     * @param length 文本长度
     * @param type 生成类型
     * @return 随机文本
     */
    public static String RandomString(int length,int type){
        //生成文本
        char[] chars=new char[length];

        switch (type){
            //数字+字母
            case 1:{
                for(int i=0;i<length;i++){
                    if(random.nextInt(2)==0){
                        chars[i]=RandomChar(1);
                    }else{
                        chars[i]=RandomChar(2);
                    }
                }
            }break;
            //纯数字
            case 2:{
                for(int i=0;i<length;i++){
                    chars[i]=RandomChar(1);
                }
            }break;
            //字母+特殊符号
            case 3:{
                for(int i=0;i<length;i++){
                    if(random.nextInt(2)==0){
                        chars[i]=RandomChar(2);
                    }else{
                        chars[i]=RandomChar(5);
                    }
                }
            }break;
            //数字+特殊符号
            case 4:{
                for(int i=0;i<length;i++){
                    if(random.nextInt(2)==0){
                        chars[i]=RandomChar(1);
                    }else{
                        chars[i]=RandomChar(5);
                    }
                }
            }break;
            //数字+字母+特殊符号
            case 5:{
                for(int i=0;i<length;i++){
                    switch (random.nextInt(3)+1){
                        case 1:chars[i]=RandomChar(1);break;
                        case 2:chars[i]=RandomChar(2);break;
                        case 3:chars[i]=RandomChar(5);break;
                        default:throw new RuntimeException("请联系作者");
                    }
                }
            }break;
            //纯字母
            case 6:{
                for(int i=0;i<length;i++){
                    chars[i]=RandomChar(2);
                }
            }break;
            //纯特殊符号
            case 7:{
                for(int i=0;i<length;i++){
                    chars[i]=RandomChar(5);
                }
            }break;
            default:throw new RuntimeException("请联系作者");
        }
        return String.valueOf(chars);
    }

    /**
     * 采用SHA256加密文本<br>
     * 如果再加一个int传参，则会变为哈希加盐算法
     * @param str 文本
     * @return 加密后的哈希
     */
    public static String SHA256Encrypt(String str){
        try {
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
     * SHA256哈希加盐算法加密<br>
     * 盐是通过数字+任意大小写字母+特殊符号的形式随机生成<br>
     * 加密文本为null，返回值也是null。如果盐的长度是>=0,则会报错
     * @param str 加密文本
     * @param length 盐的长度
     * @return 返回一个的String数组，0是加密后的文本，1是盐
     */
    public static String[] SHA256Encrypt(String str,int length){
        if(str==null)return null;
        if(length<=0)throw new RuntimeException("盐的长度必须大于1");

        String salt=RandomString(length,5);

        String hx=SHA256Encrypt(str+salt);

        return new String[]{hx,salt};
    }

    /**
     * SHA256密文与明文判断<br>
     * 哈希加密可能会出现两个不一样的值，但是加密后的密文是相同的情况。
     * 如果出现了，那就是SHA256本身的问题，而不是StringLibrary的问题<br>
     * 如果密文或者明文只要有一个为null，都会返回false
     * @param codedText SHA256加密后的密文
     * @param str 明文
     * @return 如果正确返回true，否则返回false
     */
    public static boolean SHA256Equivalent(String codedText,String str){
        if(codedText==null || str==null)return false;

        String pwd=SHA256Encrypt(str);
        return codedText.equals(pwd);
    }

    /**
     * SHA256密文加盐与明文判断<br>
     * 哈希加密可能会出现两个不一样的值，但是加密后的密文是相同的情况。
     * 如果出现了，那就是SHA256本身的问题，而不是StringLibrary的问题<br>
     * 如果密文、明文、盐只要有一个为null，都会返回false
     * @param codedText SHA256加密后的密文
     * @param str 明文
     * @param salt 盐
     * @return 如果正确返回true，否则返回false
     */
    public static boolean SHA256Equivalent(String codedText,String str,String salt){
        if(codedText==null || str==null || salt==null)return false;

        String pwd=SHA256Encrypt(str+salt);
        return codedText.equals(pwd);
    }

}
