package com.YYSchedule.store.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.YYSchedule.store.exception.FtpException;
import com.YYSchedule.store.util.PathUtils;

public class FtpUtils {
	
	/**
	 * validate ftp path
	 * @param dirPath
	 * @return dirPath
	 * @throws FtpException
	 */
	public static String validateFtpDirectory(FTPClient ftpClient, String dirPath) throws FtpException {
		
		boolean dirExists = true;
		
		String[] dirArray = PathUtils.formatPath4FTP(dirPath).split("/");
		for (String dirName : dirArray) {
			if (dirName != null && dirName.length() > 0) {
				if (dirExists) {
					try {
						dirExists = ftpClient.changeWorkingDirectory(dirName);
					} catch (IOException ioe) {
						throw new FtpException("Failed to change working directory to [ " + dirName + " ] : " + ioe.getMessage(), ioe);
					}
				}
				if(!dirExists) {
					try {
						if (!ftpClient.makeDirectory(dirName)) {
							throw new FtpException("Failed to create ftp dirtectory correctly: " + dirPath);
						}
					} catch (IOException ioe) {
						throw new FtpException("Failed to create ftp directory correctly [ " + dirName + " ] : " + ioe.getMessage(), ioe);
					}
					try {
						if (!ftpClient.changeWorkingDirectory(dirName)) {
							throw new FtpException("Failed to create ftp dirtectory correctly: " + dirPath);
						}
					} catch (IOException ioe) {
						throw new FtpException("Failed to change working directory to [ " + dirName + " ] : " + ioe.getMessage(), ioe);
					}
				}
			}
		}
		return dirPath;
	}
		
	/**
	 * list ftp directory
	 * @param ftpClient
	 * @param dirPath
	 * @return directory list
	 */
	public static List<String> listFtpDirectory(FTPClient ftpClient, String dirPath) throws FtpException {
		
		List<String> dirList = new ArrayList<String>();
		
		try {
			ftpClient.changeWorkingDirectory(dirPath);
			String[] dirItems = ftpClient.listNames();
			if (dirItems != null && dirItems.length > 0) {
				for (String item : dirItems) {
					dirList.add(item);
				}
			}
		} catch (IOException ioe) {
			throw new FtpException("Failed to list directory on ftp : " + ioe.getMessage(), ioe);
		}
		
		
		return dirList;
	}
	
	/**
	 * upload local file to remote directory
	 * @param ftpClient
	 * @param localFilePath
	 * @param remoteDirectory
	 * @return
	 * @throws FtpException
	 */
	public static boolean upload(FTPClient ftpClient, String localFilePath, String remoteDirectory) throws FtpException{
		
		File localFile = new File(localFilePath);
		String fileName = localFile.getName();
		InputStream is = null;
		boolean isSucceed = false;
		
		if(!localFile.exists()) {
			throw new FtpException("Failed to find local file : " + localFilePath);
		}
		
		try {
			is = new FileInputStream(localFile);
		} catch (FileNotFoundException fnfe) {
			throw new FtpException("Failed to find local file : " + localFilePath + " : " + fnfe.getMessage(), fnfe);
		}
		
		String remoteFilePath = validateFtpDirectory(ftpClient, PathUtils.formatPath4FTP(remoteDirectory)) + "/" + fileName;
		try {
			if (!isFileExist(ftpClient, fileName)) {
				isSucceed = ftpClient.storeFile(fileName, is);
				if(!isSucceed) {
					throw new FtpException("Failed to upload file to ftp server : " + ftpClient.getReplyCode() + ":aha~:" + ftpClient.getReplyString());
				}
			} else {
				throw new FtpException("Failed to upload file to ftp server : " + remoteFilePath + " : File already exist");
			}
		} catch (IOException ioe) {
			throw new FtpException("Failed to upload file to ftp server : " + remoteFilePath + " : " + ioe.getMessage(), ioe);
		} finally {
			try {
				is.close();
			} catch (IOException ioe) {
				throw new FtpException("Failed to close input stream : " + ioe.getMessage(), ioe);
			}
		}
		
		return isSucceed;
	}
	
