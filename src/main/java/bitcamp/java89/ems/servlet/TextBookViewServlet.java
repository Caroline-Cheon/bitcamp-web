package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.java89.ems.dao.impl.TextBookMysqlDao;
import bitcamp.java89.ems.vo.TextBook;

@WebServlet("/textbook/view") 
public class TextBookViewServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String title = request.getParameter("title");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>교재관리-상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>교재 정보</h1>");
    out.println("<form action='update' method='POST'>");
    
    try {
      TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
      TextBook textbook= textbookDao.getDetail(title);
      
      if (textbook == null) {
        throw new Exception("해당 제목의 교재가 없습니다.");
      }
      
      out.println("<table border='1'>");
      
      out.printf("<tr><th>책이름</th><td> <input name='title' type='text' value='%s' readonly></td></tr>\n", textbook.getTitle());
      out.printf("<tr><th>저자</th><td> <input name='author' type='text' value='%s'></td></tr>\n", textbook.getAuthor());
      out.printf("<tr><th>출판사</th><td> <input name='press' type='text' value='%s'></td></tr>\n", textbook.getPress());
      out.printf("<tr><th>출판년도</th><td> <input name='releaseDate' type='text' value='%d' readonly></td></tr>\n",textbook.getReleaseDate());
      out.printf("<tr><th>언어</th><td> <input name='language' type='text' value='%s'></td></tr>\n", textbook.getLanguage());
      out.printf("<tr><th>설명</th><td> <input name='description' type='text' value='%s'></td></tr>\n", textbook.getDescription());
      out.printf("<tr><th>쪽수</th><td> <input name='page' type='text' value='%d'></td></tr>\n", textbook.getPage());
      out.printf("<tr><th>가격</th><td> <input name='price' type='text' value='%d'></td></tr>\n", textbook.getPrice());
      
      out.println("</table>");
      out.println("<button type='submit'>변경</button>");
      out.printf(" <a href='delete?title=%s'>삭제</a>", textbook.getTitle());
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println(" <a href='list'>목록</a>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
