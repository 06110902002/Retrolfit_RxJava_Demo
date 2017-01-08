package upload.handler;

import android.util.Log;

import java.io.IOException;

import downupload.ProgressBean;
import downupload.ProgressListener;
import downupload.ProgressResponseBody;
import downupload.handler.ProgressHandler;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/12/4.
 */

public class UpLoadprogressHelper {
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

        return builder;
    }

    public static void setProgressHandler(ProgressHandler progressHandler){
        mProgressHandler = progressHandler;
    }
}

