package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class EmplistServlet
 */
@WebServlet("/EmplistServlet")		// 이 페이지의 주소
public class EmplistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		//out.println("{ \"id\" : \"1\",\"first_name\" :\"Hong\", \"last_name\" : \"kildong\"}"); // { "id" : "1","first_name" :"Hong", "last_name" : "kildong"}
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		Gson gson = new GsonBuilder().create();
		out.println(gson.toJson(list)); // string 타입으로 json을 만들어줌
		
//		out.println("[");
//		int cnt = 1;
//		for(Employee emp: list ) {
//			out.println("{\"id\":" + emp.getEmployeeId() 
//            + ",\"first_name\":\"" + emp.getFirstName()
//            + "\",\"email\":\"" + emp.getEmail()
//            + "\",\"hire_date\":\"" + emp.getHireDate()
//            + "\",\"last_name\":\"" + emp.getLastName() + "\"}");
//			if(cnt++ != list.size()) {
//				out.println(",");
//			}
//		}
//		out.println("]");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
