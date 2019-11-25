package com.wudang.wdt.command;

import com.wudang.wdt.command.table.ChinesSizeConstraints;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.*;

/**
 * 日志记录信息查看
 *
 * @author jet
 * @date 25-11-2019
 */
@ShellComponent
@ShellCommandGroup(value = "log")
public class LogCommand {

    @ShellMethod(value = "logTable 导航", key = {"logTable"})
    @SuppressWarnings("unchecked")
    public Table logTable() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue(" https://blog.csdn.net/u012881904/article/details/82951719");
        builder.addRow().addValue(" vi application.log ").addValue(" ").addValue(" https://blog.csdn.net/u012881904/article/details/90815398");
        builder.addRow().addValue(" find / -name application.log").addValue("find file ").addValue("");
        builder.addRow().addValue(" tail -f application.log | grep ERROR ").addValue("only find error").addValue(" ");
        builder.addRow().addValue(" less application.log ").addValue("/text ?text").addValue(" ");
        builder.addRow().addValue(" grep -C 10 -n \"ERROR\" application.log").addValue("grep error").addValue(" ");
        builder.addRow().addValue(" grep -C 10 -n -E \"ERROR|WARN\" application.log").addValue("grep regx").addValue(" ");

        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }
}
