package org.openurp.edu.teacher.introduction.model

import org.openurp.hr.base.model.Staff
import org.beangle.data.model.IntId
import org.beangle.data.model.LongId

class Introduction extends LongId {
  
   var teacher: Staff = _
   var category: Category=_
   var contents: String=_
   
  

}