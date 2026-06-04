package com.websitemonitor.comparator;

// S1: compare raw byte size only
public class SizeComparator implements ContentComparator{
    @Override
    public boolean hasChanged(String prevContent, String newContent){
        return prevContent.length() != newContent.length();
    }
}