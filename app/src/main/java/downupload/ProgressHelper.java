package downupload;

import android.util.Log;

import java.io.IOException;

import downupload.handler.ProgressHandler;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * 进度工具类
 *
 */
public class ProgressHelper {

    private static ProgressBean progressBean = new ProgressBean();      //进度信息实体
    private static ProgressHandler mProgressHandler;                    //进度信息分发句柄

    /**
     * 添加进度信息
     * @param builder
     * @return
     */
    public static OkHttpClient.Builder addProgress(OkHttpClient.Builder builder)
    {

        if (builder == null)
        {
            builder = new OkHttpClient.Builder();
        }

        final ProgressListener progressListener = new ProgressListener()
        {
            //该方法在子线程中运行
            @Override
            public void onProgress(long progress, long total, boolean done)
            {
                Log.d("progress:", String.format("%d%% done\n",(100 * progress) / total));

                if (mProgressHandler == null)
                {
                    return;
                }

                progressBean.setBytesRead(progress);
                progressBean.setContentLength(total);
                progressBean.setDone(done);
                mProgressHandler.sendMessage(progressBean);

            }
        };

        //添加拦截器，自定义ResponseBody，添加下载进度
        builder.networkInterceptors().add(new Interceptor()
        {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException
            {
                okhttp3.Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(
                        new ProgressResponseBody(originalResponse.body(), progressListener))
                        .build();

            }
        });

        return builder;
    }

    public static void setProgressHandler(ProgressHandler progressHandler){
        mProgressHandler = progressHandler;
    }
}
