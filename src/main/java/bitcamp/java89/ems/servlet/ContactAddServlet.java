package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
import bitcamp.java89.ems.vo.Contact;

@WebServlet("/contact/add")
public class ContactAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8"); // method=post 요청일 때는 톰캣이 처리를 안해줌
    
    Contact contact = new Contact();
    contact.setName(request.getParameter("name"));
    contact.setPosition(request.getParameter("position"));
    contact.setTel(request.getParameter("tel"));
    contact.setEmail(request.getParameter("email"));
    
    response.setHeader("Refresh", "1;url=list");   //메세지를 출력해야하니까 리프레시 리다이렉트는 메세지출력 안됨
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>연락처관리-등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>등록 결과</h1>");
    
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getinstance();
      
      if (contactDao.existEmail(contact.getEmail())) {
        throw new Exception("같은 이메일이 존재합니다. 등록을 취소합니다.");
      }
      
      contactDao.insert(contact);
      out.println("<p>등록하였습니다.</p>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());  // 위에 익셉션내용이 여기서 에러메세지
    }
    out.println("</body>");
    out.println("</html>");
  }
}
