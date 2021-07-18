/**
 * ajax. js : Asynchronous JavaScript And Xml (아작스 : 비동기)
	동기방식 : 순차적으로 결과값을 받아오고 결과를 받은 다음 다음으로 넘어감 (시간이 더 오래 걸림)
	비동기란 : 순차적으로 프로그램은 일단 전부 진행한 다음 결과값은 2초있다가 받아오고 2초있다가 결과를 반영?
	XML : 인터넷에서 데이터를 받아올때의 기준 
 */

function ajaxFnc() {
	let xhtp = new XMLHttpRequest();
	xhtp.open("get", "EmplistServlet"); // "http://localhost/yedamWeb/EmplistServlet"
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			let date = JSON.parse(xhtp.responseText);
			console.log(date);
		}
		//		console.log(xhtp.readyState, xhtp.status);
		//		console.log("2")
	}
}
ajaxFnc();