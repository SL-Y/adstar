package com.cloudTop.starshare.networkapi.socketapi;

import com.cloudTop.starshare.been.LoginReturnInfo;
import com.cloudTop.starshare.been.RegisterVerifyCodeBeen;
import com.cloudTop.starshare.listener.OnAPIListener;
import com.cloudTop.starshare.utils.ToastUtils;
import com.cloudTop.starshare.app.AppApplication;
import com.cloudTop.starshare.app.SocketAPIConstant;
import com.cloudTop.starshare.been.CheckUpdateInfoEntity;
import com.cloudTop.starshare.been.RegisterReturnBeen;
import com.cloudTop.starshare.been.RegisterReturnWangYiBeen;
import com.cloudTop.starshare.been.WXinLoginReturnBeen;
import com.cloudTop.starshare.networkapi.UserAPI;
import com.cloudTop.starshare.networkapi.socketapi.SocketReqeust.SocketAPINettyBootstrap;
import com.cloudTop.starshare.networkapi.socketapi.SocketReqeust.SocketDataPacket;
import com.cloudTop.starshare.utils.LogUtils;
import com.cloudTop.starshare.utils.SharePrefUtil;

import java.util.HashMap;

/**
 * Created by yaowang on 2017/2/20.
 */

public class SocketUserAPI extends SocketBaseAPI implements UserAPI {
    private void isNetBreak() {
        if (!SocketAPINettyBootstrap.getInstance().isOpen()) {
            ToastUtils.showShort("网络连接失败,请检查网络");
        }
    }

    @Override
    public void login(String phone, String password, OnAPIListener<LoginReturnInfo> listener) {
        isNetBreak();
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", password);
        map.put("deviceId", AppApplication.getAndroidId());
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Login,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, LoginReturnInfo.class, listener);
    }

    @Override
    public void registerWangYi(int user_type,String phone, String name_value, long uid, OnAPIListener<RegisterReturnWangYiBeen> listener) {
        isNetBreak();
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("uid", uid);
        map.put("user_type", user_type);
        map.put("name_value", name_value);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.WangYi,
                SocketAPIConstant.ReqeutType.Wangyi, map);
        requestEntity(socketDataPacket, RegisterReturnWangYiBeen.class, listener);
    }

    @Override
    public void wxLogin(String openid, OnAPIListener<WXinLoginReturnBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("openid", openid);
        map.put("deviceId", AppApplication.getAndroidId());
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.WXLogin,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, WXinLoginReturnBeen.class, listener);
    }

    @Override
    public void register(String phone, String password, String memberId, String agentId,String channel, String recommend,String sub_agentId, OnAPIListener<RegisterReturnBeen> listener) {
        isNetBreak();
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", password);
        map.put("memberId", memberId);
        map.put("agentId", agentId);
        map.put("recommend", recommend);
        map.put("channel", channel);
        map.put("sub_agentId", sub_agentId);
        map.put("timeStamp", System.currentTimeMillis());
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Register,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, RegisterReturnBeen.class, listener);
    }

    @Override
    public void verifyCode(String phone, OnAPIListener<RegisterVerifyCodeBeen> listener) {
        isNetBreak();
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.VerifyCode,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, RegisterVerifyCodeBeen.class, listener);
    }

    @Override
    public void resetPasswd(String phone, String pwd, OnAPIListener<Object> listener) {
        isNetBreak();
        LogUtils.logd("修改用户密码-----------");
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("pwd", pwd);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.ResetPasswd,
                SocketAPIConstant.ReqeutType.User, map);
        requestJsonObject(socketDataPacket, listener);
    }

    @Override
    public void bindNumber(String phone, String openid, String password, long timeStamp, String vToken, String vCode, String memberId, String agentId,String channel, String recommend,String sub_agentId, String nickname, String headerUrl, OnAPIListener<RegisterReturnBeen> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("openid", openid);
        map.put("pwd", password);
        map.put("vCode", vCode);
        map.put("nickname", nickname);
        map.put("headerUrl", headerUrl);
        map.put("timeStamp", timeStamp);
        map.put("vToken", vToken);
        map.put("memberId", memberId);
        map.put("agentId", agentId);
        map.put("sub_agentId", sub_agentId);
        map.put("recommend", recommend);
        map.put("channel", channel);
        map.put("deviceId", AppApplication.getAndroidId());
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.WXBind,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, RegisterReturnBeen.class, listener);
    }

    @Override
    public void loginWithToken(long token_time ,OnAPIListener<LoginReturnInfo> listener) {
        LogUtils.loge("用token登录");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", SharePrefUtil.getInstance().getUserId());
        map.put("token", SharePrefUtil.getInstance().getToken());
        map.put("token_time", token_time);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Token,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, LoginReturnInfo.class, listener);
    }

    @Override
    public void isRegisted(String phone, OnAPIListener<RegisterReturnBeen> listener) {
        LogUtils.loge("判断是否注册过");
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.IsRegister,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, RegisterReturnBeen.class, listener);
    }

    @Override
    public void starCount( OnAPIListener<RegisterReturnBeen> listener) {
        LogUtils.loge("持有明星数---");
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", SharePrefUtil.getInstance().getUserId());
//        map.put("token", SharePrefUtil.getInstance().getToken());
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.StartCount,
                SocketAPIConstant.ReqeutType.NewInfos, map);
        requestEntity(socketDataPacket, RegisterReturnBeen.class, listener);
    }

    @Override
    public void update(OnAPIListener<CheckUpdateInfoEntity> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("ttype", 1);
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Update,
                SocketAPIConstant.ReqeutType.User, map);
        requestEntity(socketDataPacket, CheckUpdateInfoEntity.class, listener);
    }

    @Override
    public void saveDevice(long uid, OnAPIListener<Object> listener) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("device_type", 1);
        map.put("device_id", AppApplication.getAndroidId());
        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.saveDevice,
                SocketAPIConstant.ReqeutType.User, map);
        requestJsonObject(socketDataPacket, listener);
    }
