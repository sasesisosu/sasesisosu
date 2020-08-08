<!-- 상품 등록에 대한 기능들
	이미지 파일과 txt파일을 올리기 위해 
	구글링하여 참고 하였음 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="product.productDAO.ProductDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.io.*" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%

		String dir = application.getRealPath("/images");
		int maxSize = 1024*1024*100;
		String encoding = "UTF-8";
		MultipartRequest multi = new MultipartRequest(request, dir, maxSize, encoding, new DefaultFileRenamePolicy());		
		
		String product_title = multi.getParameter("product_title");
		String product_writer = multi.getParameter("product_writer");
		String product_publisher = multi.getParameter("product_publisher");
		String product_price = multi.getParameter("product_price");
		String product_genre = multi.getParameter("product_genre");		
		String product_category = multi.getParameter("product_category"); 
		String product_content = multi.getParameter("product_content");		
		
		Enumeration fileNames = multi.getFileNames();
		int i=0;
		String[] fileName = new String[10];
		while(fileNames.hasMoreElements()){
			String param = (String)fileNames.nextElement();
			fileName[i] = multi.getOriginalFileName(param);
			String fileRealName = multi.getFilesystemName(param);
			if(fileName == null) continue;
			i++;
		}
		i=0;

		String imgName = fileName[0];
		String txtName = fileName[1];

		File file = new File("images/"+txtName);
		String article = "";
		BufferedReader reader = null;
		try{
			String filePath = application.getRealPath("images/"+txtName);
			reader = new BufferedReader(new FileReader(filePath));
			while(true){
				String str = reader.readLine();				
				if(str == null) break;
				article += str;
			}
		}catch(FileNotFoundException e){
			out.write("파일이 없음");
		}catch(IOException e){
			out.write("파일을 읽을 수 없음");
		}finally{
			try{
				reader.close();
			}catch(Exception e){}
		}

		PrintWriter script = response.getWriter();	
		if(product_title.equals("") || product_writer.equals("") || product_publisher.equals("") ||
				product_price.equals("") || product_content.equals("") || imgName == null || txtName == null){
			script.println("<script>");
			script.println("alert('모든 사항을 채워 주세요')");
			script.println("history.back()");
			script.println("</script>");
		}else{
			new ProductDAO().productRegister(product_title, product_price, product_genre, product_category, product_writer, product_publisher, product_content, imgName, txtName);
			
			script.println("<script>");
			script.println("alert('상품 등록 완료')");
			script.println("location.href='upload.jsp'");
			script.println("</script>");
		}
		
	%>
</body>
</html>





















