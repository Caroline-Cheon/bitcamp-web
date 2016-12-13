package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
@WebServlet("/contact/delete")  // 이 명령을 받으면 service를 호출하라
public class ContactDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String email = request.getParameter("email");
    
    response.setHeader("Refresh", "1;url=list");   //메세지를 출력해야하니까 리프레시 리다이렉트는 메세지출력 안됨
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>연락처관리-삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>삭제 결과</h1>");
    
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getinstance();
      
      if (!contactDao.existEmail(request.getParameter("email"))) {
        throw new Exception("이메일을 찾지 못했습니다.");
      }
      
      contactDao.delete(request.getParameter("email"));
      out.println("<p>삭제 완료하였습니다.</p>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());  // 위에 익셉션내용이 여기서 에러메세지
    }
    
    out.println("</body>");
    out.println("</html>");
  }
}
