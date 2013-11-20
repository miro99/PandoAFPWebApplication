<%-- 
    Document   : SiteHeader
    Created on : Nov 14, 2013, 2:00:06 PM
    Author     : ajmiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <section id="headerReusable">        
            <div id="headerColumn1">
                <img src="Images/Pando%20AFP.png" height="30" width="200"/>                
            </div>

            <div id="headerColumn2">
                ${param.pageTitle}
            </div>

            <div id="rightSideHeader">

            </div>        
        </section>
    </body>
</html>
