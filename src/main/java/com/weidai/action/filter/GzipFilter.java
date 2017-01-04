package com.weidai.action.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * Created by fuck on 17/1/4.
 */
public class GzipFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String parameter = request.getParameter("gzip");
        // 判断是否包含了 Accept-Encoding 请求头部
        HttpServletRequest s = (HttpServletRequest) request;
        //"1".equals(parameter) 只是为了控制，如果传入 gzip=1，才执行压缩，目的是测试用
        HttpServletResponse resp = (HttpServletResponse) response;
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        HttpServletResponseWrapper hsrw = new HttpServletResponseWrapper(
                resp) {

            @Override
            public PrintWriter getWriter() throws IOException {
                return new PrintWriter(new OutputStreamWriter(buffer,
                        getCharacterEncoding()));
            }

            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new ServletOutputStream() {

                    @Override
                    public boolean isReady() {
                        return false;
                    }

                    @Override
                    public void setWriteListener(WriteListener writeListener) {

                    }

                    @Override
                    public void write(int b) throws IOException {
                        buffer.write(b);
                    }
                };
            }

        };

        chain.doFilter(request, hsrw);
        System.out.println("开始压缩时间：" + System.currentTimeMillis());
        byte[] gzipData = gzip(buffer.toByteArray());
        resp.addHeader("Content-Encoding", "gzip");
        resp.setContentLength(gzipData.length);
        ServletOutputStream output = response.getOutputStream();
        output.write(gzipData);
        output.flush();
        System.out.println("结束压缩时间：" + System.currentTimeMillis());

    }

    // 用 GZIP 压缩字节数组
    private byte[] gzip(byte[] data) {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10240);
        GZIPOutputStream output = null;
        try {
            output = new GZIPOutputStream(byteOutput);
            output.write(data);
        } catch (IOException e) {
        } finally {
            try {
                output.close();
            } catch (IOException e) {
            }
        }
        return byteOutput.toByteArray();
    }

    @Override
    public void destroy() {

    }

}