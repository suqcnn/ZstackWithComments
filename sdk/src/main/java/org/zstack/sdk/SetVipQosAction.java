package org.zstack.sdk;

import java.util.HashMap;
import java.util.Map;

public class SetVipQosAction extends AbstractAction {

    private static final HashMap<String, Parameter> parameterMap = new HashMap<>();

    public static class Result {
        public ErrorCode error;
        public SetVipQosResult value;

        public Result throwExceptionIfError() {
            if (error != null) {
                throw new ApiException(
                    String.format("error[code: %s, description: %s, details: %s]", error.code, error.description, error.details)
                );
            }
            
            return this;
        }
    }

    @Param(required = true, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
    public java.lang.String uuid;

    @Param(required = false, nonempty = false, nullElements = false, emptyString = true, numberRange = {8192L,34359738367L}, noTrim = false)
    public java.lang.Long outboundBandwidth;

    @Param(required = false, nonempty = false, nullElements = false, emptyString = true, numberRange = {8192L,34359738367L}, noTrim = false)
    public java.lang.Long inboundBandwidth;

    @Param(required = false)
    public java.util.List systemTags;

    @Param(required = false)
    public java.util.List userTags;

    @Param(required = true)
    public String sessionId;

    public long timeout;
    
    public long pollingInterval;


    private Result makeResult(ApiResult res) {
        Result ret = new Result();
        if (res.error != null) {
            ret.error = res.error;
            return ret;
        }
        
        SetVipQosResult value = res.getResult(SetVipQosResult.class);
        ret.value = value == null ? new SetVipQosResult() : value; 

        return ret;
    }

    public Result call() {
        ApiResult res = ZSClient.call(this);
        return makeResult(res);
    }

    public void call(final Completion<Result> completion) {
        ZSClient.call(this, new InternalCompletion() {
            @Override
            public void complete(ApiResult res) {
                completion.complete(makeResult(res));
            }
        });
    }

    Map<String, Parameter> getParameterMap() {
        return parameterMap;
    }

    RestInfo getRestInfo() {
        RestInfo info = new RestInfo();
        info.httpMethod = "PUT";
        info.path = "/vips/{uuid}/actions";
        info.needSession = true;
        info.needPoll = true;
        info.parameterName = "setVipQos";
        return info;
    }

}
