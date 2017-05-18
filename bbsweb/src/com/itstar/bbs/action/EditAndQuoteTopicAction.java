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
		int topicSign = 0;            //帖子类型
		String topicid = "";          //原帖的编号
		int opration = 0;             //获得操作类型
		String replyid = "";          //获得回帖的编号
		String content = "";          //回帖或原帖的内容
		String author = "";           //原帖的作者
		String page = "";             //原帖所在的页
		Utils utils = new Utils();
		//定义回帖与原帖类
		try {
		
		ReplyDao rdao = new  ReplyDao();
		TopicDao tdao =new TopicDao();
		
		//获得parameter值
		topicSign =	Integer.parseInt(request.getParameter("topicSign"));
		topicid = request.getParameter("topicid");
		opration = Integer.parseInt(request.getParameter("opration"));
		author = request.getParameter("author");
		page = request.getParameter("pages");
		
		
		
			/*判断操作类型
			 *1代表是编辑操作
			 *0代表的引用操作
			 */
			if(opration==1) {
				//content = content.replace("<div class='quote'><h5>引用:</h5><blockquote> 原帖由","[quote]");
				//content = content.replace("</blockquote></div>","[/quote]");
				
				/*判断操作帖子的类型
				 *0代表操作的是回帖 1代表操作的是原帖
				 */
				if(topicSign==0) {
					//获得回帖编号 
					replyid = request.getParameter("replyid");
					//获得 回帖内容 
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
				/*判断操作帖子的类型
				 *0代表操作的是回帖 1代表操作的是原帖
				 */
				if(topicSign==0) {
					
					//获得回帖编号 
					replyid = request.getParameter("replyid");
					//获得 回帖内容 
					content = rdao.getReply(replyid).getReplyContent();
					//content = utils.replaceCharToDaohao(content);
					content = utils.replaceString(content);
					content = "[quote]"+request.getParameter("author")+"于 "+rdao.getReply(replyid).getReplyTime()+" 发表"+content+"[/quote]";
					request.setAttribute("bbsreply", content);
				} else {
					content = tdao.getBBSTopic(topicid).getTopicContent();
					//content = utils.replaceCharToDaohao(content);
					content = utils.replaceString(content);
					content = "[quote]"+request.getParameter("author")+"于 "+tdao.getBBSTopic(topicid).getTopicTime()+" 发表"+content +"[/quote]";
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