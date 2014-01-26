package com.fanaifan;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class MobileGenerate {

	public static Logger log = Logger.getLogger(MobileGenerate.class);
	public static final String PATH = "src/main/webapp/mobile/";

	public void generateMobile() {

		try {
			String path = MobileGenerate.class.getResource("/mobiledata.txt").toString().substring(5);
			File file = new File(path);
			List<String> list = FileUtils.readLines(file);
			int i =1;
			for (String str : list) {
				if(str != null && !str.equals("")){
				String mobile = str.split("	")[0];
				String area = str.split("	")[1];
				JSONObject json = new JSONObject();
				json.put("mobile", mobile);
				json.put("area", area);
				log.info("进度=>"+i+"moible => " + json.toString());
				FileUtils.writeStringToFile(new File(PATH + mobile + ".json"), json.toString(), "UTF-8");
				i++;
				}
			}
		} catch (IOException e) {
			log.error("MobileGenerate generate moible failure.");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new MobileGenerate().generateMobile();
	}

}
