
package com.statt.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class URLClientUtil {

    public interface UPLOADTYPEENUM {
        public final int PIC = 0;
        public final int VOICE = 1;
        public final int USERIMG = 2;
    }

    public static String AccessWeb(HashMap<String, String> param, String url) {

        String content = "";
        HttpPost request = new HttpPost(url);
        List<NameValuePair> paramers = new LinkedList<NameValuePair>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            paramers.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue()));
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(paramers, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000 * 7);
        HttpConnectionParams.setSoTimeout(httpParams, 1000 * 7);
        request.setEntity(entity);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        BufferedReader in = null;
        try {
            // 执行请求参数项
            HttpResponse response = httpClient.execute(request);
            // 判断是否请求成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获得响应信息
                in = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));

                StringBuffer string = new StringBuffer("");
                String lineStr = "";
                while ((lineStr = in.readLine()) != null) {
                    string.append(lineStr + "\n");
                }

                content = string.toString();
                return content;
            } else {

            }

        } catch (Exception e) {
            System.out.println("temp==" + e.toString());

            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 释放网络连接资源
            httpClient.getConnectionManager().shutdown();
        }
        return "";

    }

    /**
     * <发送post请求>
     * 
     * @param param
     *            : 文本参数
     * @param fileUrl
     *            : 文件路径
     * @param fileMapKey
     *            : 发送文件的key
     * @param url
     *            : 请求接口地址
     */
    public static String postWithFile(HashMap<String, String> param,
            String fileUrl, String fileMapKey, String url) {
        MultipartEntity mpEntity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE);
        String is = null;
        for (Map.Entry<String, String> entry : param.entrySet()) {
            try {
                StringBody sb = new StringBody(entry.getValue(),
                        Charset.forName("utf-8"));
                mpEntity.addPart(entry.getKey(), sb);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (null != fileUrl && !"".equals(fileUrl.trim())) {
            FileBody img_file = new FileBody(new File(fileUrl));
            mpEntity.addPart(fileMapKey, img_file);
        }
        try {
            DefaultHttpClient client = createHttpClient();

            HttpPost post = new HttpPost(url);
            post.setEntity(mpEntity);

            HttpResponse response = client.execute(post);
            is = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("is--->" + is);
        } catch (Exception e) {
            e.printStackTrace();
            is = null;
        }
        return is;
    }

    /**
     * <创建Android默认的HttpClient>
     */
    private static DefaultHttpClient createHttpClient() {
        HttpParams params = new BasicHttpParams();

        /* http的版本号 */
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

        /* 设置post请求时，消息体的编码格式 */
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

        HttpProtocolParams.setUseExpectContinue(params, true);

        /* 连接超时 */
        HttpConnectionParams.setConnectionTimeout(params, 8000);

        /* 请求超时 */
        HttpConnectionParams.setSoTimeout(params, 15000);

        SchemeRegistry schReg = new SchemeRegistry();

        schReg.register(new Scheme("Http", PlainSocketFactory
                .getSocketFactory(), 8800));
        schReg.register(new Scheme("Https", PlainSocketFactory
                .getSocketFactory(), 443));

        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
                params, schReg);
        return new DefaultHttpClient(conMgr, params);
    }

    public static String AccessWeb2(HashMap<String, String> param, String url) {

        String content = "";
        HttpPost request = new HttpPost(url);
        List<NameValuePair> paramers = new LinkedList<NameValuePair>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            paramers.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue()));
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(paramers, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 7000);
        HttpConnectionParams.setSoTimeout(httpParams, 8000);
        request.setEntity(entity);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        BufferedReader in = null;
        try {
            // 执行请求参数项
            HttpResponse response = httpClient.execute(request);
            // 判断是否请求成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获得响应信息
                in = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));

                StringBuffer string = new StringBuffer("");
                String lineStr = "";
                while ((lineStr = in.readLine()) != null) {
                    string.append(lineStr + "\n");
                }

                content = string.toString();
                return content;
            } else {

            }

        } catch (Exception e) {
            System.out.println("temp==" + e.toString());

            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // 释放网络连接资源
            httpClient.getConnectionManager().shutdown();
        }
        return "";

    }

    public static String AccessWebUtil(HashMap<String, String> param, String url) {

        String content = "";
        HttpPost request = new HttpPost(url);
        List<NameValuePair> paramers = new LinkedList<NameValuePair>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            paramers.add(new BasicNameValuePair(entry.getKey(), entry
                    .getValue()));
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(paramers, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000 * 6);
        HttpConnectionParams.setSoTimeout(httpParams, 1000 * 6);
        request.setEntity(entity);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        BufferedReader in = null;
        try {
            // 执行请求参数项
            HttpResponse response = httpClient.execute(request);
            // 判断是否请求成功
            // System.out.println("temp=="+response.getStatusLine().getStatusCode()
            // );
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获得响应信息
                in = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));

                StringBuffer string = new StringBuffer("");
                String lineStr = "";
                while ((lineStr = in.readLine()) != null) {
                    string.append(lineStr + "\n");
                }

                content = string.toString();
                return content;
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放网络连接资源
            httpClient.getConnectionManager().shutdown();
        }
        return "";

    }

    public static String AccessWebUtil(String param, String url) {

        System.out.println("url:" + url);
        String content = "";
        HttpPost request = new HttpPost(url + param);
        List<NameValuePair> paramers = new LinkedList<NameValuePair>();
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(paramers, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000 * 6);
        HttpConnectionParams.setSoTimeout(httpParams, 1000 * 6);
        request.setEntity(entity);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        BufferedReader in = null;
        try {
            // 执行请求参数项
            HttpResponse response = httpClient.execute(request);
            // 判断是否请求成功
            // System.out.println("temp=="+response.getStatusLine().getStatusCode()
            // );
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获得响应信息
                in = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));

                StringBuffer string = new StringBuffer("");
                String lineStr = "";
                while ((lineStr = in.readLine()) != null) {
                    string.append(lineStr + "\n");
                }

                content = string.toString();
                return content;
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放网络连接资源
            httpClient.getConnectionManager().shutdown();
        }
        return "";

    }

    /**
     * <发送post请求>
     * 
     * @param param
     *            : 文本参数
     * @param fileUrl
     *            : 文件路径
     * @param fileMapKey
     *            : 发送文件的key
     * @param url
     *            : 请求接口地址
     */
    public static String postWithFileByFile(HashMap<String, String> param,
            String fileUrl, String fileMapKey, String url) {
        MultipartEntity mpEntity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE, null,
                Charset.forName("UTF-8"));
        String is = null;
        for (Map.Entry<String, String> entry : param.entrySet()) {
            try {
                StringBody sb = new StringBody(entry.getValue(),
                        Charset.forName("utf-8"));
                mpEntity.addPart(entry.getKey(), sb);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (null != fileUrl && !"".equals(fileUrl.trim())) {
            FileBody img_file = new FileBody(new File(fileUrl), "", "utf-8");
            mpEntity.addPart(fileMapKey, img_file);
        }
        try {
            DefaultHttpClient client = createHttpClient();

            HttpPost post = new HttpPost(url);
            post.setEntity(mpEntity);

            HttpResponse response = client.execute(post);
            is = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("is--->" + is);
        } catch (Exception e) {
            e.printStackTrace();
            is = null;
        }
        return is;
    }

    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 1000; //超时时间
    private static final String CHARSET = "utf-8"; //设置编码

    /**
     * android上传文件到服务器
     * @param file  需要上传的文件
     * @param RequestURL  请求的rul
     * @return  返回响应的内容
     */
    public static String uploadFile(File file, String RequestURL, int type)
    {
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString(); //边界标识   随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; //内容类型

        System.out.println(RequestURL + "  url.....");
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            conn.setRequestMethod("POST"); //请求方式
            conn.setRequestProperty("Charset", CHARSET); //设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

            if (file != null)
            {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key   只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的   比如:abc.png  
                 */
                sb.append("Content-Disposition: form-data; name=\"fileupload\"; filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1)
                {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码  200=成功
                 * 当响应成功，获取响应的流  
                 */
                int res = conn.getResponseCode();
                System.out.println(res + "   res..aa");
                Log.e(TAG, "response code:" + res);
                //                if(res==200)
                //                {
                Log.e(TAG, "request success");
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1)
                {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
                Log.e(TAG, "result : " + result);
                //                }
                //                else{
                //                    Log.e(TAG, "request error");
                //                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
