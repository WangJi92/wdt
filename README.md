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
## 视频教程
* 简单介绍spring shell
[1、begin-spring-shell](https://v.youku.com/v_show/id_XNDQ1Mzg4MzQ0MA==.html)
* spring shell 官方文档简述
[2、introduce-spring-shell-official-doc](https://v.youku.com/v_show/id_XNDQ1MzkyODYxMg==.html)
* 武当山命令行使用
[3、use-spring-shell-solve-question 武当山命令行](https://v.youku.com/v_show/id_XNDQ1Mzk4OTcyMA==.html)



## 使用
wdt 做了一些什么？
* 比如arthas trace watch 命令，不需要记住那些复杂的使用，idea copy reference即可，方便处理线上故障
* jvm 一个table 表，记录了一些常用的命令，可以看作为一个快速查询的记事本
* log  ....继续记事本
* vim 操作的记事本，反正我是记不住很多的
* arthas ognl 语法的学习记事本
* arthas 特殊用法记事本，比如如何获取Spring applicationContext 这些高级用法，快速导航
反正就是一个记事本，日常开发中方便快速处理开发中的问题。
你自己感兴趣，可以集成http请求，redis，dubbo调用等等，导入日常开发的需求。
###  输入wdt
![image](https://user-images.githubusercontent.com/20874972/69648806-f8064f00-10a6-11ea-8ce5-9c9898fb69ea.png)

### ai 安装 arthas
  剪切板中复制到了如下的命令,执行一下，安装arthas，通过as.sh 启动 linux 环境
 ![image](https://user-images.githubusercontent.com/20874972/69730612-5a248a00-1163-11ea-9b5e-43b7f322d035.png)

```bash
curl -sk https://arthas.gitee.io/arthas-boot.jar -o ~/.arthas-boot.jar  && echo "alias as.sh='java -jar ~/.arthas-boot.jar --repo-mirror aliyun --use-http'" >> ~/.bashrc && source ~/.bashrc
```
### wdt 输入 at or arthasTrace
 *  首先复制一个copy reference，一个方法
  ![image](https://user-images.githubusercontent.com/20874972/69729310-0dd84a80-1161-11ea-9057-091b75029f4c.png)
 *  wdt 输入 at
  剪切板中获取到通过字符串处理过的，符合arthas 规则的方法
  ![image](https://user-images.githubusercontent.com/20874972/69729643-a79ff780-1161-11ea-9fbf-01607f5cd2ab.png)
 ```bash
    trace com.wudang.wdt.command.ArthasCommand asTrace -n 5
 ```
* arthas 中粘贴
![image](https://user-images.githubusercontent.com/20874972/69729700-c605f300-1161-11ea-8d00-6b6928e2831b.png)
*  再次在wdt 输入 at 执行一下方法，触发trace规则
 如下就是效果
![image](https://user-images.githubusercontent.com/20874972/69729819-f2217400-1161-11ea-81fe-2e32e9274304.png)

## 目前支持
```xml
arthas
        ai, arthasInstall: arthas 安装脚本
        arthasStatic, as: 获取类的静态字段,变量(-c classLoaderHash 默认值 SystemClassLoader）[ sc -d com.test 获取 classLoaderHash 值]
        arthasTable: arthas 学习
        arthasTrace, at: trace 某个方法的调用栈
        arthasWatch, aw: watch 某个方法的入参返回值

ip
        ip: 获取本机ip地址信息

jvm
        jvmTable: jvmTable 导航

log
        logTable: logTable 导航

ognl
        ognlTable: ognl 学习

system
        ls: 系统ls
        s, system: 输出系统参数eg 'ls -la'

vim
        vimTableCopyPaste: vimTableCopyPaste  复制粘贴
        vimTableDeleteUndo: vimTable 删除撤销
        vimTableFind: vimTable Find  查找
        vimTableManyLineEdit: vimTableManyLineEdit 多行编辑
        vimTableReplace: vimTableReplace 替换
```

## 常用命令
* ctrl+a = 进入当前行光标最前   
* ctrl+e = 进入当前行光标最后
* ctrl+u = 清除当前行的光标     
* exit or quit 退出
* tab 自动补全                
* help  帮助
* help+command 帮助具体的命令

## 开发工具
* spring shell 实现命令行 [官方文档](https://docs.spring.io/spring-shell/docs/2.0.0.RELEASE/reference/htmlsingle/#extending-spring-shell)
* arthas [官方文档](https://alibaba.github.io/arthas/)