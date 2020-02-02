package com.wudang.wdt.command;

import com.wudang.wdt.common.utils.ClipboardUtils;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

/**
 * 端口号占用 排查处理
 * 参考 : {@literal https://www.cnblogs.com/zjfjava/p/10513399.html linux下查看端口占用}
 * {@literal https://blog.csdn.net/u012881904/article/details/53487257 玩玩Linux云主机-Linux看端口的占用 、linux lsof、netstat}
 * @author 汪小哥
 * @date 02-02-2020
 */
@ShellComponent
@ShellCommandGroup(value = "port")
public class PortOccupancyCommand {

    @ShellMethod(value = " lsof -i:端口号 用于查看某一端口的占用情况", key = {"portLsof", "lsof"})
    public String lsofPort(@ShellOption(help = "", value = {"-p"}, defaultValue = "") String port) {
        StringBuilder buffer = new StringBuilder("lsof -i");
        if (StringUtils.hasText(port)) {
            buffer.append(":").append(port);
        }
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " link: https://www.cnblogs.com/zjfjava/p/10513399.html");
        ClipboardUtils.setClipboardString(buffer.toString());
        return formatStr;
    }

    @ShellMethod(value = "netstat -tunlp | grep 端口号 用于查看某一端口的占用情况(mac 不支持)", key = {"portNetstat", "netstat"})
    public String netstatPort(@ShellOption(help = "", value = {"-p"}, defaultValue = "") String port) {
        StringBuilder buffer = new StringBuilder("netstat -tunlp");
        if (StringUtils.hasText(port)) {
            buffer.append("| grep ").append(port);
        }
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " link: https://www.cnblogs.com/zjfjava/p/10513399.html");
        ClipboardUtils.setClipboardString(buffer.toString());
        return formatStr;
    }
}
