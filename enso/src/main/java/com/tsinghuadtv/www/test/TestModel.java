package com.tsinghuadtv.www.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.tsinghuadtv.www.entity.Model;

public class TestModel {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("conf.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) throws IOException {

		/*
		 * //mybatis的配置文件 String resource = "conf.xml";
		 * //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件） InputStream is =
		 * TestModel.class.getClassLoader().getResourceAsStream(resource);
		 * //构建sqlSession的工厂 SqlSessionFactory sessionFactory = new
		 * SqlSessionFactoryBuilder().build(is);
		 * //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件） //Reader reader =
		 * Resources.getResourceAsReader(resource); //构建sqlSession的工厂
		 * //SqlSessionFactory sessionFactory = new
		 * SqlSessionFactoryBuilder().build(reader); //创建能执行映射文件中sql的sqlSession
		 * SqlSession session = sessionFactory.openSession(); String statement =
		 * "com.tsinghuadtv.www.mapper.ModelMapper.getModel";//映射sql的标识字符串
		 * //执行查询返回一个唯一user对象的 sql Model model = session.selectOne(statement, 1);
		 * System.out.println(model.getInfo());
		 */
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// Model model = (Model)session.selectOne("com.tsinghuadtv.www.mapper.ModelMapper.getModel", 1);
			List<Object> list = session.selectList("com.tsinghuadtv.www.mapper.ModelMapper.listModel");
			for (Object model : list) {
				System.out.println(((Model)model).getSchool());
			}				
		} finally {
			session.close();
		}
	}
}
