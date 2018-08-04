<%--东云网修改，版权所有--%>
<%--2018年1月22日16:44:26--%>
<%@ tag import="org.apache.shiro.util.StringUtils" %>
<%@ tag import="org.apache.shiro.SecurityUtils" %>
<%@ tag import="org.apache.shiro.subject.Subject" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="权限字符串列表" %>
<%@ attribute name="delimiter" type="java.lang.String" required="false" description="权限字符串列表分隔符" %>
<%
    if(!StringUtils.hasText(delimiter)) {
        delimiter = ",";//默认逗号分隔
    }
    if(!StringUtils.hasText(name)) {
%>
        <jsp:doBody/>
<%
        return;
    }
    String[] permissions = name.split(delimiter);
    Subject subject = SecurityUtils.getSubject();
    for(String permission : permissions) {
        if(subject.isPermitted(permission)) {
%>
            <jsp:doBody/>
<%
            break;
        }
    }
%>

