CREATE TABLE `DICT_HOST_INFO`  (
                                   `HOST_IP` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP',
                                   `PORT` int(11) NOT NULL COMMENT '端口',
                                   `USER_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                                   `PASSWD` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                                   `LOG_PATH` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志目录',
                                   `DATAX_KILLSCRIPT_PATH` VARCHAR(100) DEFAULT '/opt/ppt/bin/kill.sh' COMMENT 'kill.sh脚本路径',
                                   `CREATE_TIME` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
                                   `UPDATE_TIME` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                   UNIQUE INDEX `IDX_HOST_INFO_HOST_IP`(`HOST_IP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主机信息表' ROW_FORMAT = Dynamic;