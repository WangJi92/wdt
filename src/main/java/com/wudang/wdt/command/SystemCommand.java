package com.wudang.wdt.command;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.NotBlank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 调用系统命名
 *
 * @author jet
 * @date 24-11-2019
 */
@ShellComponent
@ShellCommandGroup(value = "system")
public class SystemCommand {


    /**
     * 调用系统命令
     * @param command
     * @throws IOException
     */
    @ShellMethod(key = {"system", "s"}, value = "输出系统参数eg 'ls -la'")
    public void systemCommand(@ShellOption(value = {"-command"}, help = "输出系统参数eg 'ls -la'") @NotBlank(message = "参数不能为空") String command)
            throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = input.readLine();
        while (line != null) {
            System.out.println(line);
            line = input.readLine();
        }
    }

}
