/*
 * $RCSfile: UserAddTemplate.java,v $
 * $Revision: 1.1 $
 * $Date: 2017-08-05 $
 *
 * JSP generated by JspCompiler-1.0.0 (built 2017-08-05 00:43:18 581)
 */
package com.skin.finder.servlet.template;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * <p>Title: UserAddTemplate</p>
 * <p>Description: </p>
 * @author JspKit
 * @version 1.0
 */
public class UserAddTemplate extends com.skin.finder.web.servlet.JspServlet {
    private static final long serialVersionUID = 1L;
    private static final UserAddTemplate instance = new UserAddTemplate();


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
        out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n<title>Add User</title>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/css/user.css\"/>\r\n<script type=\"text/javascript\" src=\"");
        this.print(out, request.getAttribute("requestURI"));
        out.write("?action=res&path=/finder/jquery-1.7.2.min.js\"></script>\r\n<script type=\"text/javascript\">\r\n");
        out.write("<!--\r\njQuery(function() {\r\n    jQuery(\"#submit\").click(function() {\r\n        var userName = jQuery.trim(jQuery(\"#s1\").val());\r\n");
        out.write("        var password = jQuery.trim(jQuery(\"#s2\").val());\r\n        var params = \"userName=\" + encodeURIComponent(userName) + \"&password=\" + encodeURIComponent(password);\r\n");
        out.write("        var requestURI = window.location.pathname;\r\n\r\n        jQuery.ajax({\r\n            type: \"post\",\r\n");
        out.write("            url: requestURI + \"?action=user.save\",\r\n            dataType: \"json\",\r\n");
        out.write("            data: params,\r\n            error: function(req, status, error) {\r\n                alert(\"系统错误，请稍后再试！\");\r\n");
        out.write("            },\r\n            success: function(result) {\r\n                if(result.status == 200) {\r\n");
        out.write("                    alert(\"添加用户成功！\");\r\n                    window.location.reload();\r\n");
        out.write("                }\r\n                else {\r\n                    alert(result.message);\r\n");
        out.write("                }\r\n            }\r\n        });\r\n    });\r\n});\r\n//-->\r\n</script>\r\n</head>\r\n");
        out.write("<body>\r\n<div class=\"wrap\">\r\n    <div class=\"login-container\">\r\n        <h3>添加用户</h3>\r\n");
        out.write("        <div class=\"login-panel\">\r\n            <div class=\"row\"><input id=\"s1\" type=\"text\" class=\"text\" spellcheck=\"false\" placeholder=\"UserName\" value=\"\"/></div>\r\n");
        out.write("            <div class=\"row\"><input id=\"s2\" type=\"password\" class=\"text\" placeholder=\"Password\" value=\"\"/></div>\r\n");
        out.write("            <div class=\"row\"><input id=\"submit\" type=\"button\" class=\"button\" value=\"提交\"/></div>\r\n");
        out.write("        </div>\r\n    </div>\r\n</div>\r\n</body>\r\n</html>");

        out.flush();
    }


}
