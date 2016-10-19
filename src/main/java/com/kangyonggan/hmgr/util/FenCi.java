package com.kangyonggan.hmgr.util;


import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/7/22
 */
public class FenCi {

    /**
     * 分词器
     */
    private static final JiebaSegmenter segmenter = new JiebaSegmenter();

    /**
     * 中文分词, 并转成拼音
     *
     * @param data
     * @return
     */
    public static String process(String data) {
        if (StringUtils.isEmpty(data)) {
            return "";
        }

        data = data.replaceAll("\\s", " ");
        data = data.replaceAll("'", " ");

        List<SegToken> list = segmenter.process(data, JiebaSegmenter.SegMode.INDEX);

        StringBuilder sb = new StringBuilder();
        list.forEach(segToken -> sb.append(PinYin.converterToSpellWithMuti(segToken.word)).append(","));
        sb.deleteCharAt(sb.lastIndexOf(","));

        return sb.toString();
    }

}
