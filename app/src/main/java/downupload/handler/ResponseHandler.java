package downupload.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 响应信息句柄，处理信息
 *
 * Created by Administrator on 2016/11/27.
 */
public class ResponseHandler extends Handler
{

    /**
     * 用基类分发进度信息，ResponseHandler共用于下载/上传信息分发
     */
    private ProgressHandler mProgressHandler;

    public ResponseHandler(ProgressHandler mProgressHandler, Looper looper) {
        super(looper);
        this.mProgressHandler = mProgressHandler;
    }

    @Override
    public void handleMessage(Message msg) {
        mProgressHandler.handleMessage(msg);
    }
}