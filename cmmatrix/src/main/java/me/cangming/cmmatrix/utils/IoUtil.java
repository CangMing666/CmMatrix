package me.cangming.cmmatrix.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @date 创建时间：2019-12-31
 * @auther cangming
 * @Description IO工具类
 */
public class IoUtil {
    public static final int DEFAULT_BUFFER_SIZE = 32768;

    private IoUtil() {
    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException var2) {
            }
        }
    }
}
