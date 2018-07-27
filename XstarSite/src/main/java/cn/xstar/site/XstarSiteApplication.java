package cn.xstar.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.Banner;

@SpringBootApplication
public class XstarSiteApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(XstarSiteApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
