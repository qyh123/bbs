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

	/** 查看所有帖子
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
		
		// 定义如下分页变量
		int currentPage = 1;  //当前页
		Page page =null;       //分页类
		
		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		
		BBSSection section = new BBSSection();                //版块
		BBSTopic btopic = new BBSTopic();                     //帖子
		String sectionMaster = "";                            //版主姓名
		int sectionId = 0;                                   //版块编号
	    
		/*
		 * 获取当前页
		 */
		try {
			if(request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
			   currentPage = Integer.parseInt(request.getParameter("pages"));
			}
		} catch (Exception e) {
			request.setAttribute("error","当前页转换成数字格式时出现异常<br>"+e);
			return mapping.findForward("error");
		}
		
		//获取用户Map
		UserDao userdao = new UserDao();
		Map map = userdao.getUserMap();

		//获得版块的编号
		sectionId =Integer.parseInt(request.getParameter("sectionid"));
		btopic.setTopicSectionId(sectionId);
		btopic.setTopicState(0);
		
		//获得版块信息
		
		SectionDao sectionDao = new SectionDao();
		section = sectionDao.getSection(sectionId);
		
		//获得版主编号
		int sectionMasterID = Integer.parseInt(section.getSectionMasterID()) ;
		
		/*
		 * 获得版主姓名
		 * */
		sectionMaster =(String)map.get(sectionMasterID);
		if(sectionMaster==null) {
			sectionMaster = (String)map.get(0);
		}
		
		try {
			//获得帖子列表
			TopicDao topicDao  = new TopicDao();
			list = topicDao.getBasicTopic(btopic);
			page = new Page(list,String.valueOf(currentPage));
			
			/*
			 * 判断帖子是否置顶
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
			request.setAttribute("error","查看所有帖子出现异常<br>"+e);
			return mapping.findForward("error");
		}
	
	}
	
	/** 查看精华帖子
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
		// 定义如下分页变量
		 //当前页
		int currentPage = 1;
		//Page page =null;
		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		BBSTopic btopic = new BBSTopic();
		try {
			if(request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
			   currentPage = Integer.parseInt(request.getParameter("pages"));
			}
		} catch (Exception e) {
			request.setAttribute("error","当前页转换成数字格式时出现异常<br>"+e);
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
			request.setAttribute("error","查看精华帖出现异常<br>"+e);
			return mapping.findForward("error");
		}
		
	}
	
	
	/** 查看新帖子
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
		// 定义如下分页变量
		 //当前页
		int currentPage = 1;
		//Page page =null;
		ArrayList<BBSTopic> list = new ArrayList<BBSTopic>();
		BBSTopic btopic = new BBSTopic();
		try {
			if(request.getParameter("pages") != null && !request.getParameter("pages").equals("")) {
			   currentPage = Integer.parseInt(request.getParameter("pages"));
			}
		} catch (Exception e) {
			request.setAttribute("error","当前页转换成数字格式时出现异常<br>"+e);
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
			request.setAttribute("error","查看新帖出现异常<br>"+e);
			return mapping.findForward("error");
		}
		
	}
	
	/** 帖子置顶
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
			request.setAttribute("error","置顶帖子出现异常<br>"+e);
			return mapping.findForward("error");
		}
	}
	
	/** 帖子删除
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
			request.setAttribute("error","删帖出现异常<br>"+e);
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 精华主题帖子
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
		String topicId = request.getParameter("topicId");//取得帖子号
		int resultState=0; //是否显示详细信息 0不显示 1显示
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