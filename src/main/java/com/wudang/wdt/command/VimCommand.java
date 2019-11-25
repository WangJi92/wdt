package com.wudang.wdt.command;

import com.wudang.wdt.command.table.ChinesSizeConstraints;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.*;

/**
 * vim 使用归档
 * @author jet
 * @date 25-11-2019
 */
@ShellComponent
@ShellCommandGroup(value = "vim")
public class VimCommand {

    @ShellMethod(value = "vimTable 删除撤销", key = {"vimTableDeleteUndo"})
    @SuppressWarnings("unchecked")
    public Table vimTableOp() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue(" https://blog.csdn.net/u012881904/article/details/90815398");
        builder.addRow().addValue(":w |:w text.txt|:q |:wq ").addValue("operation file ").addValue(" ");
        builder.addRow().addValue(" x  | nx ").addValue("delete current(n) character from cursor").addValue("");
        builder.addRow().addValue(" dd |ndd ").addValue("delete current (n) row").addValue("");
        builder.addRow().addValue("dw ").addValue("delete rest of the word after cursor").addValue("删除单词剩余部分");
        builder.addRow().addValue("d$ ").addValue("delete rest of the line after cursor").addValue("删除行剩余部分");
        builder.addRow().addValue("u ").addValue("undo previous action").addValue(" ");
        builder.addRow().addValue("ctrl+r ").addValue("resume was undo in previous step").addValue(" ");
        builder.addRow().addValue("U ").addValue("undo all actions on the current line").addValue(" ");

        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }

    @ShellMethod(value = "vimTable Find  查找", key = {"vimTableFind"})
    @SuppressWarnings("unchecked")
    public Table vimTableFind() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue(" https://blog.csdn.net/u012881904/article/details/90815398");
        builder.addRow().addValue("/word").addValue("search for word  under cursor").addValue("向下搜索");
        builder.addRow().addValue("?word").addValue("search before cursor for word").addValue("向上搜索");
        builder.addRow().addValue(" n  | N  ").addValue("search down（up） element search action").addValue("小 大写(up)");
        builder.addRow().addValue("n+ | n- |nG ").addValue("jump down(up)(to global line) n lines").addValue("上,下，指定,行 ");
        builder.addRow().addValue("0 or ^ | $").addValue("go to the begin,end").addValue("行首行尾");
        builder.addRow().addValue("gg | G").addValue("page header,end of page").addValue("页首页尾");
        builder.addRow().addValue("h(左) | l(右)").addValue("j(下) |k(上)").addValue("");
        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }

    @ShellMethod(value = "vimTableCopyPaste  复制粘贴", key = {"vimTableCopyPaste"})
    @SuppressWarnings("unchecked")
    public Table vimTableCopyPaste() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue(" https://blog.csdn.net/u012881904/article/details/90815398");
        builder.addRow().addValue(" yy | nyy").addValue("copy the current row down n rows to buffer").addValue("cop(y) paste use (p)");
        builder.addRow().addValue("yw | nyw").addValue("copy n words from cursor").addValue("after cursor n个单词");
        builder.addRow().addValue(" y^  | y$ ").addValue("copy course begin or end").addValue("course 到首(尾)的内容");
        builder.addRow().addValue("p ").addValue("Paste copy word").addValue("粘贴");
        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }

    @ShellMethod(value = "vimTableReplace 替换 ", key = {"vimTableReplace"})
    @SuppressWarnings("unchecked")
    public Table vimTableReplace() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue(" https://blog.csdn.net/u012881904/article/details/90815398");
        builder.addRow().addValue("vim replace  ").addValue("vim replace").addValue("  http://c.biancheng.net/view/637.html");

        builder.addRow().addValue("s/old/new ").addValue("replace the first old in the row with new").addValue("当前行");
        builder.addRow().addValue("s/old/new/g ").addValue("replace all old s in the row with new").addValue("当前行");
        builder.addRow().addValue("n,m s/old/new/g").addValue("replace all old lines from n to m with new").addValue("n,m 行");
        builder.addRow().addValue("%s/old/new/g | 1,$ s/old/new/g").addValue("replace all old s in the current file with new").addValue("当前文件");
        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }

    @ShellMethod(value = "vimTableManyLineEdit 多行编辑", key = {"vimTableManyLineEdit"})
    @SuppressWarnings("unchecked")
    public Table vimTableManyLineEdit() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue(" https://blog.csdn.net/u012881904/article/details/90815398");
        builder.addRow().addValue("First: use # Comment Out AllLines ").addValue(" ").addValue(" ");
        builder.addRow().addValue("1、use # Comment Out AllLines").addValue(" 1,$ s/^/#/g ").addValue(" ");
        builder.addRow().addValue("2、remover #  AllLines").addValue("1,$ s/^#//g ").addValue(" ");
        builder.addRow().addValue(" ").addValue(" ").addValue(" ");

        builder.addRow().addValue("Two: use # Comment Out AllLines ").addValue(" ").addValue(" ");
        builder.addRow().addValue("ctrl+ v enter visual block mode").addValue("上下左右键调整需要注释多少行").addValue(" ");
        builder.addRow().addValue("press shift+i or s again to enter insertion mode").addValue("Enter a symbol for the comment, such as#").addValue(" ");
        builder.addRow().addValue("Press ESC  to finish multiline comment (it may take a while）").addValue(" ").addValue(" ");

        builder.addRow().addValue(" remover # Comment Out AllLines ").addValue(" ").addValue(" ");
        builder.addRow().addValue("ctrl+ v enter visual block mode").addValue("上下左右键调整需要注释多少行").addValue(" ");
        builder.addRow().addValue("press (d) again to remove the comment").addValue("使用d快捷删除").addValue(" ");

        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }
}
