package  com.yunkang.saas.platform.monitor.config;

import com.yunkang.saas.platform.manage.config.PlatformManageAppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PlatformManageAppConfig.class})
public class MonitorAppConfig {

}