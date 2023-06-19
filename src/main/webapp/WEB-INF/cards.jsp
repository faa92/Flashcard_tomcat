<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="cards" scope="request" type="java.util.List<com.example.flashcard_tomcat.model.Card>"/>
<jsp:useBean id="themeId" scope="request" type="java.lang.Long"/>

<!DOCTYPE html>
<html>
<head>
  <title>Flashcards</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<main>
  <section>
    <h2>Карточки</h2>

    <c:if test="${cards.isEmpty()}">
      <p>Нет карт</p>
    </c:if>

    <c:if test="${!cards.isEmpty()}">
      <ul>
        <c:forEach var="card" items="${cards}">
            <li>
                <form action="<c:url value="/update-card"/>"
                      method="post"
                      enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="learned" value="${!card.learned}">
                    <button type="submit" name="cardId" value="${card.idCard}">
                            ${card.learned ? '✅' : '❌'}
                    </button>
                    <c:out value="${card.question}"/>
                    <form action="<c:url value="/remove-card"/>"
                          method="post"
                          enctype="application/x-www-form-urlencoded">
                        <button type="submit" name="cardId" value="${card.idCard}">Удалить</button>
                    </form>
                </form>
            </li>
        </c:forEach>
      </ul>
    </c:if>

  </section>

  <section>
    <h2>Добавить карточку</h2>
    <form action="<c:url value="/create-card"/>"
          method="post"
          enctype="application/x-www-form-urlencoded">
      <div>
        <label for="create-theme-title">Название</label>
        <input type="text" id="create-theme-title" name="title" required>
      </div>
        <button type="submit" name="themeId" value="${themeId}">Добавить</button>
    </form>
  </section>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>


<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<%--<jsp:useBean id="cards" scope="request" type="java.util.List<com.example.flashcard_tomcat.model.Card>"/>--%>
<%--<jsp:useBean id="themeId" scope="request" type="java.lang.Long"/>--%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Flashcards</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="header.jsp"/>--%>

<%--<main>--%>
<%--    <section>--%>
<%--        <h2>Карточки</h2>--%>

<%--        <c:if test="${cards.isEmpty()}">--%>
<%--            <p>Нет карт</p>--%>
<%--        </c:if>--%>

<%--        <c:if test="${!cards.isEmpty()}">--%>
<%--            <ul>--%>
<%--                <c:forEach var="card" items="${cards}">--%>
<%--                    <li>--%>
<%--                        <form action="<c:url value='/update-card'/>"--%>
<%--                              method="post"--%>
<%--                              enctype="application/x-www-form-urlencoded">--%>
<%--                            <input type="hidden" name="learned" value="${!card.learned}">--%>
<%--                            <button type="submit" name="cardId" value="${card.id}">--%>
<%--                                    ${card.learned ? '✅' : '❌'}--%>
<%--                            </button>--%>
<%--                            <c:out value="${card.question}"/>--%>
<%--                        </form>--%>
<%--                        <form action="<c:url value='/remove-card'/>"--%>
<%--                              method="post"--%>
<%--                              enctype="application/x-www-form-urlencoded">--%>
<%--                            <button type="submit" name="cardId" value="${card.id}">Удалить</button>--%>
<%--                        </form>--%>
<%--                    </li>--%>
<%--                </c:forEach>--%>
<%--            </ul>--%>
<%--        </c:if>--%>

<%--    </section>--%>

<%--    <section>--%>
<%--        <h2>Добавить карточку</h2>--%>
<%--        <form action="<c:url value='/create-card'/>"--%>
<%--              method="post"--%>
<%--              enctype="application/x-www-form-urlencoded">--%>
<%--            <div>--%>
<%--                <label for="create-theme-title">Название</label>--%>
<%--                <input type="text" id="create-theme-title" name="title" required>--%>
<%--            </div>--%>
<%--            <button type="submit" name="themeId" value="${themeId}">Добавить</button>--%>
<%--        </form>--%>
<%--    </section>--%>
<%--</main>--%>

<%--<jsp:include page="footer.jsp"/>--%>
<%--</body>--%>
<%--</html>--%>