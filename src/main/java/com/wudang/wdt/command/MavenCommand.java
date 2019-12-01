package com.wudang.wdt.command;

import com.wudang.wdt.common.utils.ClipboardUtils;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

/**
 * maven command
 *
 * @author jet
 * @date 01-12-2019
 */
@ShellComponent
@ShellCommandGroup(value = "mvn")
public class MavenCommand {


    /**
     * 强制更新maven 跳过测试
     * @return
     */
    @ShellMethod(key = {"mavenInstall", "mi"}, value = "maven clean install skip test")
    public String mavenCleanInstall() {
        String command = "  mvn clean install -Dmaven.test.skip=true -U";
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, command + " ");
        ClipboardUtils.setClipboardString(command);
        return formatStr;
    }

    /**
     * 依赖分析
     * @param fileName
     * @return
     */
    @ShellMethod(key = {"mavenDependencyTree", "md"}, value = "mavenDependencyTree")
    public String mavenDependencyTree(@ShellOption(value = {"-f"}, help = "输入文件地址",defaultValue = "a.txt")String fileName) {
        StringBuilder command = new StringBuilder("mvn dependency:tree");
        if(StringUtils.hasText(fileName)){
            command.append(" > ").append(fileName);
        }
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, command + " ");
        ClipboardUtils.setClipboardString(command.toString());
        return formatStr;
    }
}
