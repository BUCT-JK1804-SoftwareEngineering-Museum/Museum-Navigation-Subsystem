package com.example.maptest.util.net;

/**
 * @ClassName Api
 * @Description 存储接口地址
 * @Author kaiguo
 * @Date 2020/3/17 11:01
 */
public interface Api {


  String register = "register";
  String changePassword = "changePassword";
  String appLogin = "appLogin";
  String selectByName = "museum/selectByName";
  String selectAll = "museum/selectAll";
  String selectByMusId = "video/selectByMusId";
  String uploadVideo = "video/uploadVideo";
  String selectByUserId = "video/selectByUserId";
  String collection = "collection/selectByMusId";//藏品
  String exhibition = "exhibition/selectByMusId";//展品

}
