/*
 * $RCSfile: PlayTemplate.java,v $
 * $Revision: 1.1 $
 *
 * JSP generated by JspCompiler-1.0.0
 */
package com.skin.finder.servlet.template;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * <p>Title: PlayTemplate</p>
 * <p>Description: </p>
 * @author JspKit
 * @version 1.0
 */
public class PlayTemplate extends com.skin.finder.web.servlet.JspServlet {
    private static final long serialVersionUID = 1L;
    private static final PlayTemplate instance = new PlayTemplate();


    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public static void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        instance.service(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        out.write("<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
        out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n<meta http-equiv=\"Cache-Control\" content=\"no-cache\"/>\r\n");
        out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n<title>Finder - ");
        this.print(out, request.getAttribute("path"));
        out.write("</title>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/plugins/media/css/media.css\"/>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/jquery-1.7.2.min.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/widget.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/plugins/media/index.js\"></script>\r\n<script type=\"text/javascript\">\r\n");
        out.write("<!--\r\njQuery(function() {\r\n    jQuery(document.body).css(\"margin\", \"0px\");\r\n    jQuery(document.body).css(\"padding\", \"0px\");\r\n");
        out.write("    jQuery(document.body).css(\"backgroundColor\", \"#333333\");\r\n    jQuery(document.body).css(\"overflow\", \"hidden\");\r\n");
        out.write("});\r\n\r\njQuery(function() {\r\n    var playList = new PlayList();\r\n    var host = document.body.getAttribute(\"host\");\r\n");
        out.write("    var workspace = document.body.getAttribute(\"workspace\");\r\n    var path = document.body.getAttribute(\"path\");\r\n");
        out.write("    var requestURI = window.location.pathname;\r\n\r\n    var prefix = requestURI + \"?action=finder.download&host=\" + encodeURIComponent(host) + \"&workspace=\" + encodeURIComponent(workspace);\r\n");
        out.write("    playList.add({\"title\": \"test\", \"url\": prefix + \"&path=\" + encodeURIComponent(path)});\r\n");
        out.write("\r\n    var videoPlayer = new VideoPlayer({\"container\": \"finder-videodialog\"});\r\n    var container = videoPlayer.getContainer();\r\n");
        out.write("    var parent = jQuery(container);\r\n    parent.css(\"top\", \"0px\");\r\n    parent.css(\"left\", \"0px\");\r\n");
        out.write("    parent.css(\"width\", \"100%\");\r\n    parent.css(\"height\", \"auto\");\r\n    parent.css(\"position\", \"static\");\r\n");
        out.write("    parent.css(\"border\", \"none\");\r\n    parent.find(\"div.title\").remove();\r\n    parent.find(\"video\").attr(\"width\", \"100%\");\r\n");
        out.write("    parent.find(\"video\").attr(\"height\", \"100%\");\r\n\r\n    videoPlayer.setPlayList(playList);\r\n");
        out.write("    videoPlayer.play(0);\r\n    parent.show();\r\n});\r\n\r\njQuery(function() {\r\n    /**\r\n");
        out.write("     * jQuery-1.7.2版本的paste事件获取不到clipboardData\r\n     * 该事件尽可能放到jQuery事件之前\r\n     * bug: IE11不触发paste事件\r\n");
        out.write("     */\r\n    EventUtil.addEventListener(document, \"paste\", function(event) {\r\n        return DialogManager.dispatch(\"paste\", event);\r\n");
        out.write("    });\r\n\r\n    jQuery(document).click(function(event) {\r\n        return DialogManager.dispatch(\"click\", event);\r\n");
        out.write("    });\r\n\r\n    jQuery(document).dblclick(function(event) {\r\n        return DialogManager.dispatch(\"dblclick\", event);\r\n");
        out.write("    });\r\n\r\n    /**\r\n     * keydown事件先于paste触发\r\n     * 因此要保证paste被触发必须使Ctrl + V操作返回true\r\n");
        out.write("     * 如果Ctrl + V事件存在弹框, 那么root将无法捕获到paste事件\r\n     * 因为当弹框出现的时候, 弹框是活动窗口, 因此paste事件不会被传递到root\r\n");
        out.write("     */\r\n    jQuery(document).keydown(function(event) {\r\n        var flag = DialogManager.dispatch(\"keydown\", event);\r\n");
        out.write("        return flag;\r\n    });\r\n\r\n    jQuery(document).bind(\"contextmenu\", function(event) {\r\n");
        out.write("        var e = (event || window.event);\r\n        var src = (e.srcElement || e.target);\r\n");
        out.write("        var nodeName = src.nodeName.toLowerCase();\r\n\r\n        if(nodeName == \"input\" || nodeName == \"textarea\") {\r\n");
        out.write("            return true;\r\n        }\r\n        else {\r\n            return DialogManager.dispatch(\"contextmenu\", e);\r\n");
        out.write("        }\r\n    });\r\n});\r\n//-->\r\n</script>\r\n</head>\r\n<body loacalIp=\"");
        this.print(out, request.getAttribute("loacalIp"));
        out.write("\" contextPath=\"");
        this.print(out, request.getAttribute("contextPath"));
        out.write("\" host=\"");
        this.print(out, request.getAttribute("host"));
        out.write("\" workspace=\"");
        this.print(out, request.getAttribute("workspace"));
        out.write("\" work=\"");
        this.print(out, request.getAttribute("work"));
        out.write("\" parent=\"");
        this.print(out, request.getAttribute("parent"));
        out.write("\" path=\"");
        this.print(out, request.getAttribute("path"));
        out.write("\">\r\n<div id=\"finder-videodialog\" class=\"media-dialog\" contextmenu=\"false\" style=\"z-index: 1010; margin-top: 0px; margin-left: 0px; display: block;\">\r\n");
        out.write("    <div class=\"video-player\" style=\"overflow: hidden;\">\r\n        <div style=\"position: relative;\">\r\n");
        out.write("            <video webkit-playsinline=\"true\">您的浏览器不支持视频播放。</video>\r\n            <div class=\"loading\" style=\"display: none;\"></div>\r\n");
        out.write("        </div>\r\n        <div style=\"padding: 4px 8px 0px 8px;\">\r\n            <div class=\"ctrl\">\r\n");
        out.write("                <div class=\"tag\" style=\"display: none;\"><strong>&nbsp;</strong></div>\r\n");
        out.write("                <div class=\"control\">\r\n                    <div class=\"left\">\r\n                        <div class=\"rewind icon\"></div>\r\n");
        out.write("                        <div class=\"playback icon playing\"></div>\r\n                        <div class=\"fastforward icon\"></div>\r\n");
        out.write("                    </div>\r\n                    <div class=\"right volume\">\r\n                        <div class=\"left mute icon\"></div>\r\n");
        out.write("                        <div class=\"slider left\">\r\n                            <div class=\"pace\" style=\"width: 96%;\"></div>\r\n");
        out.write("                            <a class=\"dot\" href=\"#\" style=\"left: 96%;\"></a>\r\n                        </div>\r\n");
        out.write("                    </div>\r\n                </div>\r\n                <div class=\"progress\">\r\n");
        out.write("                    <div class=\"slider\">\r\n                    <div class=\"loaded\" style=\"width: 100%;\"></div>\r\n");
        out.write("                    <div class=\"pace\" style=\"width: 13.3025%;\"></div>\r\n                        <a class=\"dot\" href=\"#\" style=\"left: 0%;\"></a>\r\n");
        out.write("                    </div>\r\n                    <div class=\"timer left\">00:00/00:00</div>\r\n");
        out.write("                    <div class=\"right\">\r\n                        <div class=\"repeat icon enable\" mode=\"1\" title=\"列表循环\"></div>\r\n");
        out.write("                        <div class=\"shuffle icon\" mode=\"2\" title=\"随机播放\"></div>\r\n");
        out.write("                        <div class=\"repeat once icon\" mode=\"3\" title=\"单曲循环\"></div>\r\n");
        out.write("                        <div class=\"fullscreen icon\" title=\"全屏\"></div>\r\n                    </div>\r\n");
        out.write("                </div>\r\n            </div>\r\n        </div>\r\n        <div class=\"play-list\"><ul></ul></div>\r\n");
        out.write("    </div>\r\n</div>\r\n</body>\r\n</html>\r\n");

        out.flush();
    }


}
