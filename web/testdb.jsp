<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sql:query var="rs" dataSource="jdbc/farmlanderDB">
    select cateId,name from t_category
</sql:query>
<html>
<head>
    <title>Test DB Connection</title>
</head>
<body>
<h2>Results</h2>
<table border="1">
    <tr>
        <th>
            ID
        </th>
        <th>
            NAME
        </th>
    </tr>
    <c:forEach var="row" items="${rs.rows}">
        <tr>
            <td>
                    ${row.cateId}
            </td>
            <td>
                    ${row.name}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

