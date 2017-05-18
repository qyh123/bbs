package com.itstar.util;

import java.util.ArrayList;
import java.util.List;

public class Page {


	public static int ipage = 10; // 分页单位

	public static int allpage; // 总页数

	public static int pages; // 接受的页码变量

	public static int cpage = 1; // 当前页

	public static int spage = 1; // 开始页
	
	public static int allCol;   //总记录数
	
	public static List relist; //显示的记录

	public Page(List list,String page) {
		allCol = list.size();// 取总记录数
		relist = new ArrayList();
		allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// 获得总页面数

		if (page == null||page.equals("0")) {// 判断参数pages是否为空
			pages = 1;

		} else {

			pages = new Integer(page).intValue();

		}

		if (pages > allpage) // 判断当前页
		{

			cpage = 1;

		} else {

			cpage = pages;

		}

		
		spage = (cpage - 1) * ipage; // 判断起始页
		//获得要显示的list列表
		for (int i = spage; i< ipage * cpage && i < list.size(); i++) { 
	           relist.add(list.get(i));
		}

	}
	
	 public Page(List list,int page) {

			allCol = list.size()+1;// 取总记录数
			relist = new ArrayList();

			allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// 获得总页面数

			if (page == 0) {// 判断参数pages是否为空
				pages = 1;

			} else {

				pages = new Integer(page).intValue();

			}

			if (pages > allpage) // 判断当前页
			{

				cpage = 1;

			} else {

				cpage = pages;

			}

			spage = (cpage - 1) * ipage; // 判断起始页
			//获得要显示的list列表
			for (int i = spage-1; i< ipage * cpage-1 && i < list.size(); i++) { 
		           relist.add(list.get(i));
			}
		}
	 
	public Page(List list,String page,int intPage) {
		 allCol = list.size();// 取总记录数
		 relist = new ArrayList();
		allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// 获得总页面数

		if (page == null||page.equals("0")) {// 判断参数pages是否为空
			pages = 1;

		} else {

			pages = new Integer(page).intValue();

		}

		if (pages > allpage) // 判断当前页
		{

			cpage = 1;

		} else {

			cpage = pages;

		}

		
		spage = (cpage - 1) * intPage; // 判断起始页
		//获得要显示的list列表
		for (int i = spage; i< intPage * cpage && i < list.size(); i++) { 
	           relist.add(list.get(i));
		}

	}
	 
	 public Page(List list,int page,int intPage) {

			 allCol = list.size()+1;// 取总记录数
			relist = new ArrayList();
			allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// 获得总页面数

			if (page == 0) {// 判断参数pages是否为空
				pages = 1;

			} else {

				pages = new Integer(page).intValue();

			}

			if (pages > allpage) // 判断当前页
			{

				cpage = 1;

			} else {

				cpage = pages;

			}

			spage = (cpage - 1) * intPage; // 判断起始页
			//获得要显示的list列表
			for (int i = spage; i< intPage* cpage && i < list.size(); i++) { 
		           relist.add(list.get(i));
			}
		}
}

