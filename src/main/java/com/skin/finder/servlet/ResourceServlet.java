/*
 * $RCSfile: ResourceServlet.java,v $
 * $Revision: 1.1 $
 * $Date: 2010-04-28 $
 *
 * Copyright (C) 2008 Skin, Inc. All rights reserved.
 *
 * This software is the proprietary information of Skin, Inc.
 * Use is subject to license terms.
 */
package com.skin.finder.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skin.finder.ContentEntry;
import com.skin.finder.util.MimeType;
import com.skin.finder.web.ResourceManager;
import com.skin.finder.web.UrlPattern;
import com.skin.finder.web.servlet.BaseServlet;

/**
 * <p>Title: ResourceServlet</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * @author xuesong.net
 * @version 1.0
 */
public class ResourceServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ResourceServlet.class);
    private static final int MAX_AGE = 7 * 24 * 60 * 60;
    private static final ResourceManager resourceManager = getResourceManager("/resource");

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @UrlPattern("res")
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        ContentEntry contentEntry = resourceManager.get(path);

        if(contentEntry == null) {
            response.setHeader("NotFound", path);
            response.sendError(404);
            return;
        }

        long lastModified = contentEntry.getLastModified();
        String contentType = MimeType.getMimeType(contentEntry.getName());
        String eTag = "W/\"f-" + lastModified + "\"";

        if(!FileServlet.checkIfHeaders(request, response, eTag, lastModified)) {
            return;
        }

        response.addHeader("Cache-Control", "max-age=" + MAX_AGE);
        response.addHeader("Cache-Control", "public");
        response.setDateHeader("Expires", lastModified + (MAX_AGE * 1000));
        response.setDateHeader("Last-Modified", lastModified);
        response.setDateHeader("Date", lastModified);
        response.setHeader("ETag", eTag);
        response.setContentType(contentType);

        int type = contentEntry.getType();
        byte[] bytes = contentEntry.getBytes();
        OutputStream outputStream = response.getOutputStream();
        String acceptEncoding = request.getHeader("Accept-Encoding");

        if(type == ContentEntry.ZIP) {
            if(acceptEncoding != null && acceptEncoding.indexOf("gzip") > -1) {
                response.setContentLength(bytes.length);
                response.setHeader("Content-Encoding", "gzip");
                outputStream.write(bytes, 0, bytes.length);
            }
            else {
                byte[] buffer = ResourceManager.ungzip(bytes);
                response.setContentLength(buffer.length);
                outputStream.write(buffer, 0, buffer.length);
            }
        }
        else {
            response.setContentLength(bytes.length);
            outputStream.write(bytes, 0, bytes.length);
        }
        outputStream.flush();
    }

    /**
     * @param timeMillis
     * @return String
     */
    protected String format(long timeMillis) {
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(new Date(timeMillis));
    }

    /**
     * @param home
     * @return ResourceManager
     */
    public static ResourceManager getResourceManager(String home) {
        File file = ResourceManager.getJarFile(ResourceServlet.class);

        if(file == null) {
            logger.warn("finder-web.xxxx.jar not found.");
            return null;
        }

        logger.warn("load resource: {}", file.getAbsolutePath());

        try {
            return new ResourceManager(file.getAbsolutePath(), home);
        }
        catch(Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
