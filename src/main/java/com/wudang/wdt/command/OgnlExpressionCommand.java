package com.wudang.wdt.command;

import com.wudang.wdt.command.table.ChinesSizeConstraints;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.*;

/**
 * ognl 表达式学习
 *
 * @author jet
 * @date 24-11-2019
 */
@ShellComponent
@ShellCommandGroup(value = "ognl")
public class OgnlExpressionCommand {


    @ShellMethod(value = "ognl 学习", key = {"ognlTable"})
    @SuppressWarnings("unchecked")
    public Table ognlTable() {

        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("ognl https://commons.apache.org/proper/commons-ognl/language-guide.html ").addValue("arthas eg https://alibaba.github.io/arthas/ognl.html").addValue("备注");
        builder.addRow().addValue("objects.{^ #this instanceof String }").addValue("watch com.Test test \"params[0].{^ #this.name != null} \"").addValue("Selecting First Match");
        builder.addRow().addValue("objects.{$ #this instanceof String }").addValue("watch com.Test test \"params[0].{$ #this.name != null}\" ").addValue("Selecting Last Match");
        builder.addRow().addValue("objects.{ #this instanceof String ? #this : #this.toString()}").addValue("watch com.Test test \"params[0].{ #this.name }\" ").addValue("将结果存储在新集合-投影");


        builder.addRow().addValue("listeners.{? #this instanceof ActionListener}").addValue("watch com.Test test \"params[0].{? #this.age > 10 }").addValue("按条件过滤");
        builder.addRow().addValue("@java.lang.Integer@toString(int, int)").addValue("ognl -x 5 '@java.lang.Integer@toString(int, int)").addValue("调用静态方法");
        builder.addRow().addValue("OuterClass$innerClassName").addValue("arthas：https://github.com/alibaba/arthas/issues/71").addValue("内部类怎么使用");

        builder.addRow().addValue("name.toCharArray()[0].numericValue.toString()").addValue("params[0].{? #this.age > 10 }.size()").addValue("链前链接的结果作下链的当前对象");
        builder.addRow().addValue("method( ensureLoaded(),name)").addValue(" ").addValue("调用方法");
        builder.addRow().addValue("#var").addValue("lets you store intermediate results and use them again").addValue("变量引用");



        TableModel tableModel = builder.build();

        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }


}
