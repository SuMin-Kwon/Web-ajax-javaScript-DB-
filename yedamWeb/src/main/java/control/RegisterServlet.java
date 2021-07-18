package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.EmpDAO;
import common.Employee;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }  
    
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String eid = request.getParameter("eid");
      String ln = request.getParameter("last_name");
      String em = request.getParameter("email");
      String hd = request.getParameter("hire_date");
      String fn = request.getParameter("first_name");
      
      EmpDAO dao = new EmpDAO();
      Employee emp = new Employee();
      emp.setEmployeeId(Integer.parseInt(eid));
      emp.setLastName(ln);
      emp.setEmail(em);
      emp.setHireDate(hd);
      emp.setFirstName(fn);
      
      dao.insertEmp(emp);
      
      PrintWriter out = response.getWriter();
      Gson gson = new GsonBuilder().create();
	  out.println(gson.toJson(emp));
	  
//      out.println("{\"id\":" + emp.getEmployeeId() 
//      			+ ",\"first_name\":\"" + emp.getFirstName()
//      			+ "\",\"email\":\"" + emp.getEmail()
//      			+ "\",\"hire_date\":\"" + emp.getHireDate()
//      			+ "\",\"last_name\":\"" + emp.getLastName() + "\"}");
//      dao.insertEmp(emp);
      //System.out.println(eid + ", " + ln + ", " + em + ", " + hd + ", " + fn);
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
