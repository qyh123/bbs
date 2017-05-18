package com.itstar.bbs.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.itstar.bbs.form.EditPostsForm;
import com.itstar.dao.ReplyDao;
import com.itstar.dao.SectionDao;
import com.itstar.dao.TopicDao;
import com.itstar.dao.UserDao;
import com.itstar.model.BBSSection;
import com.itstar.model.BBSTopic;
import com.itstar.model.BBSUser;
import com.itstar.util.Page;
import com.itstar.util.Utils;

public class PostsAction extends DispatchAction {

	/**
	 * 显示帖子及回帖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EditPostsForm editPostsForm = (EditPostsForm) form;// TODO

		// 接收传过来的当前页
		int currentPage = 1;

		// 分页对象
		Page page = null;

		// 版块编号
		int sectionId = 0;

		try {
			if ((editPostsForm.getPages()) != null
					&& !(editPostsForm.getPages()).equals("")) {
				currentPage = Integer.parseInt(request.getParameter("pages"));
			}

		} catch (Exception e) {
			request.setAttribute("error", "显示帖子出现异常<br>" + e);
			return mapping.findForward("error");
		}
		try {
			HttpSession session = request.getSession();
			BBSUser userForm = (BBSUser) session.getAttribute("user");
			BBSSection section = new BBSSection(); // 版块
			BBSTopic topic = new BBSTopic(); // 主题帖子

			// 获取form值
			String topicId = editPostsForm.getTopicid(); // 文章的编号
			String author = editPostsForm.getAuthor(); // 作者的名字

			TopicDao topicdao =new TopicDao();
			// 获取主题帖子信息
			topic = topicdao.getBBSTopic(topicId);

			// 获取版块Id编号
			sectionId = topic.getTopicSectionId();

			// 获取版块信息
			SectionDao sectionDao = new SectionDao();
			section = sectionDao.getSection(sectionId);

			if (userForm != null) {
				request.setAttribute("flag", "login");
			}
			session.setAttribute("topicId", topicId);
			session.setAttribute("author", author);
			// 获取回帖的列表信息 包括回帖作者的信息
			List topicList = topicdao.getTopicAndUser(topicId);

			/*
			 * 为了保证每页显示十条记录所以第一页的记录是一条原帖记录加9条回帖记录
			 */

			if (currentPage == 1) {

				// 第一页的list值
				page = new Page(topicList, currentPage, 9);

			} else {

				// 其它页的list值
				page = new Page(topicList, currentPage);

			}
			saveToken(request);
			
