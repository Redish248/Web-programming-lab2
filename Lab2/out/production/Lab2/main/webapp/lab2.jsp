<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"
        language="java" import="java.util.List, java.util.ArrayList, main.java.Lab_2.AreaCheckServlet"
%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Lab_2</title>
    <link rel="shortcut icon" href="images/duck.ico">
    <link rel="stylesheet" type="text/css" href="./styles/main.css">
    <script>
        function checkX(value) {
            document.getElementById("X").value = value;
            document.getElementById('X').style.display='inline';
        }
        function chosenButton(click){
            var buttons = document.getElementsByClassName("button");
            for (var i=0;i<buttons.length;i++){
                buttons[i].style.border = "1px solid black";
                if (buttons[i].id===click.id){
                    buttons[i].style.border = "2px solid blue";
                }
            }
        }
    </script>
</head>

<body>
<center>
    <div class="header_1">
        <table class="table_header_1">
            <tr>
                <td><a href="https://se.ifmo.ru/documents"> <img name="vt_image" src="./images/vt_logo.png"> </a></td>
                <td ><label> Лабораторная работа № 2 </label></td>
            </tr>
        </table>
    </div>


    <div class="header_2">
        <table class="table_header_2">
            <tr>
                <td >Группа P3212 &#8226   </td>
                <td ><a href="https://isu.ifmo.ru/pls/apex/f?p=2143:3:102153591906187::NO:RP:PID:242361">  Редькина Ирина/</a><a href="https://isu.ifmo.ru/pls/apex/f?p=2143:3:115928066281303::NO:RP:PID:243887">Соболева Ольга  </a>  </td>
                <td>&#8226 Вариант 21209</td>
            </tr>
        </table>
    </div>
    <br>
    <div class="container form" id="inputs">
        <table class = "input_table">
            <tr>
                <td>
                    <canvas id="canvas" onclick="clickCanvas()" style="background-color:#ffffff; border-radius: 20px;" width="300" height="300"></canvas>
                </td>
                <td>
                    <form class="form" id="form" action="controller" method="post" onsubmit="return validate();">
                        <table>
                            <tr>
                                <td><label id="choose"> Выбор значений:</label></td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="select_value"> &#8226 X =
                                    <input type="text" id="X" readonly value="0" name="X" class="x_input">
                                    </label>
                                    <table class ="input_button">
                                        <tr>
                                            <td><input class= "button" id="-5" type="button" value="-5" onclick="checkX(value);chosenButton(this);"></td>
                                            <td><input class= "button" id="-4" type="button" value="-4" onclick="checkX(value);chosenButton(this);"></td>
                                            <td><input class= "button" id="-3" type="button" value="-3" onclick="checkX(value);chosenButton(this);"></td>
                                        </tr>
                                        <tr>
                                            <td><input class= "button" id="-2" type="button" value="-2" onclick="checkX(value);chosenButton(this);"></td>
                                            <td><input class= "button" id="-1" type="button" value="-1" onclick="checkX(value);chosenButton(this);"></td>
                                            <td><input class= "button" id="0"  type="button" value="0" onclick="checkX(value);chosenButton(this);"></td>
                                        </tr>
                                        <tr>
                                            <td><input class= "button" id="1"  type="button" value="1" onclick="checkX(value);chosenButton(this);"></td>
                                            <td><input class= "button" id="2"  type="button" value="2" onclick="checkX(value);chosenButton(this);"></td>
                                            <td><input class= "button" id="3"  type="button" value="3" onclick="checkX(value);chosenButton(this);"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <td class="add_arror">
                                <label for="Y" class="select_value"> &#8226 Y = </label>
                                <input class="input_Y field" id="Y" type="text" maxlength="8" name="Y" placeholder="(-5 .. 5)"><br>
                                </td>
                            </tr>
                            <tr>
                                <td class="add_arror" >
                                <label for="R" class="select_value"> &#8226 R = </label>
                                <input class="input_R field" id="R" type="text" maxlength="8" name="R" placeholder="(1 .. 4)"><br>
                                </td>
                            </tr>

                            <tr align="center">
                                <td>
                                <input class="submit" type="submit" name="submit" value="ПРОВЕРИТЬ" id="button"> <br>
                                </td>
                            </tr>
                        </table>
                    </form>

                    <script src = "validate.js"></script>
                </td>

            </tr>
        </table>
    </div>

    <br>
        <jsp:useBean id="BeanPoints" class="main.java.Lab_2.PointList" scope="application" />
    <script>drawCanvas('canvas',2)</script>
    <c:if test="${not empty BeanPoints.list}">
        <div class="newWindow">
            <table id="beanTable">
                <caption>Результаты:</caption>
                <tr>
                    <th>X: </th>
                    <th>Y:</th>
                    <th>R:</th>
                    <th>Hit:</th>
                </tr>
                <c:forEach items="${BeanPoints.list}" var="item">
                    <script>drawPoint(${item.x},${item.y},2)</script>
                    <tr>
                        <td> ${item.x} </td>
                        <td> ${item.y} </td>
                        <td> ${item.r} </td>
                        <c:choose>
                            <c:when test="${item.hit == true}">
                                <td> &#9989</td>
                            </c:when>
                            <c:when test="${item.hit == false}">
                                <td> &#10060</td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>


            </table>

        </div>
    </c:if>

</center>
</body>

