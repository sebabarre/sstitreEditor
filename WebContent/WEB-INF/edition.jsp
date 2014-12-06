
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>L'éditeur de sous-titres du futur</title>
	</head>
	<body>
		<form action="modifier" method="get">
			<table>
				<thead>
					<tr>
						<td>Numéro Ligne</td>
						<td>Début</td>
						<td>Fin</td>
						<td>Sous-titre</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${fichier.detailFichier}" var="ligne">
						<tr>
							<td><c:out value="${ligne.numLigne}"/>
							<td><c:out value="${ligne.dateDebut}"/>
							<td><c:out value="${ligne.dateFin}"/>
							<td><input type="text" name="texteSSTitre${ligne.id }" value="${ligne.ssTitre }"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</body>
</html>