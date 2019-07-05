package xpu.edu.blog.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "操作成功"),
    PARAM_ERROR(1, "参数不正确"),
    NULL_FILE(2, "文件为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
