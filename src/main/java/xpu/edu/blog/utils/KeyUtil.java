package xpu.edu.blog.utils;

import java.util.Random;

/**
 * @author tim
 * @version 1.0
 */
public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(9) + 1;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
