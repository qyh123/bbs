package com.itstar.bbs.action;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.itstar.dao.SectionDao;
import com.itstar.dao.TopicDao;
import com.itstar.dao.UserDao;
import com.itstar.model.BBSSection;
import com.itstar.model.BBSTopic;
import com.itstar.util.Page;
public class TopicListAction extends DispatchAction {
	/*
	 * Generated Methods
	 */

	/** �鿴��������
	 * Method showTopic
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		// �������·�ҳ����
		int currentPage = 1;  //��ǰҳ
		Page page =null;       //��ҳ��
		
		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		
		BBSSection section = new BBSSection();                //���
		BBSTopic btopic = new BBSTopic();                     //����
		String sectionMaster = "";                            //��������
		int sectionId = 0;                                   //�����
	    
		/*
		 * ��ȡ��ǰҳ
		 */
		try {
			if(request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
			   currentPage = Integer.parseInt(request.getParameter("pages"));
			}
		} catch (Exception e) {
			request.setAttribute("error","��ǰҳת�������ָ�ʽʱ�����쳣<br>"+e);
			return mapping.findForward("error");
		}
		
		//��ȡ�û�Map
		UserDao userdao = new UserDao();
		Map map = userdao.getUserMap();

		//��ð��ı��
		sectionId =Integer.parseInt(request.getParameter("sectionid"));
		btopic.setTopicSectionId(sectionId);
		btopic.setTopicState(0);
		
		//��ð����Ϣ
		
		SectionDao sectionDao = new SectionDao();
		section = sectionDao.getSection(sectionId);
		
		//��ð������
		int sectionMasterID = Integer.parseInt(section.getSectionMasterID()) ;
		
		/*
		 * ��ð�������
		 * */
		sectionMaster =(String)map.get(sectionMasterID);
		if(sectionMaster==null) {
			sectionMaster = (String)map.get(0);
		}
		
		try {
			//��������б�
			TopicDao topicDao  = new TopicDao();
			list = topicDao.getBasicTopic(btopic);
			page = new Page(list,String.valueOf(currentPage));
			
			/*
			 * �ж������Ƿ��ö�
			 */
			boolean isHaveTop = false;
			for(int i = 0;i<Page.relist.size();i++) {
				btopic =(BBSTopic)Page.relist.get(i);
				if(btopic.getTopTime() != null && !btopic.getTopTime().equals("")) {
					isHaveTop = true;
					break;
				}
			}
			request.setAttribute("isHaveTop",isHaveTop);
			request.setAttribute("array", Page.relist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("allPage", Page.allpage);
			request.setAttribute("allRecorders",Page.allCol);
			request.setAttribute("sectionMaster", sectionMaster);
			session.setAttribute("section", section);
			return mapping.findForward("showTopicList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error","�鿴�������ӳ����쳣<br>"+e);
			return mapping.findForward("error");
		}
	
	}
	
	/** �鿴��������
	 * Method showGoodTopic
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showGoodTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// �������·�ҳ����
		 //��ǰҳ
		int currentPage = 1;
		//Page page =null;
		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		BBSTopic btopic = new BBSTopic();
		try {
			if(request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
			   currentPage = Integer.parseInt(request.getParameter("pages"));
			}
		} catch (Exception e) {
			request.setAttribute("error","��ǰҳת�������ָ�ʽʱ�����쳣<br>"+e);
			return mapping.findForward("error");
		}
		btopic.setTopicSectionId(0);
		btopic.setTopicState(1);
		try {
			TopicDao topicDao  = new TopicDao();
			list = topicDao.getBasicTopic(btopic);
			//page = new Page(list,String.valueOf(currentPage));
			new Page(list,String.valueOf(currentPage));
//			boolean isHaveTop = false;
//			for(int i = 0;i<Page.relist.size();i++) {
//				btopic =(BBSTopic)Page.relist.get(i);
//				if(btopic.getTopTime() != null && !btopic.getTopTime().equals("")) {
//					isHaveTop = true;
//					break;
//				}
//			}
//			request.setAttribute("isHaveTop",isHaveTop);
			request.setAttribute("array", Page.relist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("allPage", Page.allpage);
			request.setAttribute("allRecorders",Page.allCol);
			return mapping.findForward("showGoodTopic");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error","�鿴�����������쳣<br>"+e);
			return mapping.findForward("error");
		}
		
	}
	
	
	/** �鿴������
	 * Method showGoodTopic
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showNewTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// �������·�ҳ����
		 //��ǰҳ
		int currentPage = 1;
		//Page page =null;
		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		BBSTopic btopic = new BBSTopic();
		try {
			if(request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
			   currentPage = Integer.parseInt(request.getParameter("pages"));
			}
		} catch (Exception e) {
			request.setAttribute("error","��ǰҳת�������ָ�ʽʱ�����쳣<br>"+e);
			return mapping.findForward("error");
		}
		try {
			TopicDao topicDao  = new TopicDao();
			list = topicDao.getNewTopic(btopic);
			new Page(list,String.valueOf(currentPage));
			request.setAttribute("array", Page.relist);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("allPage", Page.allpage);
			request.setAttribute("allRecorders",Page.allCol);
			return mapping.findForward("showNewTopic");
			//page = new Page(list,String.valueOf(currentPage));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error","�鿴���������쳣<br>"+e);
			return mapping.findForward("error");
		}
		
	}
	
	/** �����ö�
	 * Method topTopic
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward topTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String topicId = "";
		try {
			topicId = request.getParameter("topicId");
			TopicDao topicDao  = new TopicDao();
			topicDao.toptopic(topicId);
			showTopic(mapping,form,request,response);
			return mapping.findForward("showTopicList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error","�ö����ӳ����쳣<br>"+e);
			return mapping.findForward("error");
		}
	}
	
	/** ����ɾ��
	 * Method topTopic
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward deleteTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String topicId = "";
		try {
			topicId = request.getParameter("topicId");
			TopicDao topicDao  = new TopicDao();
			topicDao.deleteTopic(topicId);
			showTopic(mapping,form,request,response);
			return mapping.findForward("showTopicList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error","ɾ�������쳣<br>"+e);
			return mapping.findForward("error");
		}
	}
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stateTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String topicId = request.getParameter("topicId");//ȡ�����Ӻ�
		int resultState=0; //�Ƿ���ʾ��ϸ��Ϣ 0����ʾ 1��ʾ
		TopicDao topicDao  = new TopicDao();
		topicDao.statetopic(topicId);
		if(request.getParameter("resultState")!=null) {
			   resultState = Integer.parseInt(request.getParameter("resultState"));
		}
		request.setAttribute("resultState", resultState);
		showTopic(mapping,form,request,response);
		return mapping.findForward("showTopicList");
	}
}