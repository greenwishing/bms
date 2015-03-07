<%@ attribute name="paging" type="cn.greenwishing.bms.dto.AbstractPagingDTO" required="true" %>
<%@ attribute name="formName" type="java.lang.String" required="true" %>
<%@ attribute name="simple" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ not empty paging.list and paging.pageCount > 1}">
    <nav>
        <ul class="pager">
            <c:if test="${!simple}">
                <c:choose>
                    <c:when test="${paging.currentPage == 1}">
                        <li class="disabled"><a href="javascript:void(0)" title="首页"><span>首页</span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:WF.paging.GO($('#${formName}'), '1')" title="首页"><span>首页</span></a></li>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:choose>
                <c:when test="${paging.hasPreviousPage}">
                    <li><a href="javascript:WF.paging.GO($('#${formName}'), '${paging.previousPage}')" title="上页"><span>上页</span></a></li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href="javascript:void(0)" title="上页"><span>上页</span></a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${paging.hasNextPage}">
                    <li><a href="javascript:WF.paging.GO($('#${formName}'), '${paging.nextPage}')" title="下页"><span>下页</span></a></li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href="javascript:void(0)" title="下页"><span>下页</span></a></li>
                </c:otherwise>
            </c:choose>
            <c:if test="${!simple}">
                <c:choose>
                    <c:when test="${paging.currentPage == paging.pageCount}">
                        <li class="disabled"><a href="javascript:void(0)" title="末页"><span>末页</span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:WF.paging.GO($('#${formName}'), '${paging.pageCount}')" title="末页"><span>末页</span></a></li>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </ul>
    </nav>
</c:if>
