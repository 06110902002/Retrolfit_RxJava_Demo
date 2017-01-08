package downupload;

/**
 * 下载进度监听器
 *
 * Created by Administrator on 2016/11/27.
 */
public interface ProgressListener {

    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
