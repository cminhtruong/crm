<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page session="false" %>
<html>
  <head>
    <title>${message}</title>
  </head>
  <body>
    <h1>${message}</h1>
    <f:form method="POST" action="addUser">
    <table>
      <tbody>
        <tr>
          <td>Id:</td>
          <td><f:input path="id" size="10" maxlength="10"></f:input></td>
        </tr>
        <tr>
          <td>Nom:</td>
          <td><f:input path="nom" size="30"></f:input></td>
        </tr>
        <tr>
          <td>Prenom:</td>
          <td><f:input path="prenom" size="6"></f:input></td>
        </tr>
       
        <tr>
          <td colspan="2"><input type="submit" value="Add User"></td>
        </tr>
      </tbody>
    </table>
    </f:form> 
  </body>
</html>