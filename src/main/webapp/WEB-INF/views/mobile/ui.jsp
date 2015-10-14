<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>frozen</title>

</head>
<body>
 
<div data-role="page" id="pageone">
  <div data-role="header" data-theme='a'>
   <a href="#anylink" data-role="button" data-icon="search">Search</a>
　 <h3>Edit Contact</h3>
　 <a href="index.html" data-icon="check">Save</a>
  </div>

  <div data-role="content">
    <p>Welcome!</p>
    <a href="http://www.baidu.com" data-rel="dialog">Go to Page Two</a>
    <form>
　 <label for="slider-0">Input slider:</label>
　 <input type="range" name="slider" id="slider-0" value="25" min="0" max="100" />
</form>
<a href="#" data-role="button" data-icon="star">Star button</a> 
    <ul data-role="listview" data-inset="true" data-filter="true">
	<li><a href="#">Acura</a></li>
	<li><a href="#">Audi</a></li>
	<li><a href="#">BMW</a></li>
	<li><a href="#">Cadillac</a></li>
	<li><a href="#">Ferrari</a></li>
</ul>
  </div>

  <div data-role="footer">
    <h1>Footer Text</h1>
  </div>
</div> 

<div data-role="page" id="pagetwo">
  <div data-role="header">
    <h1>I'm A Dialog Box!</h1>
  </div>

  <div data-role="content">
    <p>The dialog box is different from a normal page, it is displayed on top of the current page with a dark background color. It will not span the entire width of the page. The dialog has an icon of "X" in the header to close the box.</p>
    <a href="#pageone">Go to Page One</a>
  </div>

  <div data-role="footer">
    <h1>Footer Text</h1>
  </div>
</div>
<script type="text/javascript">
$.mobile.loading( 'show', {
	text: 'foo',
	textVisible: true,
	theme: 'z',
	html: "<h1>hello</h1>"
	});
</script>
</body>
</html>