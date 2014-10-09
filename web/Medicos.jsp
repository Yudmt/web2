<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
[
    <c:forEach items="${medicos}" var="item" varStatus="loop">
        <c:out value="${item.json}"/>
        <c:if test="${!loop.last}">,</c:if>
    </c:forEach>
]