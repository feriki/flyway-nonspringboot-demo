use dts_test_source;
/*增大DICT_CACHE_DEF的TOTAL_PARAMS和INCREMENTAL_PARAMS字段长度,并更新CAMERA的增量sql*/
alter table DICT_CACHE_DEF modify TOTAL_PARAMS varchar(2048) ;
alter table DICT_CACHE_DEF modify INCREMENTAL_PARAMS varchar(2048) ;

UPDATE `DICT_CACHE_DEF` SET `INCREMENTAL_PARAMS` = '{\"sql\":\"SELECT t.*, b.SCENE_ID FROM ( SELECT pd_res.RM_VIDEODEV_INFO.VIDEODEV_GB_ID AS DEVICE_ID, pd_res.RM_VIDEODEV_INFO.ORIGINAL_DEVICE_ID AS ORIGINAL_DEVICE_ID, pd_res.RM_VIDEODEV_INFO.DEVICE_NAME AS DEVICE_NAME, pd_res.RM_VIDEODEV_INFO.ADMINAREA_GB_CODE AS ORG_CODE, pd_res.RM_VIDEODEV_INFO.DEVICE_VENDOR_DICT AS VENDOR , pd_res.RM_VIDEODEV_INFO.INSTALL_ADDR AS CAPTURE_ADDRESS, pd_res.RM_VIDEODEV_INFO.LONGTIUDE AS LONGITUDE, pd_res.RM_VIDEODEV_INFO.LATIUDE AS LATITUDE FROM pd_res.RM_VIDEODEV_INFO WHERE pd_res.RM_VIDEODEV_INFO.DEVICE_SPEICALTYPE_DICT = 2 AND pd_res.RM_VIDEODEV_INFO.UPDATE_TIME >= @updateTime UNION ALL SELECT a.DEVICE_ID, a.SOURCE_DEVICEID AS ORIGINAL_DEVICE_ID, a.DEVICE_NAME, INSTALL_ADDR AS DEVICE_ADDR, a.ORG_CODE , LONGITUDE, LATITUDE, a.MANUFACTURER AS MANUFACTURER FROM pd_res.RS_ACCESS_DOOR a WHERE a.VIDEO_FUNCTION = 1 AND a.UPDATE_TIME >= @updateTime ) t LEFT JOIN pd_vplusplat.VPLUS_SCENE_DEVICE b ON t.DEVICE_ID = b.DEVICE_ID\"}', `REFRESH_INTERVAL` = 900, `DATABASENAME` = 'pd_res' WHERE `CACHE_NAME` = 'CAMERA';