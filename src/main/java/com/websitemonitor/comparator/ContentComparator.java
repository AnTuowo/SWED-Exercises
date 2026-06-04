package com.websitemonitor.comparator;

public interface ContentComparator {
    boolean hasChanged(String prevContent, String newContent);
}