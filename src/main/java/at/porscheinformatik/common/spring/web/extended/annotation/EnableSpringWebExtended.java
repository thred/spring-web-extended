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
package at.porscheinformatik.common.spring.web.extended.annotation;

//CHECKSTYLE:OFF
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import at.porscheinformatik.common.spring.web.extended.config.SpringWebExtendedConfig;
import at.porscheinformatik.common.spring.web.extended.config.SpringWebExtendedConfigurer;
import at.porscheinformatik.common.spring.web.extended.config.SpringWebExtendedConfigurerAdapter;
import at.porscheinformatik.common.spring.web.extended.config.SpringWebExtendedImportSelector;
import at.porscheinformatik.common.spring.web.extended.config.SpringWebExtendedRegistrar;
import at.porscheinformatik.common.spring.web.extended.template.cache.html.HtmlTemplateController;

//CHECKSTYLE:ON

/**
 * Add this annotation to @{@link Configuration} classes to enable extended web
 * support configured in {@link SpringWebExtendedConfig} and
 * {@link SpringWebExtendedRegistrar}.
 * 
 * <p>
 * You can customize the configuration by implementing the
 * {@link SpringWebExtendedConfigurer} or extending the
 * {@link SpringWebExtendedConfigurerAdapter}
 * </p>
 * 
 * <pre>
 * &#064;Configuration
 * &#064;EnableSpringWebExtended
 * &#064;ComponentScan(basePackageClasses = { AppConfig.class })
 * public class AppConfig extends SpringWebExtendedConfigurerAdapter
 * {
 * 	&#064;Override
 * 	public void configureMessageSource(MessageSourceConfig config)
 * 	{
 * 		config.addBaseName(&quot;path/to/my/messages&quot;)
 * 	}
 * }
 * </pre>
 * 
 * @author Daniel Furtlehner
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(value = { SpringWebExtendedRegistrar.class,
		SpringWebExtendedImportSelector.class })
@EnableWebMvc
public @interface EnableSpringWebExtended
{

	/**
	 * @return the configuration for the {@link HtmlTemplateController}
	 */
	HtmlTemplateControllerConfig htmlTemplateControllerConfig() default @HtmlTemplateControllerConfig;

	/**
	 * @return true if the default Assetcontroller should be registered
	 */
	boolean registerAssetController() default true;

	/**
	 * @return true if the default Stylecontroller should be registered
	 */
	boolean registerStyleController() default true;

	/**
	 * @return true if the default Scriptcontroller should be registered
	 */
	boolean registerScriptController() default true;
}
