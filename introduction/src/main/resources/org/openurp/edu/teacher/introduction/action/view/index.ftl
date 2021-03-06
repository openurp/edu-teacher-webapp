[@b.head/]
<div style="width:80%;margin:auto">
<p>教师基本信息</p>

<table class="infoTable">
	   <tr>
	     <td class="title">姓名:</td>
	     <td class="content">${(teacher.person.name.formatedName)!}</td>
	     <td class="title">性别:</td>
	     <td class="content">${(teacher.person.gender.name)!}</td>
	   </tr>
	   <tr>
	     <td class="title">职称:</td>
	     <td class="content">${(teacher.titleInfo.title.name)!}</td>
	     <td class="title">所在部门:</td>
         <td class="content">${(teacher.state.department.name)!}</td>
	   </tr>
	   <tr>
	     <td class="title">学位:</td>
	     <td class="content">${(degreeInfo?first.degree.name)!}</td>
	     <td class="title">学历:</td>
         <td class="content">${(eduIfo?first.educationDegree.name)!}</td>
	   </tr>
</table>

<p>教师简介</p>

[@b.grid items=intros var="intro" sortable="false"]
  [@b.row]
    [@b.col title="简介分类" width="15%"]${intro.category.name}[/@]
    [@b.col title="内容" width="85%"]${intro.contents}[/@]
  [/@]
[/@]

</div>
[@b.foot/]