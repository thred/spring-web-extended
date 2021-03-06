/**
 * Copyright 2014 Daniel Furtlehner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.porscheinformatik.common.spring.web.extended.template.velocity;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import at.porscheinformatik.common.spring.web.extended.expression.ExpressionHandlers;
import at.porscheinformatik.common.spring.web.extended.io.ResourceType;
import at.porscheinformatik.common.spring.web.extended.template.Template;
import at.porscheinformatik.common.spring.web.extended.template.TemplateFactory;

/**
 * @author Daniel Furtlehner
 * 
 */
public class VelocityTemplateFactory implements TemplateFactory
{
	private VelocityEngine engine;
	private ExpressionHandlers expressionHandlers;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.porscheinformatik.common.spring.web.extended.template.TemplateFactory
	 * #createTemplate(org.springframework.core.io.Resource, java.lang.String,
	 * at.porscheinformatik.common.spring.web.extended.io.ResourceType, boolean)
	 */
	@Override
	public Template createTemplate(Resource resource, String templateName,
			String location,
			ResourceType type, boolean optimized)
	{
		try
		{
			return new VelocityTemplate(resource, type, templateName, location,
					optimized,
					engine,
					expressionHandlers);
		} catch (IOException ex)
		{
			throw new RuntimeException(
					"Error creating velocityTemplate for template "
							+ templateName, ex);
		}
	}

	@PostConstruct
	public void setupEngine()
	{
		engine = new VelocityEngine();
		engine.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
		engine.setProperty("resource.loader", "expressionhandler");
		engine.setProperty("expressionhandler.resource.loader.instance",
				new ExpressionHandlerMacroResourceLoader(expressionHandlers));
		engine.setProperty("velocimacro.library",
				ExpressionHandlerMacroResourceLoader.MACRO_LIBRARY_FILE);

		engine.init();
	}

	@Autowired
	public void setExpressionHandlers(ExpressionHandlers expressionHandlers)
	{
		this.expressionHandlers = expressionHandlers;
	}
}
