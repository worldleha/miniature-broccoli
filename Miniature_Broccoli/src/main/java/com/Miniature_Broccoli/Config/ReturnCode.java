package com.Miniature_Broccoli.Config;

<<<<<<< Updated upstream
public enum ReturnCode {
    RC200(200, "操作成功"),
    RC500(500, "操作失败");

=======
/**
 * 返回状态码设置
 */
public enum ReturnCode {
    RC200(200, "操作成功"),
    RC500(500, "操作失败"),
    RC404(404, "接口不存在");
>>>>>>> Stashed changes
    // 自定义状态码
    private final int code;

    // 自定义描述
    private final String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
