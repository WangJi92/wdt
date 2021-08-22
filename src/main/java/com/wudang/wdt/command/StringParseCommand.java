package com.wudang.wdt.command;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.wudang.wdt.common.utils.ClipboardUtils;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 字符串解析命令处理
 *
 * @author 汪小哥
 * @date 22-08-2021
 */
@ShellComponent(value = "字符串解析,使用场景Excel 获取列数据复制到剪切板后构造写SQl->列表转换为构造SQL的格式")
@ShellCommandGroup(value = "TextParse")
public class StringParseCommand {
    /**
     * 缓存的数据
     */
    public static ParseListData data = new ParseListData();


    @ShellMethod(value = "字符串解析方便构造从Excel中复制的列数据构造sql", key = {"textParse", "tp"})
    public String textParse(
            @ShellOption(help = "删除第一个数据", value = {"--removeHead"}) boolean removeHead,
            @ShellOption(help = "删除最后一个数据", value = {"--removeTail"}) boolean removeTail,
            @ShellOption(help = "展示当前的数据", value = {"--show"}) boolean show,
            @ShellOption(help = "separator 分割符默认换行符", value = {"-separator"}, defaultValue = "\n") String separator,
            @ShellOption(help = "数据连接表达式", value = {"-joinType"}, defaultValue = "dataJoinAuto", valueProvider = TextParseValuesProvider.class) String joinType,
            @ShellOption(help = "解析后的数据连接加上小挎号", value = {"--joinWithParentheses"}, defaultValue = "true") boolean joinWithParentheses) {

        String readyToParseStr = ClipboardUtils.getClipboardString();
        if (StringUtils.hasText(readyToParseStr) && !readyToParseStr.startsWith("(")) {
            data = new ParseListData(readyToParseStr, separator);
        }
        if (show) {
            return data.getShowView();
        }
        if (removeHead) {
            data.removeHead();
        }
        if (removeTail) {
            data.removeTail();
        }
        String joinData = data.getJoinData(joinType);
        if (joinWithParentheses && StringUtils.hasText(joinData)) {
            joinData = "(" + joinData + ")";
        }
        String showJoinDataLittle = joinData.length() < 100 ? joinData + "......" : joinData.substring(0, 100);
        String formatStr = String.format(ClipboardUtils.CLIPBOARD_TEXT, "解析的数据" + showJoinDataLittle);
        ClipboardUtils.setClipboardString(joinData);
        return formatStr;
    }

    /**
     * textParse 提示命令行处理
     */
    @Component
    public static class TextParseValuesProvider extends ValueProviderSupport {

        @Override
        public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
            List<CompletionProposal> completionProposals = Lists.newArrayList();
            CompletionProposal completionProposal = null;
            completionProposal = new CompletionProposal("'dataJoinAuto");
            completionProposal.dontQuote(true).description("自动识别，是数字不需要单引号,是字符串增加单引号");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal("dataJoinWithComma");
            completionProposal.dontQuote(true).description("原数据逗号分割 a,b,c");
            completionProposals.add(completionProposal);

            completionProposal = new CompletionProposal("dataJoinWithCommaAndSingleQuote");
            completionProposal.dontQuote(true).description("原数据添加单引号后,逗号分割 eg 'a','b','c'");
            completionProposals.add(completionProposal);

            return completionProposals;
        }
    }


    /**
     * 解析数据载体
     */
    @Data
    public static class ParseListData implements Serializable {
        public ParseListData() {
        }

        /**
         * 解析的数据
         *
         * @param sourceData
         * @param separator
         */
        public ParseListData(String sourceData, String separator) {
            this.sourceData = sourceData;
            this.separator = separator;
            if (StringUtils.hasText(sourceData)) {
                this.afterParseList = Splitter.on(separator).omitEmptyStrings().trimResults().splitToList(sourceData);
                this.afterParseList = Lists.newArrayList(afterParseList);
            }
        }

        /**
         * 源数据
         */
        private String sourceData;
        /**
         * 分隔符
         */
        private String separator;
        /**
         * 解析后的数据
         */
        private List<String> afterParseList;

        /**
         * 删除头数据
         */
        public void removeHead() {
            if (!CollectionUtils.isEmpty(afterParseList)) {
                afterParseList.remove(0);
            }

        }

        /**
         * 删除尾部信息
         */
        public void removeTail() {
            if (!CollectionUtils.isEmpty(afterParseList)) {
                afterParseList.remove(afterParseList.size() - 1);
            }
        }

        public String getShowView() {
            if (!CollectionUtils.isEmpty(afterParseList)) {
                if (afterParseList.size() <= 8) {
                    return Joiner.on("\n").join(afterParseList);
                } else {
                    List<String> showViewList = Lists.newLinkedList();
                    showViewList.add("all list size is " + afterParseList.size());
                    showViewList.add(afterParseList.get(0));
                    showViewList.add(afterParseList.get(1));
                    showViewList.add(afterParseList.get(2));
                    showViewList.add(afterParseList.get(3));
                    showViewList.add(afterParseList.get(4));
                    showViewList.add(".......");
                    showViewList.add(afterParseList.get(afterParseList.size() - 4));
                    showViewList.add(afterParseList.get(afterParseList.size() - 3));
                    showViewList.add(afterParseList.get(afterParseList.size() - 2));
                    showViewList.add(afterParseList.get(afterParseList.size() - 1));
                    return Joiner.on("\n").join(showViewList);
                }
            }
            return "parse data list size is empty";
        }

        /**
         * 获取连接处理的数据
         *
         * @param joinType
         * @return
         */
        public String getJoinData(String joinType) {
            if (CollectionUtils.isEmpty(afterParseList)) {
                return "";
            }
            String joinData = "";
            switch (joinType) {

                case "dataJoinAuto": {
                    String dataFirst = afterParseList.get(0);
                    if (NumberUtils.isDigits(dataFirst)) {
                        joinData = this.getJoinData("dataJoinWithComma");
                    } else {
                        joinData = this.getJoinData("dataJoinWithCommaAndSingleQuote");
                    }
                    break;

                }
                case "dataJoinWithComma": {
                    joinData = Joiner.on(",").join(afterParseList);
                    break;

                }
                case "dataJoinWithCommaAndSingleQuote": {
                    joinData = "'" + Joiner.on("',").join(afterParseList) + "'";
                    break;

                }
                default: {
                    joinData = this.getJoinData("dataJoinAuto");
                    break;
                }

            }
            return joinData;

        }


    }


}
