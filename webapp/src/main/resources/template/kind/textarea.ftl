[#ftl]
<li>[#if tag.label??]<label for="${tag.id}" class="title">[#if (tag.required!"")=="true"]<em class="required">*</em>[/#if]${tag.label}:</label>[/#if]
<div style="margin-left:110px;">
<textarea id="${tag.id}" [#if tag.title??]title="${tag.title}"[/#if] name="${tag.name}" [#if tag.rows??] rows="${tag.rows}"[/#if][#if tag.cols??] cols="${tag.cols}"[/#if] ${tag.parameterString}>${tag.value}</textarea>
[#if tag.comment??]<label class="comment">${tag.comment}</label>[/#if]
</div>
</li>

<script>
   KindEditor.remove('#${tag.id}');
   var edit_${tag.id}=KindEditor.create('#${tag.id}', {
      width:'[#if tag.width??]${tag.width}[#else][#if tag.cols??]${(tag.cols?eval)*7}px[#else]700px[/#if][/#if]',
      afterBlur: function(){this.sync();}
   });
   edit_${tag.id}.focus();
</script>
