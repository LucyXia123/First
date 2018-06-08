-- MySQL dump 10.16  Distrib 10.1.13-MariaDB, for Win32 (AMD64)
--
-- Host: 192.168.10.18    Database: studyonline
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_theme`
--

DROP TABLE IF EXISTS `t_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_theme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL DEFAULT 'javascript:;',
  `img` varchar(255) NOT NULL,
  `school` varchar(32) NOT NULL,
  `hot` int(11) NOT NULL,
  `date` date NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='活动采风';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_theme`
--

LOCK TABLES `t_theme` WRITE;
/*!40000 ALTER TABLE `t_theme` DISABLE KEYS */;
INSERT INTO `t_theme` VALUES (1,'【比赛】集英小学“红色经典伴我成长”演讲比赛','https://mp.weixin.qq.com/s/V3E-u3le6VBgW8UMbc5dPA','./publish/theme/01.jpg','开封市集英小学',1,'2016-04-01',''),(2,'【活动】开封市集英小学举办首届“生活节”','https://mp.weixin.qq.com/s/RgObtsC7-27SNFrBiNPJ1Q','./publish/theme/02.jpg','开封市集英小学',1,'2016-11-20',''),(3,'【比赛】开封市集英小学举办跳绳比赛','https://mp.weixin.qq.com/s/7e5WPDTjgbe2tLKsNf29YQ','./publish/theme/03.jpg','开封市集英小学',1,'2016-10-14',''),(4,'【国庆】开封市集英小学迎国庆主题绘画活动','https://mp.weixin.qq.com/s/RzwUyrQltgoVhDyoGXKbVg','./publish/theme/04.jpg','开封市集英小学',1,'2016-10-01',''),(5,'【比赛】“庆六一新队员入队仪式暨操舞比赛”','https://mp.weixin.qq.com/s/l1iMmqaT1vC1h1jzap5y1A','./publish/theme/05.jpg','开封市集英小学',1,'2016-05-31',''),(6,'集英小学开展“一张纸献爱心”活动','https://mp.weixin.qq.com/s/0WuosgnJ-A0-lvoORI4Tqg','./publish/theme/06.jpg','开封市集英小学',1,'2016-05-10',''),(7,'集英小学开展“相约清园，欢乐同行”春游活动','https://mp.weixin.qq.com/s/VNT7f6RKQ8hq9yxEghZZ4w','./publish/theme/07.jpg','开封市集英小学',1,'2016-04-29',''),(8,'集英小学开展一年级口算达标活动','https://mp.weixin.qq.com/s/aTcQl_kPkmWmQODaxzbfLw','./publish/theme/08.jpg','开封市集英小学',1,'2016-04-06','<p>为扎实推进我校数学教学工作，培养学生扎实的口算基础技能与灵活敏捷的思维习惯，提高学生口算能力，本着从基础入手、从实践出发的原则，2016年4月6日，开封市集英小学进行了一年级口算达标测试。</p>\r\n<p>本次测试以班级为单位，各班由班主任和数学老师进行监考和计时，共100道口算题，包含了一年级近阶段所学所有口算内容，计时十分钟完成。测试结束后各班班主任收集卷子，由6个班数学老师交换阅卷。</p>\r\n<p>本次测试有助于教师查漏补缺，便于日后开展教学。同时也培养了学生认真、严谨的学习态度，促进学生口算正确率和速度的全面提高，也为学生后续数学的学习奠定了扎实的基础。</p>'),(9,'沐浴阳光，享受挑战——“阳光”体育运动会范县第一初中运动员赛场英姿','javascript:;','./publish/theme/puyang/1.jpg','濮阳市范县第一初级中学',1,'2017-11-08','<p>历时六天的阳光体育运动会终于告一段落。赛场上真是八仙过海，各显神通，为大家贡献了一幕幕精彩纷呈的画面。范县第一初级中学的运动员们更是“猛气冲长缨”，砥砺而前行，展露了披荆斩棘，力挽狂澜的精神，体现了“更快、更高、更强”的奥运精神，将本身能力发挥得淋漓尽致。最终，战绩辉煌，斩获颇丰！</p>\r\n<p>李占闯任意纵横，独领风骚。在男子100米赛跑、400米赛跑中两破纪录，均获冠军。\r\n男子1500米赛跑。1号刘国顺技压群雄，打破纪录，力获冠军。王大业后来居上，直取季军。\r\n男子3000米赛跑。刘国顺再接再厉，坚勇毅然，摘得亚军。\r\n女子百米赛场，张冬梅轻轻松松，打破纪录，以绝对优势独冠群英。\r\n青春正好，只向前方。女子200米赛跑中，祝亚蕊力取亚军。\r\n意气风发，慷慨凌厉。刘婷婷，本次运动会跳高冠军，连续四次打破纪录。\r\n乘风而起，决然一跃。刘晴，三级跳远亚军！\r\n女子4x100接力赛，厉害了，我的学校——冠军！\r\n男子4x100接力赛，不负时光不负君——亚军！\r\n风中翻腾，稳操胜券。跳绳——团体冠军！\r\n篮球，打的是腰板，是技术，更是士气。这次客场作战，我校运动员丝毫不惧，喧宾夺主，颇有大将风度。看他们——生龙活虎，闪转腾挪，凌空飞跃，貌似乔丹！最终，我校男篮力克强敌，摘得桂冠！\r\n女子篮球赛场。我校运动员带球飞奔，一路过关斩将，势如破竹。最终，取得季军。\r\n男排赛场。即使汗水一次次抛洒，但同学们依然忘我拼搏。经过一段艰苦卓绝的拉锯战，我校男排荣获亚军。\r\n起初，她发的球，无人接得住；后来，她发的球，没人敢去接。她是谁？——范县第一初级中学的铁臂阿童木！\r\n比赛结束，女排队员为学校贡献一个季军！辛苦了同学们，感谢你们为学校付出的辛勤汗水！\r\n女排精神是中国女排的历史传承，是20世纪80年代中国女排夺得五连冠之后的经验总结，诠释了“为国争光、无私奉献、科学求实、遵纪守法、团结友爱、坚强拼搏”的中华体育精神！\r\n</p>\r\n<p>感谢为本次运动会无私奉献的老师和坚持锻炼的学生！校园华章里将会留下你们的丰采！\r\n乒乓球赛活动在即，届时我们将拭目以待！\r\n</p>'),(10,'2017年中考冲刺暨表彰大会','javascript:;','publish/theme/puyang/shishidahui.jpg','濮阳市范县第一初级中学',2,'2017-03-24','<p>2017年中考冲刺暨表彰大会</p>\r\n<p>3月24日13时许，我校2017年中考冲刺暨表彰大会正式开始。初三年级十七个方队昂首阔步进入会场。校长邢玉川等领导班子、初三年级全体师生、部分学生家长代表共1200余人出席本次大会。</p>\r\n<p>会议首先对61名学生进行表彰。他们是本次全县初三年级第一次模拟考试成绩排名前100的佼佼者，是大家学习的榜样。</p>\r\n<p>随后，教师代表程海风老师、班主任代表王守虎老师、学生代表王子灿同学、蒋探探的妈妈先后上台发言，表达着他们在中考决战前的决心和希望！副校长张改玲以真实的案例提醒广大家长要密切关注当下学生的心理状态，及时给予他们生活上的关怀、精神上的鼓励和思想上的疏导。</p>\r\n<p>校长邢玉川勉励在座的学生和老师鼓足干劲，力争上游；学会自我减压，轻松上阵；学校会尽一切努力为学生们保驾护航；同时为初三学子们送上最诚挚的祝福，祝愿他们考上理想的学校，书写明天的辉煌！</p>\r\n<p>愿初三师生不畏艰难，努力攀登，再创一初辉煌荣光！</p>'),(11,'范县第一初中师生书画艺术展览','javascript:;','./publish/theme/puyang/3.jpg','濮阳市范县第一初级中学',1,'2017-12-11','<p>市县两级21位书画家走进我校，为千名师生挥毫泼墨！</p><p>本次活动邀请了市县两级书画名家</p><p>书法家：仝相和、史大作、马红兵、王玉忠、储茂彤、张恒现、王世广、苏   军、孙仪朝  </p><p>画  家：薛文信、高云峰、赵子昂、张鸿亮、赵文胜、魏相生、赵文革、岳振岭、黄承运、何希荣、胡斌、王金录</p><p>活动中，书画家们现场为学生作画，并做讲解、示范、传授书法和绘画技艺，让孩子们大饱眼福，进一步激发了学生学习书画的热情.</p><p>此次活动不仅给各位书画家提供了交流学习的平台，也使同学们更好的了解了中国书画艺术。同学们亲身感受到中国书画文化的博大精深。</p><p>现场墨香四溢，文风盎然。寥寥数笔，一副竹子栩栩如生。</p><p>此次活动的开展，为爱好书画的师生提供了与市县书画名家交流学习的机会，使广大师生更好的了解中国书画艺术，并为创建艺术校园，打造“一主三元”幸福教育工程起到了极大的推动作用！！！</p>\r\n'),(12,'元旦联欢暨艺术节文艺汇演','javascript:;','./publish/theme/puyang/4.jpg','濮阳市范县第一初级中学',1,'2017-12-30','<p>12月30日10点，我校元旦联欢暨艺术节文艺汇演正式开始。</p><p>鲜艳的灯笼高高飘扬。校长邢玉川致开幕词。</p><p>红旗下，数千师生齐看表演，其乐融融。小小主持人与人山人海，欢声如潮。表演节目有民族舞蹈、钢琴独奏、葫芦丝、小品、诗歌朗诵、T台秀、说唱舞蹈、拐棍舞、恰恰舞、手语、快板、钢琴独奏、萨克斯、诗歌朗诵等。</p>'),(13,'县长赵丽玲一行莅临我校慰问并视察工作2017.9.9','javascript:;','publish/theme/puyang/5.jpg','濮阳市范县第一初级中学',1,'2017-09-07','<p>9月7日上午，县长赵丽玲、副县长杜晓玲、县教育局局长黄守月等莅临范县第一初级中学，表达了对广大教师的慰问和教师节的美好祝福，并对初一新生的入学安排工作展开视察。</p><p>大家走进教室，观看初一教室内的布置。县教育局局长黄守月情不自禁地讲起一体机的妙用。</p><p>目前，一体机技术已被广泛地引进课堂。这种多媒体教学作为一种新兴的、先进的教学模式正在向传统的教学模式提出挑战。多媒体教学具有明显的优势，它丰富着教学内容，易引起学生的学习兴趣；增加课堂的信息量，提高教学效率，已成为解决学时矛盾的重要途径；改善教学环境，免除教师上课时板书的劳累，可以更多地注意课堂教学内容的组织和讲授。</p><p>县长赵丽玲重点询问了我校初一新生的各项入学安排进展情况，并提出了一些宝贵的建设性意见。</p><p>9月8日下午，我校举办教师节庆祝大会。会议由副校长张改玲主持。校长邢玉川高度赞扬了全体教师们的无私奉献精神，并号召大家再接再厉，坚定信心，为教育事业继续奋斗。</p>');
/*!40000 ALTER TABLE `t_theme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-25 10:55:16
