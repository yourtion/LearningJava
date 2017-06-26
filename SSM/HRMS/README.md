#  SSM + easyUI 搭建简易的人事管理系统

实验楼[《SSM + easyUI 搭建简易的人事管理系统》](https://www.shiyanlou.com/courses/824)

## 数据库

1. 管理员表（管理员 id，用户名，密码，用户角色），管理员 id 为主键
2. 公告表（公告 id，标题，内容，管理员 id，发布日期），公告 id为主键，管理员 id 为外键
3. 部门表（部门 id，名称，描述），部门 id 为主键
4. 职位表（职位 id，名称，描述），职位 id 为主键
5. 员工表（员工 id，姓名，性别，手机号，邮箱，住址，学历，生日，部门 id，职位 id），员工 id 为主键，部门 id 为外键，职位 id 为外键

[InitDB.sql](src/main/resources/db/hrms_db.sql)
