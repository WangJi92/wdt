package com.wudang.wdt.command.table;

import com.wudang.wdt.common.utils.WdtStringUtils;
import org.springframework.shell.table.SizeConstraints;

/**
 * 计算中文的长度
 * @author jet
 * @date 24-11-2019
 */
public class ChinesSizeConstraints implements SizeConstraints {
    @Override
    public Extent width(String[] raw, int tableWidth, int nbColumns) {
        int max = 0;
        int min = 0;
        for (String line : raw) {
            String[] words = line.split(" ");
            for (String word : words) {
                min = Math.max(min, WdtStringUtils.wordLength(word));
            }
            max = Math.max(max, WdtStringUtils.wordLength(line));
        }
        return new Extent(min, max);
    }
}
