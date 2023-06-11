<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="themes" scope="request" type="java.util.List<com.example.flashcard_tomcat.model.Theme>"/>

<!DOCTYPE html>
<html>
<head>
    <title>Flashcards</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>

<main>
    <section>
        <h2>Темы</h2>
        <c:if test="${themes.isEmpty()}">
            <p>Нет тем</p>
        </c:if>

        <c:if test="${!themes.isEmpty()}">
            <ul>
                <c:forEach var="theme" cards="${themes}">
                    <li>
                        <a href="<c:url value="/cards?themeId=${theme.id}"/>">
                            <c:out value="${theme.title}"/>
                        </a>
                            ${theme.learnedCardsCount} / ${theme.totalCardsCount}
                        <form action="<c:url value="/remove-theme"/>"
                              method="post"
                              enctype="application/x-www-form-urlencoded">
                            <button type="submit" name="themeId" value="${theme.id}">Удалить</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </section>

    <section>
        <h2>Создать тему</h2>
        <form action="<c:url value="/create-theme"/>"
              method="post"
              enctype="application/x-www-form-urlencoded">
            <div>
                <label for="create-theme-title">Название</label>
                <input type="text" id="create-theme-title" name="title" required>
            </div>
            <button type="submit">Создать</button>
        </form>
    </section>
</main>

<jsp:include page="/WEB-INF/footer.jsp"/>
</body>
</html>
