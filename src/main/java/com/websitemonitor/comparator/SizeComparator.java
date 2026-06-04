package com.websitemonitor.comparator;

public class SizeComparator implements ContentComparator {
    @Override
    public boolean hasChanged(String prevContent, String newContent) {
        return prevContent.length() != newContent.length();
    }
}