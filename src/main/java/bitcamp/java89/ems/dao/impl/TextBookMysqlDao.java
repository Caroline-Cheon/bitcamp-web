package bitcamp.java89.ems.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.dao.TextBookDao;
import bitcamp.java89.ems.util.DataSource;
import bitcamp.java89.ems.vo.TextBook;

public class TextBookMysqlDao implements TextBookDao {
  DataSource ds;
  
//Singleton 패턴 - start
  private TextBookMysqlDao() {
   ds = DataSource.getInstance();
  }
 
  static TextBookMysqlDao instance;
 
  public static TextBookMysqlDao getinstance() {
    if (instance == null) {
      instance = new TextBookMysqlDao();
    }
    return instance;
  }
 // Singleton 패턴 - end
 
  public ArrayList<TextBook> getList() throws Exception {
    Connection con = ds.getConnection();
    ArrayList<TextBook> list = new ArrayList<>();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select title, author, press, releaseDate, language, description, page, price from ex_textbooks");
      ResultSet rs = stmt.executeQuery(); ){
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        TextBook textbook = new TextBook(); 
        textbook.setTitle(rs.getString("title"));
        textbook.setAuthor(rs.getString("author"));
        textbook.setPress(rs.getString("press"));
        textbook.setReleaseDate(rs.getInt("releaseDate"));
        textbook.setLanguage(rs.getString("language"));
        textbook.setDescription(rs.getString("description"));
        textbook.setPage(rs.getInt("page"));
        textbook.setPrice(rs.getInt("price"));
        
        list.add(textbook);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  public ArrayList<TextBook> getListByTitle(String title) throws Exception {
    Connection con = ds.getConnection();
    ArrayList<TextBook> list = new ArrayList<>();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select title, author, press, releaseDate, language, description, page, price from ex_textbooks where title=?"); ){
      
      stmt.setString(1, title);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        TextBook textbook = new TextBook(); 
        textbook.setTitle(rs.getString("title"));
        textbook.setAuthor(rs.getString("author"));
        textbook.setPress(rs.getString("press"));
        textbook.setReleaseDate(rs.getInt("releaseDate"));
        textbook.setLanguage(rs.getString("language"));
        textbook.setDescription(rs.getString("description"));
        textbook.setPage(rs.getInt("page"));
        textbook.setPrice(rs.getInt("price"));
        
        list.add(textbook);
      }
      rs.close();
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  public void insert(TextBook textbook) throws Exception {
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into ex_textbooks(title, author, press, releaseDate, language, description, page, price)"
          + "values(?, ?, ?, ?, ?, ?, ?, ?)"); ){
      
      stmt.setString(1, textbook.getTitle());
      stmt.setString(2, textbook.getAuthor());
      stmt.setString(3, textbook.getPress());
      stmt.setInt(4, textbook.getReleaseDate());
      stmt.setString(5, textbook.getLanguage());
      stmt.setString(6, textbook.getDescription());
      stmt.setInt(7, textbook.getPage());
      stmt.setInt(8, textbook.getPrice());
     
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  public void update(TextBook textbook) throws Exception {
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "update ex_textbooks set author=?, press=? , releaseDate=?, language=?, description=?, page=?, price=? where title=?"); ) {
      
      stmt.setString(1, textbook.getAuthor());
      stmt.setString(2, textbook.getPress());
      stmt.setInt(3, textbook.getReleaseDate());
      stmt.setString(4, textbook.getLanguage());
      stmt.setString(5, textbook.getDescription());
      stmt.setInt(6, textbook.getPage());
      stmt.setInt(7, textbook.getPrice());
      stmt.setString(8, textbook.getTitle());
      
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    } 
  }
  
  public void delete(String title) throws Exception {
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from ex_textbooks where title=?"); ) {
      
      stmt.setString(1, title);
      
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    } 
  }
  
  public boolean existTitle(String title) throws Exception {
    Connection con = ds.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select * from ex_textbooks where title=?"); ) {
      
      stmt.setString(1, title);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
}