			UserDao userdao = new UserDao();
			BBSUser bUser = userdao.getUser(author);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("allPage", page.allpage);
			request.setAttribute("allRecorders", page.allCol);
			request.setAttribute("startPage", page.spage);
			request.setAttribute("bTopic", topic);
			request.setAttribute("section", section);
			request.setAttribute("topicList", page.relist);
			request.setAttribute("bUser", bUser);
			return mapping.findForward("show");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "显示帖子出现异常<br>" + e);
			return mapping.findForward("error");
		}
	}

	/**
	 * 修改帖子 Method editPosts
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward editPosts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EditPostsForm editPostsForm = (EditPostsForm) form;// TODO
		// Auto-generated
		// method stub
		TopicDao todao =new TopicDao();
		ReplyDao redao = new  ReplyDao();
		boolean bl = false;
		try {

			if (editPostsForm.getTopicSign().equals("1")) {
				todao.editTopic(editPostsForm); // 修改原帖
				show(mapping, form, request, response);
			} else {
				redao.editReply(editPostsForm); // 修改回帖
				show(mapping, form, request, response);
			}
			return mapping.findForward("show");
		} catch (Exception e) {
			request.setAttribute("error", "修改帖子出现异常<br>" + e);
			return mapping.findForward("error");
		}

	}

	/**
	 * 屏蔽帖子 Method bunPosts
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward bunPosts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EditPostsForm editPostsForm = (EditPostsForm) form;// TODO
		// Auto-generated
		// method stub
		TopicDao todao =new TopicDao();
		ReplyDao redao = new  ReplyDao();
		try {
			// PrintWriter out = response.getWriter();
			// int state = 0;

			if (editPostsForm.getTopicSign().equals("1")) {
				todao.bunTopic(editPostsForm.getTopicid()); // 屏蔽原帖
			} else {
				redao.bunReply(editPostsForm.getReplyid()); // 屏蔽回帖
				// show(mapping, form, request, response);
			}
			// out.print(state);
			show(mapping, form, request, response);
			return mapping.findForward("show");
		} catch (Exception e) {
			request.setAttribute("error", "屏蔽帖子出现异常<br>" + e);
			return mapping.findForward("error");
		}
	}

	/**
	 * 回复帖子 Method replyTopic
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward replyTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EditPostsForm editPostsForm = (EditPostsForm) form;// TODO
		BBSUser bUser = (BBSUser) request.getSession().getAttribute("user");
		String username = ""; // 回帖作者的名字

		// 获取前一次提交时间
		String time = (String) request.getSession().getAttribute("time");

		// 获得现在时间
		Utils utils = new Utils();
		String now = utils.formatDate(new Date(), "HHmmss");

		try {
			if (bUser != null) {

				// 判断用户是否灌水
				if (time == null
						|| Integer.parseInt(now) - Integer.parseInt(time) > 15) {

					// 设置本次提交时间
					request.getSession().setAttribute("time", now);

					// 设置登录标志
					request.setAttribute("flag", "login");

					// 获得回帖作者姓名
					username = bUser.getUserName();

					// 判断是否是重复提交
					if (isTokenValid(request, true)) {

						// 回帖的作者
						editPostsForm.setReplyAuthor(username);
						ReplyDao redao = new  ReplyDao();
						// 添加回复
						redao.addReply(editPostsForm);

						// 转到主题帖子页
						show(mapping, form, request, response);

						saveToken(request);
						return mapping.findForward("show");

					} else {
						request.setAttribute("error", "重复提交");
						resetToken(request);
						return mapping.findForward("error");
					}
				} else {
					request.setAttribute("message", "guanshui");
					request.setAttribute("url", request.getRequestURL());
					return mapping.findForward("guanshui");
				}

			} else {
				request.setAttribute("error", "您还没有登录请先登录");
				return mapping.findForward("error");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "回帖出现异常<br>" + e);
			return mapping.findForward("error");
		}

	}

	/**
	 * 发新帖子 Method AddTopic
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EditPostsForm post = (EditPostsForm) form;
		String username = ""; // 用户名
		int userId = 0; // 用户编号
		UserDao userdao = new UserDao();

		// 获取前一次提交时间
		String time = (String) request.getSession().getAttribute("time");

		// 获得现在时间
		Utils utils = new Utils();
		String now = utils.formatDate(new Date(), "HHmmss");

		try {
			BBSUser bUser = (BBSUser) request.getSession().getAttribute("user");
			if (bUser != null) {

				// 判断用户是否灌水
				if (time == null
						|| Integer.parseInt(now) - Integer.parseInt(time) > 15) {

					// 设置本次提交时间
					request.getSession().setAttribute("time", now);

					// 设置登录标志
					request.setAttribute("flag", "login");

					// 获得发帖作者姓名
					username = bUser.getUserName();
					
					// set用户编号
					userId = userdao.getUser(username).getUserId();
					post.setUserId(String.valueOf(userId));
					
					// 判断是否是重复提交
					if (isTokenValid(request, true)) {
						
						// 添加用户帖子
						TopicDao todao =new TopicDao();
						int topicId = todao.addTopic(post);
						if (topicId != 0) {
							post.setAuthor(username);
							post.setTopicid(String.valueOf(topicId));
							show(mapping, form, request, response);
							saveToken(request);
							return mapping.findForward("show");
						} else {
							return mapping.findForward("error");
						}
					} else {
						resetToken(request);
						request.setAttribute("error", "重复提交");
						return mapping.findForward("error");
					}
					// 调转到主题帖子所在的版块
				} else {
					request.setAttribute("message", "guanshui");
					request.setAttribute("url", request.getRequestURL());
					return mapping.findForward("guanshui");
				}

			} else {
				request.setAttribute("error", "您还没有登录请先登录");
				return mapping.findForward("error");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "发帖出现异常<br>" + e);
			return mapping.findForward("error");
		}
	}

	/**
	 * 删除帖子或回帖 Method deletePosts
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward deleteTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EditPostsForm editPostsForm = (EditPostsForm) form;// TODO
		TopicDao tdao =new TopicDao();
		ReplyDao rdao = new  ReplyDao();
		int topicSign = Integer.parseInt(editPostsForm.getTopicSign());
		String replyId = editPostsForm.getReplyid();
		String topicId = editPostsForm.getTopicid();
		String sectionId = editPostsForm.getSectionId();

		/*
		 * 判断操作帖子的类型 0代表操作的是回帖 1代表操作的是原帖
		 */
		if (topicSign == 0) {
			rdao.deleteReplyById(Integer.parseInt(replyId));
			// content = utils.replaceString(content);
			try {
				show(mapping, form, request, response);
				return mapping.findForward("show");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "删除回帖出现异常<br>" + e);
				return mapping.findForward("error");
			}
		} else {
			tdao.deleteTopic(topicId);
			try {
				response
						.sendRedirect("showTopicList.do?method=showTopic&sectionid="
								+ sectionId);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "删除主题帖子出现异常<br>" + e);
				return mapping.findForward("error");
			}
			// content = utils.replaceString(content);

		}
		return null;
	}
}
