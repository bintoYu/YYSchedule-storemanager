/**
 * Generated at 2012-4-26.
 * PathToolkit.java
 */
package com.YYSchedule.store.util;

import java.io.File;

/**
 * @author long
 * @version 下午7:32:55 in 2012-4-26
 * 
 */
public class PathUtils {
	private PathUtils() {
	}

	/**
	 * format file path，change to standard path according to operate system
	 * 
	 * @param path
	 *            file path
	 * @return file path after format
	 */
	public static String formatPath4File(String path) {
		String reg0 = "\\\\+";
		String reg = "\\\\+|/+";
		String temp = path.trim().replaceAll(reg0, "/");
		temp = temp.replaceAll(reg, "/");
		if (temp.length() > 1 && temp.endsWith("/")) {
			temp = temp.substring(0, temp.length() - 1);
		}
		temp = temp.replace('/', File.separatorChar);
		return temp;
	}

	/**
	 * format file path，change to standard path remove the end '/' (FTP or Web)。
	 * 
	 * @param path
	 *            file path
	 * @return file path after format
	 */
	public static String formatPath4FTP(String path) {
		String reg0 = "\\\\+";
		String reg = "\\\\+|/+";
		String temp = path.trim().replaceAll(reg0, "/");
		temp = temp.replaceAll(reg, "/");
		if (temp.length() > 1 && temp.endsWith("/")) {
			temp = temp.substring(0, temp.length() - 1);
		}
		return temp;
	}

	/**
	 * get ParentPath for FTP
	 * 
	 * @param path
	 *            FTP path
	 * @return parent path if has, null if not
	 */
	public static String genParentPath4FTP(String path) {
		String pp = new File(path).getParent();
		if (pp == null)
			return null;
		else
			return formatPath4FTP(pp);
	}

	/**
	 * validate directory
	 * 
	 * @param dirPath
	 * @return available directory path
	 */
	public static String validateDirectory(String dirPath) {
		return dirPath.endsWith("/") ? dirPath : dirPath + "/";
	}

	/**
	 * get file extension
	 * 
	 * @param filePath
	 * @return file extension
	 */
	public static String getFileExtension(String filePath) {
		return filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
	}

	/**
	 * fotmat url, replace some character like "&,=..." with UTF-8 code
	 * 
	 * @param url
	 * @return
	 */
	public static String formatUrl(String url) {
		String reg0 = "&";
		String temp = url.trim().replaceAll(reg0, "\"&\"");
		return temp;
	}
}
