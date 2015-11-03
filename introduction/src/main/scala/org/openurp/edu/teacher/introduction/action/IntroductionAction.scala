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
import org.beangle.webmvc.context.ActionContextHelper
import org.beangle.webmvc.api.annotation.ignore
import org.beangle.webmvc.api.view.View
/**
 * @author xinzhou
 */
class IntroductionAction extends RestfulAction[Introduction] {
  
  
  protected override def getQueryBuilder(): OqlBuilder[Introduction] = {
    val builder: OqlBuilder[Introduction] = OqlBuilder.from(entityName, shortName)
    populateConditions(builder)
    builder.where("introduction.teacher.id =:t", 309L)
    builder.orderBy(get(Order.OrderStr).orNull).limit(getPageLimit)
  }

  @ignore
  protected override def saveAndRedirect(entity: Introduction): View = {
    if (!entity.persisted) {
      entity.teacher = new Staff
      entity.teacher.id = 309L
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