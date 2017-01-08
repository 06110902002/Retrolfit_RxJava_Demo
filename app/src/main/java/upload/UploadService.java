package upload;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/4.
 */

public interface UploadService {

    @POST("upload")
    @Multipart
    Observable<ResponseBody> uploadFileInfo(@QueryMap Map<String, String> options,
                                            @PartMap Map<String, RequestBody> externalFileParameters) ;
}
