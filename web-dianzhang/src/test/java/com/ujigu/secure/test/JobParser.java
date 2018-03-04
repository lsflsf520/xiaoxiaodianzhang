package com.ujigu.secure.test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import com.google.common.reflect.TypeToken;
import com.ujigu.secure.common.utils.JsonUtil;
import com.ujigu.secure.db.service.impl.DBDataUtil;

public class JobParser {

	public static void main(String[] args) throws IOException {
//		Map<String, Map<String, String>> hangyeMap = parseHangye();
		
//		Map<String, Map<String, String>> jobClassifyMap = parseJob();
		
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://121.42.204.239:3306/bole?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true");
		dataSource.setUsername("logo_test");
		dataSource.setPassword("logotest123");
		
		String content = FileUtils.readFileToString(new File("/Users/lsf/git/web-bole/doc/tmp.txt"));
		Map<String, Object> valMap = JsonUtil.create().fromJson(content, new TypeToken<Map<String, Object>>() {}.getType());
		for(String key : valMap.keySet()) {
			List<Object> vals = (List<Object>)valMap.get(key);
			for(Object val : vals) {
				String sql = "insert into hangye_job(id, hangye_id, job_id, create_time) values(0,"+key + "," + val+", now())";
				
				DBDataUtil.execUpdate(dataSource, sql);
			}
		}
		
		/*String content = FileUtils.readFileToString(new File("/Users/lsf/git/web-bole/doc/industryJob.txt"));
		List<Map<String, Object>> list = JsonUtil.create().fromJson(content, new TypeToken<List<Map<String, Object>>>() {}.getType());
		for(Map<String, Object> valueMap : list) {
			String sql = "insert into job_classify(id, name, en_name, parent_id, status, create_time, last_uptime)"
		              + " values("+valueMap.get("id") + ",'" + valueMap.get("name") + "','" + valueMap.get("enName") +"',"+valueMap.get("parentId")+",'NORMAL',now(), now())";
			DBDataUtil.execUpdate(dataSource, sql);
			if(valueMap.get("children") != null) {
				List<Map<String, Object>> children = (List<Map<String, Object>>)valueMap.get("children");
				for(Map<String, Object> childMap : children) {
					sql = "insert into job_classify(id, name, en_name, parent_id, status, create_time, last_uptime)"
				              + " values("+childMap.get("id") + ",'" + childMap.get("name") + "','" + childMap.get("enName") +"',"+childMap.get("parentId")+",'NORMAL',now(), now())";
					DBDataUtil.execUpdate(dataSource, sql);
//					System.out.println(sql);
				}
			}
		}*/
		
		
		/*String content = FileUtils.readFileToString(new File("/Users/lsf/git/web-bole/doc/industry.txt"));
		List<Map<String, Object>> list = JsonUtil.create().fromJson(content, new TypeToken<List<Map<String, Object>>>() {}.getType());

		boolean result = false;
		for(Map<String, Object> valueMap : list) {
			if("制药·医疗".equals(valueMap.get("name"))) {
				result = true;
			}
			if(!result) {
				continue;
			}
			String sql = "insert into hangye(id, name, en_name, parent_id, status, create_time, last_uptime)"
		              + " values("+valueMap.get("id") + ",'" + valueMap.get("name") + "','" + valueMap.get("enName") +"',"+valueMap.get("parentId")+",'NORMAL',now(), now())";
			DBDataUtil.execUpdate(dataSource, sql);
			System.out.println(sql);
			if(valueMap.get("children") != null) {
				List<Map<String, Object>> children = (List<Map<String, Object>>)valueMap.get("children");
				for(Map<String, Object> childMap : children) {
					sql = "insert into hangye(id, name, en_name, parent_id, status, create_time, last_uptime)"
				              + " values("+childMap.get("id") + ",'" + childMap.get("name") + "','" + childMap.get("enName") +"',"+childMap.get("parentId")+",'NORMAL',now(), now())";
					DBDataUtil.execUpdate(dataSource, sql);
					System.out.println(sql);
				}
			}
		}*/
		
		/*for(String key : jobClassifyMap.keySet()) {
			String[] parts = key.split("_");
			
			List<Map<String, Serializable>> datas = DBDataUtil.loadData(dataSource, "select id from job_classify where id = " + parts[0]);
			if(!CollectionUtils.isEmpty(datas)) {
				System.out.println("dumplicate classify " + key);
				continue;
			}
			
			DBDataUtil.execUpdate(dataSource, "insert into job_classify(id, hangye_id, name, status, create_time, last_uptime)"
					              + " values("+parts[0] + ", 0,'" + parts[1] +"','NORMAL',now(), now())");
			
			Map<String, String> jobMap = jobClassifyMap.get(key);
			for(String jobId : jobMap.keySet()) {
				String jobname = jobMap.get(jobId);
//				String[] kvs = jobinfo.split("_");
//				if(kvs.length != 2) {
//					System.out.println(jobinfo);
//					continue;
//				}
				
				datas = DBDataUtil.loadData(dataSource, "select id from job where id = " + jobId);
				if(!CollectionUtils.isEmpty(datas)) {
					System.out.println("dumplicate job " + key);
					continue;
				}
				
				DBDataUtil.execUpdate(dataSource, "insert into job(id, classify_id, name, status, create_time, last_uptime) "
						             + " values("+jobId + "," + parts[0] + ",'"+jobname+"', 'NORMAL', now(), now())");
				
			}
		}*/
		
	}
	
	private static Map<String, Map<String, String>> parseJob() throws IOException{
		Map<String, Map<String, String>> jobMap = new HashMap<>();
		File[] dirs = new File("/Users/lsf/Documents/job").listFiles();
		for(File dir : dirs) {
			if(dir.isDirectory()) {
				File[] files = dir.listFiles();
				for(File file : files) {
					Document doc = Jsoup.parse(file, "UTF-8");
					
					Element currJobClassifyElem = doc.select("li.active").first();
					String idstr = currJobClassifyElem.attr("data-value");
					String name = currJobClassifyElem.text();
					if(idstr.contains(",")) {
						idstr = idstr.substring(0, idstr.indexOf(","));
					}
					String key = idstr + "_" + name;
					
					Map<String, String> currJobMap = new HashMap<>();
					for(Element elem : doc.select("div.data-list > ul > li > input")) {
						currJobMap.put(elem.attr("data-value"), elem.attr("data-name"));
					}
					
					jobMap.put(key, currJobMap);
				}
				
			}
		}
		
		System.out.println(jobMap);
		
		return jobMap;
	}
	
	private static Map<String, Map<String, String>>  parseHangye() throws IOException{
        Document doc = Jsoup.parse(new File("/Users/lsf/Documents/job/hangye.html"), "UTF-8");
		
		Map<String, Map<String, String>> hangyeMap = new HashMap<>();
		Elements trElems = doc.select("tbody > tr");
		for(Element elem : trElems) {
			String rootHangye = elem.select("th").text();
			
			Map<String, String> subHangyeMap = new HashMap<>();
			for(Element input : elem.select("ul > li > input")) {
				subHangyeMap.put(input.attr("data-value"), input.attr("data-name"));
			}
			
			hangyeMap.put(rootHangye, subHangyeMap);
		}
//		System.out.println(hangyeMap);
		return hangyeMap;
	}
	
}
