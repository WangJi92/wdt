package com.wudang.wdt.command;

import com.wudang.wdt.command.table.ChinesSizeConstraints;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.*;

/**
 * JVM 常用的命令行
 *
 * @author jet
 * @date 26-11-2019
 */
@ShellComponent
@ShellCommandGroup(value = "jvm")
public class JvmCommand {


    @ShellMethod(value = "jvmTable 导航", key = {"jvmTable"})
    @SuppressWarnings("unchecked")
    public Table jvmTable() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue("https://www.cnblogs.com/chenglc/p/7997988.html");
        builder.addRow().addValue(" Summary JVM monitoring").addValue(" ").addValue("https://www.cnblogs.com/wxisme/p/9878494.html");
        builder.addRow().addValue(" jps -l").addValue("jps -l -v").addValue("查看java进程");
        builder.addRow().addValue("jstat -gcutil pid 1000 3(3 times every 1s)").addValue("percentage used (man jstat)").addValue("https://blog.csdn.net/fenglibing/article/details/6321453 ");
        builder.addRow().addValue("jstat -gc pid 1000 3").addValue("actual value ").addValue("https://www.iteye.com/blog/bijian1013-2221351");
        builder.addRow().addValue("jstack -l pid").addValue("thread snapshot,deadlock,loop").addValue("http://www.hollischuang.com/archives/110");
        builder.addRow().addValue("jstack -l -F  pid").addValue("").addValue("to force a thread dump");
        builder.addRow().addValue("jstack -l -m  pid").addValue(" ").addValue("to print both java and native frames (mixed mode)");
        builder.addRow().addValue("jmap -dump:live,format=b,file=/tmp/livedump.hprof pid").addValue("").addValue("https://www.jianshu.com/p/a4ad53179df3");
        builder.addRow().addValue("jmap -dump:format=b,file=/tmp/dump.hprof pid").addValue("").addValue(" ");
        builder.addRow().addValue("jmap -heap pid").addValue("memory usage information of each memory area").addValue("内存占用统计");
        builder.addRow().addValue("jmap -histo:live pid").addValue("show statistics for objects in the heap").addValue("活着对象统计");
        builder.addRow().addValue("jmap -clstats pid").addValue("to print class loader statistics").addValue("类加载器信息");
        builder.addRow().addValue("jmap -finalizerinfo pid").addValue("print information on objects awaiting finalization").addValue("等待终结的对象");
        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }

    @ShellMethod(value = "jcmd 多功能的工具", key = {"jcmdTable"})
    @SuppressWarnings("unchecked")
    public Table jcmdTable() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment ").addValue("https://www.yuque.com/docs/share/f8272d40-cf41-4c5a-9a84-67fef4493612?#");
        builder.addRow().addValue("jcmd -l ").addValue("jps -l ").addValue("list JVM processes on the local machine");
        builder.addRow().addValue("jcmd pid help").addValue("jcmd pid help VM.native_memory").addValue("View processes all supported commands");
        builder.addRow().addValue("jcmd pid GC.heap_info").addValue("jstat -gccapacity pid").addValue("Provide generic Java heap information");
        builder.addRow().addValue("jcmd pid ManagementAgent.start").addValue("Start local management agent").addValue("https://www.yuque.com/docs/share/37ec62c8-534d-419d-b34e-ba4ec3c297a3?#");
        builder.addRow().addValue("jcmd pid VM.native_memory").addValue("Print native memory usage").addValue("https://www.yuque.com/docs/share/7ae949ca-f428-439d-8a36-f9661fc5c196?#");
        builder.addRow().addValue("jcmd pid Thread.print").addValue("jstack -l pid").addValue("Print all threads with stacktraces");
        builder.addRow().addValue("jcmd pid GC.heap_dump filename=/tmp/dump.hprof").addValue("jmap -dump:live,format=b,file=/tmp/livedump.hprof pid").addValue("Generate dump of the Java heap.");
        builder.addRow().addValue("jcmd pid VM.flags").addValue("jinfo pid ").addValue("Print VM flag options and their current values");
        TableModel tableModel = builder.build();

        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }

}
