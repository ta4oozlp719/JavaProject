<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%


String str=(new FeedDAO()).getList();
/*
String str = "<table align=center>";
//str += "<tr><th colspan=2>작성글 리스트</th></tr>";
str+="<tr height=40><td><b>작성글 리스트</b></td>";
str+="<td align=right>";
str+="<a href='feedAdd.html'><button>글쓰기</button></a>";
str+="</td></tr>";

for(int i=0; i<feeds.size(); i++) {
	FeedObj feed=feeds.get(i);
	String img=feed.getImages(), imgstr="";
	if(img!=null){
		imgstr="<img src='images/"+img+"'width=240>";
	}
    str += "<tr><td colspan=2><hr></tr>";
    str += "<tr>";
    str += "<td>" + feed.getId() + "</td>";
    str += "<td>&nbsp;" + "(" + feed.getTs() + ")" + "</td>";
    str += "</tr>";
    str +="<tr><td colspan=2>" + imgstr+"</td></tr>";

    str +="<tr><td colspan=2>" + feed.getContent()+"</td></tr>";
}
str += "</table>";
*/
out.print(str);


%>
