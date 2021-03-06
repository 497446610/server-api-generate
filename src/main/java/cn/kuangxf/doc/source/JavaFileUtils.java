/*
 * @(#)JavaFileUtils.java        1.0 2018年3月19日
 *
 *
 */

package cn.kuangxf.doc.source;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 遍历项目中的java文件.
 *
 * @version 1.0 2018年3月19日
 * @author kuangxf
 * @history
 * 
 */
public class JavaFileUtils {
	private final static Logger logger = LoggerFactory.getLogger(JavaFileUtils.class);
	private final static String JAVAPATHPREFIX;
	public final static String SEPARATOR = Character.toString(File.separatorChar);

	static {
		JAVAPATHPREFIX = StringUtils.join(new String[] { "src", "main", "java" }, SEPARATOR);
	}

	/**
	 * 遍历所有java文件
	 * 
	 * @param directory
	 *            项目的跟路径
	 * @return JSON格式数据：[{ "modifyTime": 1520490330859, "className":
	 *         "com.panda.generic.buyer.domain.po.BuyerAddressPo" },{......}]
	 * @throws Exception
	 */
	public static JSONArray listFiles(String directory) throws Exception {
		JSONArray result = new JSONArray();
		File file = new File(directory);
		if (!file.exists()) {
			throw new Exception(directory + " not exists.");
		}
		if (!file.isDirectory()) {
			throw new Exception(directory + " is not directory.");
		}

		logger.info("------------->{}", JAVAPATHPREFIX);
		Collection<File> files = FileUtils.listFilesAndDirs(file, new JavaFileFilter(), new JavaDirectoryFilter());
		Iterator<File> fileIterator = files.iterator();

		while (fileIterator.hasNext()) {
			File javaFile = fileIterator.next();

			if (!javaFile.getName().endsWith(".java")) {
				continue;
			}

			String filePath = javaFile.getAbsolutePath();
			int prefixIndex = filePath.indexOf(JAVAPATHPREFIX) + JAVAPATHPREFIX.length() + 1;

			String javaPackagePathName = filePath.substring(prefixIndex);
			String relativePath = filePath.substring(directory.length());
			String[] packageSplit = javaPackagePathName.split("\\\\");
			packageSplit[packageSplit.length - 1] = packageSplit[packageSplit.length - 1].replace(".java", "");
			String className = StringUtils.join(packageSplit, "."); // 类名

			int index = className.lastIndexOf(".");
			String packageName = ""; // 包名
			String simpleName = className;
			if (index > 0) {
				packageName = className.substring(0, index);
				simpleName = className.substring(index+1);
			}

			JSONObject clsJSONObject = new JSONObject();
			clsJSONObject.put("packageName", packageName);//
			clsJSONObject.put("className", className);
			clsJSONObject.put("simpleName", simpleName);
			clsJSONObject.put("modifyTime", javaFile.lastModified());
			clsJSONObject.put("relativePath", relativePath);

			result.add(clsJSONObject);

		}
		logger.info("------------->{}", result.toString());
		return result;
	}

	static class JavaFileFilter implements IOFileFilter {

		@Override
		public boolean accept(File file) {
			if (file.isFile() && file.getName().endsWith(".java")) {
				return true;
			}
			return false;
		}

		@Override
		public boolean accept(File dir, String name) {
			return true;
		}

	}

	static class JavaDirectoryFilter implements IOFileFilter {

		@Override
		public boolean accept(File file) {
			if (file.getName().equalsIgnoreCase("resource")) {
				return false;
			}
			if (file.getName().equalsIgnoreCase(".git")) {
				return false;
			}
			if (file.getName().equalsIgnoreCase(".settings")) {
				return false;
			}
			if (file.getName().equalsIgnoreCase("target")) {
				return false;
			}
			return true;
		}

		@Override
		public boolean accept(File dir, String name) {

			return true;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		JavaFileUtils.listFiles("E:\\java-panda\\java-panda-2b-api");

	}

}
