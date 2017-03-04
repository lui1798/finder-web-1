/*
 * $RCSfile: FinderManager.java,v $$
 * $Revision: 1.1 $
 * $Date: 2013-4-2 $
 *
 * Copyright (C) 2008 Skin, Inc. All rights reserved.
 *
 * This software is the proprietary information of Skin, Inc.
 * Use is subject to license terms.
 */
package com.skin.finder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skin.util.Path;

/**
 * <p>Title: FinderManager</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * @author xuesong.net
 * @version 1.0
 */
public class FinderManager {
    private String work;
    private static final Logger logger = LoggerFactory.getLogger(FinderManager.class);

    /**
     * @param work
     */
    public FinderManager(String work) {
        this.work = work;
    }

    /**
     * @param src
     * @return Map<String, Object>
     */
    public Map<String, Object> list(String src) {
        String realPath = this.getRealPath(src);

        if(realPath == null) {
            return null;
        }

        File dir = new File(realPath);
        String path = Path.getRelativePath(this.work, realPath);
        String parent = Path.getParent(path);
        File[] fileList = dir.listFiles();
        List<FileItem> fileItemList = new ArrayList<FileItem>();

        if(fileList != null) {
            if(System.getProperty("os.name").indexOf("Windows") < 0) {
                Arrays.sort(fileList, FileComparator.getInstance());
            }

            for(File file : fileList) {
                if(!file.isFile()) {
                    FileItem fileItem = new FileItem();
                    fileItem.setFileName(file.getName());
                    fileItem.setFileType(null);
                    fileItem.setFileIcon("folder");
                    fileItem.setFileSize(0L);
                    fileItem.setLastModified(file.lastModified());
                    fileItem.setIsFile(false);
                    fileItemList.add(fileItem);
                }
            }

            for(File file : fileList) {
                if(file.isFile()) {
                    FileItem fileItem = new FileItem();
                    fileItem.setFileName(file.getName());
                    fileItem.setFileIcon(FileType.getIcon(file.getName()));
                    fileItem.setFileType(FileType.getType(file.getName()));
                    fileItem.setFileSize(file.length());
                    fileItem.setLastModified(file.lastModified());
                    fileItem.setIsFile(true);
                    fileItemList.add(fileItem);
                }
            }
        }

        Map<String, Object> context = new HashMap<String, Object>();
        context.put("work", this.work);
        context.put("path", path);
        context.put("parent", parent);
        context.put("fileList", fileItemList);
        return context;
    }
    
    /**
     * @param list
     * @param listUrl
     * @param xmlUrl
     * @return String
     */
    public static String getWorkspaceXml(List<String> list, String listUrl, String xmlUrl) {
        StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buffer.append("<tree>\r\n");

        for(String workspace : list) {
            String name = encode(workspace);
            buffer.append("<treeNode");
            buffer.append(" icon=\"folder.gif\"");
            buffer.append(" value=\"");
            buffer.append(name);
            buffer.append("\"");
            buffer.append(" title=\"");
            buffer.append(name);
            buffer.append("\"");
            buffer.append(" href=\"");
            buffer.append(listUrl);
            buffer.append("&amp;workspace=");
            buffer.append(name);
            buffer.append("\"");
            buffer.append(" nodeXmlSrc=\"");
            buffer.append(xmlUrl);
            buffer.append("&amp;workspace=");
            buffer.append(name);
            buffer.append("\"/>");
        }
        buffer.append("</tree>");
        return buffer.toString();
    }

    /**
     * @param workspace
     * @param path
     * @param listUrl
     * @param xmlUrl
     * @return String
     */
    public String getFolderXml(String workspace, String path, String listUrl, String xmlUrl) {
        String realPath = this.getRealPath(path);
        StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buffer.append("<tree>\r\n");

        if(realPath != null) {
            File dir = new File(realPath);
            File[] fileList = dir.listFiles();

            if(fileList != null) {
                if(System.getProperty("os.name").indexOf("Windows") < 0) {
                    Arrays.sort(fileList, FileComparator.getInstance());
                }

                String relativePath = Path.getRelativePath(this.work, realPath);

                for(File file : fileList) {
                    if(file.isDirectory()) {
                        String name = encode(file.getName());
                        String url = encode(urlEncode(relativePath + "/" + file.getName(), "utf-8"));
                        buffer.append("<treeNode");
                        buffer.append(" icon=\"folder.gif\"");
                        buffer.append(" value=\"");
                        buffer.append(name);
                        buffer.append("\"");
                        buffer.append(" title=\"");
                        buffer.append(name);
                        buffer.append("\"");
                        buffer.append(" href=\"");
                        buffer.append(listUrl);
                        buffer.append("&amp;workspace=");
                        buffer.append(encode(workspace));
                        buffer.append("&amp;path=");
                        buffer.append(url);
                        buffer.append("\"");

                        if(this.hasChildFolder(file)) {
                            buffer.append(" nodeXmlSrc=\"");
                            buffer.append(xmlUrl);
                            buffer.append("&amp;workspace=");
                            buffer.append(encode(workspace));
                            buffer.append("&amp;path=");
                            buffer.append(url);
                            buffer.append("\"");
                        }
                        buffer.append("/>\r\n");
                    }
                }
            }
        }
        buffer.append("</tree>");
        return buffer.toString();
    }

