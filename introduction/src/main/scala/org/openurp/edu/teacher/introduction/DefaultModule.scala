package org.openurp.edu.teacher.introduction

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.edu.teacher.introduction.action.IndexAction
import org.openurp.edu.teacher.introduction.action.IntroductionAction
import org.openurp.edu.teacher.introduction.action.ViewAction

class DefaultModule extends AbstractBindModule {

  override def binding() {
    bind(classOf[IntroductionAction])
    bind(classOf[IndexAction])
    bind(classOf[ViewAction])
  }
}
