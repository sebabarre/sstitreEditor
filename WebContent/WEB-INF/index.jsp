
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>L'éditeur de sous-titres du futur</title>
	</head>
	<body>
		<form action="accueil" method="post" enctype="multipart/form-data">
			<input type="file" name="fichierSSTitre" accept=".srt"/>
			<input type="submit" value="IMPORTER FICHIER"/>
		</form>
		<form action="modifier" method="post">
			<hr/>
			<table>
				<thead>
					<tr></tr>
					<tr>Titre fichier</tr>
				</thead>
				<tbody>
					<c:forEach items="${listFichier}" var="fichier">
						<tr>
							<td><input type="checkbox" value="${fichier.id}" name="idFichier"></td>
							<td><c:out value="${fichier.titre }"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit" value="MODIFIER FICHIER"/>
		</form>
	</body>
</html>