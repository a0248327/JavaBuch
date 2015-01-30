/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.helloweenvsfei.forum.struts.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.helloweenvsfei.forum.bean.Board;
import com.helloweenvsfei.forum.bean.Person;
import com.helloweenvsfei.forum.bean.Reply;
import com.helloweenvsfei.forum.bean.Thread;
import com.helloweenvsfei.forum.pagination.Pagination;
import com.helloweenvsfei.forum.service.IBoardService;
import com.helloweenvsfei.forum.service.IPersonService;
import com.helloweenvsfei.forum.service.IReplyService;
import com.helloweenvsfei.forum.service.IThreadService;
import com.helloweenvsfei.forum.struts.form.ThreadForm;
import com.helloweenvsfei.forum.struts.util.PersonInfo;
import com.helloweenvsfei.forum.struts.util.PersonUtil;

/**
 * MyEclipse Struts Creation date: 07-04-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/thread" name="threadForm"
 *                input="/form/thread/listThread.jsp" parameter="action"
 *                scope="request" validate="true"
 */
public class ThreadAction extends ForumAction {

	private IThreadService<Thread> threadService;

	private IPersonService<Person> personService;

	private IBoardService<Board> boardService;

	private IReplyService<Reply> replyService;

	@Override
	@SuppressWarnings("all")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		return this.view(mapping, form, request, response);
	}

	@SuppressWarnings("all")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ThreadForm threadForm = (ThreadForm) form;

		Thread thread = threadService.find(Thread.class, threadForm.getThread().getId());

		int totalCount = replyService.getTotalCount(" select count(r) from Reply r " + " where r.deleted = false and r.thread.id = " + threadForm.getThread().getId(), null);

		Pagination pagination = new Pagination(request, response);
		pagination.setRecordCount(totalCount);

		List<Reply> replyList = replyService.list(" from Reply r where r.deleted = false and r.thread.id = " + threadForm.getThread().getId() + " order by r.id asc ",
				pagination.getFirstResult(), pagination.getPageSize(), null);

		threadService.updateHit(thread.getId());

		request.setAttribute("category", thread.getBoard().getCategory());
		request.setAttribute("board", thread.getBoard());
		request.setAttribute("thread", thread);
		request.setAttribute("pagination", pagination);
		request.setAttribute("replyList", replyList);

		threadForm.setTitle("浏览帖子 - 标题：" + thread.getTitle());

		return new ActionForward("list", "/form/thread/viewThread.jsp", false);
	}

	public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ThreadForm threadForm = (ThreadForm) form;

		Board board = boardService.find(Board.class, threadForm.getBoard().getId());

		threadForm.setBoard(board);

		request.setAttribute("board", board);

		threadForm.setTitle("发表帖子 - 版面：" + board.getName());

		return new ActionForward("add", "/form/thread/addThread.jsp", false);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ThreadForm threadForm = (ThreadForm) form;

		Board board = boardService.find(Board.class, threadForm.getBoard().getId());

		PersonInfo personInfo = PersonUtil.getPersonInfo(request, response);
		Person person = personService.find(Person.class, personInfo.getId());

		Thread thread = threadForm.getThread();
		thread.setBoard(board);
		thread.setAuthor(person);
		thread.setAuthorLastReplied(null);
		thread.setDateCreated(new Date());
		thread.setDateLastReplied(new Date());
		thread.setDeleted(false);
		thread.setIpCreated(request.getRemoteAddr());
		thread.setReadonly(false);
		thread.setReplyCount(0);
		thread.setTopped(false);

		threadService.create(thread);

		threadForm.setTitle("发表帖子 - 版面：" + board.getName());

		return this.list(mapping, form, request, response);
	}

	public IBoardService<Board> getBoardService() {
		return boardService;
	}

	public void setBoardService(IBoardService<Board> boardService) {
		this.boardService = boardService;
	}

	public IPersonService<Person> getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService<Person> personService) {
		this.personService = personService;
	}

	public IReplyService<Reply> getReplyService() {
		return replyService;
	}

	public void setReplyService(IReplyService<Reply> replyService) {
		this.replyService = replyService;
	}

	public IThreadService<Thread> getThreadService() {
		return threadService;
	}

	public void setThreadService(IThreadService<Thread> threadService) {
		this.threadService = threadService;
	}

}