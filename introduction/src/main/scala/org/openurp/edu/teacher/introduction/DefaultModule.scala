package org.openurp.edu.teacher.introduction

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.edu.teacher.introduction.action.IndexAction
import org.openurp.edu.teacher.introduction.action.MeAction

class DefaultModule extends AbstractBindModule {

  override def binding() {
    bind(classOf[MeAction])
    bind(classOf[IndexAction])
  }
}
