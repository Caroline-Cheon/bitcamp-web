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
    
    String title = request.getParameter("title");
    
    response.setHeader("Refresh", "1;url=list");  
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>교재관리-삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>삭제 결과</h1>");
    
    try {
      TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
      
      if (!textbookDao.existTitle(request.getParameter("title"))) {
        out.println("책이름을 찾지 못했습니다.");
        return;
      }
      
      textbookDao.delete(request.getParameter("title"));
      out.println("<p>삭제 완료하였습니다.</p>");
         
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());  // 위에 익셉션내용이 여기서 에러메세지
    }
    
    out.println("</body>");
    out.println("</html>");
  }
}