	/**
	 * download remote file to local
	 * @param ftpClient
	 * @param remoteFilePath
	 * @param localDirectory
	 * @return
	 * @throws FtpException
	 */
	public static String download(FTPClient ftpClient, String remoteFilePath, String localDirectory) throws FtpException {
		
		remoteFilePath = PathUtils.formatPath4FTP(remoteFilePath);
		String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/") + 1);
		String localFilePath = PathUtils.formatPath4File(localDirectory) + File.separator + fileName;
		File localFile = new File(localFilePath);
		OutputStream fos = null;
		
		if(localFile.exists()) {
			if(!localFile.delete()){
				throw new FtpException("Local file has existed : " + localFilePath +"and failed to delete it");//modify by WXY 20130913
			}
//			throw new FtpException("Local file has existed : " + localFilePath);
		}
		if(!new File(localDirectory).exists()) {
			new File(localDirectory).mkdirs();
		}

		try {
			fos = new BufferedOutputStream(new FileOutputStream(localFile));
		} catch (FileNotFoundException fnfe) {
			throw new FtpException("Failed to create local file : " + localFilePath + " : "+ fnfe.getMessage(), fnfe);
		}
		
		try {
			if(!ftpClient.retrieveFile(remoteFilePath, fos)) {
				@SuppressWarnings("unused")
				int i = ftpClient.getReplyCode();
				localFilePath = null;
			}
		} catch (IOException ioe) {
			throw new FtpException("Failed to download file : " + remoteFilePath, ioe);
		} finally {
			try {
				fos.close();
			} catch (IOException ioe) {
				throw new FtpException("Failed to close output stream : " + ioe.getMessage(), ioe);
			}
		}
		
		return localFilePath;
	}
	
	/**
	 * is file exist
	 * @param ftpClient
	 * @param filePath
	 * @return
	 * @throws FtpException
	 */
	@SuppressWarnings("unused")
	private static boolean isExist(FTPClient ftpClient, String filePath) throws FtpException {
		boolean isExist = false;
		if (filePath != null && filePath.length() > 0) {
			return isExist;
		} else {
			String fileDir = filePath.substring(0, filePath.lastIndexOf("/"));
			String filename = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
			validateFtpDirectory(ftpClient, fileDir);
			String[] fileNames;
			try {
				fileNames = ftpClient.listNames();
			} catch (IOException ioe) {
				throw new FtpException("Failed to list directory : " + fileDir, ioe);
			}
			if (fileNames != null && fileNames.length > 0) {
				for (int i = 0; i < fileNames.length; i++) {
					if(filename.equals(fileNames[i])) {
						isExist = true;
						break;
					}
				}
			}
		}
		return isExist;
	}

	/**
	 * is file exist
	 * @param ftpClient
	 * @param filePath
	 * @return
	 * @throws FtpException
	 */
	public static boolean isFileExist(FTPClient ftpClient, String filePath) throws FtpException {
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftpClient.listFiles(filePath);
		} catch (IOException ioe) {
			throw new FtpException("Failed to list file : " + filePath + " : " + ioe.getMessage(), ioe);
		}
		if (ftpFiles.length == 1 && FTPFile.FILE_TYPE == ftpFiles[0].getType()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * delete ftp file
	 * @param ftpClient
	 * @param filePath
	 * @return
	 * @throws FtpException
	 */
	public static boolean deleteFtpFile(FTPClient ftpClient, String filePath) throws FtpException {
		try {
			return ftpClient.deleteFile(filePath);
		} catch (IOException ioe) {
			throw new FtpException("Failed to delete remote file : " + filePath + " : " + ioe.getMessage(), ioe);
		}
	}
	
	/**
	 * close ftp connection
	 * @param ftpClient
	 * @return
	 * @throws FtpException
	 */
	public static boolean disconnect(FTPClient ftpClient) throws FtpException{
		boolean isSucceed = false;
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
			} catch (IOException ioe) {
				throw new FtpException("Failed to logout ftp : " + ioe.getMessage(), ioe);
			}
			try {
				ftpClient.disconnect();
				isSucceed = true;
			} catch (IOException ioe) {
				throw new FtpException("Failed to close ftp connection : " + ioe.getMessage(), ioe);
			}
		} else {
			isSucceed = true;
		}
		return isSucceed;
	}
	/**
	 * get file length
	 * @param ftpClient
	 * @param filePath
	 * @return
	 */
	public static Long getFileLength(FTPClient ftpClient, String filePath){
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftpClient.listFiles(filePath);
		} catch (IOException ioe) {
			throw new FtpException("Failed to list file : " + filePath + " : " + ioe.getMessage(), ioe);
		}
		if (ftpFiles.length == 1 && FTPFile.FILE_TYPE == ftpFiles[0].getType()) {
			return ftpFiles[0].getSize();
		} else {
			return 0L;
		}
	}
}
