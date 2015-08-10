
package com.statt.util;

/**
 * Address of service URL
 * Date: 2015/08/07
 * @author ymx
 */

public class Address {
    private static final String SERVICE = "http://192.168.1.104:8088";
    private static final String SIGN = "?sign=";
    // 首次打开APP自动注册
    private static final String REGISTER = SERVICE + "/Vaccine/api/userControllerApi/register" + SIGN;
    // 添加宝宝信息
    private static final String ADD_BABY = SERVICE + "/Vaccine/api/BabyControllerApi/addBaby" + SIGN;
    // 更新用户信息
    private static final String UPDATE_USER = SERVICE + "/Vaccine/api/userControllerApi/updateUser" + SIGN;
    // 用户注册 - 点击获得验证码
    private static final String SECURI_DN_CODE = SERVICE + "/Vaccine/api/userControllerApi/securi_dncode" + SIGN;
    // 用户注册-验证短信验证码是否通过
    private static final String SECURI_VALIDATE_CODE = SERVICE + "/Vaccine/api/userControllerApi/securi_validateCode" + SIGN;
    // 用户注册-验证通过，绑定手机号码
    private static final String BIND_PHONE_NUM = SERVICE + "/Vaccine/api/userControllerApi/bindPhoneNum" + SIGN;
    // 打开App首页，加载信息
    private static final String ON_CREATE = SERVICE + "/Vaccine/api/homeControllerApi/onCreate" + SIGN;
    // 切换宝宝
    private static final String CHANGE_BABY = SERVICE + "/Vaccine/api/homeControllerApi/changeBaby" + SIGN;
    // 上传照片
    private static final String UPLOAD_PHOTO = SERVICE + "/Vaccine/api/photoControllerApi/uploadPhoto" + SIGN;
    // 加载所有相片路径
    private static final String LOAD_PHOTOS = SERVICE + "/Vaccine/api/photoControllerApi/loadPhotos" + SIGN;
    // 删除照片
    private static final String DEL_PHOTO = SERVICE + "/Vaccine/api/photoControllerApi/delPhoto" + SIGN;
    // 删除宝宝
    private static final String DEL_BABY = SERVICE + "/Vaccine/api/BabyControllerApi/delBaby" + SIGN;
    // 修改宝宝
    private static final String UPDATE_BABY = SERVICE + "/Vaccine/api/BabyControllerApi/updateBaby" + SIGN;
    // 查询宝宝集合
    private static final String SELECTE_BABIES = SERVICE + "/Vaccine/api/BabyControllerApi/selectBabies" + SIGN;
    // 预览一个宝宝的信息
    private static final String GET_BABY = SERVICE + "/Vaccine/api/BabyControllerApi/getBaby" + SIGN;
    // 查询全部预约信息
    private static final String GET_APPOINT_INFO = SERVICE + "/Vaccine/api/orderControllerApi/selectOrdersByBabyId" + SIGN;
    // 添加预约信息
    private static final String ADD_APPOINT_INFO = SERVICE + "/Vaccine/api/orderControllerApi/addOrder" + SIGN;
    //修改 & 删除 预约信息
    private static final String UPDATE_APPOINT_INFO = SERVICE + "/Vaccine/api/orderControllerApi/updateOrder" + SIGN;
    // 获得置顶帖子
    private static final String GET_TOP_POST = SERVICE + "/Vaccine/api/topicControllerApi/getTop" + SIGN;
    // 获得一页的帖子
    private static final String GET_ONE_PAGE_POST = SERVICE + "/Vaccine/api/topicControllerApi/selectAllTopic" + SIGN;
    // 发布新帖:只有文字内容
    private static final String NEW_POST_O_IMAGE = SERVICE + "/Vaccine/api/topicControllerApi/addToicOne" + SIGN;
    // 发布新帖:上传图片,路径 BBS/帖子Id/转码后图片名称.jpg
    private static final String NEW_POST_X_IMAGE = SERVICE + "/Vaccine/api/topicControllerApi/addToicTwo" + SIGN;
    // 删除帖子
    private static final String DEL_POST = SERVICE + "/Vaccine/api/topicControllerApi/delTopic" + SIGN;
    // 查询我的帖子列表
    private static final String GET_MY_POST = SERVICE + "/Vaccine/api/topicControllerApi/selectMyTopic" + SIGN;
    // 查询帖子，包括回复，获得帖子详情
    private static final String GET_POST_DETAIL = SERVICE + "/Vaccine/api/topicControllerApi/viewTopic" + SIGN;
    // 回复帖子
    private static final String REPLY_POST = SERVICE + "/Vaccine/api/replyControllerApi/addReply" + SIGN;
    // 删除回复
    private static final String DEL_REPLY = SERVICE + "/Vaccine/api/replyControllerApi/delReply" + SIGN;
}
