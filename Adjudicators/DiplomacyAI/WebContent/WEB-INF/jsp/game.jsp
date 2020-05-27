<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
{
	"turn": ${game.turn},
	"phase": ${game.phase.id},
	"provinces": "${PROVINCES_URL_DEFINITION}",
	"countries": {
<c:forEach var="country" varStatus="cstat" items="${COUNTRIES_DEFINITION}">			"${country }": {
			"player": ${game[country].player},
			"armies": [<c:forEach var="army" varStatus="astat" items="${game[country].armies}">${astat.first ? "" : ", "}${army}</c:forEach>],
			"fleets": [<c:forEach var="fleet" varStatus="fstat" items="${game[country].fleets}">${fstat.first ? "" : ", "}${fleet}</c:forEach>],
			"bases": [<c:forEach var="base" varStatus="bstat" items="${game[country].bases}">${bstat.first ? "" : ", "}${base}</c:forEach>]
			}${cstat.last ? "" : ", "}
</c:forEach>
	}
}