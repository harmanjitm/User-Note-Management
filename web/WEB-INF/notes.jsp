<%-- 
    Document   : notes
    Created on : Nov 1, 2018, 1:01:14 PM
    Author     : 758243
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <form method="post" action="notes">
            <table>
                <th>Note ID</th>
                <th>Date Created</th>
                <th>Contents</th>
                <c:forEach items="${notes}" var="note">
                    <tr>
                        <td>${note.noteId}</td>
                        <td>${note.dateCreated}</td>
                        <td>${note.contents}</td>
                    <input type="submit" value="Delete" name="${note.noteId}">
                    <input type="submit" value="Edit" name="${note.noteId}">
                    </tr>
                </c:forEach>
            </table>
        </form>
        <form method="post" action="notes">
            <textarea name="contents" cols="40" rows="10"></textarea>
            <input type="submit" value="Add" name="addNote">    
        </form>
    </body>
</html>
