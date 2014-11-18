<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<style>
.error {
	color: #ff0000;
}

.operations form {
	display: inline-block;
}
</style>
<body>
	<h2 align="center">Student Registration</h2>
	<div align="center">
		<form:form method="POST" action="addPerson" commandName="command">
			<c:set var="saveValue" value="Save"></c:set>
			<c:choose>
				<c:when test="${command.operation eq 'DELETE'}">
					<c:set var="saveValue" value="Delete"></c:set>
				</c:when>
				<c:when test="${command.operation eq 'EDIT'}">
					<c:set var="saveValue" value="Edit"></c:set>
				</c:when>
			</c:choose>

			<form:hidden path="operation" value="${command.operation}" />
			<table>
				<c:if test="${status ne null}">
					<tr>
						<c:choose>
							<c:when test="${status eq 'success'}">
								<strong style="color: green;">Saved Successfully</strong>
							</c:when>
							<c:otherwise>
								<strong style="color: red;"><c:out value="${status}"></c:out></strong>

							</c:otherwise>
						</c:choose>
					</tr>
				</c:if>
				<c:if test="${command.id ne null}">
					<tr>
						<td>Student Id</td>
						<td><form:input path="id" readonly="true" /></td>
					</tr>
				</c:if>
				<tr>
					<td>Name</td>
					<td><form:input type="text" path="name"></form:input></td>
					<td><form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Age</td>
					<td><form:input type="number" path="age"></form:input></td>
					<td><form:errors path="age" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Sex</td>
					<td><form:select path="sex">
							<form:option value="M" label="Male" />
							<form:option value="F" label="FeMale"></form:option>
						</form:select></td>
					<td><form:errors path="sex" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><form:input path="address" /></td>
					<td><form:errors path="address" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:button>
							<c:out value="${saveValue}"></c:out>
						</form:button></td>
					<td><c:if test="${students eq null }">
							<a href="."><input type="button" value="Cancel"
								style="display: inline-block"></a>
						</c:if></td>
				</tr>
			</table>
		</form:form>

		<c:if test="${students ne null }">

			<table border="1px">
				<tr>
					<td>Student Id</td>
					<td>Name</td>
					<td>Age</td>
					<td>Sex</td>
					<td>Address</td>
					<td>Edit / Delete</td>
				</tr>
				<c:forEach items="${students}" var="student">
					<tr>
						<c:url value="/editPerson" var="editURL">
							<c:param name="id" value="${student.id}"></c:param>
						</c:url>
						<c:url value="/deletePerson" var="deleteURL">
							<c:param name="id" value="${student.id}"></c:param>
						</c:url>
						<td><c:out value="${student.id}" /></td>
						<td><c:out value="${student.name}" /></td>
						<td><c:out value="${student.age}" /></td>
						<td><c:out value="${student.sex}" /></td>
						<td><c:out value="${student.address}" /></td>
						<td>
							<div class="operations">
								<form:form action="editPerson" commandName="command">
									<form:hidden value="${student.id}" path="id" />
									<form:button>Edit</form:button>
								</form:form>
								<form:form action="deletePerson" commandName="command">
									<form:hidden value="${student.id}" path="id" />
									<form:button>Delete</form:button>
								</form:form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>

</html>
