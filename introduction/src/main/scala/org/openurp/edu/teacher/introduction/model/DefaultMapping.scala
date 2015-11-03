package org.openurp.edu.teacher.introduction.model

import scala.reflect.runtime.universe

import org.beangle.data.model.bind.Mapping

class DefaultMapping extends Mapping {

  def binding(): Unit = {
    defaultIdGenerator("auto_increment")

    bind[Introduction].on(e => declare(
      e.teacher & e.category are notnull,
      e.contents is length(4000)))


    bind[Category].on(e => declare(
      e.name is (notnull, length(50))))
  }
}