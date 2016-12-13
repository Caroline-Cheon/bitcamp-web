package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TextBookMysqlDao;
import bitcamp.java89.ems.vo.TextBook;

@WebServlet("/textbook/list")
public class TextBookListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 웹브라우저 쪽으로 출력할 수 있도록 출력 스트림 객체를 얻는다.
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>교재관리-목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>교재 정보</h1>");
      
      try {
        TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
        ArrayList<TextBook> list = textbookDao.getList();
      
      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>책이름</th><th>저자</th><th>출판사</th><th>출판년도</th><th>언어</th><th>설명</th><th>쪽수</th><th>가격</th>");
      out.println("</tr>");
      
      for (TextBook textbook : list) {
        out.println("<tr> ");
        out.printf("  <td><a href='view?title=%s'>%1$s</a></td><td>%s</td><td>%s</td><td>%d</td><td>%s</td><td>%s</td><td>%d</td><td>%d</td>\n",
            textbook.getTitle(),
            textbook.getAuthor(),
            textbook.getPress(),
            textbook.getReleaseDate(),
            textbook.getLanguage(),
            textbook.getDescription(),
            textbook.getPage(),
            textbook.getPrice());
        out.println("</tr>");
      }
      
      out.println("</table>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
      out.println("</body>");
      out.println("</html>");
  }
}
