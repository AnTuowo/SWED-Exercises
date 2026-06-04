package com.websitemonitor.comparator;

import org.jsoup.Jsoup;

// S1: compare the text in HTML
public class TextComparator implements ContentComparator{
    @Override
    public boolean hasChanged(String prevContent, String newContent){
        // Jsoup gives you the Document object 
        // .text() strips away the HTML 
        String prevText = Jsoup.parse(prevContent).text();
        String newText  = Jsoup.parse(newContent).text();
        return !prevText.equals(newText);
    }
}   