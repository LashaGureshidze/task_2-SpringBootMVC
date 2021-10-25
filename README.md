# task_2-SpringBootMVC
Demo SpringBoot MVC app

To run the application type:

`docker-compose up -d` 
this will create PostgreSql database and will fill with all required data.
It also creates application users:
<table>
<tr>
<td>username</td>
<td>password</td>
<td>permissions</td>
<td>supported endpoints</td>
</tr>
<tr>
<td>user_1</td>
<td>user_1</td>
<td>VIEW_INFO</td>
<td>/api/info</td>
</tr>
<tr>
<td>user_2</td>
<td>user_2</td>
<td>VIEW_ADMIN</td>
<td>/api/admin, api/users/blocked</td>
</tr>
<tr>
<td>user_3</td>
<td>user_3</td>
<td>VIEW_INFO, VIEW_ADMIN</td>
<td>/api/info, /api/admin, api/users/blocked</td>
</tr>
</table>
