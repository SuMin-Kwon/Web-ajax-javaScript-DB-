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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		  String eid = request.getParameter("eid");

	      
	      Employee emp = new Employee(); // updateEmp의 매개값으로 사용.
	      emp.setEmployeeId(Integer.parseInt(eid));

	      
	      EmpDAO dao = new EmpDAO();
	      dao.deleteEmp(emp);
	      
	      PrintWriter out = response.getWriter();
	      Gson gson = new GsonBuilder().create();
		  out.println(gson.toJson(emp));	// json 타입으로 결과반환.
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
