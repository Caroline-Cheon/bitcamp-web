package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TextBookMysqlDao;
import bitcamp.java89.ems.vo.TextBook;
public class TextBookViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      String title = request.getParameter("title");
      ArrayList<TextBook> list = textbookDao.getListByTitle(title);
      for (TextBook textbook : list) {
        out.println("------------------------------");
        out.printf("책이름: %s\n", textbook.getTitle());
        out.printf("저자: %s\n", textbook.getAuthor());
        out.printf("출판사: %s\n", textbook.getPress());
        out.printf("출판년도: %s\n", textbook.getReleaseDate());
        out.printf("언어: %s\n", textbook.getLanguage());
        out.printf("설명: %s\n", textbook.getDescription());
        out.printf("쪽수: %d\n", textbook.getPage());
        out.printf("가격: %d\n",textbook.getPrice());
      }
         
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
