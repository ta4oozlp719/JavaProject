package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	public static void saveImage(String root, String fname, byte[] data) throws IOException {
		root += "/images";
		File f = new File(root);
		if (!f.exists()) f.mkdir();
		
		f = new File(root + "/" + fname);
		FileOutputStream out = new FileOutputStream(f);
		out.write(data);
		out.close();
	}

}