    /**
     * @param file
     * @param name
     * @return int
     */
    public int rename(String file, String name) {
        String realPath = this.getRealPath(file);

        if(realPath == null) {
            return 0;
        }

        if(name.endsWith(".link.tail")) {
            return 0;
        }

        File oldFile = new File(realPath);
        File newFile = new File(oldFile.getParent(), name);
        logger.info("rename {} to {}", oldFile.getAbsolutePath(), newFile.getAbsolutePath());

        if(newFile.exists()) {
            return 0;
        }

        oldFile.renameTo(newFile);
        return (newFile.exists() ? 1 : 0);
    }

    /**
     * @param file
     * @param name
     * @return int
     */
    public boolean mkdir(String file, String name) {
        String realPath = this.getRealPath(file);

        if(realPath == null) {
            return false;
        }

        int i = 1;
        File dir = new File(realPath, name);
        logger.info("mkdir {}", dir.getAbsolutePath());

        while(dir.exists()) {
            dir = new File(realPath, name + "(" + i + ")");
            i++;
        }
        return dir.mkdir();
    }

    /**
     * @param source
     * @param target
     * @param offset
     */
    public void write(File source, File target, long offset) {
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(source);
            this.write(inputStream, target, offset);
        }
        catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                }
                catch(IOException e) {
                }
            }
        }
    }

    /**
     * @param inputStream
     * @param file
     * @param offset
     */
    public void write(InputStream inputStream, File file, long offset) {
        RandomAccessFile raf = null;
        logger.debug("write - offset: {}, path: {}", offset, file.getAbsolutePath());

        try {
            raf = new RandomAccessFile(file, "rw");
            raf.setLength(offset);

            if(offset > 0) {
                raf.seek(offset);
            }
            copy(inputStream, raf, 8192);
        }
        catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if(raf != null) {
                try {
                    raf.close();
                }
                catch(IOException e) {
                }
            }
        }
    }

    /**
     * @param path
     * @return int
     */
    public int delete(String path) {
        String realPath = this.getRealPath(path);

        if(realPath == null) {
            return 0;
        }

        File file = new File(realPath);

        if(file.isFile()) {
            try {
                boolean flag = file.delete();

                if(!flag) {
                    logger.warn("delete file " + file.getAbsolutePath() + " failed !");
                }
            }
            catch(Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
        else {
            this.delete(file);
        }
        return (file.exists() ? 0 : 1);
    }

    /**
     * @param file
     */
    public void delete(File file) {
        if(file.isDirectory()) {
            File[] files = file.listFiles();

            for(File f : files) {
                if(f.isDirectory()) {
                    delete(f);
                }
                else {
                    boolean flag = f.delete();

                    if(!flag) {
                        logger.warn("delete file " + file.getAbsolutePath() + " failed !");
                    }
                }
            }
        }

        boolean flag = file.delete();

        if(!flag) {
            logger.warn("delete file " + file.getAbsolutePath() + " failed !");
        }
    }
    
    /**
     * @param inputStream
     * @param raf
     * @param bufferSize
     * @throws IOException
     */
    public void copy(InputStream inputStream, RandomAccessFile raf, int bufferSize) throws IOException {
        int length = 0;
        byte[] buffer = new byte[bufferSize];

        while((length = inputStream.read(buffer, 0, bufferSize)) > 0) {
            raf.write(buffer, 0, length);
        }
    }

    /**
     * @param workspace
     * @param path
     * @return Object
     */
    public List<String> suggest(String workspace, String path) {
        String realPath = this.getRealPath(path);
        List<String> json = new ArrayList<String>();

        if(realPath != null) {
            File[] result = null;
            File file = new File(realPath);
            List<File> list = new ArrayList<File>();

            if(file.exists()) {
                if(file.isDirectory()) {
                    result = file.listFiles();
                }
                else {
                    String prefix = file.getName().toLowerCase();
                    String encoding = System.getProperty("file.encoding");
                    File[] fileList = file.getParentFile().listFiles();
    
                    if(encoding.equals("utf-8")) {
                        for(File f : fileList) {
                            String fileName = f.getName();
    
                            if(fileName.toLowerCase().startsWith(prefix)) {
                                list.add(f);
                            }
                        }
                    }
                    else {
                        try {
                            for(File f : fileList) {
                                String fileName = new String(f.getName().getBytes("utf-8"), "utf-8");
    
                                if(fileName.toLowerCase().startsWith(prefix)) {
                                    list.add(f);
                                }
                            }
                        }
                        catch(UnsupportedEncodingException e) {
                        }
                    }
    
                    result = new File[list.size()];
                    list.toArray(result);
                }
            }

            if(result != null) {
                if(System.getProperty("os.name").indexOf("Windows") < 0) {
                    Arrays.sort(result, FileComparator.getInstance());
                }

                for(int i = 0; i < result.length; i++) {
                    json.add(result[i].getName());
                }
            }
        }
        return json;
    }

    /**
     * @param workspace
     * @param path
     * @param searchment
     * @return Object
     */
    public Object find(String workspace, String path, String searchment) {
        String realPath = this.getRealPath(path);
        List<File> result = new ArrayList<File>();

        if(realPath == null) {
            return result;
        }

        File file = new File(realPath);

        if(file.isDirectory() == false) {
            return result;
        }

        List<String> stack = new ArrayList<String>();
        stack.add(file.getAbsolutePath());

        for(int i = 0; i < stack.size(); i++) {
            File[] list = new File(stack.get(i)).listFiles();

            if(list != null) {
                for(File f : list) {
                    if(f.isDirectory()) {
                        stack.add(f.getAbsolutePath());
                    }

                    if(f.getName().toLowerCase().indexOf(searchment) > -1) {
                        result.add(f);
                    }
                }
            }
        }

        for(int i = stack.size() - 1; i > -1; i--) {
            File f = new File(stack.get(i));

            if(f.getName().toLowerCase().indexOf(searchment) > -1) {
                result.add(f);
            }
        }

        if(System.getProperty("os.name").indexOf("Windows") < 0) {
            File[] list = new File[result.size()];
            result.toArray(list);
            Arrays.sort(list, FileComparator.getInstance());
            return list;
        }
        return result;
    }

    /**
     * @param file
     * @return boolean
     */
    private boolean hasChildFolder(File file) {
        File[] fileList = file.listFiles();

        for(File f : fileList) {
            if(f.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param path
     * @return String
     */
    public String getRelativePath(String path) {
        return Path.getRelativePath(this.work, path);
    }
    
    /**
     * @param path
     * @return String
     */
    public String getRealPath(String path) {
        String temp = null;

        if(path != null) {
            temp = Path.getStrictPath(path).trim();
        }
        else {
            temp = "/";
        }

        if(temp.length() < 1 || temp.equals("/")) {
            return this.work;
        }

        String work = Path.join(this.work, "");
        String full = Path.join(this.work, temp);

        if(full.startsWith(work)) {
            return full;
        }
        else {
            return null;
        }
    }

    /**
     * @param source
     * @return String
     */
    private static String encode(String source) {
        if(source == null) {
            return "";
        }

        StringBuilder buffer = new StringBuilder();

        for(int i = 0, length = source.length(); i < length; i++) {
            char c = source.charAt(i);

            switch(c) {
                case '"':
                    buffer.append("&quot;");
                    break;
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case '\'':
                    buffer.append("&#39;");
                    break;
                default:
                    buffer.append(c);
                    break;
            }
        }

        return buffer.toString();
    }

    /**
     * @param source
     * @param encoding
     * @return String
     */
    public static String urlEncode(String source, String encoding) {
        try {
            return URLEncoder.encode(source, encoding);
        }
        catch(UnsupportedEncodingException e) {
        }
        return "";
    }

    /**
     * @return the work
     */
    public String getWork() {
        return this.work;
    }

    /**
     * @param work the work to set
     */
    public void setHome(String work) {
        this.work = work;
    }
}
