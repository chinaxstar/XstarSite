package cn.xstar.site.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sys.properties")
public class SysSetting {

    @Value("${adminUser}")
    public int adminUser;
}
