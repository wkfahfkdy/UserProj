package userProj;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/getUserListServlet")
public class GetUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetUserListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		JSONArray ary = new JSONArray();
		UserDAO dao = new UserDAO();
		List<UserVO> list = dao.getUserList();
		
		for(UserVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", vo.getId());
			obj.put("name", vo.getName());
			obj.put("phone", vo.getPhone());
			obj.put("gender", vo.getGender());
			obj.put("pass", vo.getPass());
			ary.add(obj);
		}
		
		response.getWriter().print(ary);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
