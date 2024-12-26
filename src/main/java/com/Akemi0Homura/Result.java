package com.Akemi0Homura;

/**
 * 前后端交互统一对象，在使用该对象的时候，必须指定泛型的具体类型<br>
 * 不然SpringMvc可能接受不到对象
 * @author Akemi0Homura
 */
public class Result<T> {
    T data;
    Code code;
    String msg;

    /**
     * 判断Result对象和里面的data属性是否为null
     * @return 如果为null则返回true，否则返回false
     */
    public boolean ResultNoNull(Result result){
        if(result == null){
            return result.getData() == null;
        }
        return true;
    }

    public Result(Code code) {
        this.code = code;
    }

    public Result(Code code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data, Code code) {
        this.data = data;
        this.code = code;
    }

    public Result(T data, Code code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result() {
    }
}
