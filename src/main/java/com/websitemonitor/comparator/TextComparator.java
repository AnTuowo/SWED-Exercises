package com.websitemonitor.comparator;

import org.jsoup.Jsoup;

public class TextComparator implements ContentComparator {
    @Override
    public boolean hasChanged(String prevContent, String newContent) {
        String prevText = Jsoup.parse(prevContent).text();
        String newText = Jsoup.parse(newContent).text();
        return !prevText.equals(newText);
    }
}