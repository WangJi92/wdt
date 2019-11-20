package com.wudang.sst.command;

import com.wudang.sst.common.utils.ClipboardUtils;
import com.wudang.sst.common.utils.IpUtils;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * 获取本地的ip的信息
 * @author jet
 * @date 20-11-2019
 */
@ShellComponent
@ShellCommandGroup(value = "ip")
public class IpCommand {

    @ShellMethod(value = "获取本机ip地址信息")
    public String ip() {
        String ip = IpUtils.getIpAddress();
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT,ip+" ");
        ClipboardUtils.setClipboardString(ip);
        return formatStr;
    }
}
