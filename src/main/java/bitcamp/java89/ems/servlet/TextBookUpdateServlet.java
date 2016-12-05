package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TextBookMysqlDao;
import bitcamp.java89.ems.vo.TextBook;

// 톰캣 서버가 실행할 수 있는 클래스는 반드시 Servlet 규격에 맞추어 제작해야 한다.
// 그러나 Servlet 인터페이스의 메서드가 많아서 구현하기 번거롭다.
// 그래서 AbstractServlet이라는 추상 클래스를 만들어서,
// 이 클래스를 상속 받아 간접적으로 Servlet인터페이스를 구현하는 방식을 취한다.
// 이 클래스를 상속받게 되면 오직 service() 메서드만 만들면 되기 때문에 코드가 편리하다.
@WebServlet("/textbook/update")  // 이 명령을 받으면 service를 호출하라
public class TextBookUpdateServlet extends AbstractServlet {
  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TextBookMysqlDao textbookDao = TextBookMysqlDao.getinstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!textbookDao.existTitle(request.getParameter("title"))) {
        out.println("책이름을 찾지 못했습니다.");
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
      textbookDao.update(textbook);
      out.println("변경하였습니다.");
         
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
