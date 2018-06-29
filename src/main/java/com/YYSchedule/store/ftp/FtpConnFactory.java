/**
 * 
 */
package com.YYSchedule.store.ftp;

import java.io.IOException;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.YYSchedule.store.exception.FtpException;

@Component
public class FtpConnFactory {
	
	@Value("#{config['ftp_server_urls']}")
	private String ftpHost;

	private String domain;

	private int port;

	private String username;

	private String password;

	private String path;

	private String fileName;

	private Matcher matcher;
	
	/**
	 * connect and login
	 * 
	 * @param ftpHost
	 * @return Map< ftpClient, ftpHost >
	 * @throws FtpException
	 */
	public FTPClient connect() throws FtpException {

		//parse the host
		try {
			parse(ftpHost);
		} catch (FtpException fe) {
			throw new FtpException("Invalid ftp address format : " + ftpHost, fe);
		}

		// connect ftp server and login
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(domain, port);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				ftpClient.disconnect();
				throw new FtpException("Failed to connect ftp server : " + ftpHost);
			}

			ftpClient.login(username, password);
			// initialization
			ftpClient.setKeepAlive(true);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setControlEncoding("UTF-8");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setBufferSize(10240);
			ftpClient.setDataTimeout(120000);
			ftpClient.setConnectTimeout(60000);
			ftpClient.setControlKeepAliveTimeout(299);

		} catch (SocketException se) {
			throw new FtpException("Failed to establish ftp socket connection : " + se.getMessage(), se);
		} catch (IOException ioe) {
			throw new FtpException("Failed to read/write from ftp connection : " + ioe.getMessage(), ioe);
		} catch (Exception e) {
			throw new FtpException("Failed to read/write from ftp connection : " + e.getMessage(), e);
		}

		return ftpClient;
	}
	
	 /**
     * get ftp info by resultAddress
     * @param resultAddress
     * @throws FtpException
     */
	public void parse(String resultAddress) throws FtpException {
		if (resultAddress.length() == 0 || resultAddress ==null  ) {
			throw new FtpException("Address must not be null or empty.");
		}
		
		String regex = "ftp://(.+):(.+)@([^:]*):(\\d+)(/*[\\w\\./]+)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(resultAddress);
		
		if (!matcher.find()) {
			throw new FtpException("Address [" + resultAddress + "] is invalid.");
		} else {
			
			this.username = matcher.group(1);
			this.password = matcher.group(2);
			this.domain = matcher.group(3);
			this.port = Integer.parseInt(matcher.group(4));
			String filePath = matcher.group(5);
			
			if (filePath == null) {
				this.path = "/";
			} else if (filePath != null && filePath.endsWith("/")) {
				this.path = filePath;
			} else if (filePath != null && !filePath.endsWith("/")) {
				int index = filePath.lastIndexOf("/");
				if (index >= 0) {
					this.path = filePath.substring(0, index + 1);
					this.fileName = filePath.substring(index + 1, filePath.length());
				}
			}
		}
	}
	
	public String getDomain() {
		return domain;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPath() {
		return path;
	}

	public String getFileName() {
		return fileName;
	}
}
