package utils;

import rx.Subscription;

/**
 * 取消retrolfit中的http请求，直接取消它的订阅就行
 * Created by yourdream on 2016/11/22.
 */
public class SubscribeUtils {

    /**
     * 取消订阅
     * @param subscription
     */
    public static void unSubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
