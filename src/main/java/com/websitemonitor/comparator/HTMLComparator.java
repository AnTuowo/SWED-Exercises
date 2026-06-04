package com.websitemonitor.comparator;

// S2: compare the full raw HTML
public class HTMLComparator implements ContentComparator{
    @Override
    public boolean hasChanged(String prevContent, String newContent){
        return !prevContent.equals(newContent);
    }
}