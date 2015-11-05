package org.openurp.edu.teacher.introduction.action

import org.beangle.webmvc.api.annotation.mapping
import org.beangle.webmvc.api.annotation.param
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.teacher.introduction.model.Introduction
import org.beangle.data.dao.OqlBuilder
import org.beangle.commons.collection.page.PageLimit
import org.openurp.edu.base.model.Course
import java.util.Locale
import org.beangle.commons.lang.Numbers
import org.beangle.commons.collection.Order
import org.openurp.hr.base.model.Staff
import org.beangle.webmvc.api.annotation.ignore
import org.beangle.webmvc.api.view.View
import org.openurp.platform.api.security.Securities
import org.openurp.hr.info.model.EducationInfo
import org.openurp.hr.info.model.DegreeInfo
import org.openurp.edu.teacher.introduction.model.Category
/**
 * @author xinzhou
 */
class MeAction extends RestfulAction[Introduction] {

  override def index(): String = {
    val teacher = getStaff
    val categories = entityDao.getAll(classOf[Category])
    val builder_edu = OqlBuilder.from(classOf[EducationInfo], "eduInfo")
    builder_edu.where("eduInfo.staff =:t", teacher)
    builder_edu.where("eduInfo.endOn is null")
    val eduIfo = entityDao.search(builder_edu)

    val builder_degree = OqlBuilder.from(classOf[DegreeInfo], "degreeInfo")
    builder_degree.where("degreeInfo.staff =:t", teacher)
    builder_degree.where("degreeInfo.endOn is null")
    val degreeInfo = entityDao.search(builder_degree)

    put("teacher", teacher)
    put("categories", categories)

    put("eduIfo", eduIfo)
    put("degreeInfo", degreeInfo)

    forward()
  }
  
 
  private def getStaff(): Staff = {
    val staffs = entityDao.search(OqlBuilder.from(classOf[Staff], "s").where("s.code=:code", Securities.user))
    if (staffs.isEmpty) {
      throw new RuntimeException("Cannot find staff with code " + Securities.user)
    } else {
      staffs.head
    }
  }

  protected override def getQueryBuilder(): OqlBuilder[Introduction] = {
    val builder: OqlBuilder[Introduction] = OqlBuilder.from(entityName, shortName)
    populateConditions(builder)
    builder.where("introduction.teacher =:s", getStaff)
    builder.orderBy(get(Order.OrderStr).orNull).limit(getPageLimit)
  }

  @ignore
  protected override def saveAndRedirect(entity: Introduction): View = {
    if (!entity.persisted) {
      entity.teacher = getStaff
    }
    super.saveAndRedirect(entity)
  }

  //
  //  override def search(): String = {
  //    val categoryId = getLong("categoryId").get
  //    val builder = OqlBuilder.from(classOf[Introduction], "introduction")
  //    builder.where("introduction.teacher.id =:t", 309L)
  //    builder.where("introduction.category.id = :w",categoryId)
  //    val introductions = entityDao.search(builder)
  //    
  //    put("introductions", introductions)
  //    forward()
  //  }

}