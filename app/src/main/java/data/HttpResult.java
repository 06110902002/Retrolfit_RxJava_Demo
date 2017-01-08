package data;

/**
 * 对于服务器返回来的数据如果 格式上一致，可以对数据封装成泛型类
 * 信息格式类似这种：
 * {
     "resultCode": 0,
     "resultMessage": "成功",
     "data": {}
     }
 * Created by yourdream on 2016/11/21.
 */

public class HttpResult<T> {

    private int resultCode;         //前面2个字段固定

    private String resultMessage;

    private T data;                 //泛型格式一致的数据

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }


}
