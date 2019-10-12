package com.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class UtilConfiguration {

	
	public static void copyFile(String filePath, String destinationPath) throws IOException {
		File source = new File(filePath);
		File destintion = new File(destinationPath);
		
		FileUtils.copyFile(source, destintion);
	}
}
