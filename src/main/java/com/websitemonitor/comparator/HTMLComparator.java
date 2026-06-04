package com.websitemonitor.comparator;

public class HTMLComparator implements ContentComparator {
    @Override
    public boolean hasChanged(String prevContent, String newContent) {
        return !prevContent.equals(newContent);
    }
}