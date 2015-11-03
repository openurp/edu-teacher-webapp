package org.openurp.edu.teacher.introduction.action

import org.beangle.commons.lang.Numbers
import org.beangle.data.dao.EntityDao
import org.beangle.data.dao.OqlBuilder
import org.beangle.webmvc.api.action.ActionSupport
import org.beangle.webmvc.api.annotation.action
import org.beangle.webmvc.api.annotation.ignore
import org.beangle.webmvc.api.annotation.mapping
import org.beangle.webmvc.api.annotation.param
import org.openurp.edu.teacher.introduction.model.Category
import org.openurp.hr.base.model.Staff
import org.openurp.hr.info.model.EducationInfo
import org.openurp.hr.info.model.DegreeInfo

@action("")
class IndexAction(entityDao: EntityDao) extends ActionSupport {

  @mapping("{teacherId}")
  def index(@param("teacherId") teacherId: String): String = {
    val teacher = entityDao.get(classOf[Staff], Numbers.toLong(teacherId))
    val categories = entityDao.getAll(classOf[Category])
    val builder_edu = OqlBuilder.from(classOf[EducationInfo],"eduInfo")
    builder_edu.where("eduInfo.staff =:t", teacher)
    builder_edu.where("eduInfo.endOn is null")
    val eduIfo = entityDao.search(builder_edu)  
    
    val builder_degree = OqlBuilder.from(classOf[DegreeInfo],"degreeInfo")
    builder_degree.where("degreeInfo.staff =:t", teacher)
    builder_degree.where("degreeInfo.endOn is null")
    val degreeInfo = entityDao.search(builder_degree)
       
    
    put("teacher", teacher)
    put("categories", categories)
    
    put("eduIfo", eduIfo)
    put("degreeInfo", degreeInfo)
    
    forward()
  }
}