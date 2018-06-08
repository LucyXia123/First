<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="keywords" content="同方,在线教育,同方凌讯">
        <title>同方教育在线&middot;示范校更多&gt;&gt;</title>
        <link rel="shortcut icon" type="image/x-icon" href="./images/favicon.png">
        <link type="text/css" rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" />    
        <link type="text/css" rel="stylesheet" href="./css/base.css" />
        <link type="text/css" rel="stylesheet" href="./css/main.css" />
        <link type="text/css" rel="stylesheet" href="./css/more.css" />
        <style>
            .item-preview {border-radius: 0 !important;border-color:white !important;}
            .item-preview > .poster {width: 230px !important; height: 230px !important;background-color: white!important;}
             .item-preview > .poster img {width: 230px; height: 230px;}
        </style>
        <script type="text/javascript" src="./bootstrap/js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="./js/common.js"></script>
    </head>
    <body id="more">

        <nav class="navbar">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">
                    <img class="logo" src="./images/logo.jpg" title="同方教育在线" />
                </a>
                <div id="navbar" class="navbar-collapse collapse">
                    <jsp:include page="./include/nav.jsp" flush="true" />
                    <jsp:include page="./include/nav-right.jsp" flush="true" />                    
                  </div>
            </div>
        </nav>

        <div class="header">
            <div class="header-wrapper">
                <div class="crumb">
                    <a class="has-next" href="index.jsp">首页</a>
                    <a class="has-next" href="more-school.jsp">学校信息</a>
                    <a href="javascript:;" id="js-type"></a>
                </div>
            </div>
        </div>
        <div class="more-wrapper">
            <!-- 开封市五中 -->
            <div class="module-body">
                <div class="school-item">
                    <div  class="item-preview">
                        <div class="poster">
                            <img src="include/school_logo/开封市第五中学.jpg" />
                        </div>
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市第五中学</h3>
                        <div class="item-content">开封市第五中学创建于1904年，原名开封府学堂，1956年始定名为开封市第五中学。 学校地处市中心，环境优美，师资力量雄厚，现有初中、高中共34个教学班，近2000名学生。历经一个世纪的发展与进步，学校逐步成为一座拥有现代实验设备，多媒体教室、语音室、高规格微机室、电子备课室，教学闭路广播系统等先进教学设备的实力雄厚的花园式中学。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市五中">更多详情</a></div>
                    </div>
                </div>
            </div>   
            <!-- 开封市八中 -->
            <div class="module-body">
                <div class="school-item">
                    <div  class="item-preview">
                        <div class="poster">
                            <img src="include/school_logo/开封市八中.jpg" />
                        </div>
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市八中</h3>
                        <div class="item-content">开封市第八中学原名为“静宜女中”，由美籍修女盖夏姆姆1932年8月创立于的一所完中，当时这所学校因教学质量高而闻名全省。开封八中1951年更名为“开封新生女中”，是一所纯初中。1953年改名为“开封市二女中”。1956年定名为“开封市第八中学”，男女生兼收。1962年被市教育局定为重点中学。1970年改为完全中学。1985年停止招收高中，恢复初级中学至今。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市八中">更多详情</a></div>
                    </div>
                </div>
            </div>   
             <!-- 开封市第七中学 -->
            <div class="module-body">
                <div class="school-item">
                    <div  class="item-preview">
                        <div class="poster">
                            <img src="include/school_logo/开封市第七中学.jpg" />
                        </div>
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市第七中学</h3>
                          <div class="item-content">
                            <p>开封市第七中学始建于1951年。其前身就是名扬五湖四海的“开封文庙”。</p>
                            <p>开封文庙在历史上曾盛极一时，不过，清末以后就渐渐废弃了，直至新中国成立之后，它才获得新生。1951年，其遗址为开封市二初中（即今市七中）所占据，从而掀开了它辉煌的一页。</p>           
                          </div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市第七中学">更多详情</a></div>
                    </div>
                </div>
            </div>     
            <!--开封市第十三中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/开封市第十三中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市第十三中学</h3>
                        <div class="item-content">开封市第十三中学是一所具有近百年办学历史，并积淀了深厚文化底蕴的名校。她的前身是“河南省两河中学”，始建于1923年。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市第十三中学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!-- 开封市21中 -->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/开封市21中.png" /></div>       
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市开封十四中东校区（21中）</h3>
                        <div class="item-content">开封市第二十一中学前身是民办黄河中学，始建于1962年。2009年以来，开封市政府、市教育局开始落实均衡化发展举措，将我校和传统名校十四中捆绑发展，并于2012年5月挂出了开封十四中东校区校牌。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市十四中东校区(21中)">更多详情</a></div>
                    </div>
                </div>
            </div>     
            <!--开封市第二十七中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/开封市第二十七中学.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市第二十七中学</h3>
                        <div class="item-content"> 开封市第二十七中学是一所历史悠久的初级中学, 创办于1 925年, 196o年被评为文教系统先进单位受到国务院嘉奖, 现为中国西部地区教育顾问单位、 开封市首批示范性初中 。 目前二十七中有新、 老两个校区, 在编教职工192人, 54个教学班,在校学生2802人。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市第二十七中学">更多详情</a></div>
                    </div>
                </div>
            </div>
            
            <!-- 开封市33中 -->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/开封市33中.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title"><a href="video.jsp?videoId=16" target="_blank">开封市第三十三中学</a></h3>
                        <div class="item-content">开封市第三十三中学是我市公立初级中学，始建于1974年。学校现有教学班24个，学生人数为1269人，拥有专任教师118名，其中拥有中、高级职称的教师61名，省、市骨干教师14名。学校占地面积24478平方米，建筑面积9969平方米。图书、实验、电教、微机、艺术、体育等教学设施齐全。我校是全国德育先进校、河南省文明单位和河南省首批实验室建设示范学校，近几年连续获得市级先进学校、先进党组织、教学先进校、德育先进校、教育科研先进单位等荣誉称号。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市第三十三中学">更多详情</a></div>
                    </div>
                </div>
            </div>  

            <!-- 开封市集英小学 -->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/开封市集英小学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市集英小学</h3>
                        <div class="item-content">开封市集英小学是开封市新区为适应城区快速西延，倾力打造的一所高标准、高规格、高配置的现代化六年制小学。学校位于开封新区核心地带的汴西湖畔，地理位置得天得厚，交通便利，景色宜人，毗邻百年名校河南大学，文化氛围浓厚，育人环境优良。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市集英小学">更多详情</a></div>
                    </div>
                </div>
            </div>  
             <!-- 开封市马市街小学 -->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/开封市马市街小学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">开封市马市街小学</h3>
                        <div class="item-content">办一所学生满意，教师幸福，家长满意的学校，通过晨读国学品经典；阳光课间强体魄；午间美德教育塑品质；课外阅读扩视野；社团活动展个性；为孩子的高雅人生奠基，为学生的幸福人生打好底色。开封市马市街小学：办一所学生满意，教师幸福，家长满意的学校，通过晨读国学品经典；阳光课间强体魄；午间美德教育塑品质；课外阅读扩视野；社团活动展个性；为孩子的高雅人生奠基，为学生的幸福人生打好底色。</div>
                        <div class="item-date"><a href="model-school.jsp?school=开封市马市街小学">更多详情</a></div>
                    </div>
                </div>
            </div>  
            <!-- 商丘市实验小学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市实验小学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市实验小学</h3>
                        <div class="item-content">商丘市实验小学位于神火大道南伸段699号，占地280亩，可容纳120个教学班,5000名孩子就读。深厚传统文化之蕴育，锐意改革创新之意识。十年磨一剑，铸就优质教育之品牌，蜚声中原。学校先后被评为“国家级现代教育实验学校”、“国家艺术教育先进单位”、“国家级青少年体育俱乐部”、“中国教育学会先进单位”、“省级管理示范校”、“省级文明单位”等多项荣誉称号。</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市实验小学">更多详情</a></div>
                    </div>
                </div>
            </div>  
            <!-- 商丘市第二高级中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市第二高级中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市第二高级中学</h3>
                        <div class="item-content">商丘市第二高级中学是一所具有悠久历史，文化底蕴丰厚的重点高中。始建于1905年，称归德府中学堂，1912年改称省立商丘中学，1921年更名为河南省第三中学，1927年又更名为河南省立第二中学。解放后，该校又几易其名，分别称商丘中学（完全中学），河南省商丘第一高级中学，商丘县五七中学，商丘县第一高级中学。1997年撤地设市后，又改称商丘市第二高级中学，沿用至今。</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市第二高级中学">更多详情</a></div>
                    </div>
                </div>
            </div>  
             <!-- 商丘市行知学校-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市行知学校.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市行知学校</h3>
                        <div class="item-content">商丘市行知学校原名铁三局三处中小学校、中铁十局二公司中小学校，隶属铁路建设工程公司，属于企业办学。2007年6月移交商丘市人民政府，直属市教育局管理。</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市行知学校">更多详情</a></div>
                    </div>
                </div>
            </div>  
            <!-- 商丘市工学院附属兴华学校-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘工学院附属兴华学校.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘工学院附属兴华学校</h3>
                        <div class="item-content">全国课改名校——商丘工学院附属兴华学校是由丁华先生创办的集中小学于一体的九年一贯制民办学校，学校创办于1999年，校园占地465亩，建筑面积20余万平方米，在校生7000余人。</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市工学院附属兴华学校">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--商丘市第一中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市第一中学.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市第一中学</h3>
                        <div class="item-content"> 商丘市第一中学创建于1949年。通过六十余年的办学实践和探索，市一中积累了丰富的办学经验，取得了突出的办学成果，为经济发展和社会进步培养输送了数以万计的合格劳动者和数以千计的中、高级建设人才，成为商丘社会各界公认的优质学校。</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市第一中学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--商丘市外国语实验小学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市外国语实验小学.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市外国语实验小学</h3>
                        <div class="item-content"> 商丘市外国语实验小学（原名商丘铁路小学），创办于1932年。在教书育人中我们秉承“厚德博学、励责有为”之训，恪守“勇于创新、敢于担当”之风，认真贯彻落实党的教育方针，学习实践科学发展观，深入贯彻党的十八大及十八大三中、四中全会精神，深入落实习近平总书记提出的24字“社会主义核心价值观”加强道德文化建设。以校园文化建设为抓手，以丰富多彩的活动为载体，紧紧围绕教育教学为中心，扎实开展文明单位创建活动，培养出一批批文明有礼、诚信有责的阳光少年。</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市外国语实验小学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--商丘市实验中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市实验中学.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市实验中学</h3>
                        <div class="item-content"> 暂无</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市实验中学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--商丘市前进小学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市前进小学.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市前进小学</h3>
                        <div class="item-content"> 创建于1988年的前进小学，秉承“用爱心和智慧为孩子终生幸福奠基”的办学理念，树立“书香校园 、人文课堂 、涵养教师 、儒雅学生”。牢固树立科学发展为第一要务，以全新的管理模式和鲜明的办学特色，站在现代教育的制高点上，精心构筑学校腾飞的平台，办学品位和教育教学质量得到全面提升。</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市前进小学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--商丘市第一高级中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市第一高级中学.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市第一高级中学</h3>
                        <div class="item-content">暂无</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市第一高级中学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--商丘市第一实验小学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/商丘市第一实验小学.jpg"  /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">商丘市第一实验小学</h3>
                        <div class="item-content">暂无</div>
                        <div class="item-date"><a href="model-school.jsp?school=商丘市第一实验小学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!-- 濮阳市范县第一初级中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市范县第一初级中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市范县第一初级中学</h3>
                        <div class="item-content">范县第一初级中学是我县新建的一所高标准的初级中学。校址位于范县新区杏坛路与金堤路交汇处西侧，学校占地面积65亩，建筑面积达18600平方米，总投资5000余万元，设有50个教学班，在校生4100名学生。学校县绿地面积19.8亩，绿地率达30.4%，绿化覆盖率达42%。</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市范县第一初级中学">更多详情</a></div>
                    </div>
                </div>
            </div>  
            <!-- 濮阳市南乐县城关镇初级中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市南乐县城关镇初级中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市南乐县城关镇初级中学</h3>
                        <div class="item-content">南乐县城关镇初级中学，始建于1956年，1996年搬迁到南乐县金穗街北段；辖区内有15个自然村，服务人口3万余人，服务半径2公里；占地面积7600平方米，总建筑面积12315平方米，生均建筑面积7.5平方米；学校现有22个教学班，在校学生1700名，平均班额77人；辖区内适龄人口入学率100%，辍学人数0，辍学率0；在职教职工125人，党员教师54人，专任教师125人，学历达标率100%。</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市南乐县城关镇初级中学">更多详情</a></div>
                    </div>
                </div>
            </div>  
            <!-- 濮阳市南乐县第二初级中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市南乐县第二初级中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市南乐县第二初级中学</h3>
                        <div class="item-content">南乐县第二初级中学位于南乐县一行路与西环路交叉口，是县委、县政府确立的一项重要的民生工程。在2016年9月份开始招生，在教育教学中，全体师生齐心协力，斗志昂扬，以一种雏鹰展翅的姿态茁壮成长，得到了领导的肯定和人民群众的一致好评。校委会表示这个拼搏奋进的集体，定会不负众望，力争把南乐县第二初级中学打造成为豫北一流名校。</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市南乐县第二初级中学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!-- 濮阳市范县思源学校-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市范县思源学校.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">范县思源学校</h3>
                        <div class="item-content">暂无</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市范县思源学校">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!-- 濮阳市第三中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市第三中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市第三中学</h3>
                        <div class="item-content">濮阳市第三中学始建于2000年3月，濮阳市教育局直属初级中学。学校始终坚持“为学生终身发展奠基，对民族未来负责”的办学理念，以育一流人才、创特色名校为目标，坚持德育兴校、科研强校、依法治校、质量兴校，全面实施素质教育，促进学生多元智能和谐发展，教育教学质量一年一个新台阶，迅速跻身于全市先进学校行列。</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市第三中学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--濮阳市南乐县第二实验小学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市南乐县第二实验小学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市南乐县第二实验小学</h3>
                        <div class="item-content">南乐县第二实验小学始建于1994年，是县直属全日制小学。占地面积17864平方米，现有30个教学班，学生1986名，教职工99名。 学校奔着“科研兴校、争创一流”的目标，努力构建了一支高素质的教师队伍，打造出国家级骨干教师1人、省级骨干教师5人、省级学科技术带头人5人、市级骨干教师8人、河南省名师1人。建校至今，学校连续八年被评为目标管理先进单位，并先后获得了全国书法教育实验学校、省书法教育先进学校、濮阳市文明单位、濮阳市教育系统先进集体等荣誉称号。</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市南乐县第二实验小学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--濮阳市南乐县育才小学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市南乐县育才小学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市南乐县育才小学</h3>
                        <div class="item-content">学校成立于2016年， 总投资2500万元。学校占地面积21234平方米，生均占地面积22.56平方米，建筑面积7700平方米，生均建筑面积8.18平方米。学校建有200米塑胶环形跑道、图书室、体育器材室、音乐室、美术室、多功能教室、微机室、科学实验室、心理咨询室、卫生保健室。图书室内现有图书19760册，生均图书21册，仪器室、体育器材室配有科学、数学、体育等仪器器材设备，音美器材达国家一类标准。学校现有专任教师60名，其中大专5人，本科55人，教师学历达标率100％，高学历率100％。现有学生941人，有23个教学班，班均41人，学校实行就近、免试入学制度，适龄儿童入学率达100%。</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市南乐县育才小学">更多详情</a></div>
                    </div>
                </div>
            </div> 
            <!--濮阳市油田第十二中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市油田第十二中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市油田第十二中学</h3>
                        <div class="item-content">暂无</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市油田第十二中学">更多详情</a></div>
                    </div>
                </div>
            </div>
            <!--濮阳市第八中学-->
            <div class="module-body">
                <div class="school-item">
                    <div class="item-preview">
                        <div class="poster"><img src="include/school_logo/濮阳市第八中学.jpg" /></div>         
                    </div>
                    <div class="item-text">            
                        <h3 class="item-title">濮阳市第八中学</h3>
                        <div class="item-content">暂无</div>
                        <div class="item-date"><a href="model-school.jsp?school=濮阳市第八中学">更多详情</a></div>
                    </div>
                </div>
            </div> 
        </div>
        <!-- footer -->
        <div id="footer"></div>
        <script>$("#footer").load("./include/footer.html");</script>
        <script>
        // @URL_UPLOAD defined common.js      
        	$(".poster").children("img").each(function() {
        		var pos = this.src.lastIndexOf('/');
        		pos = (pos===-1) ? 0 : ++pos;
        		var filename = this.src.substring(pos);
        		this.src = URL_UPLOAD + "/include/school_logo/" + filename;
        	});
        </script>
    </body>
</html>