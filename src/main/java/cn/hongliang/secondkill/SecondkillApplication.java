package cn.hongliang.secondkill;

import cn.hongliang.secondkill.dao.UserDOMapper;
import cn.hongliang.secondkill.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = {"cn.hongliang.secondkill"})
@MapperScan("cn.hongliang.secondkill.dao")
public class SecondkillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondkillApplication.class, args);
    }

}
