/*
 * Beangle, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2005-2015, Beangle Software.
 *
 * Beangle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Beangle is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Beangle.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.beangle.webmvc.context.impl

import org.beangle.commons.lang.annotation.{ description, spi }
import org.beangle.commons.web.multipart.StandardMultipartResolver
import org.beangle.webmvc.api.context.{ ActionContext, ActionContextHolder }
import org.beangle.webmvc.context.{ ActionContextBuilder, ActionContextHelper, LocaleResolver }
import org.beangle.webmvc.execution.Handler
import javax.servlet.http.{ HttpServletRequest, HttpServletResponse }
import org.beangle.webmvc.context.ActionContextInitializer
import org.beangle.commons.inject.Container

/**
 * @author chaostone
 */
//FIXME just recompile
@description("缺省的ActionContext构建器")
class DefaultActionContextBuilder(localeResolver: LocaleResolver, initializers: List[ActionContextInitializer]) extends ActionContextBuilder {

  override def build(request: HttpServletRequest, response: HttpServletResponse,
    handler: Handler, params2: collection.Map[String, Any]): ActionContext = {

    val params = new collection.mutable.HashMap[String, Any]
    val paramIter = request.getParameterMap.entrySet.iterator
    while (paramIter.hasNext) {
      val paramEntry = paramIter.next
      val values = paramEntry.getValue
      if (values.length == 1) params.put(paramEntry.getKey, values(0))
      else params.put(paramEntry.getKey, values)
    }

    if (StandardMultipartResolver.isMultipart(request)) {
      params ++= StandardMultipartResolver.resolve(request)
    }

    params ++= params2

    val context = new ActionContext(request, response, localeResolver.resolve(request), params.toMap)
    context.temp(ActionContextHelper.HandlerAttribute, handler)
    ActionContextHolder.contexts.set(context)
    initializers foreach { i => i.init(context) }
    context
  }
}