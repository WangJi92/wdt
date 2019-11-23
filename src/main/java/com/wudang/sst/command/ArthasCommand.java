package com.wudang.sst.command;

import com.google.common.collect.Lists;
import com.wudang.sst.common.utils.ClipboardUtils;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * arthas 工具类，方便获取到 命令
 *
 * @author jet
 * @date 23-11-2019
 */
@ShellComponent
@ShellCommandGroup(value = "arthas")
public class ArthasCommand {
    /**
     * 下载脚本
     */
    private static final String AS_INSTALL = "curl -sk https://arthas.gitee.io/arthas-boot.jar -o ~/.arthas-boot.jar  && echo \"alias as.sh='java -jar ~/.arthas-boot.jar --repo-mirror aliyun --use-http'\" >> ~/.bashrc && source ~/.bashrc";

    /**
     * 命令
     */
    public static final String TRACE = "trace";
    /**
     * 观察某个值的大小
     */
    public static final String WATCH = "watch";

    /**
     * arthas trace 命令处理
     *
     * @param methodReference
     * @return
     */
    @ShellMethod(value = "trace 某个方法的调用栈", key = {"trace"})
    public String asTrace(@ShellOption(help = "idea  copy class method reference ", value = {"-m"}, defaultValue = "") String methodReference,
                          @ShellOption(help = "运行执行的最大次数", value = {"-n"}, defaultValue = "5") Integer n) {
        if (StringUtils.isEmpty(methodReference)) {
            methodReference = ClipboardUtils.getClipboardString();
        }
        if (StringUtils.isEmpty(methodReference) || !methodReference.contains("#")) {
            return "请使用dea 复制类方法信息";
        }
        methodReference = methodReference.replaceFirst("#", " ");

        StringBuilder buffer = new StringBuilder(TRACE);
        buffer.append(" ").append(methodReference);
        buffer.append(" -n ").append(n);

        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " ");
        ClipboardUtils.setClipboardString(buffer.toString());
        return formatStr;
    }

    @ShellMethod(value = "watch 某个方法的入参返回值", key = {"watch"})
    public String asWatch(@ShellOption(help = "idea  copy class method reference ", value = {"-m"}, defaultValue = "") String methodReference,
                          @ShellOption(help = "运行执行的最大次数", value = {"-n"}, defaultValue = "5") Integer n,
                          @ShellOption(help = "查看参数值的最大深度", value = {"-x"}, defaultValue = "5") Integer x,
                          @ShellOption(help = "表达式", value = {"-express"}, defaultValue = "'{params,returnObject,throwExp}'", valueProvider = WatchValuesProvider.class) String express) {
        if (StringUtils.isEmpty(methodReference)) {
            methodReference = ClipboardUtils.getClipboardString();
        }
        if (StringUtils.isEmpty(methodReference) || !methodReference.contains("#")) {
            return "请使用idea 复制类方法信息";
        }
        methodReference = methodReference.replaceFirst("#", " ");

        StringBuilder buffer = new StringBuilder(WATCH);
        buffer.append(" ").append(methodReference);
        if (StringUtils.isEmpty(express)) {
            buffer.append(" ").append("'{params,returnObject,throwExp}'");
        } else {
            buffer.append(" ").append(express);
        }

        buffer.append(" -n ").append(n);
        buffer.append(" -x ").append(x);

        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " ");
        ClipboardUtils.setClipboardString(buffer.toString());
        return formatStr;
    }

    /**
     * 自动安装脚本 alias as.sh
     *
     * @return
     */
    @ShellMethod(value = "arthas 安装脚本", key = {"as-install"})
    public String asInstallShell() {
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, AS_INSTALL);
        ClipboardUtils.setClipboardString(AS_INSTALL);
        return formatStr;
    }

    /**
     * watch 提示命令行处理
     */
    @Component
    class WatchValuesProvider extends ValueProviderSupport {

        @Override
        public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
            List<CompletionProposal> completionProposals = Lists.newArrayList();
            CompletionProposal completionProposal = null;

            completionProposal = new CompletionProposal("'{target,returnObject}'");
            completionProposal.dontQuote(true).description("查看目标类、返回值");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal(" returnObject'");
            completionProposal.dontQuote(true).description("查看返回值");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal(" params");
            completionProposal.dontQuote(true).description("查看入参");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal("'{params,returnObject,throwExp}'");
            completionProposal.dontQuote(true).description("查看入参、返回值、异常");
            completionProposals.add(completionProposal);


            return completionProposals;
        }
    }
}
