/*
 * $RCSfile: TreeTemplate.java,v $
 * $Revision: 1.1 $
 * $Date: 2017-08-05 $
 *
 * JSP generated by JspCompiler-1.0.0 (built 2017-08-05 00:43:18 569)
 */
package com.skin.finder.servlet.template;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skin.finder.i18n.I18N;
import com.skin.finder.i18n.LocalizationContext;


/**
 * <p>Title: TreeTemplate</p>
 * <p>Description: </p>
 * @author JspKit
 * @version 1.0
 */
public class TreeTemplate extends com.skin.finder.web.servlet.JspServlet {
    private static final long serialVersionUID = 1L;
    private static final TreeTemplate instance = new TreeTemplate();


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


    LocalizationContext i18n = I18N.getBundle(request);

        out.write("<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
        out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n<meta http-equiv=\"Cache-Control\" content=\"no-cache\"/>\r\n");
        out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n<title>Finder v1.0</title>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/css/finder.css\"/>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/htree/css/htree.css\"/>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/htree/htree.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/htree/htree.util.js\"></script>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/jquery-1.7.2.min.js\"></script>\r\n<script type=\"text/javascript\">\r\n");
        out.write("//<![CDATA[\r\nHTree.click = function(src) {\r\n    var url = src.getAttribute(\"data\");\r\n");
        out.write("\r\n    if(url == null || url.length < 1) {\r\n        return;\r\n    }\r\n\r\n    try {\r\n");
        out.write("        var doc = window.parent.window.document;\r\n        var iframe = doc.getElementById(\"mainFrame\");\r\n");
        out.write("\r\n        if(iframe != null) {\r\n            iframe.src = url;\r\n        }\r\n        else {\r\n");
        out.write("            alert(\"系统错误，请稍后再试！\");\r\n        }\r\n    }\r\n    catch(e) {\r\n        if(typeof(window.console) != \"undefined\") {\r\n");
        out.write("            window.console.error(e.name + \": \" + e.message);\r\n        }\r\n        alert(\"系统错误，请稍后再试！\");\r\n");
        out.write("    }\r\n};\r\n\r\nfunction expand(path){\r\n    var a = [];\r\n    var s = path.split(\"/\");\r\n");
        out.write("\r\n    for(var i = 0; i < s.length; i++) {\r\n        s[i] = HTree.trim(s[i]);\r\n\r\n        if(s[i].length > 0) {\r\n");
        out.write("            a[a.length] = s[i];\r\n        }\r\n    }\r\n\r\n    var root = HTree.Util.getRootNode(document.getElementById(\"htree\"));\r\n");
        out.write("\r\n    var handler = function(node, i) {\r\n        if(i >= a.length) {\r\n            return;\r\n");
        out.write("        }\r\n\r\n        var e = getTreeNodeByValue(node, a[i]);\r\n\r\n        if(e != null) {\r\n");
        out.write("            var height = document.documentElement.clientHeight;\r\n            var scrollTop = document.body.scrollTop;\r\n");
        out.write("            var offsetTop = e.offsetTop;\r\n\r\n            if(scrollTop > offsetTop) {\r\n");
        out.write("                document.body.scrollTop = offsetTop - Math.floor(height / 2);\r\n                document.documentElement.scrollTop = offsetTop - Math.floor(height / 2);\r\n");
        out.write("            }\r\n\r\n            if(offsetTop > (height + scrollTop)) {\r\n                document.body.scrollTop = offsetTop - Math.floor(height / 2);\r\n");
        out.write("                document.documentElement.scrollTop = offsetTop - Math.floor(height / 2);\r\n");
        out.write("            }\r\n\r\n            HTree.expand(e, {\"expand\": true, \"callback\": function(e){\r\n");
        out.write("                handler(e, i + 1);\r\n            }});\r\n        }\r\n        else {\r\n");
        out.write("            // alert(\"node [\" + value + \"] not found!\");\r\n        }\r\n    };\r\n    handler(root, 0);\r\n");
        out.write("}\r\n\r\nfunction getTreeNodeByValue(node, value) {\r\n    if(node == null) {\r\n        return null;\r\n");
        out.write("    }\r\n\r\n    var list = getChildTreeNodes(node);\r\n    var length = list.length;\r\n");
        out.write("\r\n    for(var i = 0; i < length; i++) {\r\n        var a = HTree.Util.getChildNode(list[i], \"//a\");\r\n");
        out.write("\r\n        if(a != null && a.getAttribute(\"value\") == value) {\r\n            return list[i];\r\n");
        out.write("        }\r\n    }\r\n    return null;\r\n}\r\n\r\nfunction getChildTreeNodes(node) {\r\n    var c = null;\r\n");
        out.write("    var n = node.nextSibling;\r\n\r\n    while(n != null) {\r\n        if(n.nodeType == 1) {\r\n");
        out.write("            c = n;\r\n            break;\r\n        }\r\n        else {\r\n            n = n.nextSibling;\r\n");
        out.write("        }\r\n    }\r\n\r\n    var temp = [];\r\n\r\n    if(c != null) {\r\n        var list = c.childNodes;\r\n");
        out.write("        var length = list.length;\r\n\r\n        for(var i = 0; i < length; i++) {\r\n");
        out.write("            n = list[i];\r\n\r\n            if(n.nodeType == 1 && n.className == \"node\") {\r\n");
        out.write("                temp[temp.length] = n;\r\n            }\r\n        }\r\n    }\r\n    return temp;\r\n");
        out.write("}\r\n\r\n///////////////////\r\nfunction buildTree(id, xmlUrl, rootUrl){\r\n    HTree.config.stylePath = window.location.pathname + \"?action=res&path=/htree/images/\";\r\n");
        out.write("\r\n    var e = document.getElementById(id);\r\n\r\n    if(e == null) {\r\n        return;\r\n");
        out.write("    }\r\n\r\n    var name = e.getAttribute(\"data-name\");\r\n    var tree = new HTree.TreeNode({text: name, href: rootUrl, xmlSrc: xmlUrl});\r\n");
        out.write("\r\n    tree.load(function(){\r\n        this.render(document.getElementById(id));\r\n");
        out.write("    });\r\n}\r\n\r\njQuery(function() {\r\n    var resize = function() {\r\n        var e = document.getElementById(\"htree\");\r\n");
        out.write("\r\n        if(e != null) {\r\n            var parent = e.parentNode;\r\n            var offset = parseInt(parent.getAttribute(\"offset-top\"));\r\n");
        out.write("\r\n            if(isNaN(offset)) {\r\n                offset = 0;\r\n            }\r\n\r\n");
        out.write("            var height = document.documentElement.clientHeight - offset;\r\n            parent.style.height = height + \"px\";\r\n");
        out.write("        }\r\n    };\r\n    jQuery(window).load(resize);\r\n    jQuery(window).resize(resize);\r\n");
        out.write("});\r\n\r\njQuery(function() {\r\n    var requestURI = window.location.pathname;\r\n    buildTree(\"htree\", requestURI + \"?action=finder.getWorkspaceXml\", requestURI + \"?action=finder.blank\");\r\n");
        out.write("});\r\n//]]>\r\n</script>\r\n</head>\r\n<body>\r\n<div class=\"left-nav\">\r\n    <div class=\"menu-body\" style=\"padding-left: 8px; overflow-x: auto; overflow-y: scroll;\">\r\n");
        out.write("        <div id=\"htree\" class=\"htree\" style=\"margin-top: 10px; white-space: nowrap;\" data-name=\"");
        this.print(out, (i18n.format("finder.tree.root.name")));
        out.write("\"></div>\r\n    </div>\r\n</div>\r\n</body>\r\n</html>\r\n");

        out.flush();
    }


}
