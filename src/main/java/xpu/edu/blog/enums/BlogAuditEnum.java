package xpu.edu.blog.enums;

import lombok.Getter;

@Getter
public enum BlogAuditEnum {
    DRAFT(0, "草稿"),
    RELEASE(1, "已发布"),
    RECYCLE(2, "已删除"),
    ;

    private Integer code;

    private String message;

    BlogAuditEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
