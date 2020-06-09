package me.cangming.cmmatrix.utils;

/**
 * 创建时间: 2019/12/11
 * 类描述:
 *
 * @author cangming
 * @version 1.0
 */
public interface ContentLisenter {
    void content(String content);

    void error(Exception e, String s);
}
