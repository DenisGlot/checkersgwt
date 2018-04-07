<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Checkers</title>

    <script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.min.js"/>"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/desc.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bg.css"/>">

    <!--                                            -->
    <!-- This script is required bootstrap stuff.   -->
    <!--                                            -->
    <script type="text/javascript" language="javascript" src="app/app.nocache.js"></script>
</head>

<!--                                           -->
<!-- The body can have arbitrary html, or      -->
<!-- you can leave the body empty if you want  -->
<!-- to create a completely dynamic ui         -->
<!--                                           -->
<body>

<div id="appcontainer" class="imageOnBackground"></div>

<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
<noscript>
    <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
    </div>
</noscript>
</body>
</html>
