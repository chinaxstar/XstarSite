package cn.xstar.site.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StreamUtil {

    public synchronized static String readStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuffer buffer = new StringBuffer();
        String temp;
        try {
            while ((temp = reader.readLine()) != null) {
                buffer.append(temp);
            }
            reader.close();
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            closeIO(reader);
        }
        return buffer.toString();
    }

    public synchronized static void closeIO(Closeable... closeables) {
        for (Closeable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
