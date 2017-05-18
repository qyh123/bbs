package com.itstar.bbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itstar.dao.ReplyDao;
import com.itstar.dao.TopicDao;
import com.itstar.util.Utils;

public class EditAndQuoteTopicAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int topicSign = 0;            //��������
		String topicid = "";          //ԭ���ı��
		int opration = 0;             //��ò�������
		String replyid = "";          //��û����ı��
		String content = "";          //������ԭ��������
		String author = "";           //ԭ��������
		String page = "";             //ԭ�����ڵ�ҳ
		Utils utils = new Utils();
		//���������ԭ����
		try {
		
		ReplyDao rdao = new  ReplyDao();
		TopicDao tdao =new TopicDao();
		
		//���parameterֵ
		topicSign =	Integer.parseInt(request.getParameter("topicSign"));
		topicid = request.getParameter("topicid");
		opration = Integer.parseInt(request.getParameter("opration"));
		author = request.getParameter("author");
		page = request.getParameter("pages");
		
		
		
			/*�жϲ�������
			 *1�����Ǳ༭����
			 *0��������ò���
			 */
			if(opration==1) {
				//content = content.replace("<div class='quote'><h5>����:</h5><blockquote> ԭ����","[quote]");
				//content = content.replace("</blockquote></div>","[/quote]");
				
				/*�жϲ������ӵ�����
				 *0����������ǻ��� 1�����������ԭ��
				 */
				if(topicSign==0) {
					//��û������ 
					replyid = request.getParameter("replyid");
					//��� �������� 
					content = rdao.getReply(replyid).getReplyContent();
					//content = utils.replaceCharToDaohao(content);
					content = utils.replaceString(content);
					request.setAttribute("bbsreply",content);
				} else {
					content = tdao.getBBSTopic(topicid).getTopicContent();
					//content = utils.replaceCharToDaohao(content);
					content = utils.replaceString(content);
					request.setAttribute("bbstopic", content);
				}
				
			} else {
				/*�жϲ������ӵ�����
				 *0����������ǻ��� 1�����������ԭ��
				 */
				if(topicSign==0) {
					
					//��û������ 
					replyid = request.getParameter("replyid");
					//��� �������� 
					content = rdao.getReply(replyid).getReplyContent();
					//content = utils.replaceCharToDaohao(content);
					content = utils.replaceString(content);
					content = "[quote]"+request.getParameter("author")+"�� "+rdao.getReply(replyid).getReplyTime()+" ����"+content+"[/quote]";
					request.setAttribute("bbsreply", content);
				} else {
					content = tdao.getBBSTopic(topicid).getTopicContent();
					//content = utils.replaceCharToDaohao(content);
					content = utils.replaceString(content);
					content = "[quote]"+request.getParameter("author")+"�� "+tdao.getBBSTopic(topicid).getTopicTime()+" ����"+content +"[/quote]";
					request.setAttribute("bbstopic", content);
				}
			}
		
		request.setAttribute("topictopic",tdao.getBBSTopic(topicid).getTopicTopic());
		request.setAttribute("page", page);
		request.setAttribute("topicid", topicid);
		request.setAttribute("author", author);
		request.setAttribute("opration", opration);
		request.setAttribute("topicSign",topicSign);
		request.setAttribute("replyid", replyid);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}
}