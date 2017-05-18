package com.itstar.util;

import java.util.ArrayList;
import java.util.List;

public class Page {


	public static int ipage = 10; // ��ҳ��λ

	public static int allpage; // ��ҳ��

	public static int pages; // ���ܵ�ҳ�����

	public static int cpage = 1; // ��ǰҳ

	public static int spage = 1; // ��ʼҳ
	
	public static int allCol;   //�ܼ�¼��
	
	public static List relist; //��ʾ�ļ�¼

	public Page(List list,String page) {
		allCol = list.size();// ȡ�ܼ�¼��
		relist = new ArrayList();
		allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// �����ҳ����

		if (page == null||page.equals("0")) {// �жϲ���pages�Ƿ�Ϊ��
			pages = 1;

		} else {

			pages = new Integer(page).intValue();

		}

		if (pages > allpage) // �жϵ�ǰҳ
		{

			cpage = 1;

		} else {

			cpage = pages;

		}

		
		spage = (cpage - 1) * ipage; // �ж���ʼҳ
		//���Ҫ��ʾ��list�б�
		for (int i = spage; i< ipage * cpage && i < list.size(); i++) { 
	           relist.add(list.get(i));
		}

	}
	
	 public Page(List list,int page) {

			allCol = list.size()+1;// ȡ�ܼ�¼��
			relist = new ArrayList();

			allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// �����ҳ����

			if (page == 0) {// �жϲ���pages�Ƿ�Ϊ��
				pages = 1;

			} else {

				pages = new Integer(page).intValue();

			}

			if (pages > allpage) // �жϵ�ǰҳ
			{

				cpage = 1;

			} else {

				cpage = pages;

			}

			spage = (cpage - 1) * ipage; // �ж���ʼҳ
			//���Ҫ��ʾ��list�б�
			for (int i = spage-1; i< ipage * cpage-1 && i < list.size(); i++) { 
		           relist.add(list.get(i));
			}
		}
	 
	public Page(List list,String page,int intPage) {
		 allCol = list.size();// ȡ�ܼ�¼��
		 relist = new ArrayList();
		allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// �����ҳ����

		if (page == null||page.equals("0")) {// �жϲ���pages�Ƿ�Ϊ��
			pages = 1;

		} else {

			pages = new Integer(page).intValue();

		}

		if (pages > allpage) // �жϵ�ǰҳ
		{

			cpage = 1;

		} else {

			cpage = pages;

		}

		
		spage = (cpage - 1) * intPage; // �ж���ʼҳ
		//���Ҫ��ʾ��list�б�
		for (int i = spage; i< intPage * cpage && i < list.size(); i++) { 
	           relist.add(list.get(i));
		}

	}
	 
	 public Page(List list,int page,int intPage) {

			 allCol = list.size()+1;// ȡ�ܼ�¼��
			relist = new ArrayList();
			allpage = (int) Math.ceil((allCol + ipage - 1) / ipage);// �����ҳ����

			if (page == 0) {// �жϲ���pages�Ƿ�Ϊ��
				pages = 1;

			} else {

				pages = new Integer(page).intValue();

			}

			if (pages > allpage) // �жϵ�ǰҳ
			{

				cpage = 1;

			} else {

				cpage = pages;

			}

			spage = (cpage - 1) * intPage; // �ж���ʼҳ
			//���Ҫ��ʾ��list�б�
			for (int i = spage; i< intPage* cpage && i < list.size(); i++) { 
		           relist.add(list.get(i));
			}
		}
}

