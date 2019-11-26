# 方便java 开发者快速查询常用命令

## 安装
### 方法一
```bash
./mvnw clean install -DskipTests 
java -jar target/wdt.jar
```
### 方法二
```bash
./install.sh
```

## 使用
###  输入wdt
![image](https://user-images.githubusercontent.com/20874972/69648806-f8064f00-10a6-11ea-8ce5-9c9898fb69ea.png)

### 常用命令
* ctrl+a = 进入当前行光标最前   
* ctrl+e = 进入当前行光标最后
* ctrl+u = 清除当前行的光标     
* exit or quit 退出
* tab 自动补全                
* help  帮助
* help+command 帮助具体的命令

## 开发工具
* spring shell 实现命令行 [官方文档](https://docs.spring.io/spring-shell/docs/2.0.0.RELEASE/reference/htmlsingle/#extending-spring-shell)
