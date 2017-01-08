package downupload.handler;

import android.os.Message;

import downupload.ProgressBean;

/**
 * 进度信息分发器基类，即把进度信息打到ui上
 *
 * Created by ljd on 4/12/16.
 */
public abstract class ProgressHandler {

    /**
     * 抽象类，发送进度信息方法
     * 子类重写时需要传一个进度信息实体给主UI
     * @param progressBean
     */
    public abstract void sendMessage(ProgressBean progressBean);

    /**
     * 处理信息
     * @param message
     */
    public abstract void handleMessage(Message message);

    /**
     *
     * @param progress
     * @param total
     * @param done
     */
    public abstract void onProgress(long progress, long total, boolean done);



    
   /* *//**
     * 响应信息处理句柄
     *//*
    protected static class ResponseHandler extends Handler{

        private ProgressHandler mProgressHandler;
        public ResponseHandler(ProgressHandler mProgressHandler, Looper looper) {
            super(looper);
            this.mProgressHandler = mProgressHandler;
        }

        @Override
        public void handleMessage(Message msg) {
            mProgressHandler.handleMessage(msg);
        }
    }*/

}
