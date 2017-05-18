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
	 * ��ʾ���Ӽ�����
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

		// ���մ������ĵ�ǰҳ
		int currentPage = 1;

		// ��ҳ����
		Page page = null;

		// �����
		int sectionId = 0;

		try {
			if ((editPostsForm.getPages()) != null
					&& !(editPostsForm.getPages()).equals("")) {
				currentPage = Integer.parseInt(request.getParameter("pages"));
			}

		} catch (Exception e) {
			request.setAttribute("error", "��ʾ���ӳ����쳣<br>" + e);
			return mapping.findForward("error");
		}
		try {
			HttpSession session = request.getSession();
			BBSUser userForm = (BBSUser) session.getAttribute("user");
			BBSSection section = new BBSSection(); // ���
			BBSTopic topic = new BBSTopic(); // ��������

			// ��ȡformֵ
			String topicId = editPostsForm.getTopicid(); // ���µı��
			String author = editPostsForm.getAuthor(); // ���ߵ�����

			TopicDao topicdao =new TopicDao();
			// ��ȡ����������Ϣ
			topic = topicdao.getBBSTopic(topicId);

			// ��ȡ���Id���
			sectionId = topic.getTopicSectionId();

			// ��ȡ�����Ϣ
			SectionDao sectionDao = new SectionDao();
			section = sectionDao.getSection(sectionId);

			if (userForm != null) {
				request.setAttribute("flag", "login");
			}
			session.setAttribute("topicId", topicId);
			session.setAttribute("author", author);
			// ��ȡ�������б���Ϣ �����������ߵ���Ϣ
			List topicList = topicdao.getTopicAndUser(topicId);

			/*
			 * Ϊ�˱�֤ÿҳ��ʾʮ����¼���Ե�һҳ�ļ�¼��һ��ԭ����¼��9��������¼
			 */

			if (currentPage == 1) {

				// ��һҳ��listֵ
				page = new Page(topicList, currentPage, 9);

			} else {

				// ����ҳ��listֵ
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
			request.setAttribute("error", "��ʾ���ӳ����쳣<br>" + e);
			return mapping.findForward("error");
		}
	}

	/**
	 * �޸����� Method editPosts
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
				todao.editTopic(editPostsForm); // �޸�ԭ��
				show(mapping, form, request, response);
			} else {
				redao.editReply(editPostsForm); // �޸Ļ���
				show(mapping, form, request, response);
			}
			return mapping.findForward("show");
		} catch (Exception e) {
			request.setAttribute("error", "�޸����ӳ����쳣<br>" + e);
			return mapping.findForward("error");
		}

	}

	/**
	 * �������� Method bunPosts
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
				todao.bunTopic(editPostsForm.getTopicid()); // ����ԭ��
			} else {
				redao.bunReply(editPostsForm.getReplyid()); // ���λ���
				// show(mapping, form, request, response);
			}
			// out.print(state);
			show(mapping, form, request, response);
			return mapping.findForward("show");
		} catch (Exception e) {
			request.setAttribute("error", "�������ӳ����쳣<br>" + e);
			return mapping.findForward("error");
		}
	}

	/**
	 * �ظ����� Method replyTopic
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
		String username = ""; // �������ߵ�����

		// ��ȡǰһ���ύʱ��
		String time = (String) request.getSession().getAttribute("time");

		// �������ʱ��
		Utils utils = new Utils();
		String now = utils.formatDate(new Date(), "HHmmss");

		try {
			if (bUser != null) {

				// �ж��û��Ƿ��ˮ
				if (time == null
						|| Integer.parseInt(now) - Integer.parseInt(time) > 15) {

					// ���ñ����ύʱ��
					request.getSession().setAttribute("time", now);

					// ���õ�¼��־
					request.setAttribute("flag", "login");

					// ��û�����������
					username = bUser.getUserName();

					// �ж��Ƿ����ظ��ύ
					if (isTokenValid(request, true)) {

						// ����������
						editPostsForm.setReplyAuthor(username);
						ReplyDao redao = new  ReplyDao();
						// ��ӻظ�
						redao.addReply(editPostsForm);

						// ת����������ҳ
						show(mapping, form, request, response);

						saveToken(request);
						return mapping.findForward("show");

					} else {
						request.setAttribute("error", "�ظ��ύ");
						resetToken(request);
						return mapping.findForward("error");
					}
				} else {
					request.setAttribute("message", "guanshui");
					request.setAttribute("url", request.getRequestURL());
					return mapping.findForward("guanshui");
				}

			} else {
				request.setAttribute("error", "����û�е�¼���ȵ�¼");
				return mapping.findForward("error");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "���������쳣<br>" + e);
			return mapping.findForward("error");
		}

	}

	/**
	 * �������� Method AddTopic
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
		String username = ""; // �û���
		int userId = 0; // �û����
		UserDao userdao = new UserDao();

		// ��ȡǰһ���ύʱ��
		String time = (String) request.getSession().getAttribute("time");

		// �������ʱ��
		Utils utils = new Utils();
		String now = utils.formatDate(new Date(), "HHmmss");

		try {
			BBSUser bUser = (BBSUser) request.getSession().getAttribute("user");
			if (bUser != null) {

				// �ж��û��Ƿ��ˮ
				if (time == null
						|| Integer.parseInt(now) - Integer.parseInt(time) > 15) {

					// ���ñ����ύʱ��
					request.getSession().setAttribute("time", now);

					// ���õ�¼��־
					request.setAttribute("flag", "login");

					// ��÷�����������
					username = bUser.getUserName();
					
					// set�û����
					userId = userdao.getUser(username).getUserId();
					post.setUserId(String.valueOf(userId));
					
					// �ж��Ƿ����ظ��ύ
					if (isTokenValid(request, true)) {
						
						// ����û�����
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
						request.setAttribute("error", "�ظ��ύ");
						return mapping.findForward("error");
					}
					// ��ת�������������ڵİ��
				} else {
					request.setAttribute("message", "guanshui");
					request.setAttribute("url", request.getRequestURL());
					return mapping.findForward("guanshui");
				}

			} else {
				request.setAttribute("error", "����û�е�¼���ȵ�¼");
				return mapping.findForward("error");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "���������쳣<br>" + e);
			return mapping.findForward("error");
		}
	}

	/**
	 * ɾ�����ӻ���� Method deletePosts
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
		 * �жϲ������ӵ����� 0����������ǻ��� 1�����������ԭ��
		 */
		if (topicSign == 0) {
			rdao.deleteReplyById(Integer.parseInt(replyId));
			// content = utils.replaceString(content);
			try {
				show(mapping, form, request, response);
				return mapping.findForward("show");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "ɾ�����������쳣<br>" + e);
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
				request.setAttribute("error", "ɾ���������ӳ����쳣<br>" + e);
				return mapping.findForward("error");
			}
			// content = utils.replaceString(content);

		}
		return null;
	}
}
