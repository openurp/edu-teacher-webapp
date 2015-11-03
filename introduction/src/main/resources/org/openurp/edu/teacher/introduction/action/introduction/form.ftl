[@b.head/]
[#assign action][#if introduction.persisted]!update?id=${introduction.id}[#else]!save[/#if][/#assign]
[@b.form action=action theme="list"]
    [@b.textarea name="introduction.contents" rows="30" cols="100" label="内容" value=introduction.contents theme="kind"/]
    [@b.formfoot]
       [#if !introduction.persisted]<input type="hidden" name="introduction.category.id" value="${introduction.category.id}"/>[/#if]
       <input type="hidden" name="_params" value="&introduction.category.id=${introduction.category.id}"/>
       [@b.submit value="action.submit"/]
    [/@]
[/@]

[@b.foot/]