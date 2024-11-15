<%--
  Created by IntelliJ IDEA.
  User: Tina
  Date: 11/15/2024
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
  <c:if test="${requestScope.page.pageNo > 1}">
    <a href="${requestScope.page.url}&pageNo=1">首页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
  </c:if>
  <c:choose>
    <%--1 不足5页--%>
    <c:when test="${requestScope.page.pageTotal <= 5}">
      <c:set var="begin" value="1" />
      <c:set var="end" value="${requestScope.page.pageTotal}" />
    </c:when>
    <%--2 超过5页--%>
    <c:when test="${requestScope.page.pageTotal > 5}">
      <c:choose>
        <%--2.1 前面两个--%>
        <c:when test="${requestScope.page.pageNo < 3}">
          <c:set var="begin" value="1" />
          <c:set var="end" value="5" />
        </c:when>
        <%--2.2 后面两个--%>
        <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 2}">
          <c:set var="begin" value="${requestScope.page.pageTotal - 4}" />
          <c:set var="end" value="${requestScope.page.pageTotal}" />
        </c:when>
        <%--2.3 中间剩余--%>
        <c:otherwise>
          <c:set var="begin" value="${requestScope.page.pageNo - 2}" />
          <c:set var="end" value="${requestScope.page.pageNo + 2}" />
        </c:otherwise>
      </c:choose>
    </c:when>
  </c:choose>
  <c:forEach begin="${begin}" end="${end}" var="i">
    <c:if test="${i == requestScope.page.pageNo}">
      【${i}】
    </c:if>
    <c:if test="${i != requestScope.page.pageNo}">
      <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
    </c:if>
  </c:forEach>
  <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
  </c:if>
  共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
  到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
  <input id="searchPageButton" type="button" value="确定">
  <script type="text/javascript">

    $(function () {
      $("#searchPageButton").click(function () {
        const pageNo = $("#pn_input").val();
        if (pageNo < 1 || pageNo > ${requestScope.page.pageTotal}) {
          alert("请输入正确的页码！");
          return false;
        }
        // bug, what input
        location.href = "${requestScope.page.url}&pageNo=" + pageNo;
      })
    })
  </script>
</div>
