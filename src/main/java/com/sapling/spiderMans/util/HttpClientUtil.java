package com.sapling.spiderMans.util;

import com.google.gson.Gson;
import org.apache.axis.encoding.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * http 请求
 *
 * @author Administrator
 */
public class HttpClientUtil {

    private static final Logger LOG = Logger.getLogger(HttpClientUtil.class);

    private static final String DEFAULT_CHARSET = "GBK";

    /**
     * 连接超时时间，由bean factory设置，缺省为8秒钟
     **/
    private static int defaultConnectionTimeout = 60000;

    /**
     * 回应超时时间, 由bean factory设置，缺省为30秒钟
     */
    private static int defaultSoTimeout = 60000;

    /**
     * 闲置连接超时时间, 由bean factory设置，缺省为60秒钟
     */
    private static HttpConnectionManager connectionManager = ConnectionManagerPool.getInstance().connectionManager;

    private static HttpClientUtil httpProtocolHandler = new HttpClientUtil();

    private static SSLConnectionSocketFactory sslSocketFactory;

    static {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new HttpsX509TrustManager()}, new java.security.SecureRandom());
            sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            LOG.error("init poolingConnectionManager failed.", e);
        }
    }

    private HttpClientUtil() {
    }

    /**
     * 工厂方法
     *
     * @return
     */
    public static HttpClientUtil getInstance() {
        return httpProtocolHandler;
    }

    /**
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * @param url
     * @param headers
     * @return
     */
    public static String doGet(String url, Map<String, String> headers) {

        long startTime = System.currentTimeMillis();

        CloseableHttpClient client = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;

        try {

            String charset = getCharset(headers, "utf-8");

            client = createHttpClient();
            httpGet = createHttpMethod(new HttpGet(url), headers);

            return execute(client, httpGet, headers, url, charset);

        } catch (Exception e) {
            LOG.warn(url + " request error: " + e.getMessage(), e);
        } finally {
            LOG.info("execute " + url + " used time: " + (System.currentTimeMillis() - startTime) + "ms");
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }

        return null;

    }

    private static CloseableHttpClient createHttpClient() {
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(
                RegistryBuilder.<ConnectionSocketFactory>create().register("http",
                PlainConnectionSocketFactory.INSTANCE).register("https", sslSocketFactory).build());
        return HttpClients.custom().setConnectionManager(poolingConnectionManager).build();
    }

    /**
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, String param) {
        return doPost(url, param, (Map<String, String>) null);
    }

    /**
     * @param url
     * @param params
     * @param contentType
     * @return
     */
    public static String doPost(String url, String params, String contentType) {
        return doPost(url, params, contentType, null);
    }

    /**
     * @param url
     * @param params
     * @param requestProperties
     * @return
     */
    public static String doPost(String url, String params, Map<String, String> requestProperties) {
        return doPost(url, params, "application/x-www-form-urlencoded", requestProperties);
    }

    /**
     * @param url
     * @param params
     * @param requestProperties
     * @return
     */
    public static String doPostByStream(String url, String params, Map<String, String> requestProperties) {
        return doPost(url, params, "*/*", requestProperties);
    }

    /**
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String doPost(String url, String params, String contentType, Map<String, String> headers) {

        long startTime = System.currentTimeMillis();

        CloseableHttpClient client = null;
        HttpPost httpPost = null;

        try {

            String charset = getCharset(headers, "utf-8");

            client = createHttpClient();
            httpPost = createHttpMethod(new HttpPost(url), headers);

            if (StringUtils.isNotEmpty(params)) {
                httpPost.setEntity(new StringEntity(params, ContentType.create(contentType, charset)));
            }

            return execute(client, httpPost, headers, url, charset);

        } catch (Exception e) {
            LOG.warn(url + " request error: " + e.getMessage(), e);
        } finally {
            LOG.info("execute " + url + " used time: " + (System.currentTimeMillis() - startTime) + "ms");
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }

        return "";
    }

    private static String execute(CloseableHttpClient client, HttpRequestBase httpMethod, Map<String, String> headers, String url, String charset)
            throws IOException, ClientProtocolException {

        CloseableHttpResponse response = null;

        boolean returnHttpStatus = getParameter(headers, "Http-Status", false);
        boolean acceptErrorResult = getParameter(headers, "Read-Error", false);

        response = client.execute(httpMethod);

        int statusCode = response.getStatusLine().getStatusCode();
        String responseText = EntityUtils.toString(response.getEntity(), charset);

        if (statusCode == HttpStatus.SC_OK || acceptErrorResult) {
            return responseText;
        } else {
            LOG.warn(url + " request failed: " + responseText);
            if (returnHttpStatus) {
                if (StringUtils.isNotEmpty(responseText) && responseText.length() > 256) {
                    responseText = responseText.substring(0, 256);
                }
                return "httpStatus=" + statusCode + "&httpMessage=" + StringUtils.defaultString(responseText);
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getParameter(Map<String, String> headers, String propertyName, T defaultValue) {

        if (headers == null || headers.isEmpty()) {
            return defaultValue;
        }

        String value = headers.get(propertyName);

        if (StringUtils.isNotBlank(value)) {
            Class<T> clazz = (Class<T>) defaultValue.getClass();
            if (clazz == Integer.class) {
                return (T) Integer.valueOf(value);
            }
            if (clazz == Boolean.class) {
                return (T) Boolean.valueOf("true".equals(value));
            }
            return (T) value;
        }

        return defaultValue;

    }

    private static String getCharset(Map<String, String> headers, String defaultValue) {
        if (headers == null || headers.isEmpty()) {
            return defaultValue;
        }
        String ct = headers.get("Content-Type");
        if (StringUtils.isNotEmpty(ct)) {
            int a = ct.lastIndexOf("charset");
            if (a > -1) {
                return ct.substring(a + 8);
            }
        }
        return defaultValue;
    }

    /**
     * @param requestUrl
     * @param params
     * @param headers
     * @return
     */
    public static String doPostByMimePart(String requestUrl, Map<String, String> params, Map<String, String> headers) {

        long startTime = System.currentTimeMillis();

        HttpPost httppost = null;
        CloseableHttpResponse response = null;

        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();

            String partContentType = getParameter(headers, "Part-Content-Type", "application/xml");
            int connectTimeout = getParameter(headers, "Connection-Timeout", defaultConnectionTimeout);
            String charset = getCharset(headers, "UTF-8");

            httppost = new HttpPost(requestUrl);
            httppost.setConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(connectTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(connectTimeout).build());

            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httppost.addHeader(entry.getKey(), entry.getValue());
                }
            }

            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    entityBuilder.addPart(entry.getKey(),
                            new StringBody(entry.getValue(), ContentType.create(partContentType, charset)));
                }
            }

            httppost.setEntity(entityBuilder.build());

            response = httpclient.execute(httppost);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), charset);
            } else {
                LOG.warn(requestUrl + " request failed: " + EntityUtils.toString(response.getEntity(), charset));
            }

        } catch (Exception e) {
            LOG.warn(requestUrl + " request failed: " + e.getMessage(), e);
        } finally {
            LOG.info("execute " + requestUrl + " used time: " + (System.currentTimeMillis() - startTime) + "ms");
            if (httppost != null) {
                httppost.releaseConnection();
            }
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }

        return null;
    }

    /**
     * post请求，utf-8
     *
     * @param url
     * @param parameters
     * @return
     */
    public static String doPost(String url, Map<String, String> parameters) {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        if (parameters != null) {
            Iterator<?> iter = parameters.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<?, ?> element = (Entry<?, ?>) iter.next();
                post.setParameter(element.getKey().toString(), element
                        .getValue() == null ? "" : element.getValue().toString());
            }
        }
        post.getParams().setContentCharset("UTF-8");
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        String respStr = "";
        try {
            client.executeMethod(post);
            respStr = post.getResponseBodyAsString();
            LOG.info("doPost respStr :" + respStr + " url:" + url);
        } catch (HttpException e) {
            LOG.error("doPost HttpException :" + e.getMessage() + " url:" + url, e);
        } catch (IOException e) {
            LOG.error("doPost IOException :" + e.getMessage() + " url:" + url, e);
        }
        return respStr;
    }

    public static String doPost2(String url, String param) {
        String result = "";
        PostMethod postMethod = new PostMethod(url);
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
        postMethod.getParams().setSoTimeout(defaultConnectionTimeout);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        postMethod.setParameter("param", param);

        postMethod.setRequestHeader("Connection", "close");
        try {
            int status = client.executeMethod(postMethod);
            if (status == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "utf8"));
                StringBuilder stringBuffer = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                result = stringBuffer.toString();
            } else {
                LOG.info("请求失败！状态码：" + status);
            }
        } catch (Exception e) {
            LOG.warn(url + " request error: " + e.getMessage(), e);
        } finally {
            postMethod.releaseConnection();
            if (client != null) {
                ((SimpleHttpConnectionManager) client.getHttpConnectionManager()).shutdown();
            }
        }
        return result;
    }

    private static <T extends HttpRequestBase> T createHttpMethod(T method, Map<String, String> headers) {

        int connectTimeout = getParameter(headers, "Connection-Timeout", defaultConnectionTimeout);
        method.setConfig(RequestConfig.custom().setConnectionRequestTimeout(connectTimeout).setConnectTimeout(connectTimeout)
                .setSocketTimeout(connectTimeout).build());

        if (headers == null || headers.size() == 0) {
            return method;
        }

        Set<Entry<String, String>> entrySet = headers.entrySet();
        Iterator<Entry<String, String>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Entry<String, String> next = iterator.next();
            String key = next.getKey();
            if ("Http-Status".equals(key) || "Read-Error".equals(key) || "Connection-Timeout".equals(key)) {
                continue;
            }
            String value = next.getValue();
            method.addHeader(key, value);
        }

        return method;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJsonData(HttpServletRequest request) {
        Map<String, Object> parms = new HashMap<String, Object>();
        BufferedReader br = null;
        try {
            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
            	 LOG.info("HttpClientUtil parseJsonData while" +line);
                sb.append(new String(line.getBytes("UTF-8"), "UTF-8"));
            }
            String result = sb.toString();
            if (Util.isNotEmpty(result)) {
                Gson gson = new Gson();
                parms = gson.fromJson(result, Map.class);
            }
        } catch (Exception e) {
            LOG.error("HttpClientUtil parseJsonData error:" + e.getLocalizedMessage(), e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return parms;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXmlData(HttpServletRequest request) {
        Map<String, String> parms = new HashMap<String, String>();
        BufferedReader br = null;
        try {
            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(new String(line.getBytes("UTF-8"), "UTF-8"));
            }
            String result = sb.toString();
            LOG.info("HttpClientUtil xml" + result);
            if (Util.isNotEmpty(result)) {
                parms = XmlUtils.parseToMap(result);
            }
        } catch (Exception e) {
            LOG.error("HttpClientUtil parseXmlData error:" + e.getLocalizedMessage(), e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return parms;
    }

    /*
     * @reqUrl 请求的url @dateType 数据类型 json,xml
     */
    public String getResponseMess(String reqUrl, String dateType) {
        long startTime = System.currentTimeMillis();
        String resMess = "";
        HttpClient httpClient;
        GetMethod getMethod = null;
        try {
            httpClient = new HttpClient(connectionManager);

            getMethod = new GetMethod(reqUrl);
            getMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, "FZSservice 1.0");

            LOG.info("request:" + getMethod.getStatusLine());
            getMethod.getParams().setContentCharset(DEFAULT_CHARSET);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
            if (dateType != null && "json".equals(dateType)) {
                getMethod.setRequestHeader("Accept", "application/json");
            }
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                resMess = getMethod.getResponseBodyAsString();
            }

        } catch (Exception e) {
            LOG.error(" httpClient error:" + e.getMessage(), e);

        } finally {
            LOG.info("http连接 耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
            if (getMethod != null) {
                getMethod.releaseConnection();
            }

        }

        return resMess;
    }

    /**
     * param以流的方式写入到请求中
     *
     * @param requestUrl
     * @param method
     * @param param
     * @return
     */
    public String httpRequest(String requestUrl, String method, String param, boolean isJson) {
        StringBuilder temp;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (isJson) {
                urlConnection.setRequestProperty("Content-Type", "application/json");
            }
            urlConnection.setRequestMethod(method);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(param.getBytes("utf-8"));
            outputStream.flush();
            InputStream in = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            temp = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            int code = urlConnection.getResponseCode();
            if (code != 200) {
                throw new RuntimeException("服务器错误：" + code);
            }
        } catch (Exception e) {
            throw new RuntimeException("服务器错误：" + e.getMessage());
        }
        return temp.toString();
    }

    public String httpRequest(String requestUrl, String method, String param) {
        return httpRequest(requestUrl, method, param, false);
    }

    /**
     * @reqUrl 请求的url @dateType 数据类型 json,xml
     */
    public String postResponseMess(String reqUrl, String dateType, Map<String, String> postParam) {
        String resMess = "";
        long startTime = System.currentTimeMillis();
        HttpClient httpClient;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient(connectionManager);
            postMethod = new PostMethod(reqUrl);
            postMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, "FZSservice 1.0");
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
            if (dateType != null && "json".equals(dateType)) {
                postMethod.setRequestHeader("Accept", "application/json");

            }

            if (postParam != null && !postParam.isEmpty()) {
                Set<String> keySet = postParam.keySet();
                for (String keyName : keySet) {
                    String keyValue = postParam.get(keyName);
                    postMethod.addParameter(keyName, keyValue);
                }
            }
            // 设置请求参数为utf-8字符集
            postMethod.getParams().setContentCharset("UTF-8");
            System.out.println(Arrays.toString(postMethod.getParameters()));
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                resMess = postMethod.getResponseBodyAsString();
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);

        } finally {
            LOG.info("http连接 耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }

        return resMess;
    }

    public void post(String reqUrl, String dateType, Map<String, String> postParam) {
        long startTime = System.currentTimeMillis();
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient(connectionManager);
            postMethod = new PostMethod(reqUrl);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
            postMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, "FZSservice 1.0");

            if (dateType != null && "json".equals(dateType)) {
                postMethod.setRequestHeader("Accept", "application/json");

            }
            if (postParam != null && !postParam.isEmpty()) {
                Set<String> keySet = postParam.keySet();
                for (String keyName : keySet) {
                    String keyValue = postParam.get(keyName);
                    postMethod.addParameter(keyName, keyValue);
                }
            }
            // 设置请求参数为utf-8字符集
            postMethod.getParams().setContentCharset("UTF-8");
            LOG.info(Arrays.toString(postMethod.getParameters()));
            LOG.info("uri is:" + postMethod.getURI());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            LOG.info("http连接 耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }

    }

    /*
     * @reqUrl 请求的url @dateType 数据类型 json,xml @postParam post参数 @charset 字符集
     */
    public String postResponseMess(String reqUrl, String dateType, Map<String, String> postParam, String charset) {
        String resMess = "";
        long startTime = System.currentTimeMillis();
        HttpClient httpClient;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient(connectionManager);
            postMethod = new PostMethod(reqUrl);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
            postMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, "FZSservice 1.0");
            if (dateType != null && "json".equals(dateType)) {
                postMethod.setRequestHeader("Accept", "application/json");

            }

            if (postParam != null && !postParam.isEmpty()) {
                Set<String> keySet = postParam.keySet();
                for (String keyName : keySet) {
                    String keyValue = postParam.get(keyName);
                    postMethod.addParameter(keyName, keyValue);
                }
            }
            if (charset != null && !"".equals(charset)) {
                postMethod.getParams().setContentCharset(charset);
            }

            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                resMess = postMethod.getResponseBodyAsString();
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);

        } finally {
            LOG.info("http连接 耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }

        return resMess;
    }

    public String postXML(String reqUrl, String data, Map<String, String> requestHeader) {
        String resMess = "";
        long startTime = System.currentTimeMillis();
        HttpClient httpClient = null;
        PostMethod postMethod = getPostMethod(requestHeader, reqUrl);
        try {
            httpClient = new HttpClient(connectionManager);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            postMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, "FZSservice 1.0");
            if (Util.isNull(requestHeader) || requestHeader.size() <= 0) {
                if (Util.isNotEmpty(data)) {
                    URLEncoder.encode(data, "ISO-8859-1");
                    postMethod.setRequestHeader("Content-type", "text/xml; charset=utf-8");
                }
            } else {
                if (Util.isNotEmpty(data))
                    URLEncoder.encode(data, "utf-8");
            }
            postMethod.setRequestBody(data);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                resMess = postMethod.getResponseBodyAsString();
            } else {
                System.out.println(postMethod.getResponseBodyAsString());
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            LOG.info("http连接 耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
            if (postMethod != null) {

                postMethod.releaseConnection();
            }
        }
        return resMess;
    }

    public String postStatus(String reqUrl, String data, Map<String, String> requestHeader) {
        String resMess = "falied";
        long startTime = System.currentTimeMillis();
        HttpClient httpClient = null;
        PostMethod postMethod = getPostMethod(requestHeader, reqUrl);
        try {
            httpClient = new HttpClient(connectionManager);
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            postMethod.getParams().setParameter(HttpMethodParams.USER_AGENT, "FZSservice 1.0");
            if (Util.isNull(requestHeader) || requestHeader.size() <= 0) {
                if (Util.isNotEmpty(data)) {
                    URLEncoder.encode(data, "ISO-8859-1");
                    postMethod.setRequestHeader("Content-type", "text/xml; charset=utf-8");
                }
            } else {
                if (Util.isNotEmpty(data))
                    URLEncoder.encode(data, "utf-8");
            }
            postMethod.setRequestBody(data);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                resMess = "success";
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            LOG.info("http连接 耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }
        return resMess;
    }

    public String postTestQueryXML(HttpClientUtil client, String reqUrl, String data, String authorization) {
        String resMess = "";
        long startTime = System.currentTimeMillis();
        HttpClient httpClient = client.getHttpClient();
        PostMethod postMethod = null;
        Base64 base64 = new Base64();
        try {
            byte[] textByte = authorization.getBytes("UTF-8");
            String encodedText = base64.encode(textByte);
            postMethod = new PostMethod(reqUrl);
            InputStream in = new ByteArrayInputStream(data.getBytes("utf-8"));
            BufferedInputStream bf = new BufferedInputStream(in);
            postMethod.setRequestBody(bf);
            postMethod.setRequestHeader("Content-type", "application/json;charset=utf-8;");
            postMethod.setRequestHeader("Accept", "application/json;");
            postMethod.setRequestHeader("Content-Length", String.valueOf(data.length()));
            postMethod.setRequestHeader("Authorization", encodedText);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                resMess = postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            LOG.info("http连接 耗费时间：" + (System.currentTimeMillis() - startTime) + "ms");
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
        }
        return resMess;
    }

    public HttpClient getHttpClient() {
        HttpClient httpClient = new HttpClient(connectionManager);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
        return httpClient;
    }

    public PostMethod getPostMethod(Map<String, String> map, String url) {
        PostMethod postMethod = new PostMethod(url);
        if (Util.isNotNull(map) && map.size() > 0) {
            Set<Entry<String, String>> entrySet = map.entrySet();
            Iterator<Entry<String, String>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                postMethod.setRequestHeader(key, value);
            }
        }
        return postMethod;
    }

    private static class HttpsX509TrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
