/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 8.0.28 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `blog`;

/*Table structure for table `attention` */

DROP TABLE IF EXISTS `attention`;

CREATE TABLE `attention` (
  `aid` char(255) NOT NULL COMMENT '关注表的主键',
  `aname` varchar(255) DEFAULT NULL COMMENT '被关注姓名',
  `username` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `attention` */

insert  into `attention`(`aid`,`aname`,`username`) values ('1516064132456734722','admin1','xhd'),('1516805426963517442','xhd','admin1'),('1516816001407864834','dasdas','admin1');

/*Table structure for table `banner` */

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `bannerid` char(20) NOT NULL COMMENT '轮播图id',
  `bimg` longtext COMMENT '轮播图地址',
  `desone` varchar(255) DEFAULT NULL COMMENT '描述1',
  `destwo` varchar(255) DEFAULT NULL COMMENT '描述2',
  `showpage` tinyint DEFAULT '0' COMMENT '前台展示（0：不显示 1：显示）',
  `place` tinyint DEFAULT NULL COMMENT '展示位置（1：首页，2：博客页）',
  PRIMARY KEY (`bannerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `banner` */

insert  into `banner`(`bannerid`,`bimg`,`desone`,`destwo`,`showpage`,`place`) values ('1','https://static.runoob.com/images/mix/img_fjords_wide.jpg','做那个挑战风车的傻子，鲁莽也比怯懦更接近勇敢','放弃就等同失败',1,1),('2','https://static.runoob.com/images/mix/img_nature_wide.jpg','迄今所有人生都大写着失败，但不妨碍我继续向前','越是恐惧，越要战胜它',1,1),('3','https://static.runoob.com/images/mix/img_mountains_wide.jpg','真理，并不掌握在多数人手中','全力以赴做正确的事',1,1);

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `bid` char(20) NOT NULL COMMENT '文章ID',
  `title` longtext COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  `firstPicture` longtext COMMENT '首图',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `alter_time` datetime DEFAULT NULL COMMENT '修改时间',
  `description` longtext COMMENT '内容摘取',
  `id` char(19) DEFAULT NULL COMMENT '作者id',
  `status` int DEFAULT NULL COMMENT '文章状态（1：置顶，2：原创，3：转载）',
  `pageview` int DEFAULT NULL COMMENT '访问量',
  `tieba` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分类',
  `issue` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否发布',
  `Ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'ip属地',
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `blog` */

insert  into `blog`(`bid`,`title`,`content`,`firstPicture`,`create_time`,`alter_time`,`description`,`id`,`status`,`pageview`,`tieba`,`issue`,`Ip_address`) values ('1509861330856407041','测试1','测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图','https://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/22-960x640.jpg','2022-04-01 19:52:48','2022-04-01 19:52:48','测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图测试首图','1506921901988679681',4,21,'其他','2',NULL),('1509973168755544065','测试代码块','```java\r\n/**\r\n     *  首页的分页\r\n     * @param\r\n     * @return\r\n     */\r\n    @GetMapping(\"page\")\r\n    public String pageindex(HttpSession session){\r\n        User user = (User)session.getAttribute(\"user\");\r\n        Page<Blog> page = new Page<>(PAGE_NUM, PAGE);\r\n        QueryWrapper<Blog> objectQueryWrapper = new QueryWrapper<>();\r\n        objectQueryWrapper.orderByAsc(\"status\");\r\n        objectQueryWrapper.orderByDesc(\"alter_time\");\r\n        objectQueryWrapper.eq(\"id\",user.getId());\r\n        IPage<Blog> blogIPage = blogMapper.selectPage(page,objectQueryWrapper);\r\n        session.setAttribute(\"blogIPage\",blogIPage);\r\n        return \"article\";\r\n    }\r\n    /**\r\n     *   翻页分页\r\n     * @param\r\n     * @return\r\n     */\r\n    @GetMapping(\"pagesplit\")\r\n    public String  pagesplit(String title, @RequestParam(defaultValue = \"1\")Integer currentpage ,\r\n                             HttpSession session){\r\n        QueryWrapper<Blog> obj = new QueryWrapper<>();\r\n        User user = (User)session.getAttribute(\"user\");\r\n        obj.eq(\"id\",user.getId());\r\n        obj.like(\"title\",title);\r\n        obj.or();\r\n        obj.like(\"tieba\",title);\r\n        obj.orderByAsc(\"status\");\r\n        obj.orderByDesc(\"alter_time\");\r\n        if (currentpage==null) currentpage=PAGE_NUM;\r\n        Page<Blog> page = new Page<>(currentpage, PAGE);\r\n        IPage<Blog> blogIPage = blogMapper.selectPage(page, obj);\r\n        session.setAttribute(\"blogIPage\",blogIPage);\r\n        return \"article :: LAY_bloglist\";\r\n    }\r\n\r\n    @GetMapping(\"pagesplitplus\")\r\n    public String  pagesplitplus(String title, @RequestParam(defaultValue = \"1\")Integer currentpage ,\r\n                             HttpSession session){\r\n        QueryWrapper<Blog> obj = new QueryWrapper<>();\r\n        obj.ne(\"status\",4).and(blogQueryWrapper -> blogQueryWrapper.like(\"title\", title).or().like(\"tieba\", title));\r\n        //  WHERE status <> ? AND ( title LIKE ? OR tieba LIKE ? ) ORDER BY alter_time DESC LIMIT 0,8\r\n        obj.orderByDesc(\"alter_time\");\r\n        if (currentpage==null) currentpage=PAGE_NUM;\r\n        Page<Blog> page = new Page<>(currentpage, PAGE);\r\n        IPage<Blog> blogIPage = blogMapper.selectPage(page, obj);\r\n        session.setAttribute(\"IPage\",blogIPage);\r\n        return \"content :: bloglist\";\r\n    }\r\n    /**\r\n     *\r\n     * 跳转更新文章页面\r\n     * @param\r\n     * @return\r\n     */\r\n    @GetMapping(\"updatablog\")\r\n    public String updatablog(String blogid,Model model){\r\n        Blog byId = blogService.getById(blogid);\r\n        model.addAttribute(\"blog\",byId);\r\n        return \"edit\";\r\n    }\r\n\r\n```','https://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/24-700x529.jpg','2022-04-02 03:17:12','2022-04-02 03:31:31','这是代码块的测试','1506921901988679681',1,85,'编程','发布更新',NULL),('1510181775065378817','测试7777777777777','测试7777777777777测试7777777777777测试7777777777777','https://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/22-960x640.jpg','2022-04-02 17:06:08','2022-04-02 17:06:08','测试7777777777777','1506921901988679681',1,1,'其他','2',NULL),('1510181973724393473','测试7777777777777','1111','https://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/24-700x529.jpg','2022-04-02 17:06:55','2022-04-02 17:06:55','https://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/24-700x529.jpg','1506921901988679681',1,5,'其他','2',NULL),('1510212815108665345','测试7777777777777','测试7777777777777','测试7777777777777','2022-04-02 19:09:28','2022-04-02 19:09:28','测试7777777777777','1506921901988679681',1,2,'其他','2',NULL),('1510212847635492865','测试7777777777777','测试7777777777777','测试7777777777777','2022-04-02 19:09:36','2022-04-02 19:09:36','测试7777777777777','1506921901988679681',1,1,'其他','2',NULL),('1510212882464993281','测试7777777777777','测试7777777777777','测试7777777777777','2022-04-02 19:09:44','2022-04-02 19:09:44','测试7777777777777','1506921901988679681',1,8,'其他','2',NULL),('1510212971874971649','测试7777777777777','测试7777777777777','测试7777777777777','2022-04-02 19:10:06','2022-04-02 19:10:06','测试7777777777777','1506921901988679681',1,8,'其他','2',NULL),('1510213003680378881','测试7777777777777','测试7777777777777','测试7777777777777','2022-04-02 19:10:13','2022-04-02 19:10:13','测试7777777777777','1506921901988679681',1,25,'其他','2',NULL),('1511449661964775425','湿哒','湿哒','湿哒','2022-04-06 05:04:15','2022-04-06 05:04:15','湿哒','1506907248923439106',1,3,'军事','2',NULL),('1511464697122734081','敖德萨多','大大','1','2022-04-06 06:04:00','2022-04-06 06:04:00','大萨达萨达','1506921901988679681',1,10,'军事','2',NULL),('1520087786156949506','IP地址','IP地址','https://www.baidu.com/link?url=gZozvKrh7_NDhWRyPTYCAQZ5YhAbUU6ym-xbek0OyWSqgcwWTPshnFlZ0Zyr91loCh0a0KxG79AiVIni8H7xkhbbwHtq_QI-brzsijN9rL8V75Z9zoLTQ0fjmmmwJQZ-9uj4aRCB7C8CVRztwmCu_yp_cTBrEYHTSfJAZbgA_BqSRoVHp1veUOskuIjIHbM_ENeaMDBBKqcsjIAs4Da87yo4Uws7j4NYbgGPK8BuQHIL0nbw_2lak8gdIp1Hfrs2HfYYQTORZxwQF5Rt01EIUeYSSGxQ_7-kbHAKL_k4nsEzvDACRVidZOMCSuu5jDvuUuPuQNrTkkTxEijIStr7rubFol6KixVrdP90KoVt3pN6sw-ElNCrETXhHkzrJvaV8hA7I08yZDukOc1DWW6b0slXP9ZSFkj00BWZwB2ewLysHru2fABAHOsui-qZEuOkZCMkC53B8pfi-DhsUzZ1AiJhNiuD8MSKbX0Mm7PTVqNcjn86bdMlnm0uoQAje4QsgDHLWEU2cTjDMXlGV10mqxA5jJHx46FR58izfR5fetpPJy4rvyBZHtc_j8pfbSwTo2ilvPiCx2rvl7prJqp8sdQBy0YSB8AZPmRy0sdomqLiUoKBnPHncyyhdTsuq1PhuXzgCVW40RDEMQOVRGhj-rx73Jwnt1eQn8B3iACGmKK&wd=&eqid=dc29f729000196f400000005623dbe2d','2022-04-30 01:09:05','2022-04-30 01:09:05','IP地址','1506921901988679681',1,4,'其他','2','福建省厦门市');

/*Table structure for table `chatlist` */

DROP TABLE IF EXISTS `chatlist`;

CREATE TABLE `chatlist` (
  `cid` varchar(255) NOT NULL COMMENT '主键',
  `uid` varchar(255) DEFAULT NULL COMMENT '聊天列表用户id',
  `avatar` longtext COMMENT '用户头像',
  `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `vname` varchar(255) DEFAULT NULL COMMENT '列表集合姓名',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `chatlist` */

insert  into `chatlist`(`cid`,`uid`,`avatar`,`name`,`vname`) values ('1512178074358804482','1506907248923439106','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','admin1','xhd'),('1512203286576082946','1506907100449271810','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/4ce14a57be7c4317a7d26c56d1cab15b','admin1','dasdas'),('1512473282686447618','1506921901988679681','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','xhd','admin1');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `did` char(20) NOT NULL COMMENT '说说id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `alter_time` datetime DEFAULT NULL COMMENT '修改时间',
  `content` varchar(255) DEFAULT NULL COMMENT '说说内容',
  `id` char(20) DEFAULT NULL COMMENT '作者id',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `message` */

insert  into `message`(`did`,`create_time`,`alter_time`,`content`,`id`,`deleted`) values ('1509135740289355777','2022-03-30 19:49:34','2022-04-03 02:24:47','大萨达大大大大dadas大萨达萨达硕大的dadasd',NULL,0),('1509135913170178050','2022-03-30 19:50:15','2022-04-03 02:02:16','大萨达大大',NULL,0),('1509136663174692865','2022-03-30 19:53:14','2022-04-03 02:14:26','大大dasdasd',NULL,0),('1509136900941385729','2022-03-30 19:54:10','2022-04-03 02:14:32','大大大大',NULL,0),('1509137601457250305','2022-03-30 19:56:57','2022-03-30 19:56:57','大大','1506921901988679681',1),('1509138099270807553','2022-03-30 19:58:56','2022-04-03 21:15:39','大大答案是多是是是','1506921901988679681',1),('1509138347732975618','2022-03-30 19:59:55','2022-04-03 02:00:00','打啊dadas','1506921901988679681',0),('1509140757754888194','2022-03-30 20:09:30','2022-03-30 20:09:30','大大','1506921901988679681',0),('1509140909865517058','2022-03-30 20:10:06','2022-03-30 20:10:06','SDSDD','1506921901988679681',1),('1510275561342865409','2022-04-02 23:18:48','2022-04-02 23:18:48','dassdadadadsa','1506921901988679681',1),('1510275865882890241','2022-04-02 23:20:01','2022-04-02 23:20:01','11111111111','1506921901988679681',1),('1510322235104780289','2022-04-03 02:24:16','2022-04-03 02:24:16','湿哒哒','1506921901988679681',1),('1510322288259194881','2022-04-03 02:24:29','2022-04-03 02:24:29','大萨达大大大大dadas大萨达萨达硕大的','1506921901988679681',0),('1510322370006179842','2022-04-03 02:24:48','2022-04-03 02:24:48','大萨达大大大大dadas大萨达萨达硕大的dadasd','1506921901988679681',0),('1510322392571535361','2022-04-03 02:24:53','2022-04-03 02:36:01','dasdada少时诵诗书所所所','1506921901988679681',1),('1510322480517701633','2022-04-03 02:25:14','2022-04-03 02:25:14','dasdada大萨达萨达','1506921901988679681',1),('1510324071312322562','2022-04-03 02:31:34','2022-04-03 02:31:34','撒大声地','1506921901988679681',0),('1510324174072770561','2022-04-03 02:31:58','2022-04-03 02:31:58','dasdad','1506921901988679681',0),('1510324645567115265','2022-04-03 02:33:51','2022-04-03 21:11:11','湿哒oooo','1506921901988679681',0),('1510324778853707777','2022-04-03 02:34:22','2022-04-03 02:34:22','大大','1506921901988679681',0),('1510324821178429442','2022-04-03 02:34:33','2022-04-03 02:34:33','大大撒发','1506921901988679681',1),('1510324909791490049','2022-04-03 02:34:54','2022-04-03 02:34:54','打算打打算','1506921901988679681',0),('1510325191531257857','2022-04-03 02:36:01','2022-04-03 02:36:28','dasdada少时诵诗书所所所啊啊','1506921901988679681',1),('1510325305440165890','2022-04-03 02:36:28','2022-04-03 21:08:11','dasdada少时诵诗书所所所啊啊qq','1506921901988679681',0),('1510325323567947778','2022-04-03 02:36:32','2022-04-03 02:36:32','大大','1506921901988679681',0),('1510325387199733762','2022-04-03 02:36:47','2022-04-03 02:36:47','大大','1506921901988679681',1),('1510602682867097602','2022-04-03 20:58:40','2022-04-03 20:58:40','你好','1506907248923439106',1),('1510602745085403137','2022-04-03 20:58:55','2022-04-03 20:58:55','啊啊','1506907248923439106',0),('1510603345579712514','2022-04-03 21:01:18','2022-04-03 21:01:18','想','1506907248923439106',0),('1510603382820937730','2022-04-03 21:01:27','2022-04-03 21:01:27','大大','1506907248923439106',0),('1510603439326601217','2022-04-03 21:01:40','2022-04-03 21:01:40','大萨达','1506907248923439106',0),('1510605079907639298','2022-04-03 21:08:11','2022-04-03 21:08:47','dasdada少时诵诗书所所所啊啊qq啊啊111','1506921901988679681',0),('1510605118067417090','2022-04-03 21:08:20','2022-04-03 21:09:50','dasdada少时诵诗书所所所啊啊qq啊啊a477777','1506921901988679681',0),('1510605229401022465','2022-04-03 21:08:47','2022-04-03 21:08:47','dasdada少时诵诗书所所所啊啊qq啊啊111','1506921901988679681',0),('1510605291564802050','2022-04-03 21:09:02','2022-04-03 21:09:02','dasdada少时诵诗书所所所啊啊qq啊啊a4','1506921901988679681',0),('1510605323575730177','2022-04-03 21:09:10','2022-04-03 21:09:10','请求','1506921901988679681',0),('1510605495328284674','2022-04-03 21:09:50','2022-04-03 21:09:50','dasdada少时诵诗书所所所啊啊qq啊啊a477777','1506921901988679681',0),('1510605831610802178','2022-04-03 21:11:11','2022-04-03 21:15:20','湿哒oooo77','1506921901988679681',1),('1510607378134573058','2022-04-03 21:17:19','2022-04-03 21:25:58','asda啊啊','1506921901988679681',1),('1510608107020722177','2022-04-03 21:20:13','2022-04-03 21:20:13','湿哒','1506921901988679681',1),('1510608116223025153','2022-04-03 21:20:15','2022-04-03 21:20:15','湿哒','1506921901988679681',1),('1510608137190350850','2022-04-03 21:20:20','2022-04-03 21:20:20','湿哒','1506921901988679681',1),('1510610202058080257','2022-04-03 21:28:33','2022-04-03 21:28:33','阿打算','1506921901988679681',1),('1510610206827003905','2022-04-03 21:28:34','2022-04-03 21:28:34','阿打算','1506921901988679681',1),('1510615694054572034','2022-04-03 21:50:22','2022-04-03 21:50:22','大','1506921901988679681',1),('1510615896354242561','2022-04-03 21:51:10','2022-04-03 21:51:10','啊','1506921901988679681',1),('1510616222617272321','2022-04-03 21:52:28','2022-04-03 21:52:28','啊啊啊','1506921901988679681',1),('1510618265528451074','2022-04-03 22:00:35','2022-04-03 22:00:40','大','1506921901988679681',0),('1510642505459929089','2022-04-03 23:36:54','2022-04-03 23:36:58','12','1506921901988679681',1);

/*Table structure for table `offline` */

DROP TABLE IF EXISTS `offline`;

CREATE TABLE `offline` (
  `send_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '发送人姓名',
  `accept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '接受人性名',
  `content` longtext COMMENT '消息内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `chat_id` char(20) DEFAULT NULL COMMENT '消息id',
  `isdel_send` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '发送人逻辑删除',
  `offline_message` tinyint DEFAULT '0' COMMENT '离线消息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `offline` */

insert  into `offline`(`send_name`,`accept_name`,`content`,`send_time`,`chat_id`,`isdel_send`,`offline_message`) values ('xhd','admin1','你好  xhd','2022-04-08 05:40:18','1512183507605655553','0admin1xhd',0),('xhd','admin1','你也好 admin1','2022-04-08 05:40:39','1512183595740565505','0admin1xhd',0),('xhd','admin1','还在吗？','2022-04-08 05:40:52','1512183652476915714','0admin1xhd',0),('xhd','admin1','111111111111','2022-04-08 05:44:09','1512184478859661314','0admin1xhd',0),('xhd','admin1','多了','2022-04-08 05:47:05','1512185292340727810','0admin1xhd',0),('xhd','admin1','测试\n','2022-04-08 05:49:24','1512185796617703426','0admin1xhd',0),('xhd','admin1','车市2','2022-04-08 05:49:51','1512185928457261058','0admin1xhd',0),('xhd','admin1','xhd的','2022-04-08 06:15:02','1512192251068653569','0admin1xhd',0),('xhd','admin1','测试测试啊','2022-04-08 06:15:14','1512192298879524865','0admin1xhd',0),('xhd','admin1','在吗？我是admin1','2022-04-08 06:15:54','1512192467033366529','0admin1xhd',0),('xhd','admin1','在的','2022-04-08 06:53:34','1512201945334099969','0admin1xhd',0),('xhd','admin1','啊','2022-04-08 20:00:01','1512399864137916418','0admin1xhd',0),('xhd','admin1','1','2022-04-08 20:00:32','1512399993003712513','0admin1xhd',0),('xhd','admin1','2','2022-04-08 20:00:40','1512400027422171138','0admin1xhd',0),('xhd','admin1','啊','2022-04-09 00:26:09','1512466837307875330','0xhd',0),('xhd','admin1','你好','2022-04-09 00:31:52','1512468275916398593','0xhd',0),('admin1','xhd','...\n','2022-04-30 01:53:40','1520099009887862785','0',1),('admin1','xhd','...','2022-04-30 01:53:53','1520099062731898882','0',1);

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `typeid` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类ID',
  `tieba` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分类名',
  `bid` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类id',
  PRIMARY KEY (`typeid`,`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `type` */

insert  into `type`(`typeid`,`tieba`,`bid`) values ('1','编程',''),('2','生活',''),('3','军事',''),('4','小说',''),('5','搞笑',''),('6','其他','');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` char(19) NOT NULL COMMENT '用户ID',
  `name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `intro` varchar(500) DEFAULT NULL COMMENT '用户简介',
  `t_avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `t_sort` int DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint DEFAULT NULL COMMENT '逻辑删除',
  `fid` char(19) DEFAULT NULL COMMENT '访客表ID',
  `bid` char(19) DEFAULT NULL COMMENT '博客表ID',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`intro`,`t_avatar`,`t_sort`,`is_deleted`,`fid`,`bid`,`password`,`email`) values ('1','1111',NULL,NULL,NULL,NULL,'1','1',NULL,NULL),('1506907100449271810','dasdas','dasdada','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/4ce14a57be7c4317a7d26c56d1cab15b',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506907248923439106','xhd','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg',NULL,NULL,NULL,NULL,'xhd','192243498@qq.com'),('1506913380173770754','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/9c7e9fa5cd2e4c3ead772ebbfc7dbb58',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506914200688766978','qqqqqq','qqqqqqqq','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/7c7f46ffb844416bb231fc52703d8e58',NULL,NULL,NULL,NULL,'dasdada','192243498@qq.com'),('1506915379393036290','dasdas','dasda','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/20b40100c6ab46a8964f229df5bb0bb6',NULL,NULL,NULL,NULL,'dasda','192243498@qq.com'),('1506916257873199105','yyyyyy','yyyyyyyyy','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/61511211441b45b5936e33157bdcf0e1',NULL,NULL,NULL,NULL,'yyyyyyyyy','192243498@qq.com'),('1506916937203585026','uuuu','uuuu','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/30565db662a14cb5afcbde3f5cf553a0',NULL,NULL,NULL,NULL,'uuuu','192243498@qq.com'),('1506920011712303105','gggg','gggg','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/e4eb11b48aa7434e941c9c88fe7f13ce',NULL,NULL,NULL,NULL,'ggg','192243498@qq.com'),('1506921901988679681','admin1','撒大声地撒大大叔大婶大所多实打实大多撒是的撒大大撒大声地撒大大叔大婶大所大萨达十大大大大大大叔大婶多','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg',NULL,NULL,NULL,NULL,'admin','192243498@qq.com'),('1506922294164525058','pppppp','ppppp','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/257122f6e60942d7a1f06c19ddde6890',NULL,NULL,NULL,NULL,'pppppppp','192243498@qq.com'),('1506923484914880514','hhhhhhh','hhhhhhh','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/d22611ddd37c435fbe3a6e1a4047f01d',NULL,NULL,NULL,NULL,'hhhhhhhh','192243498@qq.com'),('1506925764795940866','hhhhh','hhhhhhhhhhh','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/a9f91a102ed646d5b0e1cb7a53fb5f0f',NULL,NULL,NULL,NULL,'hhhhhhh','192243498@qq.com'),('1506926276052140033','llllllllllll','llllllllll','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/fc864f273444446f87696454de8a405b',NULL,NULL,NULL,NULL,'lllllllllll','192243498@qq.com'),('1506942123407773697','cccc','cccc',NULL,NULL,NULL,NULL,NULL,'ccccc','192243498@qq.com'),('1506942825500725250','cccc','cccc','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/c1da393d32904b859e92b14a4cf90ce0',NULL,NULL,NULL,NULL,'ccccc','192243498@qq.com'),('1506943203650785281','cccc','cccc','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/b6773c1d119b4c8297ebae25cab9903f',NULL,NULL,NULL,NULL,'ccccc','192243498@qq.com'),('1506943346999513090','cccc','cccc','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/7f98ba20ca814711bc974eb94e92fda5',NULL,NULL,NULL,NULL,'ccccc','192243498@qq.com'),('1506943387583598594','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/dd6941fc201a4cce902da9141385e4c2',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506943696431173634','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/4731f3cdada2401ab7d6e98d3dda544a',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506943863150563330','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/dbb2bd9ef8af42a881032ebe7609bf8f',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506943944129990658','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/d6d2a28d0acc4b60bf4395e0b94603d0',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506944170152669186','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/d1dfb489e22f45739c9601b4c242cbe0',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506944538181873666','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/1f4a9f9344bc4dca966ef28172aec770',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506944738237579265','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/2acefc927b6b4bf8bf68c87528fe3bbc',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1506944784718856193','dasdas','dasdad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/644a2890be9c49d989472ccb7bbda8a5',NULL,NULL,NULL,NULL,'dasdad','192243498@qq.com'),('1511030385336258562','dadadada','大萨达萨达所','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/bac0373b66e043c99b36587e56fcf152',NULL,NULL,NULL,NULL,'zxcdadasd','192243498@qq.com'),('1511034512489574401','dadas','大萨达萨达','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/dcb682e29c704498bcf32d31c3c410f5',NULL,NULL,NULL,NULL,'dadasd','192243498@qq.com'),('1511035058009075713','dadsa','dasdadad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/e4e452ba062445d9a0b941b131114778',NULL,NULL,NULL,NULL,'dasdsadad','2842933362@qq.com'),('1511055650598879233','大大女师傅','dasdsadsad','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/5593075d3232438f99a836a1c6dbf436',NULL,NULL,NULL,NULL,'dasdadada','2842933362@qq.com'),('1511058729733951489','大大声','dasdadadsadsa','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/f258debfd94c4de5817a6187dcfe289d',NULL,NULL,NULL,NULL,'dasdasdsada','284933362@qq.com'),('1511060778915708929','大该如何','dasdada','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/86bb34af260b4ef197c3e287ad071669',NULL,NULL,NULL,NULL,'dasdadad','2842933362@qq.com'),('1511061111821819905','大暗色光伏发电','dasdadada','https://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/ea817f16d3e74edc8832c3408048c2caavatar.jpg',NULL,NULL,NULL,NULL,'dsadsadad','2842933362@qq.com'),('1511061328478593025','大大撒发啊FDA','dasdada','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/501e4e7734fa4056828f7e8f1248fe4axhd.jpg',NULL,NULL,NULL,NULL,'dasdadad','2842933362@qq.com'),('1511061606137200642','徐浩东','dasdada','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-04-05/be7dd23e3ae84a268846893df3a9db4c22-960x640.jpg',NULL,NULL,NULL,NULL,'dasdadsada','284933362@qq.com');

/*Table structure for table `visit` */

DROP TABLE IF EXISTS `visit`;

CREATE TABLE `visit` (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '被访问用户id',
  `vname` varchar(255) DEFAULT NULL COMMENT '访客姓名',
  `vavatar` longtext COMMENT '访客头像',
  `vid` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '访问用户id',
  `vnum` int DEFAULT NULL COMMENT '访客数',
  `vtime` datetime DEFAULT NULL COMMENT '访问时间',
  `visitid` char(20) NOT NULL COMMENT '访问表id',
  PRIMARY KEY (`visitid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `visit` */

insert  into `visit`(`id`,`vname`,`vavatar`,`vid`,`vnum`,`vtime`,`visitid`) values ('1506921901988679681','xhd','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506907248923439106',7,'2022-03-30 21:25:32','1510247056181817345'),('1506921901988679681','xhd','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506907248923439106',6,'2022-04-02 21:26:29','1510247293923356674'),('1506907248923439106','admin1','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506921901988679681',4,'2022-04-02 22:23:47','1510261716037632002'),('1506921901988679681','xhd','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506907248923439106',5,'2022-04-06 01:03:24','1511389046617317377'),('1506907248923439106','admin1','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506921901988679681',3,'2022-04-06 05:05:26','1511449956765626370'),('1506921901988679681','dasdas','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-24/4ce14a57be7c4317a7d26c56d1cab15b','1506907100449271810',4,'2022-04-06 21:15:02','1511693965236846593'),('1506907248923439106','admin1','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506921901988679681',2,'2022-04-07 10:24:19','1511892596074139649'),('1506921901988679681','xhd','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506907248923439106',3,'2022-04-07 10:24:30','1511892639661346817'),('1506921901988679681','xhd','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506907248923439106',2,'2022-04-08 20:01:46','1512400302304272385'),('1506921901988679681','xhd','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506907248923439106',1,'2022-04-18 21:18:25','1516043472929992706'),('1506907248923439106','admin1','http://xhd-bsedu.oss-cn-shenzhen.aliyuncs.com/2022-03-30/e39c962c1b944cb2a1bd980671f0e56dxhd.jpg','1506921901988679681',1,'2022-04-23 16:55:25','1517789225176186882');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
