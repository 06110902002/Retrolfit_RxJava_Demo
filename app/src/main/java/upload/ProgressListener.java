package upload;

/**
 * Created by Administrator on 2016/12/4.
 */

public interface ProgressListener {

    void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish);
}
