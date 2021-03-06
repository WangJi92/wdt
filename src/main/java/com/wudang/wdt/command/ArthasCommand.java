package com.wudang.wdt.command;

import com.google.common.collect.Lists;
import com.wudang.wdt.command.table.ChinesSizeConstraints;
import com.wudang.wdt.common.utils.ClipboardUtils;
import com.wudang.wdt.common.utils.WdtStringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.*;
import org.springframework.shell.table.*;
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
    @ShellMethod(value = "trace 某个方法的调用栈", key = {"arthasTrace", "at"})
    public String asTrace(@ShellOption(help = "idea  copy class method reference ", value = {"-m"}, defaultValue = "") String methodReference,
                          @ShellOption(help = "运行执行的最大次数", value = {"-n"}, defaultValue = "5") Integer n) {
        if (StringUtils.isEmpty(methodReference)) {
            methodReference = ClipboardUtils.getClipboardString();
        }
        if (StringUtils.isEmpty(methodReference) || !methodReference.contains("#")) {
            return "请使用idea 复制类方法信息";
        }
        methodReference = methodReference.replaceFirst("#", " ");

        StringBuilder buffer = new StringBuilder(TRACE);
        buffer.append(" ").append(methodReference);
        buffer.append(" -n ").append(n);

        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " ");
        ClipboardUtils.setClipboardString(buffer.toString());
        return formatStr;
    }

    @ShellMethod(value = "watch 某个方法的入参返回值", key = {"arthasWatch", "aw"})
    public String asWatch(@ShellOption(help = "idea  copy class method reference ", value = {"-m"}, defaultValue = "") String methodReference,
                          @ShellOption(help = "运行执行的最大次数", value = {"-n"}, defaultValue = "5") Integer n,
                          @ShellOption(help = "查看参数值的最大深度", value = {"-x"}, defaultValue = "5") Integer x,
                          @ShellOption(help = "表达式", value = {"-express"}, defaultValue = "'{params,returnObj,throwExp}'", valueProvider = WatchValuesProvider.class) String express) {
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
            buffer.append(" ").append("'{params,returnObj,throwExp}'");
        } else {
            buffer.append(" ").append(express);
        }

        buffer.append(" -n ").append(n);
        buffer.append(" -x ").append(x);

        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " ");
        ClipboardUtils.setClipboardString(buffer.toString());
        return formatStr;
    }

    @ShellMethod(value = "获取类的静态字段,变量(-c classLoaderHash 默认值 SystemClassLoader）[ sc -d com.test 获取 classLoaderHash 值]", key = {"arthasStatic","as"})
    public String asOgnlStatic(@ShellOption(help = "idea  copy class static method、field reference ", value = {"-m"},defaultValue = "") String methodReference,
                               @ShellOption(help = "查看参数值的最大深度", value = {"-x"}, defaultValue = "5") Integer x) {
        if (StringUtils.isEmpty(methodReference)) {
            methodReference = ClipboardUtils.getClipboardString();
        }
        if (!StringUtils.hasText(methodReference) || !methodReference.contains("#")) {
            return "请使用idea 复制类方法、变量信息";
        }
        //java.lang.Integer#MIN_VALUE 常量复制的效果  -> '@java.lang.Integer@MIN_VALUE'
        //java.lang.Integer#toHexString 方法效果  需要自己添加括号   -> '@java.lang.Integer@toHexString'
        //java.lang.Integer#toUnsignedString(int, int) 方法效果,添加值  -> '@java.lang.Integer@toUnsignedString(int, int)'

        StringBuilder buffer = new StringBuilder("ognl");
        buffer.append(" -x ").append(x).append(" '@");
        methodReference = WdtStringUtils.replaceLast(methodReference, "#", "@");
        buffer.append(methodReference).append("'");


        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " ");
        ClipboardUtils.setClipboardString(buffer.toString());
        formatStr = formatStr + "\"获取类的静态字段,变量(-c classLoaderHash 默认值 SystemClassLoader）" +
                "\"[可以通过 sc -d com.test 获取 classLoaderHash 值]";
        return formatStr;
    }

    /**
     * 自动安装脚本 alias as.sh
     *
     * @return
     */
    @ShellMethod(value = "arthas 安装脚本", key = {"arthasInstall","ai"})
    public String asInstallShell() {
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, AS_INSTALL);
        ClipboardUtils.setClipboardString(AS_INSTALL);
        return formatStr;
    }

    @ShellMethod(value = "arthas 学习", key = {"arthasTable"})
    @SuppressWarnings("unchecked")
    public Table arthasTable() {

        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("arthas  ").addValue("备注").addValue("扩展");
        builder.addRow().addValue("special ognl use").addValue("https://github.com/alibaba/arthas/issues/71").addValue("special ognl ");
        builder.addRow().addValue("ognl https://commons.apache.org/proper/commons-ognl/language-guide.html ").addValue("https://alibaba.github.io/arthas/ognl.html ").addValue("officialLink");
        builder.addRow().addValue("tt get spring context").addValue(" https://github.com/alibaba/arthas/issues/482 ").addValue(" ");


        TableModel tableModel = builder.build();

        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
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

            completionProposal = new CompletionProposal("'{target,returnObj}'");
            completionProposal.dontQuote(true).description("查看目标类、返回值");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal(" returnObj'");
            completionProposal.dontQuote(true).description("查看返回值");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal(" params");
            completionProposal.dontQuote(true).description("查看入参");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal("'{params,returnObj,throwExp}'");
            completionProposal.dontQuote(true).description("查看入参、返回值、异常");
            completionProposals.add(completionProposal);


            return completionProposals;
        }
    }
}
