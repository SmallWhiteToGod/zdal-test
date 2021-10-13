package com.alipay.zdal.demo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZdalRuleParser {

	/**
	 * 解析得到分库的数据库
	 * 
	 * @param id
	 * @return
	 */
	public static int parserDbIndex(int id) {
		// 因为一共四个库，直接对4取余即可取到所有的数据库（0,1,2,3）
		return id % 4;
	}

	/**
	 * 解析得到分表的表结构
	 * 
	 * @param id
	 * @return
	 */
	public static int parserTbIndex(int id) {

		int index = parserDbIndex(id);
		// {(0-0,1,2),(1-3,4,5),(2-6,7,8)}-索引为0的数据库对应0,1,2三张表、索引为1的数据库对应3,4,5三张表
		// 这里根据数据库索引，乘3+id对3取余，即可对得到对应的表
		// 例如，id为3时，数据库索引为3，表索引为9.则这条数据插入在test_03中的t_city_09中
		int tbIndex = index * 3 + id % 3;
		return tbIndex;
	}

	public static void main(String[] args) {
		//每4个ID会被分在一个库上，共两个库，每个库4张表
		for (long i = 0; i < 1000; i++) {
			int dbIndex = Long.valueOf((i >> 2) & 1).intValue();
			int tbIndex = Math.abs(Long.valueOf(i & 3).intValue());

			System.out.println("id: "+ i + "db: " + dbIndex + "tb: " + tbIndex );
		}
	}
}