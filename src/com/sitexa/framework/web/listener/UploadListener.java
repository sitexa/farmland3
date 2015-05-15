package com.sitexa.framework.web.listener;

/**
 * User: xnpeng
 * Date: 2009-4-22
 * Time: 15:41:19
 */
public class UploadListener implements org.apache.commons.fileupload.ProgressListener {
    private volatile long bytesRead;
    private volatile long contentLength;
    private volatile long item;

    public UploadListener() {
        bytesRead = 0;
        contentLength = 0;
        item = 0;
    }

    public void update(long i_bytesRead, long i_contentLength, int i_item) {
        bytesRead = i_bytesRead;
        contentLength = i_contentLength;
        item = i_item;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public long getItem() {
        return item;
    }
}

