package com.ftsom.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.CompositeRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.function.Function;

import static com.ftsom.common.constants.NumConstant.INT_0;

/**
 * 基于「spring retry」框架的重试工具类
 *
 * @author ftsom
 * @date 2022/5/27 11:08
 */
@Slf4j
public class RetryUtil {

    public static <T, R> R execute(int retryTimes,
                                   long timeout,
                                   long backoffTime,
                                   Class<? extends Throwable> retryClazz,
                                   Function<T, R> function,
                                   T t) {
        return execute(retryTimes, timeout, backoffTime, retryClazz, function, t, null);
    }

    public static <T, R> R execute(int retryTimes,
                                   long timeout,
                                   long backoffTime,
                                   Class<? extends Throwable> retryClazz,
                                   Function<T, R> function,
                                   T t,
                                   R defaultResult) {
        CompositeRetryPolicy compositeRetryPolicy = new CompositeRetryPolicy();

        TimeoutRetryPolicy timeoutRetryPolicy = new TimeoutRetryPolicy();
        timeoutRetryPolicy.setTimeout(timeout);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(retryTimes);
        compositeRetryPolicy.setPolicies(new RetryPolicy[]{timeoutRetryPolicy, simpleRetryPolicy});
        RetryTemplate retryTemplate = RetryTemplate.builder()
                .customPolicy(compositeRetryPolicy)
                .retryOn(retryClazz)
                .fixedBackoff(backoffTime)
                .build();
        try {
            return retryTemplate.execute(getRetryCallbackInstance(function, t));
        } catch (Throwable throwable) {
            log.error("RetryInstanceFactory -> execute [{}] error", function, throwable);
            return defaultResult;
        }
    }

    private static <T, R> RetryCallback<R, Throwable> getRetryCallbackInstance(Function<T, R> function, T t) {
        return new RetryCallback<R, Throwable>() {
            int count = INT_0;

            @Override
            public R doWithRetry(RetryContext retryContext) {
                if (count > INT_0) {
                    log.info("{} retry the {} time begin", function.toString(), count);
                }
                count++;
                return function.apply(t);
            }
        };
    }
}
