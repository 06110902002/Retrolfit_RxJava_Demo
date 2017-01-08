package downupload;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 自定义响应类,此类可以获取到被下载文件的大小，已经下载或上传字节数
 * 把这些信息执行ProgressListener分发出去
 *
 * 在上面ProgressResponseBody类中，我们计算已经读取文件的字节数，并且调用了ProgressListener接口。
 * 所以这个ProgressListener接口是在子线程中运行的
 *
 */
public class ProgressResponseBody extends ResponseBody
{
    private final ResponseBody responseBody;            //信息响应体
    private final ProgressListener progressListener;    //进度信息分了接口
    private BufferedSource bufferedSource;              //信息源缓存池

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }


    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    /**
     * 在这里需要使用Okio中的Source类。在这里Source可以看做InputStream
     * 这里将进度信息分发出去
     * @param source
     * @return
     */
    private Source source(Source source)
    {
        return new ForwardingSource(source)
        {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException
            {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                progressListener.onProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}
