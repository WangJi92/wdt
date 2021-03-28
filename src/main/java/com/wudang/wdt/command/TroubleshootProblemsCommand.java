package com.wudang.wdt.command;

import com.wudang.wdt.common.utils.ClipboardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * 排查问题常用手段
 *
 * @author 汪小哥
 * @date 14-03-2021
 */
@ShellComponent
@ShellCommandGroup(value = "排查问题常用命令")
public class TroubleshootProblemsCommand {

    @Autowired
    private SystemCommand systemCommand;

    /**
     * linux 的 三驾马车
     */
    @ShellMethod(value = "linux 的三驾马车grep sed awk,需要安装 cheat", key = {"sed-awk-grep"})
    public void sedAwkGrep() {
        try {
            System.out.println("grep 文本过滤工具,用于查找文件里符合条件的字符串");
            systemCommand.systemCommand("cheat grep");
            System.out.println("\n");

            System.out.println("sed 替换处理文本");
            systemCommand.systemCommand("cheat sed");
            System.out.println("\n");

            System.out.println("awk 强大的文本分析工具");
            systemCommand.systemCommand("cheat awk");
        } catch (Exception e) {
            //
        }
    }

    /**
     * 查看tcp 链接建立、关闭、链接数量 netstat -n
     *
     * @return
     */
    @ShellMethod(value = "查看tcp 链接建立、关闭、链接数量 netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'", key = {"tcp-count", "netstat-tcp-cunt"})
    public String tcpCount() {
        StringBuilder buffer = new StringBuilder("netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'");
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " link: https://www.toutiao.com/i6812494040383095308/");
        ClipboardUtils.setClipboardString(buffer.toString());
        return formatStr;
    }

    /**
     * 找出当前路径最大的五个目录
     *
     * @return
     */
    @ShellMethod(value = "找出当前路径最大的五个目录 du -hm . --max-depth=1 | sort -nr | head -5", key = {"du-dir-max5"})
    public String duMaxFiveDir() {
        StringBuilder buffer = new StringBuilder("du -hm . --max-depth=1 | sort -nr | head -5");
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " link: https://www.toutiao.com/i6812494040383095308/");
        ClipboardUtils.setClipboardString(buffer.toString());
        try {
            System.out.println("tldr du");
            systemCommand.systemCommand("tldr du");
            System.out.println("\n");
        } catch (Exception e) {
            //
        }
        try {
            System.out.println("cheat du");
            systemCommand.systemCommand("cheat du");
        } catch (Exception e) {
            //
        }
        System.out.println("\n");
        return formatStr;
    }

    /**
     * 找最大的文件
     *
     * @return
     */
    @ShellMethod(value = "找出当前目录下大于1G的文件 find . -type f -size +1000M", key = {"find-file-max"})
    public String findMaxFile() {
        StringBuilder buffer = new StringBuilder("find . -type f -size +1000M");
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " link: https://www.toutiao.com/i6812494040383095308/");
        ClipboardUtils.setClipboardString(buffer.toString());
        try {
            systemCommand.systemCommand("cheat find");
        } catch (Exception e) {
            //
        }
        System.out.println("\n");
        return formatStr;
    }

    /**
     * 基本的统计功能
     *
     * @return
     */
    @ShellMethod(value = "（常用统计功能）查询访问最频繁的IP awk '{print $1}' access.log | sort | uniq -c | sort -n -k 1 -r | more", key = {"find-ip-max"})
    public String searchForTheMostFrequentlyAccessedIp() {
        StringBuilder buffer = new StringBuilder("awk '{print $1}' access.log | sort | uniq -c | sort -n -k 1 -r | more");
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " link: https://www.toutiao.com/i6812494040383095308/");
        ClipboardUtils.setClipboardString(buffer.toString());
        try {
            systemCommand.systemCommand("cheat awk");
            System.out.println("\n");
            systemCommand.systemCommand("cheat uniq");
            System.out.println("\n");
            systemCommand.systemCommand("cheat sort");
        } catch (Exception e) {
            //
        }
        System.out.println("\n");
        return formatStr;

    }


    /**
     * 如根据访问IP统计UV
     *
     * @return
     */
    @ShellMethod(value = "（常用统计功能）根据访问IP统计UV  awk '{print $1}'  access.log | sort | uniq -c | wc -l", key = {"find-ip-uv"})
    public String uvStatisticsBasedOnIpVisits() {
        StringBuilder buffer = new StringBuilder("awk '{print $1}'  access.log | sort | uniq -c | wc -l");
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, buffer.toString() + " link: https://www.toutiao.com/i6812494040383095308/");
        ClipboardUtils.setClipboardString(buffer.toString());
        try {
            systemCommand.systemCommand("cheat sort");
            System.out.println("\n");
            systemCommand.systemCommand("cheat uniq");
            System.out.println("\n");
            systemCommand.systemCommand("cheat wc");
        } catch (Exception e) {
            //
        }
        System.out.println("\n");
        return formatStr;
    }

    /**
     * 远程复制命令
     */
    @ShellMethod(value = "本地文件复制到远程 scp -r 源目录 用户名@ip:目标目录", key = {"scp"})
    public void scp() {
        try {
            systemCommand.systemCommand("cheat scp");
            System.out.println("\n");

        } catch (Exception e) {
            //
        }
    }

    /**
     * linux 性能优化的常见解法地址
     */
    @ShellMethod(value = "Linux Performance 图片地址", key = {"linux-performance-pic", "lp"})
    public void linuxPerformancePic() {
        System.out.println("性能优化参考图片: http://www.brendangregg.com/Perf/linux_observability_tools.png");
        System.out.println("一般Linux性能调优都用什么工具？ https://www.zhihu.com/question/448362493/answer/1770329163");
    }


}
