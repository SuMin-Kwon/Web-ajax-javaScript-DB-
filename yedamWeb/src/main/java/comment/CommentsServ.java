package comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/CommentsServ")
public class CommentsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CommentsServ() {
        super();
    }

    
    //ajax에서 servlet을 호출할 때 GET을 사용했으므로, doGET을 사용
  	//request, response를 파라미터로 사용
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 요청 
		response.setContentType("text/html;charset=utf-8"); // 응답
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter(); //응답 정보를 출력해주는
		String cmd = request.getParameter("cmd"); 	// cmd = insert & id = ?
		
		if (cmd == null) { // 파라미터 값이 아무것도 안 넘어왔을때
			StringBuffer sb = new StringBuffer();
			sb.append("<result>");
			sb.append("<code>error</code>");
			sb.append("<data>");
			sb.append("cmd null");
			sb.append("</data>");
			sb.append("</result>");
			out.println(sb.toString());	// 위에 내용들을 출력해주도록 
			
		} else if (cmd.equals("selectAll")) { 
			// 전체조회
			List<HashMap<String, Object>> list = CommentsDAO.getInstance().selectAll();
			out.print(selectAll(list));
		} else if (cmd.equals("insert")) {
			// 등록
			Comments comment = new Comments();
			comment.setContent(request.getParameter("content"));
			comment.setName(request.getParameter("name"));
			HashMap<String, Object> map = CommentsDAO.getInstance().insert(comment);
			out.println(toXML(map));
		} else if (cmd.equals("update")) {
			// 수정
			Comments comment = new Comments();
			comment.setId(request.getParameter("id"));
			comment.setContent(request.getParameter("content"));
			comment.setName(request.getParameter("name"));
			HashMap<String, Object> map = CommentsDAO.getInstance().update(comment);
			out.println(toXML(map));
		}
	}
	
	private String toXML(HashMap<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("<result>");
		sb.append("<code>");
		sb.append(map.get("code")); // 실패하면 <code>안에 코드값이 들어감
		sb.append("</code>");
		sb.append("<data>");
		Gson gson = new GsonBuilder().create();
		sb.append(gson.toJson(map));
		sb.append("</data>");
		sb.append("</result>");
		
		return sb.toString();
	}

	private String selectAll(List<HashMap<String, Object>> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>success</code>");
		sb.append("<data>");
		sb.append(" [ ");
		for(int i=0; i<list.size(); i++) {
			HashMap<String, Object> map = list.get(i);
			sb.append("{");
			sb.append("id: "+ map.get("id"));
			sb.append(", name:'" + map.get("name"));
			sb.append("', content:'" + map.get("content"));
			sb.append("'}");
			if ( i != list.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		sb.append("</data>");
		sb.append("</result>");
		
		return sb.toString();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
