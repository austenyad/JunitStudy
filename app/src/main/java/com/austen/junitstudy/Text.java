package com.austen.junitstudy;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/23 17:09
 * description :
 */
public class Text {
    private String content;

    public Text(String content) {
        this.content = content;
    }


    public Integer toNumber() {
        if (content == null || content.isEmpty()) {
            return null;
        }
        String trim = content.trim();
        char[] chars = trim.toCharArray();
        for (char charAt : chars) {
            if (charAt < 48 || charAt > 57) {
                return null;
            }
        }
        return Integer.valueOf(trim);
    }

}
