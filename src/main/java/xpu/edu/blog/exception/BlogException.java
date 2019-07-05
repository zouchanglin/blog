package xpu.edu.blog.exception;

import xpu.edu.blog.enums.ResultEnum;

public class BlogException extends RuntimeException{

    private Integer code;

    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public BlogException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
