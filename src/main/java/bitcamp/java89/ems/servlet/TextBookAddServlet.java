package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TextBookMysqlDao;
import bitcamp.java89.ems.vo.TextBook;

@WebServlet("/textbook/add")  // 이 명령을 받으면 service를 호출하라
public class TextBookAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (textbookDao.existTitle(request.getParameter("title"))) {
        out.println("같은 책이름이 존재합니다. 등록을 취소합니다.");
        return;
      }
      
      TextBook textbook = new TextBook();
      textbook.setTitle(request.getParameter("title"));
      textbook.setAuthor(request.getParameter("author"));
      textbook.setPress(request.getParameter("press"));
      textbook.setReleaseDate(Integer.parseInt(request.getParameter("releaseDate")));
      textbook.setLanguage(request.getParameter("language"));
      textbook.setDescription(request.getParameter("description"));
      textbook.setPage(Integer.parseInt(request.getParameter("page")));
      textbook.setPrice(Integer.parseInt(request.getParameter("price")));
      
      textbookDao.insert(textbook);
      out.println("등록하였습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
