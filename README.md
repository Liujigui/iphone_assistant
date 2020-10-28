# iPhone12系列库存监控（数据库版）

#### 介绍
苹果最新发布的iPhone12Pro系列，由于产量较小，目前市场火热。
线上购买等待时间较长，奈何我等实在不愿意等，能线下预约购买最好，谁知线下库存依旧见底，少有部分，需要时刻守着电脑刷新，让人枯燥！

#### 软件架构
软件架构说明
java 1.8
maven 3.6.3
mysql 5.7

#### 使用说明

1.  导入db文件夹下数据库文件
2.  修改db.setting文件中数据库名和账户密码
3.  运行系统，自动监控
4.  新增监控通知和门店、型号接口：http://localhost:8080/addUser 
    - 参数说明：{"model": 21,"email": "zhangsan@163.com","city": "广州"}
    - 参数model为数据库中型号对应的ID
5.  如果需要微信消息通知，自行百度：servlet酱，添加对应的sukey，进行微信消息通知
6.  邮箱通知，需要修改application.yml中配置。


#### 效果
![输入图片说明](https://images.gitee.com/uploads/images/2020/1028/101357_b685c288_1890767.png "QQ截图20201028092635.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/1028/101501_6d9f36b8_1890767.png "QQ截图20201028101445.png")