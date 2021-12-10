<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <!-- $중괄호 를 사용하면 프로퍼티 접근법을 사용할 수 있음 (ex) member.id) -->
    <!-- 이전에는 꺽새%= ((Member)request.getAttribute("member")).getId() %꺽새 이렇게 복잡하게 사용해야 했음-->
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>