//    @Override
//    public void login(String phone, String password, String deviceId, OnAPIListener<LoginReturnEntity> listener) {
//        LogUtil.d("开始登录");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("phone", phone);
//        map.put("pwd", password);
//        map.put("source", 2);
//        map.put("deviceId", MyApplication.getApplication().getAndroidId());
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Login,
//                SocketAPIConstant.ReqeutType.User, map);
//        requestEntity(socketDataPacket, LoginReturnEntity.class, listener);
//    }
//
//    @Override
//    public void wxLogin(String openId, OnAPIListener<LoginReturnEntity> listener) {
//        LogUtil.d("微信登录");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("openId", openId);
//        map.put("deviceId", MyApplication.getApplication().getAndroidId());
//        map.put("source", 2);
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.WXLogin,
//                SocketAPIConstant.ReqeutType.User, map);
//        requestEntity(socketDataPacket, LoginReturnEntity.class, listener);
//    }
//
//    @Override
//    public void register(String phone, String password, String vCode, long memberId, String agentId, String recommend, OnAPIListener<RegisterReturnEntity> listener) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("phone", phone);
//        map.put("pwd", password);
//        map.put("vCode", vCode);
//        map.put("timeStamp", RegisterVerifyCodeEntry.timeStamp);
//        map.put("vToken", RegisterVerifyCodeEntry.vToken);
//        map.put("memberId", memberId);
//        map.put("agentId", agentId);
//        map.put("recommend", recommend);
//        map.put("deviceId", MyApplication.getApplication().getAndroidId());
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Register,
//                SocketAPIConstant.ReqeutType.User, map);
//        requestEntity(socketDataPacket, RegisterReturnEntity.class, listener);
//    }


//
//    @Override
//    public void verifyCode(String phone, int verifyType, OnAPIListener<VerifyCodeReturnEntry> listener) {
//        LogUtil.d("负责加入网络请求---获取短信验证码--------");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id", NetworkAPIFactoryImpl.getConfig().getUserId());
//        map.put("token", NetworkAPIFactoryImpl.getConfig().getUserToken());
//        map.put("verifyType", verifyType);  //0-注册 1-登录 2-更新服务（暂用 1）
//        map.put("phone", phone);
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.VerifyCode,
//                SocketAPIConstant.ReqeutType.User, map);
//        requestEntity(socketDataPacket, VerifyCodeReturnEntry.class, listener);
//    }
//

//
//    @Override
//    public void test(int testID, OnAPIListener<Object> listener) {
//        LogUtil.d("心跳包-----------");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id", testID);
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Test,
//                SocketAPIConstant.ReqeutType.User, map);
//        requestEntity(socketDataPacket, VerifyCodeReturnEntry.class, listener);
//    }
//
//    @Override
//    public void loginWithToken(OnAPIListener<LoginReturnEntity> listener) {
//        LogUtil.d("用token登录");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id", NetworkAPIFactoryImpl.getConfig().getUserId());
//        map.put("token", NetworkAPIFactoryImpl.getConfig().getUserToken());
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Token,
//                SocketAPIConstant.ReqeutType.User, map);
//        requestEntity(socketDataPacket, LoginReturnEntity.class, listener);
//    }
//
//    @Override
//    public void balance(OnAPIListener<BalanceInfoEntity> listener) {
//        LogUtil.d("请求余额信息");
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id", NetworkAPIFactoryImpl.getConfig().getUserId());
//        map.put("token", NetworkAPIFactoryImpl.getConfig().getUserToken());
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.Balance,
//                SocketAPIConstant.ReqeutType.Verify, map);
//        requestEntity(socketDataPacket, BalanceInfoEntity.class, listener);
//    }
//
//    @Override
//    public void bindNumber(String phone, String openid, String password, String vCode, long memberId, String agentId, String recommend, String nickname, String headerUrl, OnAPIListener<RegisterReturnEntity> listener) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("phone", phone);
//        map.put("openid", openid);
//        map.put("pwd", password);
//        map.put("vCode", vCode);
//        map.put("nickname", nickname);
//        map.put("headerUrl", nickname);
//        map.put("timeStamp", RegisterVerifyCodeEntry.timeStamp);
//        map.put("vToken", RegisterVerifyCodeEntry.vToken);
//        map.put("memberId", memberId);
//        map.put("agentId", agentId);
//        map.put("recommend", recommend);
//        map.put("deviceId", MyApplication.getApplication().getAndroidId());
//        SocketDataPacket socketDataPacket = socketDataPacket(SocketAPIConstant.OperateCode.WXBind,
//                SocketAPIConstant.ReqeutType.User, map);
//        requestEntity(socketDataPacket, RegisterReturnEntity.class, listener);
//    }
}