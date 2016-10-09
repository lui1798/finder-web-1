/*
 * $RCSfile: FileRange.java,v $$
 * $Revision: 1.1 $
 * $Date: 2016-10-3 $
 *
 * Copyright (C) 2008 Skin, Inc. All rights reserved.
 *
 * This software is the proprietary information of Skin, Inc.
 * Use is subject to license terms.
 */
package com.skin.finder;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>Title: FileRange</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * @author xuesong.net
 * @version 1.0
 */
public class FileRange {
    private long start;
    private long end;
    private long length;
    private int rows;
    private byte[] buffer;
    private String charset;
    private static final Logger logger = LoggerFactory.getLogger(FileRange.class);

    /**
     * @return the start
     */
    public long getStart() {
        return this.start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public long getEnd() {
        return this.end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * @return the length
     */
    public long getLength() {
        return this.length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return the buffer
     */
    public byte[] getBuffer() {
        return this.buffer;
    }

    /**
     * @param buffer the buffer to set
     */
    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return this.charset;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * @return String
     */
    public String getContent() {
        if(this.charset != null) {
            return this.getContent(this.charset);
        }
        else {
            return this.getContent("utf-8");
        }
    }

    /**
     * @param charset
     * @return String
     */
    public String getContent(String charset) {
        if(this.buffer == null) {
            return "";
        }

        try {
            return new String(this.buffer, 0, (int)(this.end - this.start + 1), charset);
        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }
}