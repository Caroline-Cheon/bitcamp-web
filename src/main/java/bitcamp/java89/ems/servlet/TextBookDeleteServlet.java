package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TextBookMysqlDao;

@WebServlet("/textbook/delete")  // 이 명령을 받으면 service를 호출하라
public class TextBookDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!textbookDao.existTitle(request.getParameter("title"))) {
        out.println("책이름을 찾지 못했습니다.");
        return;
      }
      
      textbookDao.delete(request.getParameter("title"));
      out.println("해당 데이터 삭제 완료하였습니다.");
         
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
