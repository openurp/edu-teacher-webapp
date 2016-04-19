[@b.head]
  <link rel="stylesheet" href="${base}/static/kindeditor/themes/default/default.css" />
  <link rel="stylesheet" href="${base}/static/kindeditor/themes/simple/simple.css" />
  <script charset="utf-8" src="${base}/static/kindeditor/kindeditor-all-min.js"></script>
[/@]
<div style="width:80%;margin:auto">
<p>教师基本信息</p>

<table class="infoTable">
	   <tr>
	     <td class="title">姓名:</td>
	     <td class="content">${teacher.person.name.formatedName}</td>
	     <td class="title">性别:</td>
	     <td class="content">${teacher.person.gender.name}</td>
	   </tr>
	   <tr>
	     <td class="title">职称:</td>
	     <td class="content">${(teacher.titleInfo.title.name)!}</td>
	     <td class="title">所在部门:</td>
         <td class="content">${teacher.state.department.name}</td>
	   </tr>
	   <tr>
	     <td class="title">学位:</td>
	     <td class="content">${(eduIfo?first.degree.name)!}</td>
	     <td class="title">学历:</td>
         <td class="content">${(eduIfo?first.educationDegree.name)!}</td>
	   </tr>
</table>

<p>教师简介</p>

[#list categories as category]
   [@b.a href="!search?introduction.category.id="+category.id target="content"]${category.name}[/@]
[/#list]

[@b.div id="content" href="!search?introduction.category.id="+categories?first.id/]
</div>
[@b.foot/]