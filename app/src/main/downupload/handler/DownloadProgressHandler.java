package downupload.handler;


import android.os.Looper;
import android.os.Message;

import downupload.ProgressBean;

/**
 * 下载进度信息句柄，更新信息到ui上
 *
 */
public abstract class DownloadProgressHandler extends ProgressHandler
{

    private static final int DOWNLOAD_PROGRESS = 1; //下载信息的标志

    protected ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    public void sendMessage(ProgressBean progressBean)
    {
        //响应信息句柄，发送信息
        mHandler.obtainMessage(DOWNLOAD_PROGRESS,progressBean).sendToTarget();

    }

    @Override
    public void handleMessage(Message message)
    {
        switch (message.what){
            case DOWNLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean)message.obj;
                onProgress(progressBean.getBytesRead(),progressBean.getContentLength(),progressBean.isDone());
                break;

        }
    }
}
