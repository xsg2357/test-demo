# 测试练习项目
*  接口测试地址：http://localhost:8080/swagger-ui.html#!
*  修改docker vim 文件
```db2
修改配置文件，打开2375端口
[root@microservice ~]# vim /usr/lib/systemd/system/docker.service
在ExecStart=/usr/bin/dockerd 后面加上-H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock 
重新加载配置文件和启动：
systemctl daemon-reload
systemctl start docker
```


