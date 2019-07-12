package xpu.edu.blog.utils;

import java.util.Random;

/**
 * 生成唯一的主键工具
 */
public class KeyUtil {
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(999) + 1;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
