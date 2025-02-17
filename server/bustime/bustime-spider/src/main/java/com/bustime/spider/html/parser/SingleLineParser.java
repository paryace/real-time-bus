/*
 * Copyright (c) 2012 Shanda Corporation. All rights reserved.
 *
 * Created on 2012-11-22.
 */

package com.bustime.spider.html.parser;

import static com.bustime.spider.html.parser.meta.CharacterEncode.UTF8;
import static com.bustime.spider.html.parser.meta.ParserUrl.SingleLineUrl;

import java.util.List;
import java.util.Map;

import org.htmlparser.Parser;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bustime.common.model.SingleLine;

/**
 * TODO.
 *
 * @author chengdong
 */
@Service
public class SingleLineParser extends BaseParser<SingleLine> {

    public SingleLineParser() {
        colMap.put(0, "link");
        colMap.put(1, "standCode");
        colMap.put(2, "busNumber");
        colMap.put(3, "time");
    }

    @Override
    public SingleLine parseObject(String jsonString) {
        return JSON.parseObject(jsonString, SingleLine.class);
    }

    /**
     * 解析站点
     * @param url
     * @throws Exception
     */
    public String parser(String lineGuid) throws Exception {
        Parser parser = new Parser(SingleLineUrl.getUrl() + lineGuid);
        parser.setEncoding(UTF8.getEncode());
        List<Map<String, String>> data = parserSpanTable(parser, 1);

        return JSON.toJSONString(data);
    }

    public static void main(String args[]) throws Exception {
        SingleLineParser parser = new SingleLineParser();
        List<SingleLine> lines = parser.getData("d6700f1f-0d6c-415d-8d32-b656b0fe0481");

        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }

    }

}
