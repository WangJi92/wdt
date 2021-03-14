package com.wudang.wdt.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 快速的发现和使用 命令
 *
 * @author 汪小哥
 * @date 14-03-2021
 */
@ShellComponent
@ShellCommandGroup(value = "quicklyUseCommand")
public class QuicklyUseCommand {

    @Autowired
    private SystemCommand systemCommand;

    /**
     * tldr 记忆一下 方法学习具体的命令
     *
     * @param command
     * @throws IOException
     */
    @ShellMethod(value = "too long, don't read(太长不可阅读),brew install tldr。example: tldr tcpdump", key = {"tldr"})
    public void tldr(@ShellOption(help = "command", value = {"-c"}, defaultValue = "--help") String command) throws IOException {
        System.out.println("tldr [Too Long, Don't Read] https://github.com/tldr-pages/tldr");
        StringBuilder buffer = new StringBuilder("tldr");
        if (StringUtils.hasText(command)) {
            buffer.append(" ").append(command);
        }
        systemCommand.systemCommand(buffer.toString());
    }

    /**
     * cheat 帮助记住命令
     *
     * @param command
     * @throws IOException
     */
    @ShellMethod(value = "help remind commands that they use frequently(简化方便记忆命令),brew install cheat。example: cheat tcpdump", key = {"cheat"})
    public void cheat(@ShellOption(help = "command", value = {"-c"}, defaultValue = "--help") String command) throws IOException {
        System.out.println("cheat [help remind commands that they use frequently]  https://github.com/cheat/cheat");
        StringBuilder buffer = new StringBuilder("cheat");
        if (StringUtils.hasText(command)) {
            buffer.append(" ").append(command);
        }
        systemCommand.systemCommand(buffer.toString());
    }

    /**
     * man find command
     *
     * @param command
     * @throws IOException
     */
    @ShellMethod(value = "linux man command ", key = {"man"})
    public void man(@ShellOption(help = "command", value = {"-c"}, defaultValue = "--help") String command) throws IOException {
        StringBuilder buffer = new StringBuilder("man");
        if (StringUtils.hasText(command)) {
            buffer.append(" ").append(command);
        }
        systemCommand.systemCommand(buffer.toString());
    }
}
