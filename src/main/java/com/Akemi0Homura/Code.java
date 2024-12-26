package com.Akemi0Homura;

/**
 * A系列为成功、B系列为失败<br>
 * A1：仅有状态码。A2：有返回对象、状态码。A3：有返回对象、状态码、信息。<br>
 * B1：仅有状态码。B2：有状态码、信息。B3：有返回对象、状态码、信息。<br>
 * C1：前端专属状态码，无其他含义
 * @author Akemi0Homura
 */
public enum Code {
    A1,A2,A3,
    B1,B2,B3,
    C1,
}
