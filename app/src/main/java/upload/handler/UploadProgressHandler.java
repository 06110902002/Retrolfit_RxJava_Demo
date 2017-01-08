package upload.handler;

import android.os.Looper;
import android.os.Message;

import downupload.ProgressBean;
import downupload.handler.ProgressHandler;
import downupload.handler.ResponseHandler;

/**
 * upload目录下的handler机制均可以删除，测试用
 *
 * 上传信息句柄,继承抽象类，需要将父类的抽象方法全部实现
 *
 * Created by Administrator on 2016/12/4.
 */
public abstract class UploadProgressHandler extends ProgressHandler
{

    private static final int UPNLOAD_PROGRESS = 1; //下载信息的标志

    protected ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    public void sendMessage(ProgressBean progressBean)
    {
        //响应信息句柄，发送信息
        mHandler.obtainMessage(UPNLOAD_PROGRESS,progressBean).sendToTarget();

    }

    @Override
    public void handleMessage(Message message)
    {
        switch (message.what){
            case UPNLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean)message.obj;
                onProgress(progressBean.getBytesRead(),progressBean.getContentLength(),progressBean.isDone());
                break;

        }
    }
}
