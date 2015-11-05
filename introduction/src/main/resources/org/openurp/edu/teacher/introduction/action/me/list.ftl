[@b.head/]
[@b.messages/]
[#list introductions as intro]
[@b.a href="!edit?id="+intro.id]修改[/@]
${intro.contents}
[#else]
  您还没有填写该部分。[@b.a href="!editNew?introduction.category.id=" + Parameters['introduction.category.id']]新增[/@]
[/#list]
[@b.foot/]