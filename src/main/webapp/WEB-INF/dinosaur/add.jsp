<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 07.03.2023
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Title</title>
  </head>
  <body>
      <form action="./add" method="">
        <p>Type of dinosaur (id):</p>
        <input type="text" name="type_of_dinosaur_id"> <br/>
        <p>Dinosaur's name:</p>
        <input type="text" name="dinosaur_name"> <br/>
        <!--<p>Dinosaur's birth day:</p>
        <input type="date" name="dinosaur_birth_date"> <br/>
        <p>Dinosaur's death day (can be empty):</p>
        <input type="date" name="dinosaur_death_date"> <br/>-->
        <p>Attraction (id):</p>
        <input type="text" name="attraction_id"> <br/>

        <input type="submit">
      </form>
  </body>
</html>
