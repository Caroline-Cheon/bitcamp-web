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

@WebServlet("/textbook/update")
public class TextBookUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    
    TextBook textbook = new TextBook();
    textbook.setTitle(request.getParameter("title"));
    textbook.setAuthor(request.getParameter("author"));
    textbook.setPress(request.getParameter("press"));
    textbook.setReleaseDate(Integer.parseInt(request.getParameter("releaseDate")));
    textbook.setLanguage(request.getParameter("language"));
    textbook.setDescription(request.getParameter("description"));
    textbook.setPage(Integer.parseInt(request.getParameter("page")));
    textbook.setPrice(Integer.parseInt(request.getParameter("price")));
    
    response.setHeader("Refresh", "1;url=list");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>교재관리-변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>변경 결과</h1>");
    
    try {
      TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
      
      if (!textbookDao.existTitle(request.getParameter("title"))) {
        throw new Exception("책이름을 찾지 못했습니다.");
      }
      textbookDao.update(textbook);
      out.println("변경하였습니다.");
         
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());  // 위에 익셉션내용이 여기서 에러메세지
    }
      out.println("</body>");
      out.println("</html>");
  }
}
