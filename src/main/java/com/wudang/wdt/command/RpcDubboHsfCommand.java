package com.wudang.wdt.command;

import com.wudang.wdt.common.utils.ClipboardUtils;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * 一些常用的常识信息收集
 *
 * @author jet
 * @date 09-12-2019
 */
@ShellComponent(value = "hsf or dubbo  telnet命令")
@ShellCommandGroup(value = "rpc")
public class RpcDubboHsfCommand {

    /**
     * dubbo telnet 获取连接信息
     * http://dubbo.apache.org/zh-cn/docs/user/references/telnet.html
     * http://dubbo.apache.org/zh-cn/docs/user/references/qos.html
     *
     * @return
     */
    @ShellMethod(value = "dubbo telnet 查询服务信息", key = {"dubbo-telnet", "dt"})
    public String dubboTelnet() {
        String command = " telnet localhost 20880";
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, command + " link: http://dubbo.apache.org/zh-cn/docs/user/references/telnet.html");
        ClipboardUtils.setClipboardString(command);
        return formatStr;
    }


    /**
     * hsf 查询信息
     * hsf:https://www.alibabacloud.com/help/zh/doc-detail/100087.htm
     *
     * @return
     */
    @ShellMethod(value = "可以通过执行telnet localhost 12201 ,进入Pandora 的控制台(cd hsf)", key = {"hsf-telnet", "ht"})
    public String hsfTelnet() {
        String command = " telnet localhost 12201";
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, command + " link: https://help.aliyun.com/knowledge_detail/68931.html?spm=5176.13394938.0.0.3d0f6e52dGHjLC");
        ClipboardUtils.setClipboardString(command);
        return formatStr;
    }

}
