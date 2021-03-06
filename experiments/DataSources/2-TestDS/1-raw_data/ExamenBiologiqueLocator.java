package org.imogene.epicam.server.locator;

import javax.servlet.http.HttpServletRequest;

import org.imogene.epicam.domain.entity.ExamenBiologique;
import org.imogene.epicam.server.handler.ExamenBiologiqueHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * A Locator to locate ExamenBiologique beans 
 * @author Medes-IMPS
 */
public class ExamenBiologiqueLocator extends Locator<ExamenBiologique, String> {

	private ExamenBiologiqueHandler handler;

	public ExamenBiologiqueLocator() {

	}

	@Override
	public ExamenBiologique create(Class<? extends ExamenBiologique> clazz) {
		return new ExamenBiologique();
	}

	@Override
	public ExamenBiologique find(Class<? extends ExamenBiologique> clazz,
			String id) {
		if (handler == null)
			initHandler();
		return handler.getById(id);
	}

	@Override
	public Class<ExamenBiologique> getDomainType() {
		return ExamenBiologique.class;
	}

	@Override
	public String getId(ExamenBiologique domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(ExamenBiologique domainObject) {
		return domainObject.getVersion();
	}

	private void initHandler() {
		HttpServletRequest request = RequestFactoryServlet
				.getThreadLocalRequest();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		handler = (ExamenBiologiqueHandler) context
				.getBean("examenBiologiqueHandler");
	}
}
