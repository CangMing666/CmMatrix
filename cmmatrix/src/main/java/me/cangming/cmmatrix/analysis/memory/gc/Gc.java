package me.cangming.cmmatrix.analysis.memory.gc;

/**
 * 创建时间: 2019/12/4
 * 类描述:
 * <p>
 * 收集logcat中的GC日志
 *
 * @author cangming
 * @version 1.0
 */
public class Gc {

    public static final String SHELL_COMMAND = "logcat -v time %s | grep GC";
    //logcat捕捉GC日志
    //Dalvik/Art
    //adb shell logcat com.zmsoft.kds | grep -e GC_ -e AllocSpace


}
