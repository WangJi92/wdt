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
        builder.addRow().addValue("jstat -gcutil pid 1000 3(3 times every 1s)").addValue("percentage used").addValue("https://blog.csdn.net/fenglibing/article/details/6321453 ");
        builder.addRow().addValue("jstat -gc pid 1000 3").addValue("actual value ").addValue("https://www.iteye.com/blog/bijian1013-2221351");
        builder.addRow().addValue("jstack -l pid").addValue("thread snapshot,deadlock,loop").addValue("http://www.hollischuang.com/archives/110");
        builder.addRow().addValue("jstack -l -F  pid").addValue("").addValue("to force a thread dump");
        builder.addRow().addValue("jstack -l -m  pid").addValue(" ").addValue("to print both java and native frames (mixed mode)");
        builder.addRow().addValue("jmap -dump:live,format=b,file=/tmp/livedump.hprof pid").addValue("").addValue("https://www.jianshu.com/p/a4ad53179df3");
        builder.addRow().addValue("jmap -dump:format=b,file=/temp/dump.hprof pid").addValue("").addValue(" ");
        builder.addRow().addValue("jmap -heap pid").addValue("memory usage information of each memory area").addValue("内存占用统计");
        builder.addRow().addValue("jmap -histo:live pid").addValue("show statistics for objects in the heap").addValue("活着对象统计");
        builder.addRow().addValue("jmap -clstats pid").addValue("to print class loader statistics").addValue("类加载器信息");
        builder.addRow().addValue("jmap -finalizerinfo pid").addValue("print information on objects awaiting finalization").addValue("等待终结的对象");
        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }

}
