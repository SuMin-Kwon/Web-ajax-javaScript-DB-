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

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ModifyServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		  // 입력요청된 정보를 읽어옴.
		  String eid = request.getParameter("eid");
		  String ln = request.getParameter("last_name");
		  String em = request.getParameter("email");
	      String hd = request.getParameter("hire_date");
	      String fn = request.getParameter("first_name");
	      
	      Employee emp = new Employee(); // updateEmp의 매개값으로 사용.
	      emp.setEmployeeId(Integer.parseInt(eid));
	      emp.setLastName(ln);
	      emp.setEmail(em);
	      emp.setHireDate(hd);
	      emp.setFirstName(fn);
	      
	      EmpDAO dao = new EmpDAO();
	      dao.updateEmp(emp);
	      
	      PrintWriter out = response.getWriter();
	      Gson gson = new GsonBuilder().create();
		  out.println(gson.toJson(emp));	// json 타입으로 결과반환.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
