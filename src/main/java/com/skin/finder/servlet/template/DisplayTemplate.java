/*
 * $RCSfile: DisplayTemplate.java,v $
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
 * <p>Title: DisplayTemplate</p>
 * <p>Description: </p>
 * @author JspKit
 * @version 1.0
 */
public class DisplayTemplate extends com.skin.finder.web.servlet.JspServlet {
    private static final long serialVersionUID = 1L;
    private static final DisplayTemplate instance = new DisplayTemplate();


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


    String path = (String)(request.getAttribute("path"));
    String theme = (String)(request.getAttribute("theme"));
    String type = (String)(request.getAttribute("type"));
    String encoding = (String)(request.getAttribute("encoding"));
    Long start = (Long)(request.getAttribute("start"));

    if(path == null || (path = path.trim()).length() <= 1) {
        path = "/";
    }

    if(theme == null || (theme = theme.trim()).length() < 1) {
        theme = "Default";
    }

    if(type == null || (type = type.trim()).length() < 1) {
        type = "";
    }

    if(encoding == null || (encoding = encoding.trim()).length() < 1) {
        encoding = "utf-8";
    }

    if(start == null) {
        start = Long.valueOf(0);
    }
    request.setAttribute("path", path);

        out.write("<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\r\n");
        out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n<meta http-equiv=\"Cache-Control\" content=\"no-cache\"/>\r\n");
        out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n<title>");
        this.print(out, request.getAttribute("path"));
        out.write("</title>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/css/finder.css\"/>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/sh/style/shCore");
        this.print(out, request.getAttribute("theme"));
        out.write(".css\"/>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/sh/style/shTheme");
        this.print(out, request.getAttribute("theme"));
        out.write(".css\"/>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/jquery-1.7.2.min.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/ajax.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/widget.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/finder.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/sh/shCore.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/sh/shAutoloader.js\"></script>\r\n</head>\r\n<body style=\"overflow: hidden;\" contextPath=\"");
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
        out.write("\" page=\"display\">\r\n<div class=\"finder\">\r\n    <div class=\"menu-bar\">\r\n        <div style=\"float: left; width: 80px;\">\r\n");
        out.write("            <a class=\"button\" href=\"javascript:void(0)\" title=\"后退\"><span class=\"back\"></span></a>\r\n");
        out.write("            <a class=\"button\" href=\"javascript:void(0)\" title=\"刷新\"><span class=\"refresh\"></span></a>\r\n");
        out.write("        </div>\r\n        <div style=\"float: left; height: 28px; position: relative;\">\r\n");
        out.write("            <div style=\"float: left;\"><input id=\"address\" type=\"text\" class=\"address\" autocomplete=\"off\" file=\"true\" value=\"");
        this.print(out, request.getAttribute("path"));
        out.write("\"/></div>\r\n            <div id=\"finder-suggest\" class=\"list suggest\"></div>\r\n\r\n            <span class=\"label\">theme:</span>\r\n");
        out.write("            <select id=\"uiThemeOption\">\r\n                ");

                    String[] themes = new String[]{"Default", "Django", "Eclipse", "Emacs", "FadeToGrey", "MDUltra", "Midnight", "RDark"};

                    for(String current : themes) {
                
        out.write("                    <option value=\"");
        this.print(out, (current));
        out.write("\" ");
 if(theme.equals(current)) {
        out.write("selected=\"true\"");
}
        out.write(">");
        this.print(out, (current));
        out.write("</option>\r\n                ");

                    }
                
        out.write("            </select>\r\n\r\n            <span class=\"label\">type:</span>\r\n            <select id=\"uiTypeOption\">\r\n");
        out.write("                ");

                    String[] types = new String[]{"", "as", "sh", "bsh", "bash", "log", "shell", "cpp", "cs", "css", "dpi", "diff", "erl", "erlang", "groovy", "java", "js", "pl", "php", "txt", "text", "py", "ruby", "sass", "scala", "sql", "vb", "vbs", "xml", "xhtml", "xslt", "html", "htm", "asp", "jsp", "jspf", "asp", "php"};

                    for(String current : types) {
                
        out.write("                    <option value=\"");
        this.print(out, (current));
        out.write("\" ");
 if(type.equals(current)) {
        out.write("selected=\"true\"");
}
        out.write(">");
        this.print(out, (current));
        out.write("</option>\r\n                ");

                    }
                
        out.write("            </select>\r\n\r\n            <span class=\"label\">encoding:</span>\r\n            <select id=\"uiEncodingOption\">\r\n");
        out.write("                ");

                    String[] encodings = new String[]{"utf-8", "gbk", "gb2312", "iso-8859-1"};

                    for(String current : encodings) {
                
        out.write("                    <option value=\"");
        this.print(out, (current));
        out.write("\" ");
 if(type.equals(current)) {
        out.write("selected=\"true\"");
}
        out.write(">");
        this.print(out, (current));
        out.write("</option>\r\n                ");

                    }
                
        out.write("            </select>\r\n        </div>\r\n        <div style=\"float: right; width: 40px;\">\r\n");
        out.write("            <a class=\"button\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=finder.help\" title=\"帮助\"><span class=\"help\"></span></a>\r\n        </div>\r\n");
        out.write("    </div>\r\n    ");

        if(start > 0L) {
    
        out.write("    <div style=\"padding-left: 4px; height: 28px; line-height: 28px; background-color: #efefef; font-size: 12px;\">\r\n");
        out.write("        文件较大，只显示部分数据。要查看全部数据请使用 <a href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=finder.less&workspace=");
        this.print(out, request.getAttribute("workspace"));
        out.write("&path=");
        this.print(out, request.getAttribute("path"));
        out.write("\" style=\"color: #ff0000;\">less</a> 打开。\r\n        [");
        this.print(out, request.getAttribute("start"));
        out.write(" - ");
        this.print(out, request.getAttribute("end"));
        out.write("/");
        this.print(out, request.getAttribute("length"));
        out.write("]\r\n    </div>\r\n    ");

        }
    
        out.write("    <div id=\"content\" file-type=\"");
        this.print(out, request.getAttribute("type"));
        out.write("\" style=\"display: none;\"><pre class=\"brush: bash;\">");
        this.print(out, request.getAttribute("content"));
        out.write("</pre></div>\r\n</div>\r\n<script type=\"text/javascript\">\r\n<!--\r\njQuery(function() {\r\n");
        out.write("    var map = {\r\n        \"??\": \"brush: bash;\",\r\n        \"as\": \"brush: actionscript3;\",\r\n");
        out.write("        \"bsh\": \"brush: bash;\",\r\n        \"log\": \"brush: bash;\",\r\n        \"cpp\": \"brush: cpp;\",\r\n");
        out.write("        \"cs\": \"brush: cs;\",\r\n        \"css\": \"brush: css;\",\r\n        \"dhi\": \"brush: dpi;\",\r\n");
        out.write("        \"diff\": \"brush: diff;\",\r\n        \"erl\": \"brush: erl;\",\r\n        \"erlang\": \"brush: erlang;\",\r\n");
        out.write("        \"groovy\": \"brush: groovy;\",\r\n        \"java\": \"brush: java;\",\r\n        \"js\": \"brush: javascript;\",\r\n");
        out.write("        \"pl\": \"brush: perl;\",\r\n        \"php\": \"brush: php;\",\r\n        \"plain\": \"brush: plain;\",\r\n");
        out.write("        \"sh\": \"brush: bash;\",\r\n        \"py\": \"brush: python;\",\r\n        \"ruby\": \"brush: ruby;\",\r\n");
        out.write("        \"sass\": \"brush: sass;\",\r\n        \"scala\": \"brush: scala;\",\r\n        \"sql\": \"brush: sql;\",\r\n");
        out.write("        \"vb\": \"brush: vbscript;\",\r\n        \"vbs\": \"brush: vbscript;\",\r\n        \"xml\": \"brush: xml;\",\r\n");
        out.write("        \"xhtml\": \"brush: xml;\",\r\n        \"xslt\": \"brush: xml;\",\r\n        \"html\": \"brush: xml;\",\r\n");
        out.write("        \"htm\": \"brush: xml;\",\r\n        \"jsp\": \"brush: xml;\",\r\n        \"jspf\": \"brush: xml;\",\r\n");
        out.write("        \"asp\": \"brush: xml;\",\r\n        \"php\": \"brush: xml;\"\r\n    };\r\n\r\n    var type = jQuery(\"#content\").attr(\"file-type\");\r\n");
        out.write("\r\n    if(type == \"??\") {\r\n        jQuery(\"#content pre\").attr(\"class\", \"brush: bash;\");\r\n");
        out.write("    }\r\n    else {\r\n        var brush = map[type];\r\n\r\n        if(type != null) {\r\n");
        out.write("            jQuery(\"#content pre\").attr(\"class\", brush);\r\n        }\r\n        else {\r\n");
        out.write("            jQuery(\"#content pre\").attr(\"class\", \"brush: plain;\");\r\n        }\r\n    }\r\n");
        out.write("});\r\n\r\njQuery(function() {\r\n    function path() {\r\n        var result = [];\r\n        var args = arguments;\r\n");
        out.write("        var requestURI = window.location.pathname;\r\n\r\n        for(var i = 0; i < args.length; i++) {\r\n");
        out.write("            result.push(args[i].replace(\"@\", requestURI + \"?action=res&path=/sh/\"));\r\n");
        out.write("        }\r\n        return result;\r\n    };\r\n\r\n    var args = path(\r\n        \"applescript            @shBrushAppleScript.js\",\r\n");
        out.write("        \"actionscript3 as3      @shBrushAS3.js\",\r\n        \"bash shell             @shBrushBash.js\",\r\n");
        out.write("        \"coldfusion cf          @shBrushColdFusion.js\",\r\n        \"cpp c                  @shBrushCpp.js\",\r\n");
        out.write("        \"c# c-sharp csharp      @shBrushCSharp.js\",\r\n        \"css                    @shBrushCss.js\",\r\n");
        out.write("        \"delphi pascal          @shBrushDelphi.js\",\r\n        \"diff patch pas         @shBrushDiff.js\",\r\n");
        out.write("        \"erl erlang             @shBrushErlang.js\",\r\n        \"groovy                 @shBrushGroovy.js\",\r\n");
        out.write("        \"java                   @shBrushJava.js\",\r\n        \"jfx javafx             @shBrushJavaFX.js\",\r\n");
        out.write("        \"js jscript javascript  @shBrushJScript.js\",\r\n        \"perl pl                @shBrushPerl.js\",\r\n");
        out.write("        \"php                    @shBrushPhp.js\",\r\n        \"text plain             @shBrushPlain.js\",\r\n");
        out.write("        \"py python              @shBrushPython.js\",\r\n        \"ruby rails ror rb      @shBrushRuby.js\",\r\n");
        out.write("        \"sass scss              @shBrushSass.js\",\r\n        \"scala                  @shBrushScala.js\",\r\n");
        out.write("        \"sql                    @shBrushSql.js\",\r\n        \"vb vbnet               @shBrushVb.js\",\r\n");
        out.write("        \"xml xhtml xslt html    @shBrushXml.js\"\r\n    );\r\n    SyntaxHighlighter.autoloader.apply(null, args);\r\n");
        out.write("    SyntaxHighlighter.all();\r\n});\r\n\r\njQuery(function() {\r\n    jQuery(window).resize(function(){\r\n");
        out.write("        var c = jQuery(\"#content div.syntaxhighlighter\");\r\n        c.css(\"overflow\", \"auto\");\r\n");
        out.write("        c.height(jQuery(window).height() - 38);\r\n    });\r\n\r\n    setTimeout(function() {\r\n");
        out.write("        jQuery(window).resize();\r\n        jQuery(\"#content\").show();\r\n    }, 500);\r\n");
        out.write("});\r\n//-->\r\n</script>\r\n</body>\r\n</html>");

        out.flush();
    }


}
