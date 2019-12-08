package com.wudang.wdt.command;

import com.wudang.wdt.command.table.ChinesSizeConstraints;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.*;

/**
 * docker command
 * @author jet
 * @date 08-12-2019
 */
@ShellComponent
@ShellCommandGroup(value = "docker")
public class DockerCommand {


    /**
     * https://www.cnblogs.com/SzeCheng/p/6822905.html  docker basic and quick star
     * https://www.jianshu.com/p/d4179b6c0332  docker images
     * https://github.com/gf-huanchupk/SpringBootLearning
     * @return
     */
    @ShellMethod(value = "dockerTable 导航", key = {"dockerTable"})
    @SuppressWarnings("unchecked")
    public Table dockerTable() {
        TableModelBuilder builder = new TableModelBuilder<String>();
        builder.addRow().addValue("command  ").addValue("comment").addValue("https://www.jianshu.com/p/d4179b6c0332 ");
        builder.addRow().addValue(" docker command images").addValue("https://upload-images.jianshu.io/upload_images/12842279-f5d4c22882f4a649.png ").addValue("");
        builder.addRow().addValue("docker basic and quick star ").addValue("https://www.cnblogs.com/davidwang456/articles/9578409.html ").addValue("");
        builder.addRow().addValue("docker basic command").addValue("https://mp.weixin.qq.com/s/S9VkzSJx_JOY8zDiC_nuEg ").addValue("https://github.com/gf-huanchupk/SpringBootLearning ");
        builder.addRow().addValue("docker pull image  ").addValue("docker pull centos:centos6").addValue("");
        builder.addRow().addValue("docker  image list  ").addValue("docker images").addValue("");
        builder.addRow().addValue("run （guard）interactive container").addValue("docker run -d -p 8080:8080 imageId").addValue("docker run -i -t imageId /bin/bash");
        builder.addRow().addValue("docker start/stop container-id").addValue("docker start/stop container-id").addValue("");
        builder.addRow().addValue("docker logs  container-id").addValue("docker logs -f  container-id").addValue("");
        builder.addRow().addValue("docker top  container-id").addValue("docker top  container-id").addValue("container process");
        builder.addRow().addValue("enter Container").addValue("docker exec -it container_id /bin/bash").addValue("");
        builder.addRow().addValue("docker build").addValue("docker build -t mysql/mysql:5.7 .").addValue("https://docs.docker.com/engine/reference/builder/");
        builder.addRow().addValue("docker build").addValue("docker commit container_id:tag").addValue("https://mp.weixin.qq.com/s/foVeANf0imgU4hfOempp3A");
        builder.addRow().addValue("docker push(docker login)").addValue("docker push mysql:5.7").addValue("https://mp.weixin.qq.com/s/jN-8YgxDcAvwoiZ_BJQUKQ");
        builder.addRow().addValue("docker remove image").addValue("docker rmi -f image").addValue("");
        builder.addRow().addValue("docker remove container").addValue("docker rm -f containerId").addValue("");

        TableModel tableModel = builder.build();
        TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.on(CellMatchers.table()).addSizer(new ChinesSizeConstraints()).and();
        return tableBuilder.addFullBorder(BorderStyle.fancy_light_triple_dash).build();
    }



}
