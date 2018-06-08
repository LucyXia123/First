<%@page
        import="java.text.SimpleDateFormat"
        import="java.util.Date"
%>
<%
    SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    out.print(myFmt.format(now));
%>
