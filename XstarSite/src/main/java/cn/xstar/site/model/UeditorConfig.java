package cn.xstar.site.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.util.ResourceUtils;

public class UeditorConfig {

	public static String getConfig() {
		File file;
		StringBuffer sb = new StringBuffer();
		FileReader fr;
		BufferedReader br = null;
		try {
			file = ResourceUtils.getFile("classpath:static/ueditor1.4.3.3/jsp/config.json");
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while (br.ready()) {
				sb.append(br.readLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
