create database xiaodianbao_db;
use xiaodianbao_db;

CREATE TABLE `district` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) DEFAULT NULL COMMENT '行政区域名称',
  `short_name` varchar(10) DEFAULT NULL COMMENT '简称',
  `parent_id` int(10) unsigned DEFAULT NULL COMMENT '父级地区ID',
  `city_level` enum('A','B','C','D','E','F') DEFAULT NULL COMMENT '城市级别。与一线二线类似，只有地级城市才有此字段',
  `zone_num` varchar(4) DEFAULT NULL COMMENT '城市区号。只有地级城市才有此字段',
  `post_code` char(6) DEFAULT NULL COMMENT '邮政编码',
  `xz_code` char(6) DEFAULT NULL COMMENT '地区行政编码',
  `pinyin` varchar(20) DEFAULT NULL COMMENT '地区名称大写首字母拼音，可按此排序显示',
  `quanpin` varchar(200) DEFAULT NULL COMMENT '地区名称全拼',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间。平台推送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3481 DEFAULT CHARSET=utf8mb4 COMMENT='全国地区及其行政编码信息';

drop table if exists industry;
create table industry(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '行业ID，自增长',
  `name` varchar(64) DEFAULT NULL COMMENT '行业名称',
  `parent_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '父行业ID',
  `status` enum('NORMAL', 'CLOSED') COMMENT '数据状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24914 DEFAULT CHARSET=utf8 COMMENT='行业信息表';

drop table if exists wx_user;
create table wx_user(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID，自增长',
  `nick_name` varchar(64)  character set utf8mb4 DEFAULT NULL COMMENT '用户昵称',
  `sex` enum('U', 'M', 'F')  default 'U' comment '性别。未知、男、女',
  `head_img` varchar(200) comment '头像',
  `open_id` varchar(128) NOT NULL COMMENT '微信openID',
  `union_id` varchar(128) COMMENT '微信unionID',
  `shop_mgr` enum('N', 'Y') default 'N' comment '是否是店长。默认不是，店长资料认证通过之后，才被认为是店长',
  `phone` varchar(15) comment '手机号',
  `poster_id` int(10) unsigned comment '推荐人的id',
  `status` enum('NORMAL', 'FREEZED', 'DELETED') COMMENT '数据状态。正常、已冻结、已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='微信用户信息表';

create unique index openid_ind on wx_user(open_id);

create table wx_user_extra(
  `wx_uid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'wx_user表的id',
  `country` varchar(64) comment '国家',
  `province` varchar(128) comment '所在省份',
  `city` varchar(128) comment '所在城市',
  `county_id` int(10) unsigned comment '所属城市下属行政区域id。',
  `detail_addr` varchar(200) comment '详细街道门牌号地址',
  `lng` double(10,7) comment '经度',
  `lat` double(10,7) comment '纬度',
  `serv_msg` enum('Y', 'N') not null default 'Y' comment '是否订阅服务号消息',
  `sms_msg` enum('Y', 'N') not null default 'Y' comment '是否订短信消息',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`wx_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='微信用户信息扩展表';

drop table if exists shop;
create table shop(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '小店ID，自增长',
  `full_name` varchar(64) DEFAULT NULL COMMENT '小店全名',
  `alias` varchar(64) DEFAULT NULL COMMENT '小店简称',
  `logo` varchar(64) DEFAULT NULL COMMENT '小店logo图片地址',
  `ind_id` int(10) unsigned comment '所属一级行业ID',
  `sub_ind_id` int(10) unsigned comment '所属二级行业ID',
  `mgr_name` varchar(64) DEFAULT NULL COMMENT '店长姓名',
  `kefu` varchar(16) comment '客服电话',
  `licence` varchar(200) comment '营业执照图片地址。',
  `def_bg_img` varchar(200) comment '优惠券默认背景图片地址。',
  `province_id` int(10) unsigned comment '所属省份id。',
  `city_id` int(10) unsigned comment '所属地级城市id。',
  `county_id` int(10) unsigned comment '所属城市下属行政区域id。',
  `detail_addr` varchar(200) comment '详细街道门牌号地址',
  `lng` double(10,7) comment '小店位置所在经度',
  `lat` double(10,7) comment '小店位置所在纬度',
  `wx_uid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '小店所属微信用户id',
  `snapshot` varchar(2000) comment '快照。在用户申请修改',
  `check_state` enum('DELETED', 'CLOSED', 'REJECT', 'CHECKING', 'PASSED') COMMENT '数据状态。已关闭、已拒绝、待审核、已审核通过',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='小店信息表';

drop table if exists coupon_tmpl;
create table coupon_tmpl(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '小店ID，自增长',
  `coupon_type` enum('Discount', 'Cash') comment '券类型。折扣券、现金券',
  `tmpl_file_name` varchar(32) not null comment '券对应的样式模板文件名',
  `discount_type` enum('Amount', 'Discount') comment '折扣类型。面额、折扣',
  `discount` double(8, 2) comment '折扣值。如果discount_type为Amount,此处为面额；如果discount_type为Discount，则此处为折扣值（比如7折，此处存储为70.00）',
  `min_amount` double(11, 2) default 100 comment '使用该券，要求的最小消费金额。默认为100.00元',
  `day_limit_num` int default 1 comment '每天限领张数。默认为1张',
  `person_limit_num` int default 1 comment '每人最多可持有张数。默认为1张',
  `effect_day` int default 30 comment '优惠券领取后多少天内有效。默认为30天',
  `online_pay` enum('Y', 'N') default 'Y' comment '是否支持在线支付。默认为Y，即支持',
  `effect_after_recv_day` int default 2 comment '优惠券领取后第几天可使用。默认为第2天',
  `can_use_time` varchar(1000) comment '可使用日期时间段，可以设置多个不连续的日期时间段。用json保存，格式为[{startTime:"yyyy-MM-dd HH:mm:ss", endTime:"yyyy-MM-dd HH:mm:ss"}, {startTime:"HH:mm:ss", endTime:"HH:mm:ss"}, {startTime:"yyyy-MM-dd", endTime:"yyyy-MM-dd"}]',
  `remark` varchar(2000) comment '其它特殊说明',
  `status` enum( 'NORMAL', 'CLOSED', 'DELETED') COMMENT '数据状态。正常、停用、已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='优惠券模板表';

drop table if exists shop_coupon;
create table shop_coupon(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '小店ID，自增长',
  `wx_uid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '优惠券购买人的微信用户id。一个用户下的多家店铺共享小店优惠券数量',
  `shop_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '优惠券所属店铺ID。如果为0，则代表该券可以在wx_uid用户下的所有小店共享，否则只能在该指定的小店有效。',
  `coupon_type` enum('Discount', 'Cash') comment '券类型。折扣券、现金券',
  `tmpl_file_name` varchar(32) not null comment '券对应的样式模板文件名',
  `discount_type` enum('Amount', 'Discount') comment '折扣类型。面额、折扣',
  `discount` double(8, 2) comment '折扣值。如果discount_type为Amount,此处为面额；如果discount_type为Discount，则此处为折扣值（比如7折，此处存储为70.00）',
  `min_amount` double(11, 2) default 100 comment '使用该券，要求的最小消费金额。默认为100.00元',
  `day_limit_num` int default 1 comment '每天限领张数。默认为1张',
  `person_limit_num` int default 1 comment '每人最多可持有张数。默认为1张',
  `effect_day` int default 30 comment '优惠券领取后多少天内有效。默认为30天',
  `online_pay` enum('Y', 'N') default 'Y' comment '是否支持在线支付。默认为Y，即支持',
  `effect_after_recv_day` int default 2 comment '优惠券领取后第几天可使用。默认为第2天',
  `can_use_time` varchar(1000) comment '可使用日期时间段，可以设置多个不连续的日期时间段。用json保存，格式为[{startTime:"yyyy-MM-dd HH:mm:ss", endTime:"yyyy-MM-dd HH:mm:ss"}, {startTime:"HH:mm:ss", endTime:"HH:mm:ss"}, {startTime:"yyyy-MM-dd", endTime:"yyyy-MM-dd"}]',
  `remark` varchar(2000) comment '其它特殊说明',
  `status` enum( 'NORMAL', 'CLOSED', 'DELETED') COMMENT '数据状态。正常、停用、已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='小店设置的优惠券信息';

drop table if exists user_coupon_num;
create table user_coupon_num(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '小店ID，自增长',
  `wx_uid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '优惠券所属用户ID。',
  `free_left_num` int default 1000 comment '免费优惠券剩余数量。每月1日凌晨0点将此值重置为1000',
  `buy_left_num` int default 0 comment '用户购买的优惠券/立减券剩余数量。默认为0',
  `buy_total_num` int default 0 comment '用户购买的优惠券/立减券总数，只增不减。默认为0',
  `coupon_type` enum('Discount', 'Cash') comment '券类型。折扣券、现金券',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='用户当前可用的优惠券数量';

drop table if exists shop_order;
create table shop_order(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID，自增长',
  `shop_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '优惠券所属店铺ID。如果为0，则代表该券可以在wx_uid用户下的所有小店共享，否则只能在该指定的小店有效。',
  `buy_num` int default 0 comment '用户购买的优惠券/立减券剩余数量。默认为0',
  `send_num` int default 0 comment '本次购买，系统根据购买数量的多少自动赠送的券数量。默认为0',
  `pay_fee` double(11, 2) default 0.00 comment '本次购买需要支付的总金额。默认为0.00',
  `pay_method` set('BALANCE','WXPAY','ALIPAY','UNIONPAY') not null comment '支付方式。余额支付、微信支付、支付宝支付、银联支付。可以组合支付，但只能有余额+另外一种第三方支付方式',
  `balance_pay_fee` double(11, 2) default 0.00 comment '本次购买使用余额支付的金额。默认为0.00',
  `third_pay_fee` double(11, 2) default 0.00 comment '本次购买使用第三方支付的金额。默认为0.00',
  `third_trade_id` varchar(128) comment '如果有第三方支付，则需要有第三方支付的订单号。默认规则为对应的第三方 pay_method + 本记录id +（如果需要再次发起支付请求，则加上 -01 、-02之类的后缀）',
  `third_payment_no` varchar(64) comment '充值成功时第三方支付服务端返回的订单号',
  `coupon_type` enum('Discount', 'Cash') comment '券类型。折扣券、现金券',
  `ord_state` enum('CANCEL', 'INVALID', 'PAYING', 'PAYED') not null default 'PAYING' comment '订单状态。已取消、已失效（过期失效，默认为24小时后失效）、待支付、已支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='小店优惠券的购买记录';


drop table if exists wallet;
create table wallet(
  `wx_uid` int(10) unsigned NOT NULL COMMENT 'wx_user表的id',
  `balance` double(11, 2) default 0.00 comment '用户余额。默认为0.00',
  `total_charge_amount` double(11, 2) default 0.00 comment '用户总充值金额。默认为0.00',
  `status` enum('NORMAL', 'FREEZED', 'DELETED') COMMENT '数据状态。正常、已冻结、已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`wx_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='用户钱包';


drop table if exists cash_log;
create table cash_log(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID，自增长',
  `wx_uid` int(10) unsigned NOT NULL COMMENT 'wx_user表的id',
  `cash_amount` double(11, 2) not null comment '用户申请提现的金额。',
  `pre_amount` double(11, 2) not null default 0.00 comment '申请提现之前账户的可用余额',
  `after_amount` double(11, 2) not null default 0.00 comment '申请提现之后账户的可用余额',
  `last_cash_left_amount` double(11, 2) not null default 0.00 comment '上次申请提现之后账户的可用余额',
  `third_trade_id` varchar(64) comment '使用微信给用户打款时的订单号，只有提现记录审核通过后才会生成。生成规则为："cash" + 本记录id + （如果需要更换订单号重试，则加上 -01 、-02之类的后缀）',
  `third_payment_no` varchar(64) comment '使用微信给用户打款成功时的返回的订单号',
  `third_result_code` varchar(32) comment '使用微信给用户账户打款时，微信服务器返回的业务结果码result_code。如果为SYSTEMERROR时，需要用同一个订单号重试，其它时候需要更换订单号',
  `snapshot` varchar(2000) comment '支付成功之后，第三方支付平台返回的所有数据信息，方便后期查阅',
  `check_state` enum('REJECT', 'CHECKING', 'PASSED') not null default 'CHECKING' COMMENT '数据状态。审核不通过、待审核、审核通过、转账成功、转账失败',
  `cash_state` enum('NOSEND', 'FAILED', 'RECEIVED') not null default 'NOSEND' COMMENT '提现状态。无需发送、转账失败、转账成功',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='用户提现记录';


drop table if exists charge_log;
create table charge_log (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID，自增长',
  `wx_uid` int(10) unsigned NOT NULL COMMENT 'wx_user表的id',
  `charge_amount` double(11, 2) not null comment '用户充值金额。',
  `pre_amount` double(11, 2) not null default 0.00 comment '本次充值之前账户的可用余额',
  `pay_method` enum('WXPAY','ALIPAY','UNIONPAY') not null comment '支付方式。微信支付、支付宝支付、银联支付。',
  `third_trade_id` varchar(64) comment '生成商户的支付订单号。生成规则为："charge" + 本记录id + （如果需要再次发起充值请求，则加上 -01 、-02之类的后缀）',
  `third_payment_no` varchar(64) comment '充值成功时微信服务端返回的订单号',
  `snapshot` varchar(2000) comment '支付成功之后，第三方支付平台返回的所有数据信息，方便后期查阅',
  `ord_state` enum('CANCEL', 'INVALID', 'PAYING', 'PAYED') not null default 'PAYING' comment '订单状态。已取消、已失效（过期失效，默认为24小时后失效）、待支付、已支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='用户充值记录';


drop table if exists suggestion;
create table suggestion (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID，自增长',
  `wx_uid` int(10) unsigned NOT NULL COMMENT 'wx_user表的id',
  `phone` varchar(15) comment '手机号',
  `content` varchar(2000) not null comment '建议内容',
  `handled` enum('Y', 'N') not null default 'N' comment '对用户的建议是否已处理。已处理、待处理',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='用户反馈的建议';


drop table if exists user_coupon;
create table user_coupon(
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID，自增长',
  `wx_uid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '券领取人的微信用户id。',
  `shop_id` int(10) unsigned NOT NULL COMMENT '券所属小店ID。必须为一个有效的店铺ID，否则将无法使用',
  `src_code` varchar(64) default 'SHOP_SCAN' comment '优惠券来源的CODE。默认为 SHOP_SCAN(店铺扫码), SHEET_SCAN(传单扫码)',
  `coupon_type` enum('Discount', 'Cash') comment '券类型。折扣券、现金券',
  `tmpl_file_name` varchar(32) not null comment '券对应的样式模板文件名',
  `discount_type` enum('Amount', 'Discount') comment '折扣类型。面额、折扣',
  `discount` double(8, 2) comment '折扣值。如果discount_type为Amount,此处为面额；如果discount_type为Discount，则此处为折扣值（比如7折，此处存储为70.00）',
  `min_amount` double(11, 2) default 100 comment '使用该券，要求的最小消费金额。默认为100.00元',
  `online_pay` enum('Y', 'N') default 'Y' comment '是否支持在线支付。默认为Y，即支持',
  `uneffect_time` datetime comment '券的失效时间',
  `effect_time` datetime comment '券的生效时间',
  `can_use_time` varchar(1000) comment '可使用日期时间段，可以设置多个不连续的日期时间段。用json保存，格式为[{startTime:"yyyy-MM-dd HH:mm:ss", endTime:"yyyy-MM-dd HH:mm:ss"}, {startTime:"HH:mm:ss", endTime:"HH:mm:ss"}, {startTime:"yyyy-MM-dd", endTime:"yyyy-MM-dd"}]',
  `remark` varchar(2000) comment '其它特殊说明',
  `coupon_state` enum('DELETED', 'EXPIRED', 'RECVD', 'USED') COMMENT '数据状态。已删除、已过期、已领取、已使用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='用户领取的小店优惠券';


drop table if exists custom_order;
create table custom_order (
  `user_coupon_id` int(10) unsigned NOT NULL COMMENT 'user_coupon表的ID',
  `wx_uid` int(10) unsigned NOT NULL COMMENT 'wx_user表的id',
  `ord_fee` double(11, 2) comment '本次消费的订单总金额。',
  `can_use_fee` double(11, 2) comment '本次消费符合本券使用范围的商品金额。',
  `left_pay_fee` double(11, 2) comment '本次消费剩余需要支付的金额。ord_fee = left_pay_fee + 折扣金额或面额',
  `balance_pay_fee` double(11, 2) default 0.00 comment '本次购买使用余额支付的金额。默认为0.00',
  `third_pay_fee` double(11, 2) default 0.00 comment '本次购买使用第三方支付的金额。默认为0.00',
  `pay_method` set('OFFLINE','BALANCE','WXPAY','ALIPAY','UNIONPAY') not null comment '支付方式。线下支付、余额支付、微信支付、支付宝支付、银联支付。可以组合支付，但只能有余额+另外一种第三方支付方式',
  `third_trade_id` varchar(64) comment '生成商户的支付订单号。生成规则为：pay_method + "-pay" + 本记录user_coupon_id + （如果需要再次发起支付请求，则加上 -01 、-02之类的后缀）',
  `third_payment_no` varchar(64) comment '充值成功时微信服务端返回的订单号',
  `ord_state` enum('CANCEL', 'INVALID', 'PAYING', 'PAYED') not null default 'PAYING' comment '订单状态。已取消、已失效（过期失效，默认为24小时后失效）、待支付、已支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `last_uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次更新时间',
  PRIMARY KEY (`user_coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='如果用户选择在线支付，则会产生一条在线支付的关联记录';


drop table if exists data_day_shop;
create table data_day_shop (
  `id` int(10) unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID，自增长',
  `data_day` date not null comment '数据所属日期',
  `shop_id` int(10) unsigned comment '店铺id',
  `coupon_type` enum('Discount', 'Cash') comment '券类型。折扣券、现金券',
  `recved_num` int not null default 0 comment '领取数',
  `used_num` int not null default 0 comment '使用数',
  `new_user_num` int not null default 0 comment '当天进店消费的新用户数',
  `old_user_num` int not null default 0 comment '当天进店消费的老用户数',
  `order_fee` double(11, 2) not null default 0 comment '订单金额，单位(元)',
  `coupon_fee` double(11, 2) not null default 0 comment '优惠券抵用金额，单位(元)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间'
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='以店铺为维度，每天券的发放与使用情况';


drop table if exists data_month_shop;
create table data_month_shop (
  `id` int(10) unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID，自增长',
  `data_day` date not null comment '数据所属月份的第一天的日期',
  `shop_id` int(10) unsigned comment '店铺id',
  `coupon_type` enum('Discount', 'Cash') comment '券类型。折扣券、现金券',
  `recved_num` int not null default 0 comment '领取数',
  `used_num` int not null default 0 comment '使用数',
  `new_user_num` int not null default 0 comment '当天进店消费的新用户数',
  `old_user_num` int not null default 0 comment '当天进店消费的老用户数',
  `order_fee` double(11, 2) not null default 0 comment '订单金额，单位(元)',
  `coupon_fee` double(11, 2) not null default 0 comment '优惠券抵用金额，单位(元)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间'
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='以店铺为维度，每月券的发放与使用情况';




